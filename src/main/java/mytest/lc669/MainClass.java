package mytest.lc669;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import mytest.datamodel.TreeNode;

class Solution {

    /*
    Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root.val<L){
            return trimBST(root.right, L, R);
        }
        if(root.val > R){
            return trimBST(root.left, L, R);
        }
        if(root.val>=L && root.val<=R ){
            root.left = trimLeft(root.left, L, R);
            root.right = trimRight(root.right,L,  R);
        }
        return root;
    }

    TreeNode trimLeft(  TreeNode node, int L, int R){
        if(node == null){
            return null;
        }
        if(node.val < L){
            return trimLeft(node.right, L, R);
        }
        if(node.val >= L){
            node.left = trimLeft(node.left, L, R);
            node.right = trimRight(node.right, L, R);
        }
        return node;
    }

    TreeNode trimRight(TreeNode node, int L, int R){
        if(node == null){
            return null;
        }
        if(node.val > R){
            return trimRight(node.left, L, R);
        }
        if(node.val <= R){
            node.left = trimLeft(node.left, L, R);
            node.right = trimRight(node.right, L, R);
        }
        return node;
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

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int L = Integer.parseInt(line);
            line = in.readLine();
            int R = Integer.parseInt(line);

            TreeNode ret = new Solution().trimBST(root, L, R);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}