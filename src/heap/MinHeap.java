package heap;

public class MinHeap {
    int heapSize;
    int realSize = 0;
    int [] heap;
    MinHeap(int size) {
        this.heapSize = size;
        heap = new int[size+1];
    }
    private void add(int num) {
        realSize++;
        if(realSize > heapSize) {
            System.out.println("Heap is already full. Delete elements to add new.");
            realSize--;
            return;
        }
        int index = realSize;
        int parent = index/2;
        heap[index] = num;
        while (heap[parent] > heap[index] && index > 1) {
            // swap parent and index
            swap(heap, parent, index);
            index = parent;
            parent = index / 2;
        }
    }

    private int pop() {
        if(realSize < 1) {
            System.out.println("No elements present in the heap to delete.");
            return Integer.MAX_VALUE;
        }
        int removeElement = heap[1];
        heap[1] = heap[realSize];
        realSize--;
        int index = 1;
        // if deleted element is not a leaf node
        while(index <= realSize / 2) {
            int left = index * 2;
            int right = index * 2 + 1;
            // if deleted element is larger than left or right child of the node, we swap them
            if(heap[index] > heap[left] || heap[index] > heap[right]) {
                if(heap[left]  < heap[right]) {
                    swap(heap, left, index);
                    index = left;
                }
                else {
                    swap(heap, right, index);
                    index = right;
                }
            }
            else {
                break;
            }
        }
        return removeElement;
    }

    private int peek() {
        return heap[1];
    }

    private void swap(int [] heap, int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    @Override
    public String toString() {
        if(realSize == 0) {
            return "No element";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 1; i <= realSize; i++) {
            sb.append(heap[i]);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test case
        MinHeap minHeap = new MinHeap(3);
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(2);
        // [1,3,2]
        System.out.println(minHeap);
        // 1
        System.out.println(minHeap.peek());
        // 1
        System.out.println(minHeap.pop());
        // [2, 3]
        System.out.println(minHeap);
        minHeap.add(4);
        // Add too many elements
        minHeap.add(5);
        // [2,3,4]
        System.out.println(minHeap);
        System.out.println(minHeap.pop());
        System.out.println(minHeap);
        System.out.println(minHeap.pop());
        System.out.println(minHeap.pop());
        System.out.println(minHeap.pop());
    }
}
