
public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {

    public TreeNode cloneTree(TreeNode root) {
        // Write your code here
        if (root == null)
            return null;
        TreeNode clone_root = new TreeNode(root.val);
        clone_root.left = cloneTree(root.left);
        clone_root.right = cloneTree(root.right);
        return clone_root;
    }
}