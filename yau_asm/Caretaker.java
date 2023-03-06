import java.util.Stack;

public class Caretaker {
    private Stack<Memento> mementosList;

    public Caretaker() {
        this.mementosList = new Stack<>();
    }

    public void add_Memento(Memento memento){
        mementosList.add(memento);
    }

    public Memento get_Memento() {
        return mementosList.pop();
    }
}
