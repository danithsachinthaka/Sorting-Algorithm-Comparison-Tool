import java.util.*;

public class DataSorterMain {
    static int[] data = {};
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms (show performance table)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            if (choice == 1) {
                manualInput();
            } else if (choice == 2) {
                generateRandomData();
            } else if (choice == 3) {
                performBubbleSort();
            } else if (choice == 4) {
                performMergeSort();
            } else if (choice == 5) {
                performQuickSort();
            } else if (choice == 6) {
                compareAll();
            } else if (choice == 7) {
                System.out.println("Exiting... Goodbye!");
            } else {
                System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);
    }

    static void manualInput() {
        System.out.print("Enter number of elements: ");
        int n = getIntInput();
        data = new int[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) data[i] = getIntInput();
    }

    static void generateRandomData() {
        System.out.print("Enter dataset size: ");
        int n = getIntInput();
        data = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) data[i] = rand.nextInt(1000);
        System.out.println("Random dataset generated!");
        displayArray(data);
    }

    static void performBubbleSort() {
        sortAndDisplay("Bubble");
    }

    static void performMergeSort() {
        sortAndDisplay("Merge");
    }

    static void performQuickSort() {
        sortAndDisplay("Quick");
    }

    static void sortAndDisplay(String algorithm) {
        if (data.length == 0) {
            System.out.println("Please input or generate data first!");
            return;
        }

        int[] copy = Arrays.copyOf(data, data.length);
        int steps = 0;
        long start = System.nanoTime();

        if (algorithm.equalsIgnoreCase("Bubble")) {
            steps = BubbleSort.bubbleSort(copy);
        } else if (algorithm.equalsIgnoreCase("Merge")) {
            steps = MergeSort.sort(copy);
        } else if (algorithm.equalsIgnoreCase("Quick")) {
            steps = QuickSort.sort(copy);
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        System.out.println("\n--- " + algorithm + " Sort Results ---");
        displayArray(copy);
        System.out.printf("Steps: %d | Time: %.3f ms%n", steps, timeMs);
    }

    static void compareAll() {
        if (data.length == 0) {
            System.out.println("Please input or generate data first!");
            return;
        }

        System.out.println("\n--- Sorting Algorithm Performance Comparison ---");
        System.out.print("\n");
        System.out.printf("%-15s %-15s %-15s%n", "Algorithm", "Steps", "Time (ms)");
        System.out.println("--------------------------------------------------");

        compareAlgorithm("Bubble");
        compareAlgorithm("Merge");
        compareAlgorithm("Quick");
    }

    static void compareAlgorithm(String algorithm) {
        int[] copy = Arrays.copyOf(data, data.length);
        int steps = 0;
        long start = System.nanoTime();

        if (algorithm.equalsIgnoreCase("Bubble")) {
            steps = BubbleSort.bubbleSort(copy);
        } else if (algorithm.equalsIgnoreCase("Merge")) {
            steps = MergeSort.sort(copy);
        } else if (algorithm.equalsIgnoreCase("Quick")) {
            steps = QuickSort.sort(copy);
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        System.out.printf("%-15s %-15d %-15.3f%n", algorithm + " Sort", steps, timeMs);
    }

    static void displayArray(int[] arr) {
        System.out.print("Sorted Array: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
    
    static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input, enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}