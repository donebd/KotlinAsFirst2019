@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val answer = mutableMapOf<String, Int>()
    val text = File(inputName).readText().toLowerCase()
    for (subWord in substrings) {
        var k = 0
        var j = 0
        var index = text.indexOf(subWord.toLowerCase(), j)
        while (index != -1) {
            k++
            j = index + 1
            index = text.indexOf(subWord.toLowerCase(), j)
        }
        answer[subWord] = k
    }
    return answer
}

/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val change = mapOf('ы' to 'и', 'Ы' to 'И', 'я' to 'а', 'Я' to 'А', 'ю' to 'у', 'Ю' to 'У')
    File(outputName).bufferedWriter().use {
        var text = File(inputName).readText()
        var previous = ' '
        var k = 0
        for (i in text) {
            if (previous in "ЖШЧЩжшчщ" && change[i] != null) text = text.replaceRange(k, k + 1, change[i].toString())
            previous = i
            k++
        }
        it.write(text)
    }
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */

fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val edit = listOf("**", "*", "~~")
    val editOpen = listOf("<b>", "<i>", "<s>")
    val editClose = listOf("</b>", "</i>", "</s>")
    File(outputName).bufferedWriter().use {
        it.write("<html>")
        it.newLine()
        it.write("<body>")
        it.newLine()
        it.write("<p>")
        var k = -1
        var newLine = ""
        val checkArr = arrayOf(0, 0, 0)
        for ((count, line) in File(inputName).readLines().withIndex()) {
            if (newLine != "" && newLine != "\\n") k = 0
            if (((line == "" || line == "\\n" && newLine == "" || newLine == "\\n")
                        || (newLine != line && (line != "" && line != "\\n" && newLine != "" && newLine != "\\n"))) && k != -1
            ) k = 0
            if (k > 0 && count != File(inputName).readLines().size) {
                it.write("</p>")
                it.newLine()
                it.write("<p>")
            } else if (k != -1) k++
            newLine = line
            for ((index, value) in edit.withIndex())
                while (newLine.contains(value))
                    if (checkArr[index] == 0) {
                        newLine = newLine.replaceFirst(value, editOpen[index])
                        checkArr[index] = 1
                    } else {
                        newLine = newLine.replaceFirst(value, editClose[index])
                        checkArr[index] = 0
                    }
            it.write(newLine)

            it.newLine()
        }
        it.write("</p>")
        it.write("</body>")
        it.newLine()
        it.write("</html>")
    }
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    var newLhv = lhv.toString()
    val result = (lhv / rhv).toString()
    var resultTemp = ((result[0].toInt() - 48) * rhv).toString()
    var partLhv = lhv.toString()
    var checker = 0
    var countSpace = resultTemp.length + 1
    var countSeparate = countSpace
    File(outputName).bufferedWriter().use {
        if (result.length == 1 && lhv.toString().length >= 1 + resultTemp.length) it.write("$lhv | $rhv")// первая строка
        else if (partLhv.substring(0, countSpace - 1).toInt() - resultTemp.toInt() < 0) {
            it.write("$lhv | $rhv")
            checker = 5
        } else it.write(" $lhv | $rhv")

        it.newLine()
        var space = ""
        var separator = "-".repeat(countSeparate)

        if (result.length == 1) {//вторая строка и вывод остатка если результат деления от 0..9
            if (lhv.toString().length >= 1 + resultTemp.length) {//минус под делимым
                space = " ".repeat(lhv.toString().length - resultTemp.length - 1)
                separator = "-".repeat(lhv.toString().length)
                it.run {
                    write("$space-$resultTemp   $result")
                    newLine()
                    write(separator)
                    newLine()
                    write("${" ".repeat(separator.length - (lhv % rhv).toString().length)}${lhv % rhv}")
                }
            } else {// минус не под делимым
                space = " ".repeat(lhv.toString().length - resultTemp.length)
                if (lhv.toString().length == resultTemp.length) {
                    separator = "-".repeat(lhv.toString().length + 1)
                    space = ""
                } else separator = "-".repeat(lhv.toString().length)
                it.write("$space-$resultTemp   $result")
                it.newLine()
                countSpace = if (lhv.toString().length == resultTemp.length) {
                    it.write(separator)
                    separator.length - (lhv % rhv).toString().length
                } else {
                    it.write(" $separator")
                    separator.length - (lhv % rhv).toString().length + 1
                }
                it.newLine()
                it.write("${" ".repeat(countSpace)}${lhv % rhv}")
            }
        } else {//вторая строка и последующие если результат деления >= 10
            space = if (checker == 5) " ".repeat(lhv.toString().length - countSpace + 3)
            else " ".repeat(lhv.toString().length - countSpace + 4)
            it.write("-$resultTemp$space$result")
            it.newLine()
            it.write(separator)
            it.newLine()
            var remain = ""
            if (partLhv.substring(0, countSpace - 1).toInt() - resultTemp.toInt() >= 0) {
                partLhv = partLhv.substring(0, countSpace - 1)
                remain = (partLhv.toInt() - resultTemp.toInt()).toString()
            } else {
                partLhv = partLhv.substring(0, countSpace)
                remain = (partLhv.toInt() - resultTemp.toInt()).toString()
            }
            newLhv = newLhv.replaceFirst(partLhv, "")
            countSpace = 0
            space = ""
            checker = 0

            for (i in 1 until result.length) {//блок последующих операций после первой
                if (i != 1) remain = (remain.toInt() - resultTemp.toInt()).toString()
                checker = 1
                countSpace += countSeparate - remain.length
                if (remain.toInt() < rhv && i != result.length) {
                    remain += newLhv[0]
                    newLhv = newLhv.substring(1, newLhv.length)
                    countSeparate = remain.length
                }
                space = " ".repeat(countSpace)
                it.run {
                    write("$space$remain")
                    newLine()
                }
                resultTemp = ((result[i].toInt() - 48) * rhv).toString()
                if (resultTemp.length == remain.length) {
                    countSpace--
                    space = space.substring(0, space.length - 1)
                    countSeparate++
                } else while (space.length + resultTemp.length + 1 < countSpace + remain.length) space += " "
                separator = "-".repeat(countSeparate)
                it.run {
                    write("$space-$resultTemp")
                    newLine()
                }
                space = " ".repeat(countSpace)
                it.write("$space$separator")
                it.newLine()
                space = ""
            }

            if (checker == 0) countSpace = 0
            when (checker) {
                2 -> for (i in 2 until countSpace + countSeparate) space += " "
                else -> for (i in 1..countSpace + countSeparate - (lhv % rhv).toString().length) space += " "
            }
            it.run { write("$space${lhv % rhv}") }
        }

    }
}

