import java.util.*;

public interface Joueur {
    public int getAge();

    public void joue();

    public void fixerObjectifs(Stack<Objectif> objectifs);

    public void recevoirPion(Pion p);

    public Stack<Objectif> getStack();
}