# Binary Search Tree (BST) – Java (Array-Based)

A simple **Binary Search Tree implementation in Java** using an **array-based representation** instead of traditional node objects. The program supports **insertion, deletion, predecessor/successor replacement, and tree printing** through a console interface.

---

# Features

- Insert values into the BST
- Prevent duplicate values
- Delete nodes using:
  - **Inorder predecessor**
  - **Inorder successor** (fallback)
- Array-based tree representation
- Console command interface
- Print tree structure as a level-order array

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

Inserts a new value into the BST.

- Traverses the tree using BST rules
- Rejects duplicate values

Example:

```
Cmd (i/d/p/x): i
Enter val: 15
Enter val: 5
Enter val: 20
```

---

## Delete (`d`)

Deletes a node from the BST.

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
Enter val to delete: 15
```

---

## Print (`p`)

Displays the tree as a **complete array representation**.

Example output:

```
Tree: 10,5,15,3,7,0,20
```

---

## Exit (`x`)

Prints the tree and exits the program.

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
| `d` | Delete a value |
| `p` | Print tree |
| `x` | Exit program |

Example session:

```
Enter root: 10

Cmd (i/d/p/x): i
Enter val: 5
Enter val: 15
Enter val: 3
Enter val: p

Tree: 10,5,15,3,0,0,0

Cmd (i/d/p/x): d
Enter val to delete: 5

Cmd (i/d/p/x): p
Tree: 10,3,15,0,0,0,0
```
---

# Limitations

- Uses a **fixed array size (1000)**
- `0` represents empty nodes (cannot store value `0`)
- Tree may become sparse when nodes are deleted
- Not dynamically resized

---

# Possible Improvements

- Dynamic resizing
- Node-based BST implementation
- Tree traversals:
  - Inorder
  - Preorder
  - Postorder
- Search function
- Tree visualization
- Self-balancing tree (AVL / Red-Black Tree)
