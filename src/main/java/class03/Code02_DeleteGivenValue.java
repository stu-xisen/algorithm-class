package class03;

public class Code02_DeleteGivenValue {

    public static Node removeValue(int value , Node head){
        while (head != null){
            if (head.value == value){
                head = head.next;
            }else {
                break;
            }
        }

        Node cur = head;
        Node pre = null;
        while (cur != null){
            if (cur.value == value && pre != null){
                pre.next = cur.next;
                cur = cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        Node node = removeValue(2, node1);
        System.out.println(node);
    }
}
