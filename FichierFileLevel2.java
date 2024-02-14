import java.util.LinkedList;
import java.util.Queue;

import javax.lang.model.element.QualifiedNameable;

public class file<E> extends fichier {
    private java.util.Queue<Integer> file;

    public file(){
        file = new java.util.LinkedList<Integer>();
    }

    public Queue<Integer> getFile(){
        return file;
    }

    public boolean add(E item){
        return file.add(item);
    }

    public Queue<Integer> poll(){
        return file.poll();
    }

    public Queue<Integer> remove(){
        return file.remove();
    }

    public Queue<Integer> peek(){
        return file.peek();
    }

    public boolean contains(E item){
        return file.contains(item);
    }

    public boolean isEmpty(){
        return file.isEmpty();
    }

}
