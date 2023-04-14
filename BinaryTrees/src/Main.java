
import java.util.ArrayList;

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
        ArrayList<Integer> list;
        //To try out the solution, a list will be created
        //the levels of the list will be 3
        int i= 3;
        list=ListGenerators.balancedTreeListGenerator(i);

        //the first element will be a 4 which will be the root,
        //the the two children of the list will be the subsequent 2 numbers
        //the last 4 numbers will be the nodes belonging to the third level.
        System.out.println(list);
        
        //This solves the problem of having an unbalanced binary tree.
        //The next step would be to insert the numbers into a tree.
        
        

    }
    
}
