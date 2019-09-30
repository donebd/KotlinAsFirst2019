@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.maxDivisor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (element in v) {
        result += sqr(element)
    }
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = when {
    list.isEmpty() -> 0.0
    else -> list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var average = mean(list)
    for (i in 0 until list.size) list[i] -= average
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in 0 until a.size) result += (a[i] * b[i])
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    if (p.isEmpty()) return result
    result = p[0]
    for (i in 1 until p.size) result += p[i] * x.toDouble().pow(i).toInt()
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var result = 0
    for (i in 0 until list.size) {
        result += list[i]
        list[i] = result
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */


fun factorize(n: Int): List<Int> {
    var n = n
    var a = mutableListOf<Int>()
    while (n != 1) {
        for (i in 2..n) if (n % i == 0) {
            a.add(i)
            n /= i
            break
        }
        if (maxDivisor(n) == 0 && n != 1) {
            a.add(n)
            n /= n
        }
    }
    return a
}


/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var n = n
    var a = mutableListOf<Int>()
    while (n != 1) {
        for (i in 2..n) if (n % i == 0) {
            a.add(i)
            n /= i
            break
        }
        if (maxDivisor(n) == 0 && n != 1) {
            a.add(n)
            n /= n
        }
    }
    return a.joinToString(separator = "*")
}


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var a = mutableListOf<Int>()
    var n = n
    while (n >= base) {
        a.add(n % base)
        n /= base
    }
    a.add(n)
    return a.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var a = ""
    var n = n
    while (n >= base) {
        if (n % base > 9) a += (n % base + 87).toChar()
        else a += n % base
        n /= base
    }
    if (n > 9) a += (n + 87).toChar() else
        a += n
    return a.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */


fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0.0
    for (i in 0 until digits.size) {
        result += digits[i] * base.toDouble().pow(digits.size - i - 1.0)
    }
    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0.0
    for (i in 0 until str.length) {
        result += if (str[i].toInt() - 48 in 0..9) (str[i].toInt() - 48) * base.toDouble().pow(str.length - i - 1)
        else (str[i].toInt() - 87) * base.toDouble().pow(str.length - i - 1)
    }
    return result.toInt()
}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    var n = n
    for (i in 1..n / 1000) {
        result += 'M'
        n %= 1000
    }
    for (i in 1..n / 900) {
        result += "CM"
        n %= 900
    }
    for (i in 1..n / 500) {
        result += "D"
        n %= 500
    }
    for (i in 1..n / 400) {
        result += "CD"
        n %= 400
    }
    for (i in 1..n / 100) {
        result += "C"
        n %= 100
    }
    for (i in 1..n / 90) {
        result += "XC"
        n %= 90
    }
    for (i in 1..n / 50) {
        result += "L"
        n %= 50
    }
    for (i in 1..n / 40) {
        result += "XL"
        n %= 40
    }
    for (i in 1..n / 10) {
        result += "X"
        n %= 10
    }
    for (i in 1..n / 9) {
        result += "IX"
        n %= 9
    }
    for (i in 1..n / 5) {
        result += "V"
        n %= 5
    }
    for (i in 1..n / 4) {
        result += "IV"
        n %= 4
    }
    for (i in 1..n) result += "I"
    return result
}

fun main() {
    print(russian(2019))
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var n = n
    val n1 = n
    var str = ""
    for (i in 0..1) { //цикл для того чтобы первый раз пройтись по тысячам а второй по сотням десяткам ...
        if (n / (100000 - (99900 * i)) in 1..9) {
            str += when (n / (100000 - (99900 * i))) {
                9 -> "девятьсот "
                8 -> "восемьсот "
                7 -> "семьсот "
                6 -> "шестьсот "
                5 -> "пятьсот "
                4 -> "четыреста "
                3 -> "триста "
                2 -> "двести "
                else -> "сто "
            }
            n %= 100000 - (99900 * i)
        }
        if (n / (10000 - (9990 * i)) in 1..9) {
            when (n / (10000 - (9990 * i))) {
                9 -> str += if (i == 0) "девяноста " else "девяносто "
                8 -> str += "восемьдесят "
                7 -> str += "семьдесят "
                6 -> str += "шестьдесят "
                5 -> str += "пятьдесят "
                4 -> str += "сорок "
                3 -> str += "тридцать "
                2 -> str += "двадцать "
                else -> {
                    str += when (n / (1000 - (999 * i))) {
                        10 -> "десять "
                        11 -> "одиннадцать "
                        12 -> "двенадцать "
                        13 -> "тринадцать "
                        14 -> "четырнадцать "
                        15 -> "пятнадцать "
                        16 -> "шестнадцать "
                        17 -> "семнадцать "
                        18 -> "восемнадцать "
                        else -> "девятнадцать "
                    }
                    n %= 1000
                    if (i == 1) n = 0
                }
            }
            n %= 10000 - (9990 * i)
        }
        if (n / 1000 in 0..9 && i == 0 && n1 > 1000) {
            str += when (n / 1000) {
                0 -> "тысяч "
                1 -> "одна тысяча "
                2 -> "две тысячи "
                3 -> "три тысячи "
                4 -> "четыре тысячи "
                5 -> "пять тысяч "
                6 -> "шесть тысяч "
                7 -> "семь тысяч "
                8 -> "восемь тысяч "
                else -> "девять тысяч "
            }
            n %= 1000
        }
        if (i == 1 && n in 1..9) {
            str += when (n) {
                1 -> "один"
                2 -> "два"
                3 -> "три"
                4 -> "четыре"
                5 -> "пять"
                6 -> "шесть"
                7 -> "семь"
                8 -> "восемь"
                else -> "девять"
            }
        }
    }
    return str.trim()
}