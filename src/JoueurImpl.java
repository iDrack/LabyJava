import java.util.*;

import javax.swing.JPanel;

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
        System.out.println("Position couloir");

        String expr = null;
        PositionInsertion positionInsertion = null;

        PositionInsertion[] tab = PositionInsertion.values();
        ArrayList<String> arrayStr = new ArrayList<String>();
        ArrayList<PositionInsertion> arrayPI = new ArrayList<PositionInsertion>();
        for (int i = 0; i < tab.length; i++){
            arrayStr.add(tab[i].toString());
            arrayPI.add(tab[i]);
        }
        boolean choix = false;
        while(choix == false) {
            VueJeu vue = MainWindow.instance.getMenuJeu();
            expr = vue.getPosCouloir();
            if(arrayStr.contains(expr)){
                choix = true;
                positionInsertion = arrayPI.get(arrayStr.indexOf(expr));
            }
        }
        return positionInsertion;
    }
    public Pion getPion(){
        return this.pion;
    }
    public Position choisirPositionPion(){
        int x = -1, y = -1;

        while ((x < 0 || x > 6) || (y < 0 || y > 6)){
            VueJeu vue = MainWindow.instance.getMenuJeu();
            x = vue.getPosX();
            y = vue.getPosY();
        }
        return new Position(x,y);
    }

    @Override
    public void joue(){
        System.out.println(objectifs.peek());
        Position posAv = pion.getPositionCourante();
        System.out.println("Pion : " + this.pion.getCouleurPion() + ".");

        jeu.modifierCouloirs(choisirPositionInsertionCouloir());    //Modification du couloir

        Objectif objectif = pion.deplacer(choisirPositionPion());   //Déplacement du pion ?

        while (objectif == null){
            System.out.println("Pion : " + this.pion.getCouleurPion() + ".");
            objectif = pion.deplacer(choisirPositionPion());
        }
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

        //sc.close();
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
        //JoueurImpl j = new JoueurImpl(null, 10, null);
        //Position p = new Position(0,0);
        //j.choisirPositionPion();
        
        //j.choisirOrientationCouloir();
        //j.choisirPositionInsertionCouloir();
    }
}