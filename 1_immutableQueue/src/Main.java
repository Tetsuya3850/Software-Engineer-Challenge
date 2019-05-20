
public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = ImmutableQueue.getEmptyQueue();
        try {
            System.out.println(q.isEmpty());
            q = q.enQueue(5);
            System.out.println(q.isEmpty());
            q = q.enQueue(10);
            q = q.enQueue(20);
            System.out.println(q.head());
            q = q.deQueue();
            System.out.println(q.head());
            q = q.deQueue();
            System.out.println(q.head());
            System.out.println(q.isEmpty());
            q = q.deQueue();
            System.out.println(q.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
