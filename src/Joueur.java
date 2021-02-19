import java.util.*;

public class Joueur{
    private Pion pion;
    private Stack<Objectif> objectifs = new Stack<Objectif>();
    private int age;
    private Jeu jeu;

    public Joueur(Pion pion,int age,Jeu jeu){
        this.pion = pion;
        this.age=age;
        this.jeu = jeu;
    }
    public int getAge(){ return this.age;}
    public PositionInsertion choisirPositionInsertionCouloir(){
        return null;
    }
    public Position choisirPositionPion(){
        return null;
    }
    public void joue(){
       jeu.modifierCouloirs(choisirPositionInsertionCouloir());
       Objectif objectif = pion.deplacer(choisirPositionPion()); 
       if (objectif == objectifs.peek()) {
           objectifs.pop();
       }
    }
    

}