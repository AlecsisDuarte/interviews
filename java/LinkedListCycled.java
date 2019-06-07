import utils.Node;

public class LinkedListCycled {
    public static boolean isLooped(Node head) {
        if (head == null) {
            return false;
        }

        Node hare = head;
        Node tortoise = head;

        while (hare != null) {
            hare = hare.next;
            if (hare == null) {
                break;
            }
            hare = hare.next;
            tortoise = tortoise.next;
            if (hare == tortoise) {
                return true;
            }
        }
        return false;
    }

    public static Node createLoopedLinkedList() {
        Node head = new Node("T");
        Node tmp = head;
        tmp.next = new Node("E");
        tmp = tmp.next;
        Node e = tmp;
        tmp.next = new Node("S");
        tmp = tmp.next;
        tmp.next = e;
        return head;
    }

    public static Node createLinkedList() {
        Node head = new Node("T");
        Node tmp = head;
        tmp.next = new Node("E");
        tmp = tmp.next;
        tmp.next = new Node("S");
        tmp = tmp.next;
        tmp.next = new Node("T");
        return head;
    }
}