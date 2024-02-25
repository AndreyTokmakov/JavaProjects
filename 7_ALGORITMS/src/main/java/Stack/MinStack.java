package Stack;


import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.Comparator;
import java.util.Stack;

/*
class MinStackImpl<T extends Number & Comparable<T>>
{
    private final Stack<T> stack = new Stack<T>();
    private final Stack<T> minStack = new Stack<T>();

    public void push(T value) {
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }
}

MinStackImpl<Integer> minStack = new MinStackImpl<Integer>();

*/

class MinStackImpl
{
    private final Stack<Integer> stack = new Stack<Integer>();
    private final Stack<Integer> minStack = new Stack<Integer>();

    public MinStackImpl push(Integer value) {
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
        return this;
    }

    public void pop()
    {
        if (!stack.isEmpty()) {
            final int popped = stack.pop();
            if (popped == minStack.peek())
                minStack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        throw new IllegalStateException("Stack is empty");
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        throw new IllegalStateException("Stack is empty");
    }
}


public class MinStack
{
    public static void main(String[] args)
    {
        MinStackImpl minStack = new MinStackImpl();

        /*
        minStack.push(10);
        minStack.push(5);
        minStack.push(7);
        minStack.push(8);

        System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());

        minStack.pop();
        System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());

        minStack.pop();
        System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());

        minStack.pop();
        System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());
        */

        minStack.push(10).push(12).push(6).push(4).push(5);

        for (int i = 0; i < 3; ++i) {
            System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());
            minStack.pop();
        }

        minStack.push(1).push(3);


        for (int i = 0; i < 2; ++i) {
            System.out.println("Min = " + minStack.getMin() + ", Top = " + minStack.top());
            minStack.pop();
        }
    }
}
