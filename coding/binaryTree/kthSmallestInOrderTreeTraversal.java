/*

1) use a stack
2) do in order traversal, since it gives us the sorted order of nodes 
3) eveytime we pop, we decrement count of K, since popping off the stack gives us the smallest elment (sorted)
4) once k == 0, whatever we popped will be the kth smallest

O(n)
*/

public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
 
    TreeNode p = root;
    int result = 0;
 
    while(!stack.isEmpty() || p!=null){
        if(p!=null){
            stack.push(p);
            p = p.left;
        }else{
            TreeNode t = stack.pop();
            k--;
            if(k==0)
                result = t.val;
            p = t.right;
        }
    }
 
    return result;
}