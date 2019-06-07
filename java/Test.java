import utils.Node;

public class Test {
    public static void main(String[] args) {
        Node loopedList = LinkedListCycled.createLoopedLinkedList();
        boolean isLooped = LinkedListCycled.isLooped(loopedList);
        System.out.println("The list is looped: " + (isLooped ? "YEAP" : "NOPE"));

        Node list = LinkedListCycled.createLinkedList();
        isLooped = LinkedListCycled.isLooped(list);
        System.out.println("The list is looped: " + (isLooped ? "YEAP" : "NOPE"));
    }

    
}