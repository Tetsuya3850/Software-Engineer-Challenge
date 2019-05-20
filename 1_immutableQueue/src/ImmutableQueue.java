
// Since a queue is effectively a reverse stack, it can be implemented using 2 stacks.
public final class ImmutableQueue<T> implements Queue<T>{

    private final Stack<T> backwards;
    private final Stack<T> forwards;

    // Private constructor used from inner methods.
    // Uses two immutable stacks, backwards and forwards, to keep track of the items being enQueued and deQueued, respectively.
    private ImmutableQueue(Stack<T> forwards, Stack<T> backwards){
        this.forwards = forwards;
        this.backwards = backwards;
    }

    // Reverses the provided stack. Runs in O(N) time.
    public static Stack reverseStack(Stack stack) throws Exception {
        Stack r = ImmutableStack.getEmptyStack();
        while(!stack.isEmpty()){
            r = r.push(stack.head());
            stack = stack.pop();
        }
        return r;
    }

    // Returns singleton inner class, EmptyQueue.
    public static Queue getEmptyQueue(){
        return EmptyQueue.getInstance();
    }

    // Pushes the new item to the backwards and creates a new queue using the existing forwards stack and the new backwards stack.
    // Runs in O(1) time.
    public final Queue<T> enQueue(T t){
        return new ImmutableQueue<T>(forwards, backwards.push(t));
    }

    // Amortized runtime O(1).
    public final Queue<T> deQueue() throws Exception{
        // First pops from the forwards stack.
        Stack<T> f = forwards.pop();

        // If the resulting stack of the pop() is the not the empty stack,
        // returns a new queue with the resulting stack as forwards and the existing backwards as backwards.
        if (!f.isEmpty()){
            return new ImmutableQueue<T>(f, backwards);
        }
        // If the backwards stack is empty as well, then we have an empty queue.
        // So it returns the empty singleton queue.
        else if (backwards.isEmpty()){
            return ImmutableQueue.getEmptyQueue();
        }
        // Else, since there was only one element left in the forward stack, overwrite the element by
        // reversing the backwards stack and setting as forwards.
        // Worst case runtime O(N).
        else {
            return new ImmutableQueue<T>(ImmutableQueue.reverseStack(backwards), ImmutableStack.getEmptyStack());
        }
    }

    // Returns the head element of the forwards stack. Runs in O(1) time.
    public final T head() throws Exception{
        return forwards.head();
    }

    // Always false. Runs in O(1) time.
    public final boolean isEmpty(){
        return false;
    }

    // Singleton
    private static final class EmptyQueue<T> implements Queue<T>{

        private final static EmptyQueue emptyQueue = new EmptyQueue();

        public final static EmptyQueue getInstance(){
            return emptyQueue;
        }

        // Item is pushed to the forwards stack, since otherwise deQueue will always fail.
        // Runs in O(1) time.
        public final Queue<T> enQueue(T t){
            return new ImmutableQueue<T>(ImmutableStack.getEmptyStack().push(t), ImmutableStack.getEmptyStack());
        }

        // Throws exception. Runs in O(1) time.
        public final Queue<T> deQueue() throws Exception{
            throw new Exception("Empty queue.");
        }

        // Throws exception. Runs in O(1) time.
        public final T head() throws Exception{
            throw new Exception("Empty queue.");
        }

        // Always true. Runs in O(1) time.
        public final boolean isEmpty(){
            return true;
        }
    }
}
