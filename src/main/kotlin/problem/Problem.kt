package problem
//Imports need for running the application.
import graphCollections.Graph
import graphCollections.GraphStructure
import java.util.*
import kotlin.system.exitProcess

// Place on command Line
private const val COUNTING_TRIANGLES_INDEX = 1
private const val FILENAME_INDEX = 2
private const val EXIT_CODE = -1

//Divider for triangle Counting
private const val DIVIDER = 6

//Default DATA
private const val DEFAULT_DATA = 0

//
private const val DEFAULT_DATA_ADDER = 1

/**
 * Class that has the information of an [Occurrence].
 * @property occurrence number of times that the [vertex] occurred.
 * @property vertex identifier of a Vertex.
 */
data class Occurrence(var occurrence: Int, val vertex: Int)


/**
 *
 */
fun main() {
    println("For [counting Triangles] use the following structure:")
    println("[number of triangles][space]countingTriangles[space][fileName]")
    println("For terminating the application type: exit")
    while (true) {
        val input = readLine()!!
        val command = input.split(" ")
        if (command.first() == "exit") {
            exitProcess(EXIT_CODE)
        } else if (command[COUNTING_TRIANGLES_INDEX] == "countingTriangles") {
            val triangles = countingTriangles(command[FILENAME_INDEX])
            val vertex = countingKVertex(command.first().toInt(), triangles.second)
            printResults(triangles.first, vertex)
        } else println("Invalid command, please use a valid command.")
    }
}


/**
 * Function that prints for the Standard Output the results pretended.
 * @param triangles number of triangles in the Graph.
 * @param vertex Priority queue that has the k most occurrent Vertex in the Graph.
 */
fun printResults(triangles: Int, vertex: PriorityQueue<Occurrence>) {
    println("Number of Triangles: $triangles")
    print("Vertices: ")
    vertex.forEach { print("${it.vertex}; ") }
}


/**
 * Function that counts the number of triangles of a Graph.
 * @param fileName Name of the file of the pretended Graph
 * @return Returns a Pair
 */
fun countingTriangles(fileName: String): Pair<Int, GraphStructure<Int, Int>> {
    val graph = graphOfAFile(fileName)
    val iterator = graph.iterator()
    var count = 0
    while (iterator.hasNext()) {
        val vertex = iterator.next()
        val adjacency = filterAdj(vertex, vertex.getAdjacencies())
        for (i in adjacency) {
            val child = graph.getVertex(i)
            val childAdjacency = filterAdj(child!!, child.getAdjacencies())
            for (j in childAdjacency) {
                val aux = graph.getEdge(vertex.id, j)
                if (aux != null) {
                    count++
                    val vertex1 = graph.getVertex(vertex.id)
                    vertex1?.setData(vertex1.data + DEFAULT_DATA_ADDER)
                    val vertex2 = graph.getVertex(i)
                    vertex2?.setData(vertex2.data + DEFAULT_DATA_ADDER)
                    val vertex3 = graph.getVertex(j)
                    vertex3?.setData(vertex3.data + DEFAULT_DATA_ADDER)
                }
            }
        }
    }
    return Pair(count / DIVIDER, graph)
}


/**
 * Function that gets the adjacency's of a vertex.
 * @param vertex Vertex to get adjacency's.
 * @param set Set of original Edges of that particular [vertex].
 * @return List of adjacency's of the Vertex
 */
fun filterAdj(vertex: Graph.Vertex<Int, Int>, set: MutableSet<Graph.Edge<Int>?>): List<Int> {
    return set.map { if (vertex.id == it!!.adjacent) it.id else it.adjacent }
}


/**
 * Function that identifies the K most vertex that belongs to a triangle.
 * @param k K vertex to match.
 * @param graph Graph to go through to find the vertx
 * @return
 */
fun countingKVertex(k: Int, graph: GraphStructure<Int, Int>): PriorityQueue<Occurrence> {
    val pqp = PriorityQueue<Occurrence>(k) { a, b -> a.occurrence - b.occurrence }
    val iterator = graph.iterator()
    var counter = 0
    while (iterator.hasNext()) {
        val vertex = iterator.next()
        val newVertexData = vertex.setData(vertex.data / DIVIDER)
        if (counter < k) {
            pqp.offer(Occurrence(newVertexData, vertex.id))
            counter++
        } else {
            if (pqp.peek().occurrence < vertex.data) {
                pqp.poll()
                pqp.offer(Occurrence(newVertexData, vertex.id))
            }
        }
    }
    return pqp
}

/**
 * Function that cleans a line from an input File.
 * @receiver String to transform into an Integer List.
 * @return Returns an Integer List from a Line.
 */
fun String.toIntList() = this.split(",").map { it.toInt() }


/**
 * Function that creates a Graph form a File.
 * @param fileName Name of the file to create a Graph from.
 * @return Returns the completed Graph.
 */
fun graphOfAFile(fileName: String): GraphStructure<Int, Int> {
    val reader = createReader(fileName)
    var line: String?
    val graph = GraphStructure<Int, Int>()
    while (reader.readLine().also { line = it/*; println(line)*/ } != null) {
        val ids = line!!.toIntList()
        if (ids.first() < ids.last()) {
            graph.addVertex(ids.first(), DEFAULT_DATA)
            graph.addEdge(ids.first(), ids.last())
            graph.addVertex(ids.last(), DEFAULT_DATA) //take
            graph.addEdge(ids.last(), ids.first()) // 2 ways
        } else {
            graph.addVertex(ids.last(), DEFAULT_DATA)
            graph.addEdge(ids.last(), ids.first())
            graph.addVertex(ids.first(), DEFAULT_DATA) // take
            graph.addEdge(ids.first(), ids.last()) // 2 ways
        }
    }
    return graph
}
