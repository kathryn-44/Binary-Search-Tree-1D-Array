import java.util.Scanner;

public class Main {

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
        // Go to left subtree
        int curr = 2 * index + 1;
        
        // If no left subtree, return -1
        if (curr > maxIndex || tree[curr] == 0)
            return -1;
        
        // Find maximum value in left subtree (rightmost node)
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
        // Go to right subtree
        int curr = 2 * index + 2;
        
        // If no right subtree, return -1
        if (curr > maxIndex || tree[curr] == 0)
            return -1;
        
        // Find minimum value in right subtree (leftmost node)
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
        if (idx < 0 || idx > maxIndex || tree[idx] == 0) {
            return;
        }

        int value = tree[idx];
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        boolean hasLeft = left <= maxIndex && tree[left] != 0;
        boolean hasRight = right <= maxIndex && tree[right] != 0;

        // Case 1: Leaf node
        if (!hasLeft && !hasRight) {
            tree[idx] = 0;
            System.out.println("Deleted leaf: " + value);
            
            // Update maxIndex
            while (maxIndex >= 0 && tree[maxIndex] == 0) {
                maxIndex--;
            }
            return;
        }

        // Case 2: Node has at least one child
        int repIdx;

        // Try predecessor first (from left subtree)
        repIdx = pred(idx);

        // If no predecessor, get successor
        if (repIdx == -1) {
            repIdx = succ(idx);
        }

        if (repIdx != -1) {
            // Replace current node with predecessor/successor value
            int repVal = tree[repIdx];
            tree[idx] = repVal;
            System.out.println("Replaced " + value + " with " + repVal);
            
            // Delete the predecessor/successor node (which is now a duplicate)
            deleteAtIndex(repIdx);
        }
    }

    public static void del(int value) {
        int idx = findIdx(value);

        // Check if node exists
        if (idx == -1) {
            System.out.println("Value " + value + " not found.");
            return;
        }

        deleteAtIndex(idx);
    }

    public static void print() {
        // Calculate size needed for complete tree representation
        int size = 1;
        while (size - 1 < maxIndex) {
            size = size * 2 + 1;
        }

        System.out.print("Tree: ");
        for (int i = 0; i < size; i++) {
            if (tree[i] != 0 || i <= maxIndex) {
                System.out.print(tree[i]);
            } else {
                System.out.print("0");
            }
            
            if (i < size - 1)
                System.out.print(",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int root;

        while (true) {
            try {
                System.out.print("Enter root: ");
                root = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid. Enter integer.");
            }
        }

        tree[0] = root;

        while (true) {
            System.out.print("\nCmd (i/d/p/x): ");
            String cmd = sc.nextLine().toLowerCase();

            switch (cmd) {
                case "i":
                    while (true) {
                        System.out.print("Enter val: ");
                        String input = sc.nextLine();

                        if (input.equalsIgnoreCase("d") ||
                            input.equalsIgnoreCase("p") ||
                            input.equalsIgnoreCase("x")) {
                            cmd = input.toLowerCase();
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
                    try {
                        System.out.print("Enter val to delete: ");
                        int val = Integer.parseInt(sc.nextLine());
                        del(val);
                    } catch (Exception e) {
                        System.out.println("Invalid.");
                    }
                    break;

                case "p":
                    print();
                    break;

                case "x":
                    print();
                    System.out.println("End.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid. Use i/d/p/x");
            }
        }
    }
}
