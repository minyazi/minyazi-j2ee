package com.minyazi.j2ee.test.datastructure;

/**
 * 线性表
 * 
 * @author minyazi
 */
public interface List {
    /**
     * 在线性表的指定位置插入结点
     * 
     * @param index 要插入结点处的索引
     * @param node 要插入的结点
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index > size())
     */
    void add(int index, Node<?> node) throws IndexOutOfBoundsException;
    
    /**
     * 返回线性表中指定位置的结点
     * 
     * @param index 要返回的结点的索引
     * @return 线性表中指定位置的结点
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    Node<?> get(int index) throws IndexOutOfBoundsException;
    
    /**
     * 删除线性表中指定位置的结点
     * 
     * @param index 要删除的结点的索引
     * @return 被删除的结点
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    Node<?> remove(int index) throws IndexOutOfBoundsException;
    
    /**
     * 替换线性表中指定位置的结点
     * 
     * @param index 要替换的结点的索引
     * @param node 待替换的结点
     * @return 被替换的结点
     * @throws IndexOutOfBoundsException 索引越界时抛出 (index < 0 || index >= size())
     */
    Node<?> set(int index, Node<?> node) throws IndexOutOfBoundsException;
    
    /**
     * 对线性表中的结点按升序进行排序
     * 
     * @return 按升序排好序的线性表
     */
    List sort();
    
    /**
     * 在线性表中查找指定结点
     * 
     * @param node 要查找的结点
     * @return 此线性表中第一次出现要查找的结点的索引，如果线性表中不包含该结点，则返回-1
     */
    int search(Node<?> node);
    
    /**
     * 返回线性表中的结点数
     * 
     * @return 线性表中的结点数
     */
    int size();
}
