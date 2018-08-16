package com.minyazi.j2ee.test.datastructure;

import java.util.Arrays;

/**
 * 线性表：顺序存储结构
 * 
 * @author minyazi
 */
public class ArrayList implements List {
    private Node<?>[] nodes;
    private int size; // 线性表的大小
    
    public ArrayList() {
        nodes = new Node<?>[10];
        size = 0;
    }
    
    @Override
    public void add(int index, Node<?> node) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        if (index == size) { // 在线性表的末尾插入结点
            nodes[size] = node;
        } else { // 在线性表的其他位置插入结点
            for (int i = size - 1; i >= index; i--) {
                nodes[i + 1] = nodes[i];
            }
            nodes[index] = node;
        }
        size++; // 线性表的大小加1
        if (size == nodes.length) { // 扩容
            nodes = Arrays.copyOf(nodes, size * 2);
        }
    }
    
    @Override
    public Node<?> get(int index) throws IndexOutOfBoundsException {
        return null;
    }
    
    @Override
    public Node<?> remove(int index) throws IndexOutOfBoundsException {
        return null;
    }
    
    @Override
    public Node<?> set(int index, Node<?> node) throws IndexOutOfBoundsException {
        return null;
    }
    
    @Override
    public List sort() {
        return null;
    }
    
    @Override
    public int search(Node<?> node) {
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
            result.append(String.valueOf(nodes[i]));
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i, new Node<Integer>(i));
        }
        System.out.println(list.toString());
    }
}
