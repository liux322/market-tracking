package mytest.BFS.maxnodes;

import java.util.LinkedList;
import java.util.Queue;

import mytest.datamodel.TreeNode;

class Solution{
    int maxNodeLevel(TreeNode root) {
        int level = 0;
        int num_nodes = 0;
        int max_num_nodes = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if(curNode != null){
                num_nodes++;
                if(curNode.left != null){
                    nextLevelQueue.add(curNode.left);
                }
                if(curNode.right != null){
                    nextLevelQueue.add(curNode.right);
                }
            }
            if(queue.isEmpty()){
                //finish the current level;
                level++;
                max_num_nodes = Math.max(max_num_nodes, num_nodes);
                num_nodes =0;
                queue = nextLevelQueue;
                nextLevelQueue = new LinkedList<>();
            }
        }
        return max_num_nodes;
    }
}

public class MainClass {
    public static void main(String[] args){
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);

        root.left.right.right = new TreeNode(5);

        root.right.right = new TreeNode(8);

        int max = new Solution().maxNodeLevel(root);
        System.out.println(max);


    }

}
