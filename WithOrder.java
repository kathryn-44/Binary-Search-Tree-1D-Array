import java.util.Scanner;

public class WithOrder {

    static int[] tree = new int[1000];
    static int maxIndex = 0;

    public static void ins(int value) {

        int i = 0;

        while (true) {

            if (tree[i] == value) {
                System.out.println("Duplicate.");
                return;
            }

            if (tree[i] == 0) {
                tree[i] = value;

                if (i > maxIndex)
                    maxIndex = i;

                return;
            }

            if (value < tree[i])
                i = 2 * i + 1;
            else
                i = 2 * i + 2;
        }
    }

    public static int findIdx(int value) {

        for (int i = 0; i <= maxIndex; i++) {
            if (tree[i] == value)
                return i;
        }

        return -1;
    }

    public static int pred(int index) {

        int curr = 2 * index + 1;

        if (curr > maxIndex || tree[curr] == 0)
            return -1;

        while (true) {

            int right = 2 * curr + 2;

            if (right <= maxIndex && tree[right] != 0)
                curr = right;
            else
                break;
        }

        return curr;
    }

    public static int succ(int index) {

        int curr = 2 * index + 2;

        if (curr > maxIndex || tree[curr] == 0)
            return -1;

        while (true) {

            int left = 2 * curr + 1;

            if (left <= maxIndex && tree[left] != 0)
                curr = left;
            else
                break;
        }

        return curr;
    }

    public static void deleteAtIndex(int idx) {

        if (idx < 0 || idx > maxIndex || tree[idx] == 0)
            return;

        int value = tree[idx];

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        boolean hasLeft = left <= maxIndex && tree[left] != 0;
        boolean hasRight = right <= maxIndex && tree[right] != 0;

        if (!hasLeft && !hasRight) {

            tree[idx] = 0;
            System.out.println("Deleted leaf: " + value);

            while (maxIndex >= 0 && tree[maxIndex] == 0)
                maxIndex--;

            return;
        }

        int repIdx = pred(idx);

        if (repIdx == -1)
            repIdx = succ(idx);

        if (repIdx != -1) {

            int repVal = tree[repIdx];
            tree[idx] = repVal;

            System.out.println("Replaced " + value + " with " + repVal);

            deleteAtIndex(repIdx);
        }
    }

    public static void del(int value) {

        int idx = findIdx(value);

        if (idx == -1) {
            System.out.println("Value " + value + " not found.");
            return;
        }

        deleteAtIndex(idx);
    }

    public static void print() {

        int size = 1;

        while (size - 1 < maxIndex)
            size = size * 2 + 1;

        System.out.print("Tree (1D): ");

        for (int i = 0; i < size; i++) {

            if (tree[i] != 0 || i <= maxIndex)
                System.out.print(tree[i]);
            else
                System.out.print("0");

            if (i < size - 1)
                System.out.print(",");
        }

        System.out.println();

        printTraversals();
    }

    public static void printTraversals() {

        System.out.print("Preorder: ");
        preorder(0);
        System.out.println();

        System.out.print("Inorder: ");
        inorder(0);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(0);
        System.out.println();
    }

    public static void preorder(int idx) {

        if (idx > maxIndex || tree[idx] == 0)
            return;

        System.out.print(tree[idx] + " ");

        preorder(2 * idx + 1);
        preorder(2 * idx + 2);
    }

    public static void inorder(int idx) {

        if (idx > maxIndex || tree[idx] == 0)
            return;

        inorder(2 * idx + 1);
        System.out.print(tree[idx] + " ");
        inorder(2 * idx + 2);
    }

    public static void postorder(int idx) {

        if (idx > maxIndex || tree[idx] == 0)
            return;

        postorder(2 * idx + 1);
        postorder(2 * idx + 2);

        System.out.print(tree[idx] + " ");
    }

    public static void searchIndexPrompt(Scanner sc) {

        System.out.print("Search index? (y/n): ");
        String ans = sc.nextLine().toLowerCase();

        if (ans.equals("y")) {

            try {

                System.out.print("Enter value: ");
                int val = Integer.parseInt(sc.nextLine());

                int idx = findIdx(val);

                if (idx == -1)
                    System.out.println("Value " + val + " not found.");
                else
                    System.out.println("Value " + val + " is at index " + idx);

            } catch (Exception e) {
                System.out.println("Invalid.");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String cmd = "";

        while (true) {

            if (cmd.equals("")) {
                System.out.print("Cmd (i/d/p/x): ");
                cmd = sc.nextLine().toLowerCase();
            }

            switch (cmd) {

                case "i":

                    while (true) {

                        String input = sc.nextLine().toLowerCase();

                        if (input.equals("i") || input.equals("d") || input.equals("p") || input.equals("x")) {
                            cmd = input;
                            break;
                        }

                        try {
                            int val = Integer.parseInt(input);
                            ins(val);
                        } catch (Exception e) {
                            System.out.println("Invalid.");
                        }
                    }

                    break;

                case "d":

                    while (true) {

                        String input = sc.nextLine().toLowerCase();

                        if (input.equals("i") || input.equals("d") || input.equals("p") || input.equals("x")) {
                            cmd = input;
                            break;
                        }

                        try {
                            int val = Integer.parseInt(input);
                            del(val);
                        } catch (Exception e) {
                            System.out.println("Invalid.");
                        }
                    }

                    break;

                case "p":

                    print();
                    searchIndexPrompt(sc);
                    cmd = "";
                    break;

                case "x":

                    print();
                    System.out.println("End.");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid. Use i/d/p/x");
                    cmd = "";
            }
        }
    }
}
