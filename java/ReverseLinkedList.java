import java.util.Scanner;

public class ReverseLinkedList {
    public static class Node {
        String value;
        Node next;

        public Node(String value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        Node head = null;
        Node tmp = null;
        while (!(line = scan.next()).equals("0")) {
            if (head == null) {
                head = new Node(line);
                tmp = head;
            } else {
                tmp.next = new Node(line);
                tmp = tmp.next;
            }
        }
        scan.close();

        printNodes(head);
        head = reverseList(head);
        printNodes(head);
    }

    public static Node reverseList(Node head) {
        if (head == null) {
            return null;
        }
        Node tmp = head;
        head = reverseList(head, head.next);
        tmp.next = null;

        return head;
    }


    private static Node reverseList(Node head, Node next) {
        Node tmp = head;
        if (next != null) {
            head = reverseList(next, next.next);
            next.next = tmp;
        }
        return head;
    }

    private static void printNodes(Node head) {
        while (head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

}