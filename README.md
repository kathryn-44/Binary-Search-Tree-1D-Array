# Binary Search Tree (BST) – Java (Array-Based)

A simple **Binary Search Tree implementation in Java** using an **array-based representation** instead of traditional node objects. The program supports **insertion, deletion, predecessor/successor replacement, and tree printing** through a console interface.

This repository includes **two versions of the program**:

- **Main.java** – Basic BST implementation with array printing
- **WithOrder.java** – Extended version that also prints **tree traversals (Preorder, Inorder, Postorder)** and includes a **search index feature**

---

# Project Structure

```
/BST-Java
 ├── Main.java
 ├── WithOrder.java
 └── README.md
```

| File | Description |
|-----|-------------|
| `Main.java` | Original BST implementation with insert, delete, and array printing |
| `WithOrder.java` | Enhanced version that prints **Preorder, Inorder, Postorder traversals** and allows **index searching** |

---

# Features

### Shared Features (Both Versions)

- Insert values into the BST
- Prevent duplicate values
- Delete nodes using:
  - **Inorder predecessor**
  - **Inorder successor** (fallback)
- Array-based tree representation
- Console command interface
- Print tree structure as a **level-order array**
- Continuous insert/delete input until command changes

### Additional Features (WithOrder.java)

- Print **tree traversals**
  - Preorder
  - Inorder
  - Postorder
- Search for the **array index of a value**
- More detailed tree output for debugging and learning

---

# Tree Representation

The BST is stored in a **fixed-size integer array**:

```java
int[] tree = new int[1000];
```

Children are calculated using standard binary heap indexing:

| Node | Formula |
|-----|--------|
| Left Child | `2*i + 1` |
| Right Child | `2*i + 2` |
| Parent | `(i - 1) / 2` |

Example tree:

```
        10
       /  \
      5    15
     / \     \
    3   7     20
```

Array representation:

```
[10,5,15,3,7,0,20]
```

`0` represents an empty slot.

---

# Operations

## Insert (`i`)

Inserts values into the BST.

- Traverses the tree using BST rules
- Rejects duplicate values
- Continues inserting until another command is entered

Example:

```
Cmd (i/d/p/x): i
8
9
10
```

---

## Delete (`d`)

Deletes node(s) from the BST.

Cases handled:

### 1. Leaf Node
Node is removed directly.

### 2. Node With Children
The node is replaced with:

- **Inorder predecessor** (maximum in left subtree)
- If not available → **Inorder successor**

Example:

```
Cmd (i/d/p/x): d
8
9
```

---

## Print (`p`)

Displays the tree as a **complete array representation**.

Example:

```
Tree: 10,5,15,3,7,0,20
```

### Additional Output (WithOrder.java)

The enhanced version also prints **tree traversals**.

Example:

```
Tree: 10,5,15,3,7,0,20

Preorder: 10 5 3 7 15 20
Inorder: 3 5 7 10 15 20
Postorder: 3 7 5 20 15 10
```

The program may also prompt the user to **search for the array index of a value**.

Example:

```
Search index? (y/n): y
Enter value: 10
Value 10 is at index 0
```

---

## Exit (`x`)

Prints the tree and exits the program.

Example:

```
Cmd (i/d/p/x): x
Tree: 10,5,15,3,7,0,20
End.
```

---

# Program Flow

1. User enters the **root value**
2. Program waits for commands:

| Command | Action |
|--------|-------|
| `i` | Insert value(s) |
| `d` | Delete value(s) |
| `p` | Print tree |
| `x` | Exit program |

Example session:

```
Enter root: 10

Cmd (i/d/p/x): i
5
15
3

Cmd (i/d/p/x): p
Tree: 10,5,15,3,0,0,0

Cmd (i/d/p/x): d
5

Cmd (i/d/p/x): p
Tree: 10,3,15,0,0,0,0
```

---

# Limitations

- Uses a **fixed array size (1000)**
- `0` represents empty nodes (cannot store value `0`)
- Tree may become sparse when nodes are deleted
- Not dynamically resized
- Not self-balancing

---

# Possible Improvements

- Dynamic array resizing
- Node-based BST implementation
- Tree visualization (level-based display)
- Self-balancing trees:
  - AVL Tree
  - Red-Black Tree
- Graphical tree visualization
- File input/output support
