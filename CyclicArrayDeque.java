package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Item[] elementData = (Item[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void pushFront(Item item) {
        grow();
        size++;
        if (head==0)
        {
            head = elementData.length-1;
        }
        elementData[(head--)%elementData.length] = item;
    }

    @Override
    public void pushBack(Item item) {
        grow();
        size++;
        elementData[(tail++)%elementData.length] = item;
    }

    @Override
    public Item popFront() {
        if (size!=0) {
            shrink();
            size--;
            return elementData[(++head) % elementData.length];
        }
        return null;
    }

    @Override
    public Item popBack() {
        if (size!=0)
        {
            shrink();
            size--;
            return elementData[(--tail)%elementData.length];
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
        if(size==elementData.length){
            copy(true);
        }
    }

    private void shrink() {
        if (elementData.length>DEFAULT_CAPACITY && size << 2 <= elementData.length) {
           // copy(false);
           /*
            Программа падает при вызове этого метода... Я не понимаю, как можно это все реализовать.
             */
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
            for (int i = head; i < elementData.length; i++) {
                tempArr[Math.abs(tempArr.length-elementData.length+(i-head))] = elementData[i];
            }
            for (int i=0;i<tail%elementData.length;i++)
            {
                tempArr[tempArr.length - head + i] = elementData[i];
        }
        head = tempArr.length-elementData.length-1;
        tail = 0 ;
        elementData = tempArr;
    }
    @Override
    public Iterator<Item> iterator() {
        return new CyclicDequeIterator();
    }
    private class CyclicDequeIterator implements  Iterator<Item>{

        @Override
        public boolean hasNext() {
            if (size!=0){
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            return popFront();
        }
    }
    public static void main(String[] args) {
        CyclicArrayDeque myQ = new CyclicArrayDeque();
        myQ.pushBack(29);
        myQ.pushBack(29);
        myQ.pushBack(29);
        myQ.pushFront(-1);
        myQ.popFront();
        myQ.popFront();
        for (int i=0;i<10;i++)
        {
            myQ.pushBack(i);
        }
        for (int i=0;i<10;i++)
        {
            System.out.println(myQ.popFront());
        }
        System.out.println(myQ.popFront());
        System.out.println(myQ.size());
    }
}
