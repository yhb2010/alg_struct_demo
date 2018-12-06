package com.demo.data.struct.linkedlist;

/**双端链表(不是双向链表)：

与单向链表的不同之处在保存有对最后一个链接点的引用(last)
insertFirst：在表头插入一个新的链接点，时间复杂度O(1)
insertLast：在表尾插入一个新的链接点，时间复杂度O(1)
deleteFirst：删除表头的链接点，时间复杂度O(1)
deleteLast:：删除表尾的链接点，由于只保存了表尾的链接点，而没有保存表尾的前一个链接点(这里就体现出双向链表的优势了)，所以在删除表尾链接点时需要遍历以找到表尾链接点的前一个链接点，需查找N-1次，也就是O(N)
 * @author DELL
 *
 */
public class FirstLastList<E> {

	@SuppressWarnings("hiding")
	private class Data<E>{
        private E obj;
        private Data<E> next = null;

        Data(E obj){
            this.obj = obj;
        }
    }

    private Data<E> first = null;
    private Data<E> last = null;

    public void insertFirst(E obj){
        Data<E> data = new Data<>(obj);
        if(first == null)
            last = data;
        data.next = first;
        first = data;
    }

    public void insertLast(E obj){
        Data<E> data = new Data<>(obj);
        if(first == null){
            first = data;
        }else{
            last.next = data;

        }
        last = data;
    }

    public E deleteFirst() throws Exception{
          if(first == null)
             throw new Exception("empty");
          Data<E> temp = first;
          if(first.next == null)
             last = null;
          first = first.next;
          return temp.obj;
   }

    public void deleteLast() throws Exception{
        if(first == null)
            throw new Exception("empty");
        if(first.next == null){
            first = null;
            last = null;
        }else{
            Data<E> temp = first;
            while(temp.next != null){
                if(temp.next == last){
                    last = temp;
                    last.next = null;
                    break;
                }
                temp = temp.next;
            }
        }
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
        FirstLastList<Integer> fll = new FirstLastList<>();
        fll.insertFirst(2);
        fll.insertFirst(1);
        fll.display();
        fll.insertLast(3);
        fll.display();
        fll.deleteFirst();
        fll.display();
        fll.deleteLast();
        fll.display();
    }

}
