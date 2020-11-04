package com.jiang.Algorithm.interview;

public class SingleLinks<E> {
    class Node<T> {
        Node<T> next;
        T data;

        /**
         * 构造函数
         *
         * @auther T-Cool
         * @description 构造一个新节点
         * 新元素与链表结合节点
         */
        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /**
     * 链表表头
     */
    private Node<E> head;
    /**
     * 链表大小
     */
    private int size;

    public SingleLinks() {
        head = new Node<E>(null);
    }

    public Node<E> getHead() {
        return head;
    }

    /**
     * @param data
     * @param index
     * @throws Exception
     * @description 向链表中指定位置的元素(0 - size), 返回新节点
     */
    public Node<E> add(E data, int index) throws Exception {

        if (index > size) {
            throw new Exception("超出范围...");
        }

        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node<E> node = new Node<E>(data); // 将新元素链入链表
        cur.next = node;
        size++;
        return node;
    }

    /**
     * @param data
     * @throws Exception
     * @description 向链表末尾添加元素, 返回新节点
     */
    public Node<E> add(E data) throws Exception {
        return add(data, size);
    }

    /**
     * @param node
     * @description 向链表尾部添加新节点
     */
    public void add(Node<E> node) {
        Node<E> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;

        while (node != null) {
            size++;
            node = node.next;
        }
    }

    /**
     * @param index
     * @return
     * @throws Exception
     * @description 删除链表中指定位置的元素(0 ~ size - 1)
     */
    public E remove(int index) throws Exception {
        if (index > size - 1 || index < 0) {
            throw new Exception("超出范围...");
        }

        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Node<E> temp = cur.next;
        cur.next = temp.next;
        temp.next = null;

        size--;
        return temp.data;
    }

    /**
     * @return
     * @throws Exception
     * @description 向链表末尾删除元素
     */
    public E remove() throws Exception {
        return remove(size - 1);
    }

    /**
     * @description 删除链表中的重复元素(外循环 + 内循环)
     * 时间复杂度：O(n^2)
     */
    public void removeDuplicateNodes() {
        Node<E> cur = head.next;
        while (cur != null) { // 外循环
            Node<E> temp = cur;
            while (temp != null && temp.next != null) { // 内循环
                if (cur.data.equals(temp.next.data)) {
                    Node<E> duplicateNode = temp.next;
                    temp.next = duplicateNode.next;
                    duplicateNode.next = null;
                    size--;
                }
                temp = temp.next;
            }
            cur = cur.next;
        }
    }

    /**
     * @param k
     * @return 时间复杂度：O(n)
     * @description 找出单链表中倒数第 K 个元素(双指针法,相差 K-1 步)
     */
    public Node<E> getEndK(int k) {
        Node<E> pre = head.next;
        Node<E> post = head.next;
        for (int i = 1; i < k; i++) { // pre 先走 k-1 步
            if (pre != null) {
                pre = pre.next;
            }
        }
        if (pre != null) {
            // 当 pre 走到链表末端时，post 正好指向倒数第 K 个节点
            while (pre != null && pre.next != null) {
                pre = pre.next;
                post = post.next;
            }
            return post;
        }
        return null;
    }

    /**
     * @return
     * @description 返回链表的长度
     */
    public int size() {
        return size;
    }
}
