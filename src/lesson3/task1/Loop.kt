@file:Suppress("UNUSED_PARAMETER", "UNREACHABLE_CODE")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int = when {
    abs(n) % 10 >= 0 -> if (abs(n) / 10 != 0) 1 + digitNumber(abs(n) / 10) else 1
    else -> 0
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n < 3) return 1
    var n3 = 1
    var n2 = 1
    var n1 = 2
    for (i in 3..n) {
        n1 = n2 + n3
        n3 = n2
        n2 = n1
    }
    return n1
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var answer = maxOf(m, n)
    var i = 0
    while (true) {
        i++
        if (answer * i % m == 0 && answer * i % n == 0) {
            answer *= i
            break
        }
    }
    return answer
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var result = n
    for (i in 2..sqrt(n.toDouble()).toInt())
        if (n % i == 0) {
            result = i
            break
        }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var m1 = m
    var n1 = n
    while (m1 != 0 && n1 != 0) {
        if (m1 > n1) m1 %= n1
        else n1 %= m1
    }
    return (m1 + n1 == 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean =
    (sqrt(m.toDouble()) % 1.0 == 0.0 || sqrt(n.toDouble()) % 1.0 == 0.0 || sqrt(n.toDouble()).toInt() > sqrt(m.toDouble()).toInt())

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x1 = x
    var count = 0
    while (x1 > 1) {
        if (x1 % 2 == 0) x1 /= 2 else x1 = x1 * 3 + 1
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var f = 0.0
    var eps1 = x % (2 * PI)
    var count = 3
    while (abs(eps1) >= eps) {
        f += eps1
        eps1 = -eps1 * (x % (2 * PI)).pow(2) / (count * (count - 1))
        count += 2
    }
    return f
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var f = 0.0
    var eps1 = 1.0
    var count = 2
    while (abs(eps1) >= eps) {
        f += eps1
        eps1 = -eps1 * (x % (PI * 2)).pow(2) / (count * (count - 1))
        count += 2
    }
    return f
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n2 = n
    var n1: Long = 0
    while (n2 > 0) {
        n1 = (n1 + (n2 % 10)) * 10
        n2 /= 10
    }
    n1 /= 10
    return n1.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = (n == revert(n))

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1 = n
    var variable = n1 % 10
    if (n1 < 10) return false
    while (n1 > 9) {
        if ((n1 / 10) % 10 != variable) {
            return true
            break
        }
        n1 /= 10
        variable %= 10
    }
    return false
}

//Объединение двух функций
fun doubleDigit(n: Int, k: Int): Int {
    var count = 0
    if (n < 4 && k == 0) return n * n
    if (n < 7 && k != 0) return fib(n)
    var i = 0.0
    while (count < n) {
        i++
        var number = if (k == 0) i.pow(2).toInt()
        else fib(i.toInt())
        while (number > 0) {
            count++
            number /= 10
        }
    }
    var answer = if (k == 0) i.pow(2).toInt()
    else fib(i.toInt())
    count -= n
    for (j in 1..count) answer /= 10
    return answer % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144..
 *
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = doubleDigit(n, 0)

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1 1 2 3 5 8 13 21 34 55 89 144 ...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строкамиё в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = doubleDigit(n, 1)

