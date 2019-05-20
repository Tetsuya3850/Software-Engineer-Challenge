
public final class ImmutableStack<T> implements Stack<T> {

    private final T head;
    private final Stack<T> prev;

    // Private constructor used from inner methods.
    // Holds a head element and a prev that is another immutable stack.
    private ImmutableStack(T head, Stack<T> prev){
        this.head = head;
        this.prev = prev;
    }

    // Returns singleton inner class, EmptyStack.
    public static Stack getEmptyStack(){
        return EmptyStack.getInstance();
    }

    // Creates a new stack using the existing stack as prev and the new element as head. Runs in O(1) time.
    public final Stack<T> push(T t){
        return new ImmutableStack<T>(t, this);
    }

    // Returns the previous stack, excluding the head element. Runs in O(1) time.
    public final Stack<T> pop(){
        return prev;
    }

    // Returns the head element. Runs in O(1) time.
    public final T head(){
        return head;
    }

    // Always false. Runs in O(1) time.
    public final boolean isEmpty(){
        return false;
    }

    // Singleton
    private static final class EmptyStack<T> implements Stack<T> {

        private final static EmptyStack emptyStack = new EmptyStack();

        public final static EmptyStack getInstance(){
            return emptyStack;
        }

        // Creates a new stack using the empty stack as prev and the new element as head. Runs in O(1) time.
        public final Stack<T> push(T t){
            return new ImmutableStack<T>(t, this);
        }

        // Throws exception. Runs in O(1) time.
        public final Stack<T> pop() throws Exception{
            throw new Exception("Empty stack.");
        }

        // Throws exception. Runs in O(1) time.
        public final T head() throws Exception{
            throw new Exception("Empty stack.");
        }

        // Always true. Runs in O(1) time.
        public final boolean isEmpty(){
            return true;
        }
    }
}
