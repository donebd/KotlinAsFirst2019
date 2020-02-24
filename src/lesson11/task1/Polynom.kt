@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.math.max
import kotlin.math.pow

/**
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- сложная.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x^2+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(coeffs: List<Double>) {

    private var coeffsList = coeffs.dropWhile { it == 0.0 }

    init {
        if (coeffsList.isEmpty()) coeffsList = coeffsList + 0.0
    }

    private val maxCoeff = coeffsList.size - 1

    constructor(vararg coeff: Double) : this(coeff.toList())

    fun empty() = degree() == 0 && coeff(0) == 0.0

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = coeffsList.reversed()[i]

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double = coeffsList.reversed().indices.map { coeffsList.reversed()[it] * x.pow(it) }.sum()

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int =
        if (coeffsList.any { it != 0.0 }) maxCoeff - coeffsList.indices.first { coeffsList[it] != 0.0 }
        else 0

    private fun plusOrMinus(other: Polynom, switch: Int): Polynom {
        val answList = MutableList(max(degree(), other.degree()) + 1) { 0.0 }
        for (i in answList.indices) {
            if (i <= maxCoeff)
                answList[i] += coeffsList[maxCoeff - i]
            if (i <= other.maxCoeff)
                when (switch) {
                    1 -> answList[i] += other.coeffsList[other.maxCoeff - i]
                    else -> answList[i] -= other.coeffsList[other.maxCoeff - i]
                }
        }
        return Polynom(answList.reversed())
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom = plusOrMinus(other, 1)

    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom = Polynom(this.coeffsList.map { if (it != 0.0) -it else 0.0 })

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom = plusOrMinus(other, 2)

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        var answPolynom = Polynom()
        for (i in coeffsList.reversed().indices) {
            val tmpList = other.coeffsList.toMutableList()
            for (j in tmpList.indices) tmpList[j] *= coeffsList.reversed()[i]
            for (j in 1..i) tmpList.add(0.0)
            answPolynom += Polynom(tmpList)
        }
        return answPolynom
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom {
        require(!other.empty()) { "На ноль делить нельзя!" }
        val newMaxCoeff = Pair(degree() - other.degree(), coeff(degree()) / other.coeff(other.degree()))
        if (newMaxCoeff.first < 0 || empty()) return Polynom(0.0)
        val answList = mutableListOf(newMaxCoeff.second)
        for (i in 1..newMaxCoeff.first) answList.add(0.0)
        val answPolynom = Polynom(answList)
        val tmpPolynom = other * answPolynom
        val remainderPolynom = this - tmpPolynom
        return answPolynom + remainderPolynom.div(other)
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = this - (this / other * other)

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        other is Polynom && (this - other).empty()

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int {
        var result = coeffsList.hashCode()
        result = 31 * result + maxCoeff
        return result
    }
}
