import kotlin.math.abs
import kotlin.math.max

/**Create BSTs using Ints**/
val cmp={ i1: Int, i2: Int -> i1 - i2 }

fun emptyBST(): Node<Int>?=null

fun singleNodeBST(i:Int): Node<Int>?=add(null,i,cmp)

fun populatedBST(array:IntArray): Node<Int>? {
    var tree: Node<Int>? = null
    for (i in array) tree = add(tree, i, cmp)
    return tree
}

/**Generic BST Functions**/
fun <E> height(root: Node<E>?): Int {
     return if (root == null)  -1
     else 1 + max(height(root.left),height(root.right))
}

fun <E> search(root: Node<E>?, key: E, cmp: Comparator<E>): Node<E>? {
    return if (root == null || cmp.compare(key, root.item) == 0) root
    else if (cmp.compare(key, root.item) < 0) search(root.left, key, cmp)
    else search(root.right, key, cmp)
}

fun <E> add(root: Node<E>?, e: E, cmp: Comparator<E>): Node<E> {
    var root = root
    if (root == null) root = Node(e,null,null)
    else if (cmp.compare(e, root.item) < 0) root.left = add(root.left, e, cmp)
    else root.right = add(root.right, e, cmp)
    return root
}


//prefix path
fun <E> preorder(root: Node<E>?) {
    if (root != null) {
        print("$root.item ")
        preorder(root.left)
        preorder(root.right)
    }
}
//infix path
fun <E> inorder(root: Node<E>?) {
    if (root != null) {
        inorder(root.left)
        print("$root.item")
        inorder(root.right)
    }
}
//suffix path
fun <E> postorder(root: Node<E>?) {
    if (root != null) {
        postorder(root.left)
        postorder(root.right)
        print("$root.item")
    }
}


fun <E> isBalanced(root:Node<E>?):Boolean{
    return isBalancedAux(root)!=-1
}


fun <E> isBalancedAux(root:Node<E>?):Int{
    if(root==null) return 0
    val l= isBalancedAux(root.left)
    val r= isBalancedAux(root.right)
    if(l==-1 || r==-1) return -1
    return if(abs(l-r) <=1) max(l,r) +1 else -1
}

