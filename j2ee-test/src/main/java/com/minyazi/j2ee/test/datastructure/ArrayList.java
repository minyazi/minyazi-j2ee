package com.minyazi.j2ee.test.datastructure;

import java.util.Arrays;

/**
 * 线性表：顺序存储结构
 * 
 * @author minyazi
 */
public class ArrayList<E extends Comparable<E>> implements List<E> {
    private Object[] elements;
    private int size; // 线性表的大小
    
    public ArrayList() {
        elements = new Object[10];
        size = 0;
    }
    
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        if (index == size) { // 在线性表的末尾插入元素
            elements[size] = element;
        } else { // 在线性表的其他位置插入元素
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = element;
        }
        size++; // 线性表的大小加1
        if (size == elements.length) { // 扩容
            elements = Arrays.copyOf(elements, size * 2);
        }
    }
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return null;
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
        for (int i = 0; i < size; i++) {
            result.append(String.valueOf(elements[i]));
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        ArrayList<Node<Integer>> list = new ArrayList<Node<Integer>>();
        for (int i = 0; i < 20; i++) {
            list.add(i, new Node<Integer>(i));
        }
        System.out.println(list.toString());
    }
}
