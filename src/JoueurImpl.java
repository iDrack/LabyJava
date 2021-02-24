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
        System.out.println("Position x :");
        Scanner sc = new Scanner(System.in);

        int x = Integer.parseInt(sc.nextLine());

        System.out.println(x);

        sc.close();
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

    @Override
    public Stack<Objectif> getStack(){
        return this.objectifs;
    }

    @Override
    public void setStack(Stack<Objectif> objectifs){
        this.objectifs = objectifs;
    }

    public String toString(){
        String chaine = "Pion " + this.pion.getCouleurPion() + ", " +
                        "age : " + this.age + ", " +
                        "objectifs (" + this.objectifs.size() + "): \n \t" + 
                        this.objectifs.toString() + ".";
        
        return chaine;
    }
}