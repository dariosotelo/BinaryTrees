
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class BinaryTree <T extends Comparable <T>> implements BinaryTreeADT<T> {
    BTNode<T> root;
    int i;

    public BinaryTree(T elem) {
        root = new BTNode<T>(elem);
        i = 1;
    }

    @Override
    public boolean isEmpty() {
        return i == 0;
    }

    @Override
    public int size() {
        return i;
    }

    public boolean contains(Object elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void preOrder(BTNode<T> act, ArrayList<T> list){
        if (act == null)
            return;
        
        list.add(act.elem);
        preOrder(act.left,list);
        preOrder(act.right,list);
    }
    
    private void inOrder(BTNode<T> act, ArrayList<T> list){
        if (act == null)
            return;
        
        inOrder(act.left, list);
        list.add(act.elem);
        inOrder(act.right,list);
    }
    
    private void postOrder(BTNode<T> act, ArrayList<T> list){
        if (act == null)
            return;
        
        postOrder(act.left,list);
        postOrder(act.right,list);
        list.add(act.elem);
    }
    
    public Iterator<T> preOrderIterative(){
        ArrayList<T> list = new ArrayList<T>();
        Stack<BTNode> stack = new Stack<BTNode>();
        stack.push(root);
        BTNode<T> act;
        
        while(!stack.isEmpty()){
            act = stack.pop();
            list.add(act.getElem());
            
            if (act.getRight() != null)
                stack.add(act.getRight());
            
            if (act.getLeft() != null)
                stack.add(act.getLeft());
            
        }
        return list.iterator();
    }

    @Override
    public Iterator<T> byLevelIterative() {
        QueueADT<BTNode> queue ;
        queue = new Queue<BTNode>();
        ArrayList<T> list = new ArrayList<T>();
        queue.agrega(root);
        BTNode<T> act;
        
        while(!queue.estaVacia()){
            act = queue.saca();
            list.add(act.getElem());
            
            if (act.getRight() != null)
                queue.agrega(act.getRight());
            
            if (act.getLeft() != null)
                queue.agrega(act.getLeft());
            
        }
        return list.iterator();
        
    }
    
    public int sacaAltura(BTNode<T> arbol, int contador) {
        if (arbol==null){
            return contador;
        }
        contador++;
        int f1 = sacaAltura(arbol.left, contador);
        int f2 = sacaAltura(arbol.right, contador);
        if (f1<f2){
            return f2;
        } else {
            return f1;
        }

    }
    
    @Override
    public BTNode<T> search(T toSearch) {
        return search(root, toSearch);
    }
    
    private BTNode<T> search(BTNode<T> actual, T dato) {
        if (actual==null)
            return null;
        if (actual.getElem().equals(dato))
            return actual;
        BTNode<T> res=search(actual.getLeft(), dato);
        if (res==null)
            res=search(actual.getRight(), dato);
        return res;
    }

    @Override
    public boolean contains(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
