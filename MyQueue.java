/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class MyQueue {
    int count = 0;
    // not sure what monitor is for
    Object monitor;
    Node head;

    public void add(Object o) {
        //checks if object is null
        if (o != null) {
            Node n = new Node(o);
            //checks if head is null, if yes, assigns Node n to head position
            if (head == null) {
                this.head = n;
                head.setNext(null);
                count += 1;
            }
            else {
                //checks if head is only entry in queue
                if (head.getNext() != null) {
                    Node next = head.getNext();
                    boolean status = true;
                    // loops until next Node in queue is null, then assigns Node n to end of queue
                    while (status) {
                        if (next != null) {
                            if (next.getNext() != null) {
                                next = next.getNext();
                            }
                            else {
                                next.setNext(n);
                                n.setPrev(next);
                                n.setNext(null);
                                count += 1;
                                status = false;
                            }
                        }
                        else {
                            next.setNext(n);
                            n.setPrev(next);
                            n.setNext(null);
                            count += 1;
                            status = false;
                        }
                    }
                }
                // if head only entry, sets Node n to next entry in list
                else {
                    head.setNext(n);
                    n.setPrev(head);
                    n.setNext(null);
                    count += 1;
                }
            }
        }
    }
    public Node remove() {
        if (head == null) {
            return null;
        }
        else {
            //takes Node right after head and makes that the new head, then updates previous Node to null
            if (head.getNext() != null) {
                Node newHead = head.getNext();
                Node returnHead = head;
                head = newHead;
                head.setPrev(null);
                count -= 1;
                return returnHead;
            }
            else {
                //if head only entry, returns head and makes head null
                Node returnHead = head;
                head = null;
                count -= 1;
                return returnHead;
            }
        }
    }
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public Node peek() {
        if (head != null) {
            return head;
        }
        else {
            return null;
        }
    }
    public int size() {
        return count;
    }


}