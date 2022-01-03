package graphCollections

class GraphStructure<I, D>: Graph<I, D> {

    private class Vertex<I,D> :Graph.Vertex<I,D> {

        override var data: D
            //get() = TODO("Not yet implemented")
        override var id: I
            //get() = TODO("Not yet implemented")

        private val mSet = mutableSetOf<Graph.Edge<I>?>(null)
        constructor(Id: I, Data: D){
            data = Data
            id = Id
        }

        override fun getAdjacencies(): MutableSet<Graph.Edge<I>?> {
            return mSet
        }

        override fun setData(newData: D): D {
            this.data = newData
            return newData
        }

    }

    private class Edge<I> : Graph.Edge<I> {
        override var adjacent: I
            //get() = TODO("Not yet implemented")
        override var id: I
            //get() = TODO("Not yet implemented")

        constructor(Adjacent: I, Id: I) {
            adjacent = Adjacent
            id = Id
        }
    }

    private val initialSize = 10

    private val map = HashMap<I,Graph.Vertex<I,D>>(initialSize)

    override val size: Int = map.size
        //get() { TODO("Not yet implemented") }

    override fun addVertex(id: I, d: D): D? {
        val vertex = getVertex(id)
        if (vertex != null) return null

        vertex?.set(id, Vertex(id,d))
        return d
    }

    override fun addEdge(id: I, idAdj: I): I? {
        val vertex = getVertex(id) ?: return null
        if (getEdge(id,idAdj) != null) return null

        vertex.getAdjacencies().add(Edge(id,idAdj))
        return id
    }

    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        return map[id]
    }

    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        TODO("Not yet implemented")
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
        return object : Iterator<Graph.Edge<I>> {
            val iterator = map.mSet.iterator()

            override fun hasNext(): Boolean {
                return iterator.hasNext()
            }

            override fun next(): Graph.Edge<I> {
                return iterator.next().adjacent
            }
        }
    }
}