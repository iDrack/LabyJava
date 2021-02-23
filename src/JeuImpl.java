import java.util.*;

public class JeuImpl implements Jeu {
    private Plateau plateau;
    private ArrayList<Couloir> couloirs = new ArrayList<Couloir>();
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>();
    private ArrayList<Pion> pions = new ArrayList<Pion>();
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Joueur joueurCourant;
    private int nbJoueur = 0;

    public JeuImpl(){
        enregistrer();

        joueurCourant = joueurs.get(0);
        for (Joueur joueur : joueurs){
            if (joueur.getAge() < joueurCourant.getAge() ){
                joueurCourant = joueur;
                // ...
            }
        }
        /*
            while(! aGagner(JoueurCourant)){
                joueurCourant.joué();
                joueurCourant = prochainJoueur();
            }
           
        */

        // ...
    }

    @Override
    public Joueur prochainJoueur(){
        return joueurs.get((joueurs.indexOf(joueurCourant) + 1) % nbJoueur);
    }

    @Override
    public void modifierCouloirs(PositionInsertion pos){
        if(pos != positionOrigine){
            supplementaire = plateau.modifierCouloirs(pos,supplementaire);
            positionOrigine = pos.oppose();
            /*
            for (Pion pion : supplementaire.getPions()){
                pion.poserA(pos.getPosition());
            }
            */
        }
    }

    @Override
    public void jouer() {
        // Demande où il souhaite aller, etc.
        // ..
    }

    @Override
    public void enregistrer(){
        // Donner un pion au joueur.
        Joueur joueur;
        Scanner sc = new Scanner(System.in);
        String expr = null;
        int age;
        Position pos = null;

        do {
            System.out.println("Nombre de joueur a jouer : ");
            nbJoueur = Integer.parseInt(sc.nextLine());
        }while(nbJoueur <= 0 || nbJoueur >4);
        System.out.println();

        for(int i = 0; i < nbJoueur; i++){
            System.out.println("Joueur n°" + (i + 1) + ".");
            do {
                System.out.println("Choisir une couleur : ");
                System.out.println("- Bleu \t - Rouge");
                System.out.println("- Vert \t - Jaune");
                System.out.print("Votre choix : ");
                expr = sc.nextLine();
                expr = expr.toUpperCase(); 
            } while (!("BLEU".equals(expr)) && !("JAUNE".equals(expr)) && !("VERT".equals(expr)) && !("ROUGE".equals(expr)));
                
            do {
                System.out.print("Entrez votre age (mini 8) : ");
                age = Integer.parseInt(sc.nextLine());
            } while (age < 8);
            System.out.println();

            if (i == 0) {pos = new Position(0,0);}
            else if (i == 1) {pos = new Position(6,6);}
            else if (i == 2) {pos = new Position(0,6);}
            else {new Position(6,0);}

            Pion pion = new PionImpl(pos, Couleur.getCouleur(expr));
            joueur = new JoueurImpl(pion, age, this);
            joueurs.add(joueur);
        }

        sc.close();
    }

    @Override
    public void preparer(){
        // Distribuer les objectifs aux joueurs et mettres les couloirs mobiles sur la plateau.

        // On a déjà mis les couloirs mobiles sur le plateau ..
    }

    @Override
    public boolean aGagné(Joueur joueur){
       return joueur.getStack().empty();
    }

    @Override
    public ArrayList<Couloir> couloirs(){
        return this.couloirs;
    }

    @Override
    public ArrayList<Objectif> objectifs() {
        return this.objectifs;
    }

    @Override
    public ArrayList<Pion> pions() {
        return this.pions;
    }

    public String toString(){        
        String chaine = "Nb joueurs : " + this.nbJoueur + "\n" +
                        "Joueurs : \n";
        for(int i = 0; i < this.nbJoueur; i++){
            chaine += "Joueur " + (i + 1) + " : " + joueurs.get(i) + "\n";
        }
        
        return chaine;
    }

    public static void main(String[] args){
        Jeu jeu = new JeuImpl();
        System.out.println(jeu);
    }
}