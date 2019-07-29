
/*
Given a binary tree, return the vertical order traversal of its nodes' values.
(ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
*/
/* Level order traversal(Queue) + treeMap<pos, List<Integer>>
 * expands pos from center to both sides. root (pos = 0),left node (pos-1), right node(pos+1) */
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<Integer>> treeMap = new TreeMap<>();  /* use treeMap to order the output from left to right */
        Queue<NodeContainer> queue = new LinkedList<>();
        queue.offer(new NodeContainer(0, root));
        while(!queue.isEmpty()){
            NodeContainer top = queue.poll();
            int pos = top.pos;
            TreeNode node = top.node;
            if(!treeMap.containsKey(pos)){
                treeMap.put(pos, new ArrayList<Integer>());
            }
            treeMap.get(pos).add(node.val);
            if(node.left != null){
                queue.offer(new NodeContainer(pos-1, node.left));
            }
            if(node.right != null){
                queue.offer(new NodeContainer(pos+1, node.right));
            }
        }
        for(List<Integer> list : treeMap.values()){
            res.add(list);
        }
        return res;
    }
    /* created a wrapper node that contains current posistion and node */
    class NodeContainer{
        int pos;
        TreeNode node;
        public NodeContainer(int pos, TreeNode node){
            this.pos = pos;
            this.node = node;
        }
    }
}
