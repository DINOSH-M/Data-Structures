# Data Structures - From-Scratch Java Implementations

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A comprehensive collection of **from-scratch Java implementations** of fundamental data structures, designed for learning, interview preparation, and understanding the inner workings of core computer science concepts.

## 📚 Table of Contents

- [Overview](#overview)
- [Data Structures Implemented](#data-structures-implemented)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [System Design Perspective](#system-design-perspective)
- [Time & Space Complexity](#time--space-complexity)
- [Use Cases & Applications](#use-cases--applications)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This repository contains **production-quality implementations** of essential data structures without relying on Java's built-in collections framework. Each implementation is crafted to:

- **Demonstrate core algorithmic concepts** with clear, readable code
- **Provide practical insights** into memory management and performance
- **Serve as interview preparation material** for technical coding interviews
- **Bridge the gap between theory and real-world applications**

Whether you're preparing for FAANG interviews, learning data structures, or building system design knowledge, this repository provides a solid foundation.

## 📦 Data Structures Implemented

### Linear Data Structures

#### **Linked Lists** (`linkedlist/`)
- `SinglyLinkedList.java` - Basic linked list with O(1) head insertions
- `DoublyLinkedList.java` - Bidirectional traversal with O(1) deletions
- `CircularLinkedList.java` - Circular variant for cyclic operations
- `CircularDoublyLinkedList.java` - Combines circular and doubly-linked properties

**Use Cases**: LRU cache implementation, undo/redo functionality, memory-efficient lists

#### **Stacks** (`stack/`)
- `ArrayStack.java` - Fixed-size array-based stack
- `LinkedListStack.java` - Dynamic linked-list-based stack

**Use Cases**: Expression evaluation, function call stacks, backtracking algorithms

#### **Queues** (`queue/`)
- `ArrayQueue.java` - Basic FIFO queue with array implementation
- `CircularQueue.java` - Optimized circular buffer for efficient space usage
- `DoubleEndedQueue.java` - Deque with insertion/deletion at both ends
- `LinkedListQueue.java` - Dynamic queue using linked nodes
- `LinkedlistCircularQueue.java` - Circular queue with linked list
- `LinkedlistDoubleEndedQueue.java` - Linked list-based deque
- `StackQueue.java` - Queue implementation using two stacks

**Use Cases**: Task scheduling, message queues, breadth-first search, rate limiting

### Tree Data Structures

#### **Binary Trees** (`tree/`)
- `BinarySearchTree.java` - Basic BST with O(log n) average operations
- `AvlTree.java` - Self-balancing BST with strict height guarantees
- `RedBlackTree.java` - Balanced BST with relaxed balancing rules
- `SplayTree.java` - Self-adjusting BST for frequently accessed elements
- `BTree.java` - Multiway tree optimized for disk storage

**Use Cases**: Database indexing, file systems, autocomplete, ordered data storage

#### **Specialized Trees** (`tree/`)
- `SegmentTree.java` - Range query optimization
- `SegmentTree2.java` - Alternative segment tree implementation
- `FenwickTree.java` - Binary Indexed Tree for prefix sums

**Use Cases**: Range queries, dynamic prefix sums, competitive programming

### Hash-Based Structures

#### **Hash Tables** (`hashTable/`)
- `HashTable.java` - Chaining-based collision resolution
- `HashTableLinearProbing.java` - Open addressing with linear probing
- `HashTableQuadraticProbing.java` - Quadratic probing for better distribution
- `HashTableDoubleHashing.java` - Double hashing for minimal clustering

**Use Cases**: Caching, symbol tables, database indexing, distributed systems

### Priority-Based Structures

#### **Heaps** (`heaps/`)
- `MaxBinaryHeap.java` - Max-heap with O(log n) insertions
- `MinBinaryHeap.java` - Min-heap for priority queue operations

**Use Cases**: Priority task scheduling, Dijkstra's algorithm, heap sort, top-k problems

### Graph Structures

#### **Graphs** (`graph/`)
- `AdjacentMatrix.java` - Matrix representation for dense graphs

**Use Cases**: Social networks, routing algorithms, dependency resolution

### String Structures

#### **Tries** (`tries/`)
- `StandardTries.java` - Prefix tree for efficient string operations

**Use Cases**: Autocomplete, spell checking, IP routing, dictionary implementation

## 🗂️ Project Structure

```
Data-Structures/
├── README.md                       # This file
├── SystemDesignAnalysis.md         # System design insights & interview prep
│
├── linkedlist/
│   ├── SinglyLinkedList.java
│   ├── DoublyLinkedList.java
│   ├── CircularLinkedList.java
│   ├── CircularDoublyLinkedList.java
│   └── Main.java
│
├── stack/
│   ├── ArrayStack.java
│   └── LinkedListStack.java
│
├── queue/
│   ├── ArrayQueue.java
│   ├── CircularQueue.java
│   ├── DoubleEndedQueue.java
│   ├── LinkedListQueue.java
│   ├── LinkedlistCircularQueue.java
│   ├── LinkedlistDoubleEndedQueue.java
│   └── StackQueue.java
│
├── tree/
│   ├── BinarySearchTree.java
│   ├── AvlTree.java
│   ├── RedBlackTree.java
│   ├── SplayTree.java
│   ├── BTree.java
│   ├── SegmentTree.java
│   ├── SegmentTree2.java
│   └── FenwickTree.java
│
├── heaps/
│   ├── MaxBinaryHeap.java
│   └── MinBinaryHeap.java
│
├── hashTable/
│   ├── HashTable.java
│   ├── HashTableLinearProbing.java
│   ├── HashTableQuadraticProbing.java
│   └── HashTableDoubleHashing.java
│
├── graph/
│   └── AdjacentMatrix.java
│
└── tries/
    └── StandardTries.java
```

## 🚀 Getting Started

### Prerequisites

- **Java 8+** (Recommended: Java 17+)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

### Installation

```bash
# Clone the repository
git clone https://github.com/dinoshm/Data-structures.git

# Navigate to the project directory
cd Data-structures/Data-Structures

# Compile a specific data structure (example)
javac linkedlist/SinglyLinkedList.java

# Run the main class (if available)
java -cp . com.ds.linkedlist.Main
```

### Usage Example

```java
// Using SinglyLinkedList
SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
list.insert(10);
list.insert(20);
list.insert(30);
list.display();

// Using MinBinaryHeap for priority queue
MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(10);
heap.insert(5);
heap.insert(3);
heap.insert(8);
Integer min = heap.extractMin(); // Returns 3

// Using HashTable for key-value storage
HashTable<String, Integer> cache = new HashTable<>();
cache.add("user123", 42);
Integer value = cache.get("user123");
```

## 🏗️ System Design Perspective

This repository isn't just about data structures—it's about **understanding how they power real-world systems**. Check out [`SystemDesignAnalysis.md`](SystemDesignAnalysis.md) for:

- **Real-world use cases** for each data structure
- **High-Level Design (HLD)** of distributed systems using these structures
- **Low-Level Design (LLD)** with class hierarchies and interactions
- **Scalability & performance considerations**
- **Interview questions and answers** for system design rounds

### Example: Building a Distributed Task Scheduler

```
API Gateway (CircularQueue) 
    ↓
Task Scheduler (MinBinaryHeap + RedBlackTree)
    ↓
Worker Pool (LinkedListQueue)
    ↓
Result Cache (HashTable + DoublyLinkedList for LRU)
```

## ⏱️ Time & Space Complexity

| Data Structure | Insert | Delete | Search | Space |
|----------------|--------|--------|--------|-------|
| SinglyLinkedList | O(1) head<br>O(n) tail | O(1) head<br>O(n) other | O(n) | O(n) |
| DoublyLinkedList | O(1) | O(1) | O(n) | O(n) |
| Stack | O(1) | O(1) | O(n) | O(n) |
| Queue | O(1) | O(1) | O(n) | O(n) |
| Binary Search Tree | O(log n) avg<br>O(n) worst | O(log n) avg<br>O(n) worst | O(log n) avg<br>O(n) worst | O(n) |
| AVL Tree | O(log n) | O(log n) | O(log n) | O(n) |
| Red-Black Tree | O(log n) | O(log n) | O(log n) | O(n) |
| B-Tree | O(log n) | O(log n) | O(log n) | O(n) |
| Binary Heap | O(log n) | O(log n) extract | O(n) | O(n) |
| Hash Table | O(1) avg<br>O(n) worst | O(1) avg | O(1) avg<br>O(n) worst | O(n) |
| Trie | O(m) | O(m) | O(m) | O(ALPHABET_SIZE × N × M) |

*Note: m = string length, n = number of elements*

## 💡 Use Cases & Applications

### Backend Engineering
- **Caching**: Hash tables with LRU eviction (DoublyLinkedList)
- **Task Queues**: Circular queues for message processing
- **Rate Limiting**: Sliding window with hash tables + queues
- **Session Storage**: Hash tables for O(1) user session retrieval

### Database Systems
- **Indexing**: B-trees for disk-based storage, Red-Black trees for in-memory indexes
- **Query Optimization**: Segment trees for range queries
- **Transaction Logs**: Queues for write-ahead logging

### Distributed Systems
- **Load Balancing**: Heaps for server selection by load
- **Consistent Hashing**: Hash tables for data distribution
- **Replication**: Graphs for topology management

### Search & Autocomplete
- **Prefix Matching**: Tries for fast autocomplete
- **Ranking**: Heaps for top-k results
- **Caching**: Hash tables for query result storage

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/NewDataStructure`)
3. **Commit your changes** (`git commit -m 'Add SkipList implementation'`)
4. **Push to the branch** (`git push origin feature/NewDataStructure`)
5. **Open a Pull Request**

### Contribution Guidelines
- Maintain consistent code style with existing implementations
- Add comprehensive comments explaining algorithms
- Include complexity analysis in code comments
- Add usage examples in documentation
- Ensure code compiles without warnings

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by classic computer science textbooks and FAANG interview patterns
- Built for learners, by learners
- Special thanks to the open-source community

🚨 READ-ONLY PUBLIC REPOSITORY

This repository is intentionally public for:
```
✔ Learning
✔ Code reading
✔ Architecture understanding
```
```
❌ Copying
❌ Reuse
❌ Commercial use
❌ Production deployment
```
is strictly prohibited.


## 📧 Contact

**Author**: DINOSH-M
**GitHub**: [@dinoshm](https://github.com/dinoshm)  
**Repository**: [Data-structures](https://github.com/dinoshm/Data-structures)

---

⭐ **If you find this repository helpful, please give it a star!** ⭐

*Last Updated: January 5, 2026*
