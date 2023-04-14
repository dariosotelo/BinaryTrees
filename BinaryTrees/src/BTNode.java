
public class BTNode<T extends Comparable <T>> {
    T elem;
    BTNode<T> left, right,dad;
    int ef=0;

    public BTNode(T elem) {
        this.elem = elem;
        this.left = null;
        this.right = null;
        this.dad = null;
        ef=0;
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

    public BTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BTNode<T> left) {
        this.left = left;
    }

    public BTNode<T> getRight() {
        return right;
    }

    public void setRight(BTNode<T> right) {
        this.right = right;
    }

    public BTNode<T> getDad() {
        return dad;
    }

    public void setDad(BTNode<T> dad) {
        this.dad = dad;
    }
    
    public void hang(BTNode<T> son) {
        if (son==null)
            return;
        if (son.getElem().compareTo(elem)<=0)
            left=son;
        else
            right=son;
        son.setDad(this);
    }
   
    public void hangRight(BTNode<T> son){
        if (son==null){
            right=null;
            return;
        }
        right=son;
        son.dad=this;
    }
    
    public void hangLeft(BTNode<T> son){
        if (son==null){
            left=null;
            return;
        }
        left=son;
        son.dad=this;
    }
    
    public void setRightNull(){
        right=null;
    }
    
    public void setLeftNull(){
        left=null;
    }
    
    public int getEF(){
        return ef;
    }
    
    public void setEF(int newEF){
        this.ef=newEF;
    }
    
}
