import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Deque;

public class pile<E> extends Vector<E> , fichier {

    private java.util.Deque<Integer> pile;

    public pile() {
        pile = new ArrayDeque<Integer>();
    }

    public Deque<Integer> getPile() {
        return pile;
    }

    public E push(E item){
        return pile.push(item);
    }

    public E pop(){
        if(pile.isEmpty()){
            throw new EmptyStackException();
        }
        return pile.pop();
    }

    public E peek(){
        if(pile.isEmpty()){
            throw new EmptyStackException();
        }
        return pile.peek();
    }

    public boolean add(E item){
        return pile.add(item);
    }

    public boolean isEmpty(){
        return pile.isEmpty();
    }

}
