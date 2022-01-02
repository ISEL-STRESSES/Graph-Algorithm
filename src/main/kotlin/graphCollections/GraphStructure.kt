package graphCollections

class GraphStructure<I, D>: Graph<I, D> {
    private class Vertex<I,D> :Graph.Vertex<I,D> {
        override var data: D
            //get() = TODO("Not yet implemented")
        override var id: I
            //get() = TODO("Not yet implemented")

        constructor(Id: I, Data: D){
            data = Data
            id = Id
        }

        override fun getAdjacencies(): MutableSet<Graph.Edge<I>?> {
            TODO("Not yet implemented")
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

        constructor(Adjacent: I, Id: I){
            adjacent = Adjacent
            id = Id
        }
    }
    // TO IMPLEMENT

    override val size: Int
        get() { TODO("Not yet implemented") }

    override fun addVertex(id: I, d: D): D? {
        TODO("Not yet implemented")
    }

    override fun addEdge(id: I, idAdj: I): I? {
        TODO("Not yet implemented")
    }

    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        TODO("Not yet implemented")
    }

    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Graph.Vertex<I, D>> {
        TODO("Not yet implemented")
    }

    override fun edgesIterator(): Iterator<Graph.Edge<I>> {
        TODO("Not yet implemented")
    }
}