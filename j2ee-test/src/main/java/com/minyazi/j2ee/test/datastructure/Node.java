package com.minyazi.j2ee.test.datastructure;

/**
 * 结点
 * 
 * @author minyazi
 */
public class Node {
    private String data; // 数据域
    private Node next; // 指针域
    
    public Node(){}
    
    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public Node getNext() {
        return next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
}
