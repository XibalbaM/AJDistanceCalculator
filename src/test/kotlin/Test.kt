import fr.xibalba.aj.distanceCalculator.Point
import fr.xibalba.aj.distanceCalculator.computeDistance
import fr.xibalba.aj.distanceCalculator.distanceTo
import fr.xibalba.aj.distanceCalculator.haversine
import kotlin.test.Test
import kotlin.test.assertEquals

class DistanceCalculatorTest {

    /**
     * Test the haversine function.
     */
    @Test
    fun testHaversine() {
        assertEquals(0.0, haversine(0.0))
        assertEquals(0.06, haversine(0.5), 0.01)
        assertEquals(0.22, haversine(1.0), 0.01)
    }

    /**
     * Test the distanceTo function with two points.
     */
    @Test
    fun testDistanceTo() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        assertEquals(157.249, p1 distanceTo p2, 0.001)
    }

    /**
     * Test the computeDistance function with a simple route.
     */
    @Test
    fun testComputeDistance() {
        val route = listOf(Point(0.0, 0.0), Point(1.0, 1.0), Point(2.0, 2.0))
        assertEquals(314.45, route.computeDistance(), 0.03)
    }

    /**
     * Test the computeDistance function with a more realistic route.
     */
    @Test
    fun testComputeDistanceRealistic() {
        val route = listOf(
            Point(48.8566, 2.3522),
            Point(51.5074, -0.1278),
            Point(40.7128, -74.0060),
            Point(34.0522, -118.2437)
        )
        assertEquals(9849.524494688721, route.computeDistance(), 1.0)
    }
}