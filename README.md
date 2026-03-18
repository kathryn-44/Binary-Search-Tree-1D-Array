# 🌳 Binary Search Tree (BST) – Java (Array-Based + AVL)

A collection of Binary Search Tree implementations in Java, demonstrating different approaches to building and working with trees:

- Array-based BST (basic)
- Array-based BST with traversals and index search
- Self-balancing AVL tree (node-based)

All versions share a common interactive console interface, making it easy to compare how each implementation behaves.

---


| File | Description |
|------|------------|
| `Main.java` | Basic array-based BST with insert, delete, and printing |
| `WithOrder.java` | Adds traversals and index searching to the array BST |
| `WithOrderAVL.java` | Node-based AVL tree with self-balancing and full traversal support |

---

## 🧠 Overview of Implementations

### 1. `Main.java` (Basic BST)

A straightforward array-based Binary Search Tree.

- Stores data in a fixed array
- Uses index formulas to navigate the tree

**Core Operations:**
- Insert  
- Delete  
- Print  

✅ Best for understanding:
- BST fundamentals  
- Array representation of trees  

---

### 2. `WithOrder.java` (BST with Traversals)

Extends the basic BST by adding traversal and search features.

**Additional Capabilities:**
- Preorder, Inorder, Postorder traversals  
- Search for the index of a value  
- More detailed output  

✅ Best for understanding:
- Tree traversal techniques  
- Structure vs traversal relationships  

---

### 3. `WithOrderAVL.java` (AVL Tree)

A node-based AVL (self-balancing) Binary Search Tree.

**Key Differences:**
- Uses dynamic nodes instead of a fixed array  
- Maintains balance automatically using rotations  
- Simulates index positions for compatibility  

✅ Best for understanding:
- Tree balancing concepts  
- Performance improvements over BST  

---

## ⚙️ Shared Features

All versions support:

- `i` → Insert values  
- `d` → Delete values  
- `p` → Print tree  
- `x` → Exit program  
- Continuous input until command changes  
- Duplicate value prevention  

---

## 🌲 Tree Representation

### Array-Based (`Main.java` & `WithOrder.java`)

```java
int[] tree = new int[1000];
