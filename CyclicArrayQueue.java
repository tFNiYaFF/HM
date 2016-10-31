package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    @Override
    public void enqueue(Item item) {
        grow();
        size++;
        elementData[(tail++)%elementData.length] = item;
    }

    @Override
    public Item dequeue() {
        if (size!=0)
        {
            shrink();
            size--;
            return elementData[(head++)%elementData.length];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if (size==0)
        {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if (size!=0)
        {
            return size;
        }
        return 0;
    }

    private void grow() {
        if (size==elementData.length)
        {
            copy(true);
        }
    }

    private void shrink() {
        if ((size>DEFAULT_CAPACITY)&&(size << 2 <=elementData.length))
        {
            copy(false);
        }
    }
    private void copy(boolean mode){
        Item[] tempArr;
        if (mode) {
            tempArr = (Item[])new Object[(int)(elementData.length*1.5)];
        }
        else{
            tempArr = (Item[])new Object[(elementData.length >> 1)];
        }
        if (head>(tail-1)%elementData.length) {
            for (int i = head; i < elementData.length; i++) {
                tempArr[i - head] = elementData[i];
            }
            for (int i=0;i<tail%elementData.length;i++)
            {
                tempArr[i+elementData.length-head] = elementData[i];
            }
        }
        head = 0;
        tail = size;
        elementData = tempArr;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayQueueIterator();
    }
    private class CyclicArrayQueueIterator implements Iterator<Item>{

        @Override
        public boolean hasNext() {
            if (size!=0){
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            return dequeue();
        }
    }
    public static void main(String[] args) {
        CyclicArrayQueue myQ = new CyclicArrayQueue();
        myQ.enqueue(29);
        myQ.enqueue(29);
        myQ.enqueue(29);
        myQ.dequeue();
        myQ.dequeue();
        for (int i=0;i<10;i++)
        {
            myQ.enqueue(i);
        }
        for (int i=0;i<10;i++)
        {
            System.out.println(myQ.dequeue());
        }
        System.out.println(myQ.dequeue());
        System.out.println(myQ.size());
    }
}
