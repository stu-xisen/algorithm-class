package class03;

public class Code01_ReverseList {


    public static Node reverseList(Node head){
        if (head == null){
            return null;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null){
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static DoubleNode reverseList(DoubleNode head){
        if (head == null){
            return null;
        }

        DoubleNode pre = null;
        DoubleNode cur = head;
        while (cur != null){
            DoubleNode next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;


        Node node = reverseList(node1);
        while (node != null){
            System.out.println(node);
            node = node.next;
        }


        DoubleNode dnode1 = new DoubleNode(1);
        DoubleNode dnode2 = new DoubleNode(2);
        DoubleNode dnode3 = new DoubleNode(3);
        DoubleNode dnode4 = new DoubleNode(4);
        DoubleNode dnode5 = new DoubleNode(5);

        dnode1.pre = null;
        dnode1.next = dnode2;

        dnode2.pre = dnode1;
        dnode2.next = dnode3;

        dnode3.pre = dnode2;
        dnode3.next = dnode4;

        dnode4.pre = dnode3;
        dnode4.next = dnode5;

        dnode5.pre = dnode4;
        dnode5.next = null;

        DoubleNode head = reverseList(dnode1);
        while (head != null){
            System.out.println(head.value);
            head = head.next;
        }


    }
}




