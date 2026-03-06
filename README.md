# Binary Search Tree (BST) – 1D Array Implementation in Java

## Overview
This project implements a **Binary Search Tree (BST)** in **Java** using a **1-Dimensional Array representation** instead of node objects.

The program allows users to interact with the BST through commands such as **insert**, **delete**, **print**, and **exit**.

### Array Index Rules
The BST follows the standard array-based tree relationships:

- **Root** → index `0`
- **Left Child** → `2 * i + 1`
- **Right Child** → `2 * i + 2`
- **Parent** → `(i - 1) / 2`

---

## Features

- Insert integer values into the BST
- Delete a value and replace it with another value
- Prevent duplicate values
- Print the BST in **1D array format**
- Automatically adjusts printed size to match the tree depth
- Find the **index of a specific value**
- Handles invalid input safely
- Command-based interaction system

---

## Commands

After entering the root value, the program accepts the following commands:

| Command | Description |
|--------|-------------|
| `insert` | Insert integer values into the BST |
| `delete` | Delete a value and replace it with another value |
| `print` | Display the BST as a 1D array |
| `exit` | Print the tree and terminate the program |

---

## Example Run
Enter root value: 30

Command (insert / delete / print / exit): insert
Enter integer to insert (or type command): 11
Enter integer to insert (or type command): 40
Enter integer to insert (or type command): 8
Enter integer to insert (or type command): 12
Enter integer to insert (or type command): 35
Enter integer to insert (or type command): 50
Enter integer to insert (or type command): 9
Enter integer to insert (or type command): 32
Enter integer to insert (or type command): 36
Enter integer to insert (or type command): 49
Enter integer to insert (or type command): 53
Enter integer to insert (or type command): 31
Enter integer to insert (or type command): print

Output:
1D array: 30,11,40,8,12,35,50,0,9,0,0,32,36,49,53,0,0,0,0,0,0,0,0,31,0,0,0,0,0,0,0

Then the program asks:
Enter value to find its index: 31
Index of 31 is: 23

---

## Example BST Structure


            30
         /       \
       11         40
     /    \     /     \
    8     12   35      50
     \         /  \    /  \
      9       32  36  49  53
                 /
                31
