package com.demo.data.struct.stack;

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

    public void display(){
        if(first == null)
            System.out.println("empty");
        System.out.print("top -> bottom : | ");
        Data<E> cur = first;
        while(cur != null){
            System.out.print(cur.obj.toString() + " | ");
            cur = cur.next;
        }
        System.out.print("\n");
    }

}
