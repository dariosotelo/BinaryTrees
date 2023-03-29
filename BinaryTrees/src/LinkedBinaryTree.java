/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darios
 */
public class LinkedBinaryTree <T extends Comparable<T> >{
    T elem;
    BTNode<T> root;
    int count;
    
    public T findMin(){
        BTNode<T> actual=root;
        BTNode<T> add;
        if(root==null)
            throw new RuntimeException();
        else {
            actual=root;
            while (actual.getLeft()!=null)
                add=actual.getLeft();
        }
        return actual.getElem();
    }
    
    public void insert(T elem) {
        BTNode<T> actual=root, dad;
        BTNode<T> newData = new BTNode<T>(elem);
        if (root==null){
            root=newData; 
            count++;
            return;
        }
        while (actual!=null) {
            dad=actual;
            if (elem.compareTo(actual.getElem())<=0)
                actual=actual.getRight();
            else
                actual=actual.getRight();
        }
    }    
    
    private BTNode<T> search(T elem) {
        BTNode<T> actual=root;
        while (actual!=null && actual.getElem().compareTo(elem)!=0){
            if (elem.compareTo(actual.getElem())<0)
                actual=actual.getLeft();
            else
                actual=actual.getRight();
        }
        return actual;
    }
    
    
    
    

}
