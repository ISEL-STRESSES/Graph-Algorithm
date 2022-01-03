import com.sun.source.tree.BinaryTree
import com.sun.source.tree.Tree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateBSTFromRangeTest {

    val cmp = { i1: Int, i2: Int -> i1 - i2 }

    @Test
    fun creatBSTFromRange_empty_trees() {
        var tree = emptyBST()
        val resultTree = createBSTFromRange(0, -1)
        assertEqual(tree,resultTree)
        tree = singleNodeBST(0)

    }


    fun <E> assertEqual(root1: Node<E>?, root2: Node<E>?) {
        if (root1 != null && root2 != null) {
            assertEquals(root1.item, root2.item)
            assertEqual(root1.left, root2.left)
            assertEqual(root1.right, root2.right)
        }
        assertEquals(root1, root2)
    }

    @Test
    fun createBST_singleton_tree() {
        val tree = singleNodeBST(1)
        val resultTree = createBSTFromRange(1,1)
        assertEqual(tree, resultTree)
    }

    @Test
    fun createBST_even_leaves_tree() {
        val parent = singleNodeBST(6)
        add(parent,5,cmp)
        add(parent,7,cmp)
        val tree = createBSTFromRange(5,7)
        assertEqual(parent,tree)
    }

    @Test
    fun createBST_od_leaves_tree() {
        val start = 0
        val end = 11
        val mid = end - (end - start)/2
        val parent = singleNodeBST(mid)
        val interval = (start..end)
        val rigth = IntArray((mid +1..end).step)
        val left = IntArray((start until mid).step)
        //val populate = add(parent,rigth,cmp)
        val tree = createBSTFromRange(start,end)
        //assertEqual(populate,tree)
    }


}


