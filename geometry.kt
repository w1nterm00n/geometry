// Окружность в треугольнике (обязательно использование класса Точка и класса Треугольник. 
// Класс Окружность и другие классы - по желанию)

// Треугольник расположен на координатной плоскости и описан координатами своих вершин. 
// Написать программу вычисляющую координаты центра вписанной в треугольник окружности и ее радиус.

import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

class Triangle(val a: Point, val b: Point, val c: Point) {
    fun calculateDistance(point1: Point, point2: Point): Double {
        return sqrt((point2.x - point1.x).pow(2) + (point2.y - point1.y).pow(2))
        //находит длины сторон треугольника по формуле
    }

    fun calculateRadius(): Double {
        val dAB = calculateDistance(a, b)   //длина стороны AB
        val dBC = calculateDistance(b, c)   //длина стороны BC
        val dCA = calculateDistance(c, a)   //длина стороны CA

        val s = (dAB + dBC + dCA) / 2.0     //s - полупериметр треугольника
        val radius = sqrt((s - dAB) * (s - dBC) * (s - dCA) / s)   //находит радуис по формуле
        return radius
    }

    fun calculateCenter(): Point {
        val dAB = calculateDistance(a, b)
        val dBC = calculateDistance(b, c)
        val dCA = calculateDistance(c, a)

        val centerX = (dBC * a.x + dCA * b.x + dAB * c.x) / (dAB + dBC + dCA)   //формула для поиска координат
        val centerY = (dBC * a.y + dCA * b.y + dAB * c.y) / (dAB + dBC + dCA)

        return Point(centerX, centerY)  //координата центра окружности
    }
}

fun main() {
    println("Введите координаты вершин треугольника:")
    val pointA = readPoint("A")
    val pointB = readPoint("B")
    val pointC = readPoint("C")

    //создает треугольник и ищет нужные значения
    val triangle = Triangle(pointA, pointB, pointC)
    val radius = triangle.calculateRadius()
    val center = triangle.calculateCenter()

    println("Радиус вписанной окружности: $radius")
    println("Координаты центра вписанной окружности: (${center.x}, ${center.y})")
}

fun readPoint(pointName: String): Point {   //функция которая помогает считывать координаты вершин треугольника
    print("Введите координаты точки $pointName (x y): ")
    val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
    return Point(x, y)
}