package com.demo.data.struct.linkedlist;

/**有序链表：链表中的数据按从小到大排列
 * @author DELL
 *
 */
public class SortedList<E> {

	@SuppressWarnings("hiding")
	private class Data<E>{
        private E obj;
        private Data<E> next = null;

        Data(E obj){
            this.obj = obj;
        }
    }

    private Data<E> first = null;

    @SuppressWarnings("unchecked")
	public void insert(E obj){
        Data<E> data = new Data<>(obj);
        Data<E> pre = null;
        Data<E> cur = first;
        while(cur != null && ((Comparable<E>)data.obj).compareTo(cur.obj) > 0){
            pre = cur;
            cur = cur.next;
        }
        if(pre == null)
            first = data;
        else
            pre.next = data;
        data.next = cur;
    }

    public E deleteFirst() throws Exception{
        if(first == null)
            throw new Exception("empty!");
        Data<E> temp = first;
        first = first.next;
        return temp.obj;
    }

    public void display(){
        if(first == null)
            System.out.println("empty");
        System.out.print("first -> last : ");
        Data<E> cur = first;
        while(cur != null){
            System.out.print(cur.obj.toString() + " -> ");
            cur = cur.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args) throws Exception{
        SortedList<Integer> sl = new SortedList<>();
        sl.insert(80);
        sl.insert(2);
        sl.insert(100);
        sl.display();
        System.out.println(sl.deleteFirst());
        sl.insert(33);
        sl.display();
        sl.insert(99);
        sl.display();
    }

}
