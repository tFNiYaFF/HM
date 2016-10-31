package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(Item item) {
        grow();
        elementData[size++] = item;
    }

    @Override
    public Item pop() {
        if (size!=0){
            Item result = elementData[--size];
            shrink();
            return result;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        if (size==elementData.length)
        {
            changeCapacity((int)(size*1.5));
        }
    }

    private void shrink() {
        if ((size << 2<=elementData.length) && (elementData.length>10))
        {
            changeCapacity(elementData.length >> 1);
        }
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }

    public static void main(String[] args) {
        ArrayStack myStack = new ArrayStack();
        for (int i=0;i<20;i++)
        {
            myStack.push(i);
        }
        for (int i=0;i<20;i++) {
            System.out.println(myStack.pop());
        }
        myStack.push(100);
        myStack.push(100);
        System.out.println(myStack.pop());
        System.out.println(myStack.size());
    }
}
