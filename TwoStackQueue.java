package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        stack1 = new ArrayStack<Item>();
        stack2 = new ArrayStack<Item>();
    }

    @Override
    public void enqueue(Item item) {
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }
        if (!stack2.isEmpty())
        {
            return stack2.pop();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if ((!stack1.isEmpty()) || (!stack2.isEmpty()))
        {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        if (!isEmpty()){
            return (stack2.size()+stack1.size());
        }
        return 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new TwoStackQueueIterator();
    }
    private class TwoStackQueueIterator implements Iterator<Item>{

        @Override
        public boolean hasNext() {
            if (!isEmpty()){
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
        TwoStackQueue myStack = new TwoStackQueue();
        for (int i=0;i<20;i++)
        {
            myStack.enqueue(i);
        }
        for (int i=0;i<20;i++) {
            System.out.println(myStack.dequeue());
        }
        myStack.enqueue(100);
        myStack.enqueue(123);
        System.out.println(myStack.dequeue());
        System.out.println(myStack.size());
    }
}
