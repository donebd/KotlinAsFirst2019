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
    if (n == 1 || n == 2) return 1
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
    var result = 0
    for (i in 2..n) {
        if (n % i == 0) {
            result = i
            break
        }
    }

    return result
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var answer = 0
    for (i in n / 2 downTo 1) {
        if (n % i == 0) {
            answer = i
            break
        }
    }
    return answer
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = min(m, n)
    if (m % min == 0 && n % min == 0 && min > 1) return false
    for (i in min / 2 downTo 2) {
        if (m % i == 0 && n % i == 0) {
            return false
            break
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val sqrtm = sqrt(m.toDouble()).toInt()
    val sqrtn = sqrt(n.toDouble()).toInt()
    for (i in sqrtm..sqrtn) {
        if (i * i in m..n) {
            return true
            break
        }
    }
    return false
}

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
    var x = x
    var count = 0
    while (x > 1) {
        if (x % 2 == 0) {
            x /= 2
            count++
        } else {
            x = x * 3 + 1
            count++
        }
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
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n = n
    var n1: Long = 0
    while (n > 0) {
        n1 = (n1 + (n % 10)) * 10
        n /= 10
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
    var n = n
    var variable = n % 10
    if (n in 0..9) return false
    else while (n > 9) {
        if ((n / 10) % 10 != variable) {
            return true
            break
        }
        n /= 10
        variable %= 10
    }
    return false
}

fun main() {
    var b = 0
    for (i in 1..3163) {
        b = i * i
        print(" $b ")
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 14916253649648110012114416919622525628932436140044148452957662567672978484190096110241089115612251296136914441521160016811764184919362025211622092304240125002601270428092916302531363249336434813600372138443969409642254356448946244761490050415184532954765625577659296084624164006561672468897056722573967569774479218100828184648649883690259216940996049801

 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 0
    return when {
        n < 4 -> n * n
        n < 16 -> {
            for (i in 6..n step 2) count++
            if (n % 2 == 0) ((n - count).toDouble().pow(2) / 10).toInt()
            else ((n - count - 1).toDouble().pow(2) % 10).toInt()
        }
        n < 82 -> {
            count = 6
            for (i in 19..n step 3) count += 2
            when {
                n % 3 == 1 -> ((n - count).toDouble().pow(2) / 100).toInt()
                n % 3 == 2 -> ((n - count - 1).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 2).toDouble().pow(2) % 10).toInt()
            }
        }
        n < 354 -> {
            count = 50
            for (i in 86..n step 4) count += 3
            when {
                n % 4 == 2 -> ((n - count).toDouble().pow(2) / 1000).toInt()
                n % 4 == 3 -> ((n - count - 1).toDouble().pow(2) / 100 % 10).toInt()
                n % 4 == 0 -> ((n - count - 2).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 3).toDouble().pow(2) % 10).toInt()
            }
        }
        n < 1439 -> {
            count = 254
            for (i in 359..n step 5) count += 4
            when {
                n % 5 == 4 -> ((n - count).toDouble().pow(2) / 10000).toInt()
                n % 5 == 0 -> ((n - count - 1).toDouble().pow(2) / 1000 % 10).toInt()
                n % 5 == 1 -> ((n - count - 2).toDouble().pow(2) / 100 % 10).toInt()
                n % 5 == 2 -> ((n - count - 3).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 4).toDouble().pow(2) % 10).toInt()
            }

        }
        n < 5537 -> {
            count = 1122
            for (i in 1445..n step 6) count += 5
            when {
                n % 6 == 5 -> ((n - count).toDouble().pow(2) / 100000).toInt()
                n % 6 == 0 -> ((n - count - 1).toDouble().pow(2) / 10000 % 10).toInt()
                n % 6 == 1 -> ((n - count - 2).toDouble().pow(2) / 1000 % 10).toInt()
                n % 6 == 2 -> ((n - count - 3).toDouble().pow(2) / 100 % 10).toInt()
                n % 6 == 3 -> ((n - count - 4).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 5).toDouble().pow(2) % 10).toInt()
            }

        }
        n < 20678 -> {
            count = 4537
            for (i in 5544..n step 7) count += 6
            when {
                n % 7 == 0 -> ((n - count).toDouble().pow(2) / 1000000).toInt()
                n % 7 == 1 -> ((n - count - 1).toDouble().pow(2) / 100000 % 10).toInt()
                n % 7 == 2 -> ((n - count - 2).toDouble().pow(2) / 10000 % 10).toInt()
                n % 7 == 3 -> ((n - count - 3).toDouble().pow(2) / 1000 % 10).toInt()
                n % 7 == 4 -> ((n - count - 4).toDouble().pow(2) / 100 % 10).toInt()
                n % 7 == 5 -> ((n - count - 5).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 6).toDouble().pow(2) % 10).toInt()
            }

        }

        else -> {
            count = 17513
            for (i in 20686..n step 8) count += 7
            when {
                n % 8 == 6 -> ((n - count).toDouble().pow(2) / 10000000).toInt()
                n % 8 == 7 -> ((n - count - 1).toDouble().pow(2) / 1000000 % 10).toInt()
                n % 8 == 0 -> ((n - count - 2).toDouble().pow(2) / 100000 % 10).toInt()
                n % 8 == 1 -> ((n - count - 3).toDouble().pow(2) / 10000 % 10).toInt()
                n % 8 == 2 -> ((n - count - 4).toDouble().pow(2) / 1000 % 10).toInt()
                n % 8 == 3 -> ((n - count - 5).toDouble().pow(2) / 100 % 10).toInt()
                n % 8 == 4 -> ((n - count - 6).toDouble().pow(2) / 10 % 10).toInt()
                else -> ((n - count - 7).toDouble().pow(2) % 10).toInt()
            }
        }
    }
}
    /**
     * Сложная
     *
     * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
     * 1123581321345589144...
     * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
     *
     * Использовать операции со строками в этой задаче запрещается.
     */
    fun fibSequenceDigit(n: Int): Int = TODO()
