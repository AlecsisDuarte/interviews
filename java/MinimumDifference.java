public class MinimumDifference {
    private static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
            left = right = null;
        }
    }

    private static class IntHolder {
        private int val;

        IntHolder() {
            this.val = Integer.MAX_VALUE;
        }

        void set(int val) {
            this.val = val;
        }

        int get() {
            return this.val;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(50);

        Node tmp = root;
        tmp.left = new Node(30);
        tmp.right = new Node(80);

        tmp.left.left = new Node(10);
        tmp.left.right = new Node(49);

        tmp.right.left = new Node(65);
        tmp.right.right = new Node(100);

        System.out.println("Min Diff: " + findMinDiff(root));
        System.out.println("InOrder: " + inOrder(root));
        System.out.println("PosOrder: " + posOrder(root));
        
        int[] arr = new int[] { 1, 2, 3, 4, 5, 7, 8, 10 };
        root = arrayToBST(arr);
        System.out.println("Min Diff: " + findMinDiff(root));
        System.out.println("InOrder: " + inOrder(root));
        System.out.println("PosOrder: " + posOrder(root));
    }

    private static int findMinDiff(Node root) {
        return findMinDiff(root, new IntHolder(), Integer.MAX_VALUE);
    }

    // 5, MAX, MAX
    // 3, max, max
    // 1, max, max
    // MAX < (1 - MAX)
    // 3, 1, (1 - max)
    // |3-1| < (1-max)
    // 4, 3, 2
    // |4-3| < 2
    // 5, 4, 1
    // |5-1| < 1
    // 8, 5, 1
    // 7, 5, 1
    // |7-5| < 1
    // 8, 7, 1
    private static int findMinDiff(Node root, IntHolder prev, int min) {
        if (root == null) {
            return min;
        }
        min = findMinDiff(root.left, prev, min);
        int diff = Math.abs(root.val - prev.get());
        if (diff < min) {
            min = diff;
        }
        prev.set(root.val);
        min = findMinDiff(root.right, prev, min);
        return min;
    }

    private static Node arrayToBST(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        return arrayToBST(arr, 0, arr.length - 1);
    }

    private static Node arrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int len = end - start + 1;
        int middle = len / 2 + start;
        Node root = new Node(arr[middle]);
        if (len == 1) {
            return root;
        }
        
        root.left = arrayToBST(arr, start, middle - 1);
        root.right = arrayToBST(arr, middle + 1, end);
        return root;
    }

    private static String inOrder(Node root) {
        return inOrder(root, new StringBuilder()).toString();
    }

    private static StringBuilder inOrder(Node root, StringBuilder sb) {
        if (root == null) {
            return sb;
        }
        sb = inOrder(root.left, sb);
        sb.append(root.val);
        sb.append(' ');
        sb = inOrder(root.right, sb);
        return sb;
    }

    private static String posOrder(Node root) {
        return posOrder(root, new StringBuilder()).toString();
    }

    private static StringBuilder posOrder(Node root, StringBuilder sb) {
        if (root == null) {
            return sb;
        }
        sb = posOrder(root.left, sb);
        sb = posOrder(root.right, sb);
        sb.append(root.val);
        sb.append(' ');
        return sb;
    }
}