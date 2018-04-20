package mytest.lc671;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import mytest.datamodel.TreeNode;

// Definition for a binary tree node.


class Solution {
    public int findSecondMinimumValue(TreeNode root) {

        if(root.left == null && root.right == null) {
            return -1 ;
        }

        if(root.left.val !=root.right.val){
            return Math.max(root.left.val, root.right.val);
        }

        Set<Integer> result = new HashSet<>();
        addValue(root, result);
        if(result.size() == 1){
            return -1;
        }else{
            List<Integer> list = new ArrayList<>();
            list.addAll(result);
            return list.get(1);
        }

       // int left = findSecondMinimumValue(root.left);
//        int right = findSecondMinimumValue(root.right);
//
//        if(right == -1){
//            return -1;
//        } else {
//            return right;
//        }
////        TreeNode node = root;
//
//        if(root.left.val !=root.right.val){
//            return Math.max(root.left.val, root.right.val);
//        }
//        TreeNode lnode = root.left;
//        TreeNode rnode
//


    }

    void addValue(TreeNode node, Set<Integer> result){
        result.add(node.val);

        if(node.left == null && node.right == null){
            return;
        }
        addValue(node.left, result);
        addValue(node.right, result);
    }

}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new Solution().findSecondMinimumValue(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
