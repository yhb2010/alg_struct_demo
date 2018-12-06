package com.demo.data.struct.stack;

/**栈单链表实现：没有长度限制，并且出栈和入栈速度都很快
 * @author DELL
 *
 */
public class LinkedStack {

	private LinkedList ll = new LinkedList();

    public void push(Object obj){
        ll.insertFirst(obj);
    }

    public Object pop() throws Exception{
        return ll.deleteFirst();
    }

    public void display(){
        ll.display();
    }

    public static void main(String[] args) throws Exception{
    	LinkedStack lls = new LinkedStack();
        lls.push(1);
        lls.push(2);
        lls.push(3);
        lls.display();
        System.out.println(lls.pop());
        lls.display();
    }

}
