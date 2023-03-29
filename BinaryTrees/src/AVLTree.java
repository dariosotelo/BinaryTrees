
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
public class AVLTree <T extends Comparable <T>> {
    AVLNode root;
    
    public AVLTree(T elem){
        this.root=new AVLNode(elem);
    }
    
    
    
    public AVLNode<T> search(T elem) {
        AVLNode<T> actual=root;
        
        if (elem==null)
            throw new RuntimeException("null value cannot be searched");
        
        //This while will search for the element until it finds it in the tree.
        //It uses a binary search method.
        while (actual!=null && !actual.getElem().equals(elem)){
            if (elem.compareTo(actual.getElem())<0)
                actual=actual.getLeft();
            else
                actual=actual.getRight();
        }
        
        return actual;
    }
    
    
    public void add(T elem) {
        AVLNode <T> actual = root;
        AVLNode <T> newNode = new AVLNode(elem);
        AVLNode <T> dad = root;
        
        if (elem==null)
            throw new RuntimeException("null value cannot be added.");
        
        if (root == null){
            root=newNode;
            return;
        }
        
        //This case is to avoid duplicates.
        if (!search(elem).equals(root.getElem()))
            return;
            
        while (actual!=null){
            dad=actual;
            
            if (elem.compareTo(actual.getElem())<=0)
                actual=actual.getLeft();
            else
                actual=actual.getRight();
        }
        dad.hang(newNode);
    }
    
    
    public T delete(T elem) {
        AVLNode<T> guide = search(elem);
        
        if (guide == null)
            throw new RuntimeException("null value cannot be erased");
        
        if (guide.getLeft() == null && guide.getRight() == null)
            return caseOneR(guide);
        
        if (guide.getLeft() == null || guide.getRight() == null)
            return caseTwoR(guide);
        
        if(guide.getRight() != null && guide.getLeft() != null) {
            AVLNode<T> successor = successorInOrder(guide);
            guide.setElem(successor.getElem());
            caseOneR(successor);

        }
        
        return guide.getElem();
        
    }
    
    public AVLNode<T> unbalancedNode(AVLNode<T> root1) {
        if (root1==null)
            return null;
        
        int ef = root1.getEf(root1);
        if (Math.abs(ef)>1)
            return root1;
        
        AVLNode<T> unbLeft = unbalancedNode(root1.getLeft());
        if (unbLeft!=null)
            return unbLeft;
        
        AVLNode<T> unbRight = unbalancedNode(root1.getRight());
        if (unbRight!=null)
            return unbRight;
        
        return null;
    }
    
    public AVLNode<T> successorInOrder(AVLNode<T> guide) {
        if (guide == null || guide.getRight() == null)
            return null;
        AVLNode<T> inOrd = guide.getRight();
        while (inOrd.getLeft() != null){
            inOrd=inOrd.getLeft();
        }
        return inOrd;
    }
    
    public void balance(AVLNode<T> unb) {
        //unb is the unbalanced node.
        //This code is used to check which type of problem it has to balance, and then it will restore the equilibrium.
        if (root==null)
            return;
        
        if (unb.getEf(unb)==-2) {
            AVLNode<T> alpha = unb.getLeft();
            
            if (alpha.getEf(alpha)==-1 || alpha.getEf(alpha)==0)
                leftleft(unb);
            if (alpha.getEf(alpha)==1)
                leftright(unb);
            
        }
        
        if (unb.getEf(unb)==2) {
            AVLNode<T> alpha = unb.getRight();
            if (alpha.getEf(alpha)==1 || alpha.getEf(alpha)==0)
                rightright(unb);
            if (alpha.getEf(alpha)==-1)
                rightleft(unb);
        }
    }
    
    private T caseOneR(AVLNode<T> unb) {
        T aux = null;
        
        //removes the root
        if (unb.equals(root)){
            aux=(T) root.getElem();
            root=null;
            return aux;
        }
        
        aux = (T) root.getElem();
        
        if (aux.compareTo((T)unb.getDad().getElem()) > 0)
            unb.getDad().setRight(null);
        else
            unb.getDad().setLeft(null);
        
        return aux;
        
    }
    
    private T caseTwoR(AVLNode<T> unb) {
        T aux = null;
        
        AVLNode<T> son;
        
        if (unb.getLeft() != null)
            son = unb.getRight();
        else
            son = unb.getLeft();
        
        if (unb.equals(root))
            root=son;
        else
            unb.getDad().hang(son);
        aux = son.getElem();
        
        return aux;
    }
    
    
    public void print(AVLNode<T> root) {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<AVLNode<T>> level = new ArrayList<AVLNode<T>>();
        List<AVLNode<T>> next = new ArrayList<AVLNode<T>>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (AVLNode<T> n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }
            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<AVLNode<T>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }
        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
        

    }
    
    
    public void rightright(AVLNode alpha) {
        //We define the nodes.
        AVLNode<T> dad = alpha.getDad();
        AVLNode<T> beta;
        AVLNode<T> B;
        
        //This piece of code is to initialize the previously defined AVLNode variables.
        beta=alpha.getRight();
        B=beta.getLeft();
        
        //This piece of code is the one which rotates.
        alpha.setRight(B);
        beta.setLeft(alpha);
        dad.hang(beta);
    }
    
    public void leftleft(AVLNode alpha) {
        //This method is simmetrical to the rightright method.
        AVLNode<T> dad = alpha.getDad();
        AVLNode<T> beta;
        AVLNode<T> C;
        
        beta=alpha.getLeft();
        C=beta.getRight();
        
        beta.setRight(alpha);
        alpha.setLeft(C);
        dad.hang(beta);
    }
    
    public void leftright(AVLNode alpha) {
        //We define the nodes we are going to use
        AVLNode<T> dad = alpha.getDad();
        AVLNode<T> beta;
        AVLNode<T> gamma;
        AVLNode<T> B;
        AVLNode<T> C;
        
        //Initialize the nodes
        beta=alpha.getLeft();
        gamma=beta.getRight();
        B=gamma.getLeft();
        C=gamma.getRight();
        
        //Rotate the tree
        gamma.setLeft(beta);
        gamma.setRight(alpha);
        beta.setRight(B);
        alpha.setLeft(C);
        dad.hang(gamma);
        
        
    }
    
    public void rightleft(AVLNode alpha) {
        //This method is simetrical to the leftright method.
        AVLNode<T> dad = alpha.getDad();
        AVLNode<T> beta;
        AVLNode<T> gamma;
        AVLNode<T> B;
        AVLNode<T> C;
        
        beta=alpha.getRight();
        gamma=beta.getLeft();
        B=gamma.getLeft();
        C=gamma.getRight();
        
        gamma.setLeft(alpha);
        alpha.setRight(B);
        gamma.setRight(beta);
        beta.setLeft(C);
        dad.hang(gamma);
    }
    
}
