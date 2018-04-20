package mytest.basic;

class LinkedNode {
    int value;
    LinkedNode next;
}
public class TestLinkedList {

    public static LinkedNode reverse(LinkedNode root){
        if(root == null){
            return root;
        }
        LinkedNode newRoot = new LinkedNode();
        newRoot = root.next;
        newRoot.next = root;
        return reverse(newRoot);
    }

}
