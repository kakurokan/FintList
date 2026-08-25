Here is a suggested README for the project, based on the provided files.

-----

# Assignment 2 AED: Implementation and Analysis of List Data Structures

This project was developed as part of the Algorithms and Data Structures (AED) course. The main objective is the implementation of a custom list data structure (`FintList`) and its comparative analysis against a standard linked list (`LinkedList`).

## Implemented Data Structures

The project focuses on two primary list implementations:

### 1. FintList (Fast Integer List)

(File: `src/aed/collections/FintList.java`)

`FintList` is a doubly-linked list implementation optimized specifically for the primitive `int` type.

Key features:

- Array-based storage: Instead of allocating separate `Node` objects, the list uses three parallel arrays (`elements`, `next_index`, `prev_index`) to manage the data and the connections.
- Memory management: Implements a free index list (`free_index`) to reuse positions of removed elements, reducing allocations and pressure on the garbage collector.
- Access optimization (cache): Tracks the last accessed node (`lastUsedNode` and `lastArrayPosition`). If a subsequent access (`get`, `addAt`, `removeAt`) is near the last access, the list can use this cached position to accelerate traversal.
- Additional operations: Supports functional-style operations like `map` (UnaryOperator) and `reduce` (BinaryOperator).
- Interactive shell: A `main` method inside `FintList.java` provides a command-line shell to test list operations (e.g., `addAt`, `remove`, `reverse`, `print`).

### 2. LinkedList

(File: `src/aed/collections/LinkedList.java`)

`LinkedList` is a generic (`<T>`) singly linked list implementation.

Key features:

- Node-based representation: Uses an inner `Node` class that stores the item and a reference to the next node (`next`).
- Standard operations: Implements common list operations such as `add` (at the front), `remove` (from the front), `addAt`, `removeAt`, `get`, `set`, and `reverse`.
- Copy: Provides a `shallowCopy` method.

## Performance Analysis

(Files: `src/aed/collections/Main.java` and `src/aed/collections/TemporalAnalysisUtils.java`)

The core of the project performs a temporal (time) comparison between `FintList` and `LinkedList`.

- Entry point: `Main.java` is the entry point for the performance tests.
- Methodology: `TemporalAnalysisUtils` contains utilities for measuring CPU time (e.g., `getAverageCPUTime` using `ThreadMXBean`) and for running doubling-ratio tests.
- Tests performed: `Main.java` compares the two data structures on the performance of the following operations:
  - `addAt` (insertion at a random index)
  - `removeAt` (removal at a random index)
  - `deepCopy` (for `FintList`) vs `shallowCopy` (for `LinkedList`)

## How to Run

The project has two main entry points:

1. Run the Performance Analysis:

   - Compile and run the `Main` class.
   - This will execute the comparative tests (e.g., `ensaioGraficoAddAt`, `ensaioRazaoDobradaRemoveAt`, etc.) and print results (complexity vs. time in ms) for both lists to the console.

   ```bash
   # (After compiling the .java files)
   java aed.collections.Main
   ```

2. Run the FintList Interactive Shell:

   - Compile and run the `FintList` class.
   - This will start an interactive shell where you can test `FintList` methods.

   ```bash
   # (After compiling the .java files)
   java aed.collections.FintList
   ```

   Example commands: `add 10`, `add 20`, `addAt 1 15`, `print`, `get 1`, `removeAt 0`, `reverse`, `print`.

## Project Structure (src)

- `src/aed/collections/FintList.java`: Array-based optimized list implementation for `int`.
- `src/aed/collections/LinkedList.java`: Generic linked list implementation.
- `src/aed/collections/Main.java`: Entry point for temporal/performance tests.
- `src/aed/collections/TemporalAnalysisUtils.java`: Utility class for performance measurement.
