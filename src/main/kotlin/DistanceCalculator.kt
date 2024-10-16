package fr.xibalba.aj.distanceCalculator

import kotlin.math.*

/**
 * A class describing a point on the Earth with latitude and longitude.
 * Uses a data class because it is a simple data container.
 */
data class Point(val lat: Double, val lon: Double)

/**
 * A function to compute the distance between two points on the Earth.
 * It uses the haversine formula to compute the distance.
 * The function is pure as it only depends on its input parameters.
 *
 * Written in infix notation to make the code more readable.
 * Using an extension function instead of a member function to emphasize the fact that the function really just takes two parameters, and is not a method of the Point class.
 * It could also be a top-level function, but using an extension function is mandatory for infix notation.
 *
 * @param other the other point to compute the distance to.
 * @receiver the point to compute the distance from.
 * @return the distance between the two points in kilometers.
 */
infix fun Point.distanceTo(other: Point): Double {
    val earthRadius = 6371.0
    val dLat = Math.toRadians(other.lat - this.lat)
    val dLon = Math.toRadians(other.lon - this.lon)
    val a = haversine(dLat) + cos(Math.toRadians(this.lat)) * cos(Math.toRadians(other.lat)) * haversine(dLon)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}

/**
 * As a route is just a list of points, we can define it as a typealias.
 * This makes the code more readable and easier to understand.
 */
typealias Route = List<Point>

/**
 * A function to compute the total distance of a route.
 * It uses the zipWithNext function to get all pairs of consecutive points.
 * It then computes the distance between each pair and sums them up.
 * The function is pure as it only depends on its input parameters.
 *
 * It's again an extension function for readability, but could also be a top-level function.
 */
fun Route.computeDistance(): Double {
    return this.zipWithNext().sumOf { (p1, p2) -> p1 distanceTo p2 }
}

/**
 * Haversine function.
 * @param x the value to compute the haversine of.
 * @return the haversine of x.
 */
fun haversine(x: Double): Double {
    return sin(x / 2).pow(2)
}