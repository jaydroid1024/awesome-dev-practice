package com.jay.lib_java.o4_reference;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/5
 */
class LinkedQueue {
  Node front; // 队头指针，指向队头节点
  Node rail; // 队尾指针，指向队尾节点

  int size = 0; // 记录队列长度

  // 构造函数
  public LinkedQueue() {
    front = rail = null;
  }

  public static void main(String[] args) {
    LinkedQueue queue = new LinkedQueue();
    queue.addQueue(1);
    queue.addQueue(2);
    queue.addQueue(3);
    queue.deleteQueue();

    System.out.println("queue size " + queue.size);
  }

  public boolean isEmpty() {
    return size == 0 ? true : false;
  }

  // 添加元素
  public boolean addQueue(Object ele) {
    if (size == 0) {
      front = new Node(null, ele);
      rail = front;
      size++;
      return true;
    }
    Node s = new Node(null, ele);
    // 这块有个主意的地方，一旦rail设置了next属性，因为front节点与rail节点指向了同一个node节点，持有同一个结点的一个引用，因此front节点next属性也被填充
    rail.setNext(s);
    rail = s;
    size++;
    return true;
  }

  /**
   * 删除元素,出队列
   *
   * @return
   */
  public boolean deleteQueue() {
    if (isEmpty()) {
      System.out.println("当前队列为空");
      return false;
    }

    front = front.next;
    size--;
    return true;
  }
}

class Node {

  Node next;
  Object element;

  public Node(Node next, Object element) {
    this.next = next;
    this.element = element;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public Object getElement() {
    return element;
  }

  public void setElement(Object element) {
    this.element = element;
  }
}
