package seminar1.collections;
import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
       if (size==0)
       {
           head = new Node<Item>(item,null);
           size++;
       }
       else {
           head = new Node<Item>(item,head);
           size++;
       }
    }

    @Override
    public Item pop() {
        if (size!=0)
        {
            size--;
            Item result = head.item;
            head = head.next;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            if (head!=null)
            {
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            return pop();
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        LinkedStack myStack = new LinkedStack();
        for (int i=0;i<20;i++)
        {
            myStack.push(i);
        }
        for (int i=0;i<20;i++) {
            System.out.println(myStack.pop());
        }
        myStack.push(100);
        System.out.println(myStack.pop());
        System.out.println(myStack.size());
    }
}
