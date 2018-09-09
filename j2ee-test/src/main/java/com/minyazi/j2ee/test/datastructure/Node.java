package com.minyazi.j2ee.test.datastructure;

/**
 * 结点
 * 
 * @author minyazi
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T data; // 数据域
    private Node<T> next; // 指针域（用于实现动态链表）
    private int cursor; // 游标（用于实现静态链表）
    
    public Node() {}
    
    public Node(T data) {
        this.data = data;
    }
    
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    public Node(T data, int cursor) {
        this.data = data;
        this.cursor = cursor;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    public int getCursor() {
        return cursor;
    }
    
    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof Node<?>)) {
            return false;
        }
        
        Node<?> other = (Node<?>) obj;
        return other.getData() == null ? false : other.getData().equals(data);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode * 31 + (data == null ? 0 : data.hashCode());
        return hashCode;
    }
    
    @Override
    public String toString() {
        return data == null ? "" : data.toString();
    }
    
    @Override
    public int compareTo(Node<T> other) {
        return data.compareTo(other.getData());
    }
}
