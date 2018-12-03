/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */
public class Node {
    private Object data;
    private Node next;
    private Node prev;

    public Node(Object obj) {
        this.data = obj;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Node getNext() {
        return this.next;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public Node getPrev() {
        return this.prev;
    }
    public Object getData() {
        return this.data;
    }

}