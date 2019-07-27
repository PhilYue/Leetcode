/* we dont necessary have to check null node when adding row, we need to add anyway */
/* Approach: using queue for level order traversal
 * time complexity: O(n), space complexity: O(x), x is the maximum nodes in each level
 */
 class Solution {
     public TreeNode addOneRow(TreeNode root, int v, int d) {
         if (d == 1) {
             TreeNode newRoot = new TreeNode(v);
             newRoot.left = root;
             return newRoot;
         }
         Queue<TreeNode> queue = new LinkedList<>();
         queue.offer(root);
         int level = 1;

         while(!queue.isEmpty()) {
             int size = queue.size();
             for (int i = 0; i < size; i++) {
                 TreeNode top = queue.poll();
                 if (level == d - 1) {
                     TreeNode leftN = new TreeNode(v);
                     TreeNode rightN = new TreeNode(v);
                     leftN.left = top.left;
                     rightN.right = top.right;
                     top.left = leftN;
                     top.right = rightN;
                 } else {
                     if (top.left != null) queue.offer(top.left);
                     if (top.right != null) queue.offer(top.right);
                 }
             }
             level++;
         }
         return root;
     }
 }

/* Approach: recursive, by traverse tree with increasing depth variable
 * time complexity: O(n), space complexity: average O(logn), worse case: o(n) if the tree is skewed at one side
 */
 public class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        insert(root, v, d, 1);
        return root;
    }

    public void insert(TreeNode root, int v, int d, int depth) {
        if (root == null) {
            return;
        }
        if (depth == d - 1) {
            TreeNode leftNode = new TreeNode(v);
            TreeNode rightNode = new TreeNode(v);
            leftNode.left = root.left;  // not necessary to check root.left or right is null
            rightNode.right = root.right;
            root.left = leftNode;
            root.right = rightNode;
        } else {
            insert(root.left, v, d, depth + 1);
            insert(root.right, v, d, depth + 1);
        }
    }
}
