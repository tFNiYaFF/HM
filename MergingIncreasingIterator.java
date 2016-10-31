package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {
    private int[] Data;
    private int[] Queue;
    private int size;
    private int head;
    private int tail;
    private IncreasingIterator first;
    private IncreasingIterator second;
    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        Data = new int[first.stepLimit+second.stepLimit];
        Queue = new int[first.stepLimit+second.stepLimit];
        int check = 0;
        while (first.hasNext() || second.hasNext()){
            int a = 0;
            int b = 0;
            boolean flag1 = false;
            boolean flag2 = false;
            if (first.hasNext()){
                a = first.next();
                System.out.println("a: " + a);
                flag1 = true;
            }
            if (second.hasNext()){
                b = second.next();
                System.out.println("b: " + b);
                flag2 = true;
            }
            if (flag1&&flag2){
                if (a<b){
                    if (a>=Queue[head]){
                        dropQueue(a);
                    }
                    check = 1;
                    Data[size++] = a;
                    Queue[tail++] = b;
                }
                else{
                    if (a>b){
                        if (b>=Queue[head]){
                            dropQueue(b);
                        }
                        check = 2;
                        Data[size++] = b;
                        Queue[tail++] = a;
                    }
                    else{
                        dropQueue(a);
                        check = 0;
                        Data[size++] = a;
                        Data[size++] = b;
                    }
                }
                continue;
            }
            if (flag1){
                dropQueue(a);
                Data[size++] = a;
                continue;
            }
            if (flag2){
                dropQueue(b);
                Data[size++] = b;
                continue;
            }
            else{
                break;
            }
        }
        dropQueue(Queue[tail-1]);
    }
    private void dropQueue(int a){
        while (Queue[head]<=a && tail!=head){
            Data[size++] = Queue[head++];
        }
    }

    @Override
    public boolean hasNext() {
        if (size!=0){
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return Data.length-size--;
    }

    public static void main(String[] args) {
        IncreasingIterator f = new IncreasingIterator(1,20,3);
        IncreasingIterator s = new IncreasingIterator(1,20,5);
        MergingIncreasingIterator it = new MergingIncreasingIterator(f,s);
        System.out.println();
    }
}
