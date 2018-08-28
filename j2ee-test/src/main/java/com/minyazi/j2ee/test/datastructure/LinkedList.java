package com.minyazi.j2ee.test.datastructure;

/**
 * 线性表：链式存储结构
 * 
 * @author minyazi
 */
public class LinkedList<E extends Comparable<E>> implements List<E> {
    private Node<E> headPointer; // 头指针
    private Node<E> tailPointer; // 尾指针
    private int size; // 线性表的大小
    
    public LinkedList() {
        initList();
    }
    
    /**
     * 初始化线性表
     */
    private void initList() {
        headPointer = null;
        tailPointer = null;
        size = 0;
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public void addFromHead(E element) {
        add(0, element);
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public void addFromTail(E element) {
        add(size, element);
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = new Node<E>(element);
        if (index == 0) { // 在线性表的首部插入元素
            if (size != 0) {
                node.setNext(headPointer);
            }
            headPointer = node;
        } else if (index == size) { // 在线性表的尾部插入元素
            tailPointer.setNext(node);
        } else { // 在线性表的其他位置插入元素
            Node<E> tempNode = headPointer;
            for (int i = 1; i < index; i++) {
                tempNode = tempNode.getNext();
            }
            node.setNext(tempNode.getNext());
            tempNode.setNext(node);
        }
        if (node.getNext() == null) {
            tailPointer = node;
        }
        size++; // 线性表的大小加1
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public void clear() {
        Node<E> node = headPointer;
        Node<E> tempNode = null;
        while (node != null) {
            tempNode = node.getNext();
            node.setNext(null);
            node = tempNode;
        }
        
        // 重新初始化线性表
        initList();
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = headPointer;
        for (int i = 1; i<= index; i++) {
            node = node.getNext();
        }
        return node.getData();
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = headPointer;
        if (index == 0) { // 在线性表的首部移除元素
            headPointer = node.getNext();
            node.setNext(null);
            if (headPointer == null) {
                tailPointer = null;
            }
        } else { // 在线性表的其他位置移除元素
            Node<E> tempNode = headPointer;
            for (int i = 1; i < index; i++) {
                tempNode = tempNode.getNext();
            }
            node = tempNode.getNext();
            tempNode.setNext(node.getNext());
            node.setNext(null);
            if (tempNode.getNext() == null) {
                tailPointer = tempNode;
            }
        }
        size--; // 线性表的大小减1
        return node.getData();
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = headPointer;
        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }
        E tempElement = node.getData();
        node.setData(element);
        return tempElement;
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
    
    /*
     * 时间复杂度：O(n^2)
     */
    @Override
    public void union(List<E> list) {
        E tempElement = null;
        int tempSize = list.size();
        for (int i = 0; i < tempSize; i++) {
            tempElement = list.get(i);
            if (search(tempElement) == -1) {
                addFromTail(tempElement);
            }
        }
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(500);
        result.append("[");
        Node<E> node = headPointer;
        while (node != null) {
            result.append(String.valueOf(node.getData()));
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
        list.remove(5);
        System.out.println(list.toString());
        System.out.println(list.set(5, "a"));
        System.out.println(list.toString());
    }
}
