package com.minyazi.j2ee.test.datastructure;

import java.util.Arrays;

/**
 * 线性表：顺序存储结构
 * 
 * @author minyazi
 */
public class ArrayList<E extends Comparable<E>> implements List<E> {
    private int initialCapacity = 10; // 初始容量
    private int addCapacityCount; // 扩容次数
    private Node<E>[] elements;
    private int size; // 线性表的大小
    
    public ArrayList() {
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
     */
    @SuppressWarnings("unchecked")
    private void initList() {
        elements = new Node[initialCapacity];
        size = 0;
    }
    
    /*
     * 时间复杂度：O(n)
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
        // 为了便于理解，此处按插入元素的位置分开处理
        if (index == size) { // 在线性表的尾部插入元素
            elements[index] = node;
        } else { // 在线性表的其他位置插入元素
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = node;
        }
        size++; // 线性表的大小加1
        if (size == elements.length) { // 扩容
            elements = Arrays.copyOf(elements, size * 2);
            addCapacityCount++;
        }
    }
    
    /*
     * 时间复杂度：O(n)
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i].setData(null);
            elements[i] = null;
        }
        
        // 重新初始化线性表
        initList();
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return elements[index].getData();
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
        Node<E> node = elements[index];
        // 为了便于理解，此处按移除元素的位置分开处理
        if (index == size - 1) { // 在线性表的尾部移除元素
            elements[index] = null;
        } else { // 在线性表的其他位置移除元素
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
        }
        E data = node.getData();
        node.setData(null);
        size--; // 线性表的大小减1
        return data;
    }
    
    /*
     * 时间复杂度：O(1)
     */
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node<E> node = elements[index];
        E data = node.getData();
        node.setData(element);
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
    
    /*
     * 时间复杂度：O(n^2)
     */
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
        for (int i = 0; i < size; i++) {
            result.append(String.valueOf(elements[i].getData()));
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
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
