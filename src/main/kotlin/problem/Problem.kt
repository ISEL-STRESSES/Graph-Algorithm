package problem

import graphCollections.GraphStructure
import kotlin.system.exitProcess


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
            val triangles = countingTriangles(command[0].toInt(), command[2])
            printTriangles(triangles)
        } else println("Invalid command, please use a valid command.")
    }
}

fun printTriangles(pair: Pair<Int, Array<Int>>) {
    println(pair.first)
    println("Vertices: ${pair.second.joinToString { "${it};\t" }}")
}

fun String.toIntList() = this.split(" " + "\t").map { it.toInt() }

fun countingTriangles(k: Int, fileName: String): Pair<Int, Array<Int>> {
    val graph = graphOfAFile(fileName)
    val iterator = graph.iterator()

    while (iterator.hasNext()) {
        val vertex = graph.getVertex(iterator.next().id)
        val adjacency = vertex?.getAdjacencies() as Array<Int>
        for (i in adjacency.indices) {

        }
    }
    return Pair(0, emptyArray())
}

fun graphOfAFile(fileName: String): GraphStructure<Int, String> {
    val reader = createReader(fileName)
    var line: String
    val graph = GraphStructure<Int, String>()
    while (reader.readLine().first() == '#');
    while (reader.readLine().also { line = it } != null) {
        val ids = line.toIntList()
        if ( ids.first() < ids.last()) {
            graph.addVertex(ids.first(), "V${ids.first()}")
            graph.addEdge(ids.first(), ids.last())
        } else {
            graph.addVertex(ids.last(), "V${ids.last()}")
            graph.getEdge(ids.last(), ids.first())
        }
    }
    return graph
}

