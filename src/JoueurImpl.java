import java.util.*;

/**
 * La classe JoueurImpl représente un joueur du jeu du labyrinthe. 
 * Cette classe implémente l'interface Joueur.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class JoueurImpl implements Joueur {
    /**
     * Pion du joueur.
     */
    private Pion pion;
    /**
     * Pile d'objectifs du joueur.
     */
    private Stack<Objectif> objectifs = new Stack<Objectif>();
    /**
     * Âge du joueur.
     */
    private int age;
    /**
     * Jeu lié au joueur.
     */
    private Jeu jeu;

    /**
     * Constructeur, initialise le joueur (pion, âge, avec un jeu).
     * @param pion Pion 'pion' (Pion) du joueur.
     * @param age Âge 'age' du joueur (int).
     * @param jeu Le jeu 'jeu' (Jeu) lié au joueur.
     */
    public JoueurImpl(Pion pion, int age, Jeu jeu){
        this.pion = pion;
        this.age = age;
        this.jeu = jeu;
    }

    @Override
    public int getAge(){ 
        return this.age;
    }

    @Override
    public void joue(){
        System.out.println(objectifs.peek());

        //jeu.modifierCouloirs(choisirPositionInsertionCouloir(), choisirOrientationCouloir());    //Modification du couloir

        Objectif objectif = pion.deplacer(choisirPositionPion());   //Déplacement du pion

        while (objectif == null){
            System.out.println("Pion : " + this.pion.getCouleurPion() + ".");
            objectif = pion.deplacer(choisirPositionPion());
        }

        if (objectif == objectifs.peek()) {
            objectifs.pop();
        }
    }

    @Override
    public Stack<Objectif> getStack(){
        return this.objectifs;
    }

    @Override
    public void setStack(Stack<Objectif> objectifs){
        this.objectifs = objectifs;
    }

    @Override
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
    public Pion getPion(){
        return this.pion;
    }
    
    @Override
    public Orientation choisirOrientationCouloir(){
        VueJeu vue = MainWindow.instance.getMenuJeu();
        return Orientation.getOrientation(vue.getOrientation());
    }

    @Override
    public PositionInsertion choisirPositionInsertionCouloir(){
        System.out.println("Position couloir : ");
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
    
    /**
     * Permet d'obtenir le jeu lié au joueur.
     * @return Le jeu (Jeu).
     */
    public Jeu getJeu(){
        return this.jeu;
    }
    /**
     * Affiche les informations du joueur (pion, âge, objectifs).
     */
    public String toString(){
        String chaine = "Pion " + this.pion.getCouleurPion() + ", " +
                        "age : " + this.age + ", " +
                        "objectifs (" + this.objectifs.size() + "): \n \t" + 
                        this.objectifs.toString() + ".";
        
        return chaine;
    }
}