package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        if (size==0)
        {
            head = new Node<Item>(item,null);
            tail = head;
            size++;
        }
        else
        {
            tail.next = new Node<Item>(item,null);
            tail = tail.next;
            size++;
        }
    }
    @Override
    public Item dequeue() {
        if (size!=0)
        {
            Item result = head.item;
            head = head.next;
            size--;
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

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            if (head!=null){
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
                return dequeue();
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        LinkedQueue myQ = new LinkedQueue();
        for (int i=0;i<20;i++)
        {
            myQ.enqueue(i);
        }
        for (int i=0;i<20;i++) {
            System.out.println(myQ.dequeue());
        }
        myQ.enqueue(100);
        System.out.println(myQ.dequeue());
        System.out.println(myQ.size());
    }
}
