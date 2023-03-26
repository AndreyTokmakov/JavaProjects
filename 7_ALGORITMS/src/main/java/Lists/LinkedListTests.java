package Lists;

class LinkedList {
	private Node<String> head;
    private Node<String> tail;
 
    public LinkedList(){
        this.head = new Node<String>("HEAD");
        tail = head;
    }
 
    public Node head(){
        return head;
    }
 
    public void add(Node node){
        tail.next = node;
        tail = node;
    }
 
    public static final class Node<T> {
        private Node next;
        private T data;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
     
        public T data() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString(){
            return this.data.toString();
        }
    }
}

public class LinkedListTests {
	
	public static void FindMiddleElement() {
		LinkedList linkedList = new LinkedList();
		LinkedList.Node head = linkedList.head();
		
		for (int i = 0; i< 100; i++) {
			linkedList.add( new LinkedList.Node(String.valueOf(i)));
		}
		
		int len = 0;
		LinkedList.Node current = head, middle = head;
		while (null != current.next()) {
			len++;
			if (0 == len %2)
				middle = middle.next();
			current = current.next();
		}
		System.out.println(middle.data());
	}

	public static void Print_List() {
        LinkedList linkedList = new LinkedList();

        for (int i = 0; i< 100; i++)
            linkedList.add( new LinkedList.Node("Data_".concat(String.valueOf(i))));

        LinkedList.Node node = linkedList.head();

        while (null != node) {
            System.out.println(node.data());
            node = node.next();
        }
    }
	
	public static void main(String[] args)
    {
		// FindMiddleElement();

        // Print_List();

        int [] numbers = new int[5];
        for (int i: numbers) {
            System.out.println(i);
        }
	}
}
