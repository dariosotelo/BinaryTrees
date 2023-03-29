/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darios
 */
public class AVLNode <T extends Comparable <T>> {
    T elem;
    AVLNode<T> left, right,dad;
    int height, ef;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setEf(int ef) {
        this.ef = ef;
    }

    public int getHeight(AVLNode<T> node) {
        if (node==null)
            return -1;
        return 1+Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    public int getEf(AVLNode<T> node) {
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return rightHeight-leftHeight;
    }

    
    public AVLNode(T elem) {
        this.elem = elem;
        this.left = null;
        this.right = null;
        this.height=1;
        this.ef=0;
    }
    
    public int descendants(){
        int i=0;
        
        if (left != null)
            i = left.descendants() + 1;
        
        if (right != null)
            i += right.descendants() + 1;
        
        return i;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public AVLNode<T> getDad() {
        return dad;
    }

    public void setDad(AVLNode<T> dad) {
        this.dad = dad;
    }
    
    
    public void setEF(int ef) {
        this.ef=ef;
    }
    
    public void hang(AVLNode<T> son) {
        if (son==null)
            return;
        if (son.getElem().compareTo(elem)<=0)
            left=son;
        else
            right=son;
        son.setDad(this);
    }
   
    
}
