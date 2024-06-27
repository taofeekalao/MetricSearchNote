import com.metric.search.visualisation.Histogram
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import testloads.TestContext

const val DATA_POINTS = 500

/**
 * This is a demo run for Euclidean Distance Histogram plot using Kotlin Kandy.
 * Run for 10, 20, 30 and 100 dimensions with 500_000 data points.
 */
fun main() {
    var tc: TestContext
    var distanceList: List<Double>


    var dimension = 10
    tc = TestContext(
        TestContext.Context.euc10,
        DATA_POINTS + dimension
    )
    distanceList = computeEuclideanDistances(dimension, tc)
    plotVisualisation(distanceList, "Euc" + "_" + dimension + "_")


    dimension = 20
    tc = TestContext(
        TestContext.Context.euc20,
        DATA_POINTS + dimension
    )
    distanceList = computeEuclideanDistances(dimension, tc)
    plotVisualisation(distanceList, "Euc" + "_" + dimension + "_")


    dimension = 30
    tc = TestContext(
        TestContext.Context.euc30,
        DATA_POINTS + dimension
    )
    distanceList = computeEuclideanDistances(dimension, tc)
    plotVisualisation(distanceList, "Euc" + "_" + dimension + "_")


    dimension = 100
    tc = TestContext(
        TestContext.Context.euc100,
        DATA_POINTS + dimension
    )
    distanceList = computeEuclideanDistances(dimension, tc)
    plotVisualisation(distanceList, "Euc" + "_" + dimension + "_")
}


/**
 * This method computes the Euclidean distances between the points generated,
 * and returns a list of the points in floating point format.
 */
private fun computeEuclideanDistances(dimension: Int, tc: TestContext): List<Double> {
    tc.setSizes(0, dimension)

    val distanceList = mutableListOf<Double>()
    val eucData = tc.getDataCopy()

    for (i in 0 until DATA_POINTS) {
        for (j in 0 until DATA_POINTS) {
            if (i != j) {
                distanceList.add(tc.metric().distance(eucData[i], eucData[j]))
            }
        }
    }
    return distanceList
}


/**
 * This method plots the histogram and saves the output file in a jpg format.
 */
private fun plotVisualisation(distanceList: List<Double>, imageName: String) {
    val histogram = Histogram(xDataSet = distanceList)
    val res = histogram.plot()
    res.save(imageName + "Distance Histogram - Custom Library.jpg")
}
