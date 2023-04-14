
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darios
 */
//This section of code generates lists that can be inserted into the binary trees.
public class ListGenerators {
    
    public static ArrayList<Integer> balancedTreeListGenerator(int n){
       ArrayList<Integer> list = new ArrayList<>();
       list.add((int) Math.pow(2, n-1)); 
       int j=list.get(0)/2; 
       int k=1; 
       int index=0;
       
       while(j>=2){
           for (int i = 0; i<k; i++){
           list.add(list.get(index+i)-j);
           list.add(list.get(index+i)+j);
           }
           index+=k;
           k*=2;
           j/=2;
       }
       int oddNumbers=list.get(0);
       for(int i=0; i<oddNumbers; i++)
           list.add(2*i+1);
       return list;
    }
    
    public static ArrayList<Integer> rightLoadedListGenerator(int n){
        ArrayList<Integer> list = new ArrayList<>();
        int k=(int) Math.pow(2, n);
        for(int i=1; i<=k-1; i++)
            list.add(i);
        return list;
    }
    
    public static ArrayList<Integer> randomListGenerator(int n){
        ArrayList<Integer> list= new ArrayList<>();
        int k=(int) Math.pow(2, n), j;
        
        for (int i=0; i<k-1; i++){
            j=(int) Math.floor(Math.random()*k);
            list.add(j);
        }
        return list;
    }
    
}
