import org.jetbrains.kotlinx.kandy.letsplot.export.save
import testloads.TestContext


const val DATA_POINTS = 500


/**
 * This Kotlin application demonstrates the use of the Metric-Space-Search and the metric-space-search visualisation frameworks.
 * It uses the metric-space-search framework to generate data and uses the visualisation framework to produce visualisations.
 * The visualisation generated is saved in static image format.
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
 * This method computes the Euclidean distances between points.
 * It instantiates an instance of TestContext class from the metric-space-search framework.
 * The instance is used to call utilities from the framework to generate cartesian products of distance points.
 * @param dimension This is the dimension of the metric-space.
 * @param tc This is an instance of the TestContext from the metric-space-search framework.
 * @return The method returns array list of doubles representing the distance values between points.
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
 * This method uses the visualisation library to plot the visualisation of the generated Euclidean distance data.
 * Once the visualisation is done, the output is saved in a static image format.
 * @param distanceList This is the value to be plotted on the x-axis of the two-dimensional plane.
 * @param imageName This is the image name of the plot that will be saved on the local directory.
 */
private fun plotVisualisation(distanceList: List<Double>, imageName: String) {
    val histogram = Histogram(xDataSet = distanceList)
    val res = histogram.plot()
    res.save(imageName + "Distance Histogram - Custom Library_Kotlin Usage.jpg")
}
