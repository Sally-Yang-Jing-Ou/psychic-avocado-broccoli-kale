/*
pre-order traversal is root-left-right, and post order is left-right-root.
modify the code for pre-order to make it root-right-left, and then reverse the output so that we can get left-right-root .

Create an empty stack, Push root node to the stack.
Do following while stack is not empty.
2.1. pop an item from the stack and print it.

2.2. push the left child of popped item to stack.

2.3. push the right child of popped item to stack.

reverse the ouput.
*/

class Solution {
public:
    vector<int> postorderTraversal(TreeNode *root) {
        stack<TreeNode*> nodeStack;
        vector<int> result;
        //base case
        if(root==NULL)
        return result;
        nodeStack.push(root);
    while(!nodeStack.empty())
    {
        TreeNode* node= nodeStack.top();  
        result.push_back(node->val);
        nodeStack.pop();
        if(node->left)
        nodeStack.push(node->left);
        if(node->right)
        nodeStack.push(node->right);
    }
     reverse(result.begin(),result.end());
     return result;
    
    }
}



public List<Integer> postorderTraversal(TreeNode root) {
    Stack<TreeNode> traversal = new Stack<TreeNode>();
    List<Integer> res = new LinkedList<Integer>();
    
    if(root == null)
        return res;
    
    traversal.push(root);
    while(!traversal.isEmpty()){
        TreeNode top = traversal.pop();
        res.add(0, top.val);
        
        if(top.left != null){
            traversal.push(top.left);
        }
        
        if(top.right != null){
            traversal.push(top.right);
        }
    }
    return res;
}