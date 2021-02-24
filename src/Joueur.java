import java.util.*;

public interface Joueur {
    public int getAge();

    public void joue();

    public Stack<Objectif> getStack();

    public void setStack(Stack<Objectif> objectifs);
}