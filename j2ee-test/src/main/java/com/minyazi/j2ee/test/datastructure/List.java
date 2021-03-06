package com.minyazi.j2ee.test.datastructure;

/**
 * 线性表
 * 
 * @author minyazi
 */
public interface List<E extends Comparable<E>> {
    /**
     * 在线性表的头部插入元素（头插法）
     * 
     * @param element 要插入的元素
     */
    void addFromHead(E element);
    
    /**
     * 在线性表的尾部插入元素（尾插法）
     * 
     * @param element 要插入的元素
     */
    void addFromTail(E element);
    
    /**
     * 在线性表的指定位置插入元素
     * 
     * @param index 要插入元素处的索引
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index > size())
     */
    void add(int index, E element) throws IndexOutOfBoundsException;
    
    /**
     * 清空线性表
     */
    void clear();
    
    /**
     * 返回线性表中指定位置的元素
     * 
     * @param index 要返回的元素的索引
     * @return 线性表中指定位置的元素
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    E get(int index) throws IndexOutOfBoundsException;
    
    /**
     * 判断线性表是否是空表
     * 
     * @return 如果线性表是空表，则返回true，否则返回false
     */
    boolean isEmpty();
    
    /**
     * 移除线性表中指定位置的元素
     * 
     * @param index 要移除的元素的索引
     * @return 被移除的元素
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    E remove(int index) throws IndexOutOfBoundsException;
    
    /**
     * 替换线性表中指定位置的元素
     * 
     * @param index 要替换的元素的索引
     * @param element 待替换的元素
     * @return 被替换的元素
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    E set(int index, E element) throws IndexOutOfBoundsException;
    
    /**
     * 根据元素的自然顺序对线性表按升序进行排序
     * 
     * @return 按升序排好序的线性表
     */
    List<E> sort();
    
    /**
     * 在线性表中查找指定元素
     * 
     * @param element 要查找的元素
     * @return 此线性表中第一次出现要查找的元素的索引，如果线性表中不包含该元素，则返回-1
     */
    int search(E element);
    
    /**
     * 返回线性表中的元素数
     * 
     * @return 线性表中的元素数
     */
    int size();
    
    /**
     * 将指定线性表并入线性表中，即求两个线性表的并集
     * 
     * @param list 要并入的线性表
     */
    void union(List<E> list);
}
