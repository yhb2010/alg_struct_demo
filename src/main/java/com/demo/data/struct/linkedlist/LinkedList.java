package com.demo.data.struct.linkedlist;

/**单链表：
insertFirst：在表头插入一个新的链接点，时间复杂度为O(1)
deleteFirst：删除表头的链接点，时间复杂度为O(1)
find：查找包含指定关键字的链接点，由于需要遍历查找，平均需要查找N/2次，即O(N)
remove：删除包含指定关键字的链接点，由于需要遍历查找，平均需要查找N/2次，即O(N)
 * @author DELL
 *
 */
public class LinkedList<E> {

	@SuppressWarnings("hiding")
	private class Data<E>{
        private E obj;
        private Data<E> next = null;

        Data(E obj){
            this.obj = obj;
        }
    }

    private Data<E> first = null;

    public void insertFirst(E obj){
        Data<E> data = new Data<>(obj);
        data.next = first;
        first = data;
    }

    public E deleteFirst() throws Exception{
        if(first == null)
            throw new Exception("empty!");
        Data<E> temp = first;
        first = first.next;
        return temp.obj;
    }

    public E find(Object obj) throws Exception{
        if(first == null)
            throw new Exception("LinkedList is empty!");
        Data<E> cur = first;
        while(cur != null){
            if(cur.obj.equals(obj)){
                return cur.obj;
            }
            cur = cur.next;
        }
        return null;
    }

    public void remove(Object obj) throws Exception{
        if(first == null)
            throw new Exception("LinkedList is empty!");
        if(first.obj.equals(obj)){
            first = first.next;
        }else{
            Data<E> pre = first;
            Data<E> cur = first.next;
            while(cur != null){
                if(cur.obj.equals(obj)){
                    pre.next = cur.next;
                }
                pre = cur;
                cur = cur.next;
            }
        }
    }

    public void removeByIndex(int index) throws Exception{
    	if(index == 0){
    		first = first.next;
    	}else{
    		Data<E> pre = first;
            Data<E> cur = first.next;
            int i = 1;
            while(cur != null){
            	if(i++ == index){
                    pre.next = cur.next;
                }
                pre = cur;
                cur = cur.next;
            }
    	}
    }

    public boolean isEmpty(){
        return (first == null);
    }

    public void display(){
        if(first == null)
            System.out.println("empty");
        Data<E> cur = first;
        while(cur != null){
            System.out.print(cur.obj.toString() + " -> ");
            cur = cur.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args) throws Exception {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.insertFirst(6);
        ll.insertFirst(5);
        ll.insertFirst(4);
        ll.insertFirst(3);
        ll.insertFirst(2);
        ll.insertFirst(1);
        ll.display();
        ll.deleteFirst();
        ll.display();
        ll.remove(3);
        ll.display();
        ll.removeByIndex(2);
        ll.display();
        System.out.println(ll.find(1));
        System.out.println(ll.find(4));
    }

}
