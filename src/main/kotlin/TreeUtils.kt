data class Node<E>(var item: E, var left: Node<E>?, var right: Node<E>?)

fun <E> contains(root: Node<E>?, min: E, max: E, cmp: (e1: E, e2: E) -> Int): Boolean {
    if (root?.item == null) return false
    if (cmp(min, root.item) <= 0 && cmp(max, root.item) >= 0) return true
    if (cmp(root.item, min) < 0) return contains(root.right, min, max, cmp)
    if (cmp(max, root.item) < 0) return contains(root.left, min, max, cmp)
    return false
}

fun <E> isComplete(root: Node<E>?): Boolean {
    return isCompleteAux(root) != -1
}

fun <E> isCompleteAux(root: Node<E>?): Int {
    if (root == null) return 0
    val l = isCompleteAux(root.left)
    val r = isCompleteAux(root.right)
    if (l == -1 || r == -1) return -1
    return if (r - l == 0) l + 1 else -1
}

fun createBSTFromRange(start: Int, end: Int): Node<Int>? {
    //non-existing interval
    if (end - start <= 0) return null

    //variable initialization
    val mid = end - (end - start) / 2
    val interval = (start..end)
    var leftElem = mid - 1
    var rightElem = mid + 1
    val root = Node(mid, null, null)
    var parent = root

    //loop to go through every element in interval
    while (parent.item in interval) {
        if (parent.left == null) {
            parent.left = Node(leftElem, null, null)
            leftElem--
        } else {
            parent.right = Node(rightElem, null, null)
            rightElem++
        }

        parent = if (isComplete(parent)) parent.left!!
        else parent.right!!
    }
    return root
}

