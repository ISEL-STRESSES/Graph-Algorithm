package problem
//Imports need for running the application.
import graphCollections.Graph
import graphCollections.GraphStructure
import java.util.*
import kotlin.system.exitProcess

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
        if (command[0] == "exit") {
            println("meninas")
            exitProcess(-1)
        } else if (command[1] == "countingTriangles") {
            println("da-lhe")
            val triangles = countingTriangles(command[2])
            val vertex = countingKVertex(command[0].toInt(), triangles.second)
            printResults(triangles.first, vertex)
        } else println("Invalid command, please use a valid command.")
    }
}


/**
 *
 */
fun printResults(triangles: Int, vertex: PriorityQueue<Occurrence>) {
    println(triangles)
    println("Vertices:")
    vertex.forEach{ print("${it.vertex}; ") }
}


/**
 *
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
                    vertex1?.setData(vertex1.data + 1)
                    val vertex2 = graph.getVertex(i)
                    vertex2?.setData(vertex2.data + 1)
                    val vertex3 = graph.getVertex(j)
                    vertex3?.setData(vertex3.data + 1)
                }
            }
        }
    }
    return Pair(count / 6, graph)
}


/**
 *
 */
fun filterAdj(v: Graph.Vertex<Int, Int>, set: MutableSet<Graph.Edge<Int>?>) :List<Int> {
    return set.map { if (v.id == it!!.adjacent) it.id else it.adjacent }
}


/**
 *
 */
fun countingKVertex(k: Int, graph: GraphStructure<Int, Int>): PriorityQueue<Occurrence> {
    val pqp = PriorityQueue<Occurrence>(k) { a, b -> a.occurrence - b.occurrence }
    val iterator = graph.iterator()
    var counter = 0
    while (iterator.hasNext()) {
        val vertex = iterator.next()
        val newVertexData = vertex.setData(vertex.data / 6)
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
 *
 */
fun String.toIntList() =
    this.split(",").map { it.toInt() }


/**
 *
 */
fun graphOfAFile(fileName: String): GraphStructure<Int, Int> {
    val reader = createReader(fileName)
    var line: String?
    val graph = GraphStructure<Int, Int>()
    //while (reader.readLine().first() == '#'); // resolver o facto de comer a primeira linha com dados
    while (reader.readLine().also { line = it; println(line) } != null) {
        val ids = line!!.toIntList()
        if (ids.first() < ids.last()) {
            graph.addVertex(ids.first(), 0)
            graph.addVertex(ids.last(), 0)
            graph.addEdge(ids.first(), ids.last())
            graph.addEdge(ids.last(), ids.first()) // 2 ways
        } else {
            graph.addVertex(ids.last(), 0)
            graph.addVertex(ids.first(), 0)
            graph.addEdge(ids.last(), ids.first())
            graph.addEdge(ids.first(), ids.last())
        }
    }
    return graph
}
