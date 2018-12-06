package com.demo.data.struct.linkedlist;

/**
 * 循环链表：表中的最后一个节点的指针域指向头结点，整个链表形成一个环。
 *循环链表的特点是无须增加存储量，仅对表的链接方式稍作改变，即可使得表处理更加方便灵活。
 *①循环链表中没有NULL指针。涉及遍历操作时，其终止条件就不再是像非循环链表那样判别p或p-＞next是否为空，而是判别它们是否等于某一指定指针，如头指针或尾指针等。
 *②在单链表中，从一已知结点出发，只能访问到该结点及其后续结点，无法找到该结点之前的其它结点。而在单循环链表中，从任一结点出发都可访问到表中所有结点，这一优点使某些运算在单循环链表上易于实现。
 * @author DELL
 *
 */
public class CircularLinkedList<E> {

	@SuppressWarnings("hiding")
	class Data<E> {
		public E obj = null;
		private Data<E> next = null;

		Data(E obj){
            this.obj = obj;
        }
	}

	private Data<E> first = null;// 头结点
	private int size;

	/**
	 * 初始化链表
	 * */
	public CircularLinkedList() {
		first = new Data<>(null);
		first.next = first;
	}

	/**
	 * 插入链表
	 * */
	public void insertFirst(E o) {
		Data<E> e = new Data<>(o);
		if (first.next == first)// 第一次插入元素
		{
			first.next = e;
			e.next = first;
		} else// 不是第一次插入元素
		{
			// temp引用在栈中，temp和header引用都指向堆中的initList()中new的Element对象
			Data<E> temp = first;
			while (temp.next != first)// 寻找最后一个元素
			{
				temp = temp.next;
			}
			temp.next = e;
			e.next = first;// 新插入的最后一个节点指向头结点
		}
		size++;
	}

	/**
	 * 删除链表中第i个元素
	 * */
	public void deletelist(E o) {
		Data<E> temp = first;
		while (temp.next != first) {
			// 判断temp当前指向的结点的下一个结点是否是要删除的结点
			if (temp.next.obj.equals(o)) {
				temp.next = temp.next.next;// 删除结点
			} else {
				temp = temp.next;// temp“指针”后移
			}
		}
		size--;
	}

	/**
	 * 获取链表的第i个位置的元素
	 * */
	public E getElement(int i) {
		if (i <= 0 || i > size) {
			System.out.println("获取链表的位置有误！返回null");
			return null;
		} else {
			int count = 0;
			Data<E> temp = first;
			while (temp.next != first) {
				if (++count == i) {
					return temp.next.obj;
				}
				temp = temp.next;
			}
			return null;
		}
	}

	/**
	 * 链表长度
	 * */
	public int length() {
		return size;
	}

	/**
	 * 判断链表中是否存在某元素
	 * */
	public Boolean isContain(E o) {
		Data<E> temp = first;
		while (temp.next != first) {
			if (temp.next.obj.equals(o)) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	public void display(){
        if(first == null)
            System.out.println("empty");
        Data<E> cur = first;
        while (cur.next != first){
            System.out.print(cur.next.obj.toString() + " -> ");
            cur = cur.next;
        }
        System.out.print("\n");
    }

	public static void main(String[] args) {
		CircularLinkedList<Integer> ll = new CircularLinkedList<>();
		ll.insertFirst(5);
		ll.insertFirst(50);
		ll.insertFirst(500);
		ll.insertFirst(5000);
		ll.display();
		System.out.println(ll.isContain(5));
		System.out.println(ll.isContain(5000));
		System.out.println(ll.isContain(50000));
		System.out.println(ll.getElement(1));
		System.out.println(ll.getElement(4));
		ll.deletelist(50000);
		ll.deletelist(5000);
		ll.deletelist(500);
		ll.display();
	}

}
