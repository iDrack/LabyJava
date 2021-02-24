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
        Scanner sc = new Scanner(System.in);
        String expr ="";
        while (!("NORD".equals(expr)) && !("SUD".equals(expr)) && !("EST".equals(expr)) && !("OUEST".equals(expr))){
                System.out.println("\nChoisissez l'orientation :");
                System.out.println("-NORD");
                System.out.println("-SUD");
                System.out.println("-EST");
                System.out.println("-OUEST");
                expr= sc.nextLine();
                expr = expr.toUpperCase();
        }
        sc.close();
        return Orientation.getOrientation(expr);
    }

    public Stack<Objectif> getStack(){
        return this.objectifs;
    }

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

    public static void main(String[] args) {
        JoueurImpl j = new JoueurImpl(null, 10, null);
        j.choisirOrientationCouloir();
    }
}