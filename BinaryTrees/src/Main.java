
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darios
 */
public class Main {
    public static void main(String[] args){
        LinkedBinaryTree<Integer> tree=new LinkedBinaryTree<Integer>();
        List<Integer> list;
        
        //This solves the problem of having an unbalanced binary tree.
        
        //This code is to create the tree.
        BTNode<Integer> root = new BTNode<>(4);
        tree.setRoot(root);
        root.setLeft(new BTNode<>(2));
        root.setRight(new BTNode<>(6));
        root.getLeft().setLeft(new BTNode<>(1));
        root.getLeft().setRight(new BTNode<>(3));
        root.getRight().setLeft(new BTNode<>(5));
        root.getRight().setRight(new BTNode<>(7));
        //In this section we will build an unbalanced tree.
        root.left.left.setLeft(new BTNode<>(0));
        root.left.left.left.setLeft(new BTNode<>(-1));
        root.right.right.setRight(new BTNode<>(8));
        root.right.right.right.setRight(new BTNode<>(9));
        root.right.right.right.right.setRight(new BTNode<>(10));
        root.right.right.right.right.right.setRight(new BTNode<>(11));

        //We will ask to print the tree to show the unbalanced tree
        tree.printTree(tree.root);
        
        //These are the two lines of code which are going to balance the tree.
        //the first one sorts the values of the nodes into a list
        list=tree.getValues(tree.root);
        
        //this code is used to create a balanced tree from the list.
        tree.root=tree.createTreeFromList(list);
        
        tree.printTree(tree.root);

    }
    
}
