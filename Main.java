import java.util.Scanner;

public class Main {

    static int[] tree = new int[1000];
    static int maxIndex = 0;

    public static void insert(int value) {

        int i = 0;

        while (true) {

            if (tree[i] == value) {
                System.out.println("Duplicate value not allowed.");
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

    public static int findIndex(int value) {

        for (int i = 0; i <= maxIndex; i++) {
            if (tree[i] == value)
                return i;
        }

        return -1;
    }

    public static void delete(int value, int replacement) {

        int index = findIndex(value);

        if (index == -1) {
            System.out.println("Value not found.");
            return;
        }

        tree[index] = replacement;

        if (index > maxIndex)
            maxIndex = index;

        System.out.println("Node replaced.");
    }

    public static void printTree(Scanner sc) {

        int size = 1;

        while (size - 1 < maxIndex) {
            size = size * 2 + 1;
        }

        System.out.print("1D array: ");

        for (int i = 0; i < size; i++) {

            System.out.print(tree[i]);

            if (i < size - 1)
                System.out.print(",");
        }

        System.out.println();

        try {

            System.out.print("Enter value to find its index: ");
            int val = Integer.parseInt(sc.nextLine());

            int idx = findIndex(val);

            if (idx == -1)
                System.out.println("Value not found.");
            else
                System.out.println("Index of " + val + " is: " + idx);

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int root;

        while (true) {

            try {
                System.out.print("Enter root value: ");
                root = Integer.parseInt(sc.nextLine());
                break;
            }

            catch (Exception e) {
                System.out.println("Invalid input. Enter an integer.");
            }
        }

        tree[0] = root;

        while (true) {

            System.out.print("\nCommand (insert / delete / print / exit): ");
            String cmd = sc.nextLine().toLowerCase();

            switch (cmd) {

                case "insert":

                    while (true) {

                        System.out.print("Enter integer to insert (or type command): ");
                        String input = sc.nextLine();

                        if (input.equalsIgnoreCase("delete") ||
                            input.equalsIgnoreCase("print") ||
                            input.equalsIgnoreCase("exit")) {

                            cmd = input.toLowerCase();
                            break;
                        }

                        try {

                            int val = Integer.parseInt(input);
                            insert(val);

                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;

                case "delete":

                    try {

                        System.out.print("Enter value to delete: ");
                        int val = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter replacement value: ");
                        int rep = Integer.parseInt(sc.nextLine());

                        delete(val, rep);

                    }

                    catch (Exception e) {
                        System.out.println("Invalid input.");
                    }

                    break;

                case "print":
                    printTree(sc);
                    break;

                case "exit":
                    printTree(sc);
                    System.out.println("Program ended.");
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
