package graphCollections

class GraphStructure<I, D>: Graph<I, D> {

    private class Vertex<I,D> :Graph.Vertex<I,D> {

        override var data: D
            //get() = TODO("Not yet implemented")
        override var id: I
            //get() = TODO("Not yet implemented")

        private val edges = mutableSetOf<Graph.Edge<I>?>()

        constructor(Id: I, Data: D){
            data = Data
            id = Id
        }

        override fun getAdjacencies(): MutableSet<Graph.Edge<I>?> {
            return edges
        }

        override fun setData(newData: D): D {
            val oldData = data
            data = newData
            return oldData
        }

    }

    private class Edge<I> : Graph.Edge<I> {
        override var adjacent: I

        override var id: I

        constructor(Adjacent: I, Id: I) {
            adjacent = Adjacent
            id = Id
        }
    }

    private val defaultSize = 11

    val map = HashMap<I, Graph.Vertex<I, D>>(defaultSize)

    override val size: Int
        get() {
            return map.size
        }

    override fun addVertex(id: I, d: D): D? {
        val vertex = getVertex(id)
        if (vertex != null) return null

        map[id] = Vertex(id,d)
        return d
    }

    override fun addEdge(id: I, idAdj: I): I? {
        val idVertex = getVertex(id) ?: return null
        if (getEdge(id, idAdj) != null) return null

        idVertex.getAdjacencies().add(Edge(id,idAdj))
        return idAdj
    }

    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        return map[id]
    }

    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        val address = getVertex(id) ?: return null
        address.getAdjacencies().forEach{
            if( it!!.adjacent == idAdj) return it
        }
        return null
    }

    override fun iterator(): Iterator<Graph.Vertex<I, D>> {
        return object :Iterator<Graph.Vertex<I, D>> {
            val iterator = map.iterator()

            override fun hasNext(): Boolean {
                return iterator.hasNext()
            }

            override fun next(): Graph.Vertex<I, D> {
                return iterator.next().value
            }
        }

    }

    override fun edgesIterator(): Iterator<Graph.Edge<I>> {
        TODO("NOt yet implemented")
//        return object : Iterator<Graph.Edge<I>> {
//            val iterator = map.mSet.iterator()
//
//            override fun hasNext(): Boolean {
//                return iterator.hasNext()
//            }
//
//            override fun next(): Graph.Edge<I> {
//                return iterator.next().adjacent
//            }
//        }
    }
}