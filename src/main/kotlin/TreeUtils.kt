data class Node<E>(var item: E, var left:Node<E>?, var right:Node<E>?)

fun <E> contains(root: Node<E>?, min:E, max:E, cmp:(e1:E, e2:E)->Int):Boolean{
    throw UnsupportedOperationException()
}

fun <E> isComplete(root: Node<E>?): Boolean {
    throw UnsupportedOperationException()
}

fun createBSTFromRange(start:Int,end:Int): Node<Int>? {
    throw UnsupportedOperationException()
}

