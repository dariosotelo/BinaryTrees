
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author darios
 */
public interface LinkedBinaryTreeADT <T extends Comparable <T>> {
    public void add (T dato);
    public T remove (T dato);
    public T findMin();
    public T findMax();
}
