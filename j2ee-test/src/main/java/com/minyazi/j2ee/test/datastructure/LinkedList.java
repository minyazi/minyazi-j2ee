package com.minyazi.j2ee.test.datastructure;


/**
 * 线性表：链式存储结构
 * 
 * @author minyazi
 */
public class LinkedList<E extends Comparable<E>> implements List<E> {
    private Node<E> rootNode; // 根结点
    private int size; // 线性表的大小
    
    public LinkedList() {
        rootNode = null;
        size = 0;
    }
    
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = new Node<E>(element);
        if (index == 0) { // 在线性表的首位插入元素
            if (size != 0) {
                node.setNext(rootNode);
            }
            rootNode = node;
        } else { // 在线性表的其他位置插入元素
            Node<E> tempNode = rootNode;
            for (int i = 1; i < index; i++) {
                tempNode = tempNode.getNext();
            }
            node.setNext(tempNode.getNext());
            tempNode.setNext(node);
        }
        size++; // 线性表的大小加1
    }
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = rootNode;
        for (int i = 1; i<= index; i++) {
            node = node.getNext();
        }
        return node.getData();
    }
    
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        return null;
    }
    
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        return null;
    }
    
    @Override
    public List<E> sort() {
        return null;
    }
    
    @Override
    public int search(E element) {
        return 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(500);
        result.append("[");
        Node<E> node = rootNode;
        while (node != null) {
            result.append(String.valueOf(node));
            node = node.getNext();
            if (node != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < 20; i++) {
            list.add(i, i + "");
        }
        System.out.println(list.toString());
    }
}
