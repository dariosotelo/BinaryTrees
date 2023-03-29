
import java.util.Iterator;


public interface BinaryTreeADT <T extends Comparable <T>> {
    public boolean isEmpty();
    public int size();
    public boolean contains(T elem);
    public Iterator<T> preOrderIterative();
    public Iterator<T> byLevelIterative();
    public BTNode<T> search(T toSearch);
}
