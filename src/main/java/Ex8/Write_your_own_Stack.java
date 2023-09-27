package Ex8;


import java.util.LinkedList;

public class Write_your_own_Stack<T> {
    private LinkedList<T> stackList = new LinkedList<>();

    // Push an element onto the stack
    public void push(T element) {
        stackList.addFirst(element);
    }

    // Pop and return the top element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.removeFirst();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    // Get the size of the stack
    public int size() {
        return stackList.size();
    }

    public static void main(String[] args) {
        // Create a stack of integers
        Write_your_own_Stack<Integer> intStack = new Write_your_own_Stack<>();

        // Push some integers onto the stack
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        // Pop and print elements from the stack
        while (!intStack.isEmpty()) {
            System.out.println("Popped: " + intStack.pop());
        }

        // Create a stack of strings
        Write_your_own_Stack<String> stringStack = new Write_your_own_Stack<>();

        // Push some strings onto the stack
        stringStack.push("Hello");
        stringStack.push("World");

        // Pop and print elements from the stack
        while (!stringStack.isEmpty()) {
            System.out.println("Popped: " + stringStack.pop());
        }
    }
}

//public class Write_your_own_Stack {
//
//    }
//}
