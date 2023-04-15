
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
    
    public <T extends Comparable<T>> List<T> getValues(BTNode<T> root) {
        List<T> list = new ArrayList<>();
        if (root==null)
            return list;
        Stack<BTNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BTNode<T> node = stack.pop();
            list.add(node.getElem());
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        Collections.sort(list);
        return list;
    }
    
    public <T extends Comparable<T>> BTNode<T> createTreeFromList(List<T> list) {
        if (list==null || list.isEmpty())
            return null;
        int middleNumber = list.size()/2;
        BTNode<T> root = new BTNode<>(list.get(middleNumber));
        root.left = createTreeFromList(list.subList(0, middleNumber));
        root.right = createTreeFromList(list.subList(middleNumber+1, list.size()));
        return root;
    }
    
    
    //This rest of code just serves as a way to print the tree
    public static <T extends Comparable<T>> void printTree(BTNode<T> root) {
    int maxLevel = maxLevel(root);
    printNode(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<T>> void printNode(List<BTNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<BTNode<T>> newNodes = new ArrayList<>();
        for (BTNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.getElem());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (BTNode<T> node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                if (node.getLeft() != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);
                printWhitespaces(i + i - 1);
                if (node.getRight() != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);
                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println();
        }
        printNode(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<T>> int maxLevel(BTNode<T> node) {
        if (node == null)
            return 0;
        return Math.max(maxLevel(node.getLeft()), maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }


}
