/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darios
 */
public class LinkedBinaryTree <T extends Comparable<T> >{
    BTNode<T> root;
    int count;
    int comparisons;

    public LinkedBinaryTree() {
        root=null;
        count=0;
        comparisons=0;
    }
    
    public LinkedBinaryTree(T elem){
        root=new BTNode<T>(elem);
        count=1;
    }

    public BTNode<T> getRoot() {
        return root;
    }

    public int getCount() {
        return count;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void setRoot(BTNode<T> root) {
        this.root = root;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
    
    public void resetComparisons(){
        comparisons=0;
    }
    
    
    //Functions
    
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
    
    public boolean find(T elem){
        return find(elem, root)!= null;
    }
    
    private BTNode<T> find(T different, BTNode<T> leaf){
        if(leaf==null)
            return null;
        comparisons++;
        if(leaf.elem.equals(different))
            return leaf;
        BTNode<T> aux=find(different, leaf.left);
        if (aux==null)
            aux=find(different, leaf.right);
        return aux;
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
    
    
    public boolean isEmpty(){
        return count ==0;
    }
    
    public void clear(){
        root=null;
        resetComparisons();
        count=0;
    }
    
    public int height(){
        if (root==null)
            return -1;
        return height(root)-1;
    }
    private int height(BTNode<T> actual){
        if (actual==null)
            return 0;
        return 1+Math.max(height(actual.getLeft()), height(actual.getRight()));
    }
    
    private void add(BTNode<T> newNode, BTNode<T> actual){
        BTNode<T> insertSide;
        comparisons++;
        if (actual.getElem().compareTo(newNode.getElem())>=0){
            insertSide=actual.getLeft();
            if(insertSide==null){
                actual.hangLeft(newNode);
                return;
            }
        }else{
            insertSide=actual.getRight();
            if (insertSide==null){
                actual.hangRight(newNode);
                return;
            }
        }
        add(newNode, insertSide);
    }
    
    public void delete(T elem){
        BTNode<T> actual=find(elem, root);
        if(actual==null)
            return;
        if(actual.getRight()==null && actual.getLeft()==null) //This is a leaf
            actual=deleteLeaf(actual);
        else{
            if(actual.getRight()==null || actual.getLeft()==null) //Only one child
                actual=deleteOneChild(actual);
            else
                actual=deleteTwoChildren(actual);
        }
    }
    
    private BTNode<T> deleteLeaf(BTNode<T> actual){
        if(actual==root)
            root=null;
        else{
            if(actual.getDad().getLeft()==actual)
                actual.getDad().setLeftNull();
            else
                actual.getDad().setRightNull();
        }
        return actual;
    }
    
    private BTNode<T> deleteOneChild(BTNode<T> actual) {
        BTNode<T> son;
        if (actual.getRight()==null)
            son=actual.getLeft();
        else
            son=actual.getRight();
        if(actual==root){
            root=son;
            son.setDad(null);
        }
        else
            actual.getDad().hang(son);
        
        comparisons++;
        return son;
        
    }
    
    private BTNode <T> deleteTwoChildren(BTNode<T> actual){
        BTNode<T> sucInorder=findNextInOrder(actual);
        T data=actual.getElem();
        actual.setElem(sucInorder.getElem());
        sucInorder.setElem(data);
        if (actual.getRight()==sucInorder){
            actual.right=sucInorder.right;
            if(actual.right!=null)
                actual.right.dad=actual;
        }
        else{
            sucInorder.dad.left=sucInorder.right;
            if (sucInorder.right!=null)
                sucInorder.right.dad=sucInorder.dad;
        }
        return sucInorder;
            
    }
    
    private BTNode<T> findNextInOrder(BTNode<T> actual){
        if (actual.getRight()!=null){
            BTNode<T> sucInOrder=actual.getRight();
            while(sucInOrder.getLeft()!=null)
                sucInOrder=sucInOrder.getLeft();
            return sucInOrder;
        }
        else
            return actual.getDad();
    }
    

}
