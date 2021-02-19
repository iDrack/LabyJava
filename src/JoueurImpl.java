import java.util.*;

public class JoueurImpl implements Joueur {
    private Pion pion;
    private Stack<Objectif> objectifs = new Stack<Objectif>();
    private int age;
    private Jeu jeu;

    public JoueurImpl(Pion pion, int age, Jeu jeu){
        this.pion = pion;
        this.age = age;
        this.jeu = jeu;
    }

    @Override
    public int getAge(){ 
        return this.age;
    }

    public PositionInsertion choisirPositionInsertionCouloir(){
        return null;
    }

    public Position choisirPositionPion(){
        return null;
    }

    @Override
    public void joue(){
       jeu.modifierCouloirs(choisirPositionInsertionCouloir());
       Objectif objectif = pion.deplacer(choisirPositionPion()); 
       if (objectif == objectifs.peek()) {
           objectifs.pop();
       }
    }

    public Orientation choisirOrientationCouloir(){
        return null;
    }

    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs){
        // Todo
    }

    @Override
    public void recevoirPion(Pion p){
        // Todo
    }
}