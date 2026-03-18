import java.util.*;

public class WithOrderAVL {

    static class Node {
        int val, height;
        Node left, right;

        Node(int v) {
            val = v;
            height = 1;
        }
    }

    static Node root = null;

    // HEIGHT
    static int h(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // BALANCE FACTOR
    static int bf(Node n) {
        return (n == null) ? 0 : h(n.left) - h(n.right);
    }

    // UPDATE HEIGHT
    static void update(Node n) {
        n.height = 1 + Math.max(h(n.left), h(n.right));
    }

    // ROTATIONS
    static Node rotateRight(Node y) {
        Node x = y.left;
        Node t = x.right;

        x.right = y;
        y.left = t;

        update(y);
        update(x);

        return x;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node t = y.left;

        y.left = x;
        x.right = t;

        update(x);
        update(y);

        return y;
    }

    // BALANCE
    static Node balance(Node n) {
        update(n);
        int b = bf(n);

        // LEFT HEAVY
        if (b > 1) {
            if (bf(n.left) < 0)
                n.left = rotateLeft(n.left);
            return rotateRight(n);
        }

        // RIGHT HEAVY
        if (b < -1) {
            if (bf(n.right) > 0)
                n.right = rotateRight(n.right);
            return rotateLeft(n);
        }

        return n;
    }

    // INSERT
    static Node insert(Node n, int val) {
        if (n == null) return new Node(val);

        if (val == n.val) {
            System.out.println("Duplicate.");
            return n;
        }

        if (val < n.val)
            n.left = insert(n.left, val);
        else
            n.right = insert(n.right, val);

        return balance(n);
    }

    static void ins(int val) {
        root = insert(root, val);
    }

    // FIND MIN
    static Node min(Node n) {
        while (n.left != null)
            n = n.left;
        return n;
    }

    // DELETE
    static Node delete(Node n, int val) {
        if (n == null) return null;

        if (val < n.val)
            n.left = delete(n.left, val);
        else if (val > n.val)
            n.right = delete(n.right, val);
        else {
            // FOUND
            if (n.left == null || n.right == null) {
                Node temp = (n.left != null) ? n.left : n.right;
                if (temp == null) return null;
                else n = temp;
            } else {
                Node succ = min(n.right);
                n.val = succ.val;
                n.right = delete(n.right, succ.val);
            }
        }

        return balance(n);
    }

    static void del(int val) {
        root = delete(root, val);
    }

    // -------- TRAVERSALS --------

    static void preorder(Node n) {
        if (n == null) return;
        System.out.print(n.val + " ");
        preorder(n.left);
        preorder(n.right);
    }

    static void inorder(Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.val + " ");
        inorder(n.right);
    }

    static void postorder(Node n) {
        if (n == null) return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.val + " ");
    }

    // -------- INDEX SIMULATION --------

    static int findIdx(Node n, int val, int idx) {
        if (n == null) return -1;
        if (n.val == val) return idx;

        if (val < n.val)
            return findIdx(n.left, val, 2 * idx + 1);
        else
            return findIdx(n.right, val, 2 * idx + 2);
    }

    // -------- PRINT TREE AS ARRAY --------

    static void fillArray(Node n, int[] arr, int idx) {
        if (n == null || idx >= arr.length) return;
        arr[idx] = n.val;

        fillArray(n.left, arr, 2 * idx + 1);
        fillArray(n.right, arr, 2 * idx + 2);
    }

    static void print() {
        int[] arr = new int[1000];
        fillArray(root, arr, 0);

        System.out.print("\nTree (1D): ");
        for (int i = 0; i < 31; i++) {
            System.out.print(arr[i]);
            if (i < 30) System.out.print(",");
        }
        System.out.println();

        System.out.print("\nPreorder: ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();
    }

    static void searchIndexPrompt(Scanner sc) {
        System.out.print("Search index? (y/n): ");
        String ans = sc.nextLine().toLowerCase();

        if (ans.equals("y")) {
            try {
                System.out.print("Enter value: ");
                int val = Integer.parseInt(sc.nextLine());

                int idx = findIdx(root, val, 0);

                if (idx == -1)
                    System.out.println("Not found.");
                else
                    System.out.println("Index: " + idx);

            } catch (Exception e) {
                System.out.println("Invalid.");
            }
        }
    }

    // -------- MAIN --------

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
                        String input = sc.nextLine();

                        if (input.matches("[idpx]")) {
                            cmd = input;
                            break;
                        }

                        try {
                            ins(Integer.parseInt(input));
                        } catch (Exception e) {
                            System.out.println("Invalid.");
                        }
                    }
                    break;

                case "d":
                    while (true) {
                        String input = sc.nextLine();

                        if (input.matches("[idpx]")) {
                            cmd = input;
                            break;
                        }

                        try {
                            del(Integer.parseInt(input));
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
                    System.out.println("Invalid.");
                    cmd = "";
            }
        }
    }
}
