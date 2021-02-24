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
        Scanner sc = new Scanner(System.in);
        String expr = null;

        PositionInsertion positionInsertion = null;

        PositionInsertion[] tab = PositionInsertion.values();
        ArrayList<String> arrayStr = new ArrayList<String>();
        ArrayList<PositionInsertion> arrayPI = new ArrayList<PositionInsertion>();
        for (int i = 0; i < tab.length; i++){
            arrayStr.add(tab[i].toString());
            arrayPI.add(tab[i]);
        }

        System.out.println("Choisir la position d'insertion du couloir :");
        boolean choix = false;
        while(choix == false) {
            System.out.println("Choisir une position insertion : ");
            System.out.println(arrayStr.toString());
            System.out.print("Votre choix : ");
            expr = sc.nextLine();
            expr = expr.toUpperCase(); 
            
            if(arrayStr.contains(expr)){
                choix = true;
                positionInsertion = arrayPI.get(arrayStr.indexOf(expr));
            }
        }
        System.out.println();
        
        sc.close();
        return positionInsertion;
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
        String expr = "";
        while (!("NORD".equals(expr)) && !("SUD".equals(expr)) && !("EST".equals(expr)) && !("OUEST".equals(expr))){
                System.out.println("\nChoisissez l'orientation :");
                System.out.println("-NORD");
                System.out.println("-SUD");
                System.out.println("-EST");
                System.out.println("-OUEST");
                expr = sc.nextLine();
                expr = expr.toUpperCase();
        }

        sc.close();

        return Orientation.getOrientation(expr);
    }

    @Override
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
        //j.choisirOrientationCouloir();
        //j.choisirPositionInsertionCouloir();
        j.choisirPositionPion();
    }
}