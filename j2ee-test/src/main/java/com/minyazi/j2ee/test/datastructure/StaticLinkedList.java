package com.minyazi.j2ee.test.datastructure;

import java.util.Arrays;

/**
 * 线性表：链式存储结构（静态链表）
 * 
 * @author minyazi
 */
public class StaticLinkedList<E extends Comparable<E>> implements List<E> {
    private int initialCapacity = 10; // 初始容量
    private int addCapacityCount; // 扩容次数
    private Node<E>[] elements;
    private int size; // 线性表的大小
    
    public StaticLinkedList() {
        initList();
    }
    
    /**
     * 获取线性表的扩容次数
     * 
     * @return 线性表的扩容次数
     */
    public int getAddCapacityCount() {
        return addCapacityCount;
    }
    
    /**
     * 初始化线性表
     * <p>
     * <B>PS</B>：数组的第一个和最后一个元素不存放数据，第一个元素的游标指向备用链表（未使用的数组元素）的第一个结点的下标，
     * 最后一个元素的游标指向第一个存放数据的结点的下标。
     * <p>
     * 另外，备用链表的最后一个结点的游标指向数组的最后一个元素，最后一个存放数据的结点的游标指向数组的第一个元素。
     */
    @SuppressWarnings("unchecked")
    private void initList() {
        elements = new Node[initialCapacity];
        size = 0;
        // 建立备用链表的游标链路
        for (int i = 0; i < initialCapacity; i++) {
            elements[i] = new Node<E>();
            if (i != initialCapacity - 1) {
                elements[i].setCursor(i + 1);
            } else {
                elements[i].setCursor(0);
            }
        }
    }
    
    /**
     * 从备用链表中取得一个结点
     * <p>
     * 时间复杂度：O(1)
     * 
     * @return 所取得的结点在数组中的下标
     */
    private int malloc() {
        int index = elements[0].getCursor();
        if (index == elements.length - 1) { // 扩容
            int tempIndex = elements[index].getCursor();
            elements = Arrays.copyOf(elements, size * 2);
            addCapacityCount++;
            int capacity = elements.length;
            // 建立备用链表的游标链路
            for (int i = index; i < capacity; i++) {
                if (i != index) {
                    elements[i] = new Node<E>();
                }
                if (i != capacity - 1) {
                    elements[i].setCursor(i + 1);
                } else {
                    elements[i].setCursor(tempIndex);
                }
            }
        }
        elements[0].setCursor(elements[index].getCursor());
        return index;
    }
    
    /**
     * 添加一个结点到备用链表中
     * <p>
     * 时间复杂度：O(1)
     * 
     * @param index 所添加的结点在数组中的下标
     */
    private void free(int index) {
        elements[index].setData(null);
        elements[index].setCursor(elements[0].getCursor());
        elements[0].setCursor(index);
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public void addFromHead(E element) {
        add(0, element);
    }
    
    /*
     * 时间复杂度：O(n)
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
        int tempIndex = malloc();
        Node<E> node = elements[tempIndex];
        node.setData(element);
        if (index == 0) { // 在线性表的首部插入元素
            node.setCursor(elements[elements.length - 1].getCursor());
            elements[elements.length - 1].setCursor(tempIndex);
        } else { // 在线性表的其他位置插入元素
            Node<E> tempNode = elements[elements[elements.length - 1].getCursor()];
            for (int i = 1; i < index; i++) {
                tempNode = elements[tempNode.getCursor()];
            }
            node.setCursor(tempNode.getCursor());
            tempNode.setCursor(tempIndex);
        }
        size++; // 线性表的大小加1
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public void clear() {
        int index = elements[elements.length - 1].getCursor();
        int tempIndex = 0;
        while (index != 0) {
            tempIndex = elements[index].getCursor();
            free(index);
            index = tempIndex;
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
        int tempIndex = elements[elements.length - 1].getCursor();
        for (int i = 1; i <= index; i++) {
            tempIndex = elements[tempIndex].getCursor();
        }
        return elements[tempIndex].getData();
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
        int tempIndex = elements[elements.length - 1].getCursor();
        if (index == 0) { // 在线性表的首部移除元素
            elements[elements.length - 1].setCursor(elements[tempIndex].getCursor());
        } else { // 在线性表的其他位置移除元素
            Node<E> tempNode = elements[tempIndex];
            for (int i = 1; i < index; i++) {
                tempNode = elements[tempNode.getCursor()];
            }
            tempIndex = tempNode.getCursor();
            tempNode.setCursor(elements[tempIndex].getCursor());
        }
        E data = elements[tempIndex].getData();
        free(tempIndex);
        size--; // 线性表的大小减1
        return data;
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        int tempIndex = elements[elements.length - 1].getCursor();
        for (int i = 1; i <= index; i++) {
            tempIndex = elements[tempIndex].getCursor();
        }
        E data = elements[tempIndex].getData();
        elements[tempIndex].setData(element);
        return data;
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
    public void union(List<E> list) {
        E data = null;
        int tempSize = list.size();
        for (int i = 0; i < tempSize; i++) {
            data = list.get(i);
            if (search(data) == -1) {
                addFromTail(data);
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
        if (size > 0) {
            Node<E> node = elements[elements.length - 1];
            while (true) {
                node = elements[node.getCursor()];
                result.append(String.valueOf(node.getData()));
                if (node.getCursor() != 0) {
                    result.append(", ");
                } else {
                    break;
                }
            }
        }
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        StaticLinkedList<String> list = new StaticLinkedList<String>();
        for (int i = 0; i < 20; i++) {
            list.add(i, i + "");
        }
        System.out.println(list.toString());
        System.out.println(list.remove(5));
        System.out.println(list.toString());
        System.out.println(list.set(5, "a"));
        System.out.println(list.toString());
        System.out.println("扩容次数：" + list.getAddCapacityCount());
    }
}
