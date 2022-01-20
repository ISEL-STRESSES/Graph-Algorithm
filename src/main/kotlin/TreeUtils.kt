
/**
 * Class that represents a Node in a BST.
 * @property item Item of that node.
 * @property left Left Node of the Root Node.
 * @property right Right Node of the Root Node.
 */
data class Node<E>(var item: E, var left: Node<E>?, var right: Node<E>?)


/**
 * Function that searches through a BST for any value between [min]..[max] by a certain comparator.
 * @param root Root of the BST.
 * @param min first value in the interval.
 * @param max last value in the interval.
 * @param cmp Comparator Criteria.
 * @return Returns true if it finds or false if it didn't.
 */
fun <E> contains(root: Node<E>?, min: E, max: E, cmp: (e1: E, e2: E) -> Int): Boolean {
    if (root?.item == null) return false
    if (cmp(min, root.item) <= 0 && cmp(max, root.item) >= 0) return true
    if (cmp(root.item, min) < 0) return contains(root.right, min, max, cmp)
    if (cmp(max, root.item) < 0) return contains(root.left, min, max, cmp)
    return false
}


/**
 * Function that verifies if the BST from [root] is complete or not.
 * @param root Root of the BST.
 * @return Returns true if it is or false if it isn't.
 */
fun <E> isComplete(root: Node<E>?): Boolean {
    return isCompleteAux(root) != -1
}


/**
 * Auxiliary function that check if a BST is Complete.
 * @param root Root of a branch.
 * @return returns an Integer that reflects if it is complete.
 */
fun <E> isCompleteAux(root: Node<E>?): Int {
    if (root == null) return 0
    val l = isCompleteAux(root.left)
    val r = isCompleteAux(root.right)
    if (l == -1 || r == -1) return -1
    return if (r - l == 0) l + 1 else -1
}


/**
 * Function that creates a BST form a valid interval.
 * @param start first value from the interval, must be smaller that [end].
 * @param end the last value from the interval, must be bigger that [start].
 * @return Returns he root of that BST.
 */
fun createBSTFromRange(start: Int, end: Int): Node<Int>? {
    //non-existing interval
    if (end - start < 0) return null

    //variable initialization
    val mid = end - (end - start)/ 2
    val parent = Node(mid,null,null)

    //adding an element to the left subtree
    parent.left = createBSTFromRange(start, mid - 1)
    //adding an element to the left subtree
    parent.right = createBSTFromRange(mid + 1, end)
    //returns the parent o
    return parent
}
