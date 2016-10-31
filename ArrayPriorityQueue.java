package seminar1.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Key[] elementData = (Key[]) new Object[DEFAULT_CAPACITY];
    private Comparator<Key> comparator;

    public ArrayPriorityQueue() {
        /* TODO: implement it — O(n) */
    }

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        /* TODO: implement it — O(n) */
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        grow();
        elementData[size++] = key;
        siftUp();
    }

    @Override
    public Key peek() {
        if (size!=0){
            return elementData[0];
        }
        return null;
    }

    @Override
    public Key extractMin() {
        if (size!=0){
            Key result = elementData[0];
            size--;
            elementData[0] = elementData[size];
            siftDown();
            shrink();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp() {
        int index = size-1;
        while(true){
            int parent = (index-1)/2;
            if (comparator.compare(elementData[parent],elementData[index])==1){
                Key temp = elementData[parent];
                elementData[parent] = elementData[index];
                elementData[index] = temp;
            }
            else{
                break;
            }
            index = parent;
        }
    }

    private void siftDown() {
        int index = 0;
        while (true){
            int leftChild = index*2+1;
            int rightChild = index*2+2;
            int min = index;
            if (leftChild<size && comparator.compare(elementData[index],elementData[leftChild]) == 1){
                min = leftChild;
            }
            if (rightChild<size && comparator.compare(elementData[index],elementData[rightChild]) == 1){
                min = rightChild;
            }
            if (min==index){
                break;
            }
            Key temp = elementData[min];
            elementData[min] = elementData[index];
            elementData[index] = temp;
            index = min;
        }
    }

    private void grow() {
        if (size==elementData.length){
            elementData = Arrays.copyOf(elementData, (int)(elementData.length*1.5));
        }
    }

    private void shrink() {
        if (size << 2 <= elementData.length && elementData.length>DEFAULT_CAPACITY){
            elementData = Arrays.copyOf(elementData,elementData.length >> 1);
        }
    }

    private boolean greater(int i, int j) {
        return comparator == null
                ? elementData[i].compareTo(elementData[j]) > 0
                : comparator.compare(elementData[i], elementData[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        return new PQueueIterator();
    }
    private class PQueueIterator implements Iterator<Key>{

        @Override
        public boolean hasNext() {
            if (size!=0){
                return true;
            }
            return false;
        }

        @Override
        public Key next() {
            return extractMin();
        }
    }
}
