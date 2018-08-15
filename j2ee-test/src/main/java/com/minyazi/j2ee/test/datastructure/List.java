package com.minyazi.j2ee.test.datastructure;

/**
 * 线性表
 * 
 * @author minyazi
 */
public interface List {
    /**
     * 在指定位置插入结点
     * 
     * @param index
     * @param node
     */
    void add(int index, Node node);
    
    /**
     * 获取指定位置的结点
     * 
     * @param index
     * @return
     */
    Node get(int index);
    
    /**
     * 删除指定位置的结点
     * 
     * @param index
     * @return
     */
    Node remove(int index);
    
    /**
     * 替换指定位置的结点
     * 
     * @param index
     * @param node
     * @return
     */
    Node set(int index, Node node);
    
    /**
     * 排序
     * 
     * @return
     */
    List sort();
    
    /**
     * 查找
     * 
     * @param node
     * @return
     */
    int search(Node node);
}
