package seminar1.collections;

import javax.xml.soap.Node;
import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {
    private int size = 0;
    private Node<Item> head;
    private Node<Item> tail;
    @Override
    public void pushFront(Item item) {
        if (size==0)
        {
            head = new Node<Item>(item,null,null);
            tail = head;
            size++;
        }
        else
        {
            head = new Node<Item>(item,head,null);
            size++;
        }
    }

    @Override
    public void pushBack(Item item) {
        if (size==0)
        {
            head = new Node<Item>(item,null,null);
            tail = head;
            size++;
        }
        else
        {
            tail.next = new Node<Item>(item,null,tail);
            tail = tail.next;
            size++;
        }
    }

    @Override
    public Item popFront() {
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
    public Item popBack() {
        if (size!=0)
        {
            Item result = tail.item;
            tail = tail.prev;
            size--;
            return result;

        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if (size!=0)
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
    private static class Node<Item>
    {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
        public Node (Item item, Node<Item> next, Node<Item> prev)
        {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    @Override
    public Iterator<Item> iterator() {
        return new LinkedDequeIterator();
    }
    private class LinkedDequeIterator implements Iterator<Item>{

        @Override
        public boolean hasNext() {
            if (head!=null){
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
        LinkedDeque myQ = new LinkedDeque();
        for (int i=0;i<20;i++)
        {
            myQ.pushBack(i);
        }
        for (int i=0;i<20;i++) {
            System.out.println(myQ.popBack());
        }
        myQ.pushFront(100);
        myQ.pushBack(101);
        System.out.println(myQ.popFront());
        System.out.println(myQ.size());
    }
}
