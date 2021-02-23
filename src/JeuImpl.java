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
    private int nbJoueur=0;

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

    public Joueur prochainJoueur(){
        return joueurs.get((joueurs.indexOf(joueurCourant) +1) % nbJoueur);
    }

    @Override
    public void modifierCouloirs(PositionInsertion pos){
        if(pos != positionOrigine){
            supplementaire = plateau.modifierCouloirs(pos,supplementaire);
            positionOrigine = pos.oppose();
            for (Pion pion : supplementaire.getPions()){
                pion.poserA(pos.getPosition());
            }
        }
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
            System.out.println("Nombre de joueur a jouer");
            nbJoueur = Integer.parseInt(sc.nextLine());
        }while(nbJoueur <= 0 || nbJoueur >4);

        for(int i = 0; i < nbJoueur; i++){

            do {
                System.out.println("Entrez une couleur :");
                System.out.println("-Bleu ");
                System.out.println("-Rouge ");
                System.out.println("-Vert");
                System.out.println("-Jaune ");
                expr = sc.nextLine();
                expr = expr.toUpperCase(); 
            }while (!("BLEU".equals(expr)) && !("JAUNE".equals(expr)) && !("VERT".equals(expr)) && !("ROUGE".equals(expr)));
                
            do {
                System.out.println("Entrez votre age :");
                age = Integer.parseInt(sc.nextLine());
            }while (age < 8);

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

    public void preparer(){
    
        // Distribuer les objectifs aux joueurs et mettres les couloirs mobiles sur la plateau.
    }

    public boolean aGagné(Joueur joueur){
       return joueur.getStack().empty();
    }

    @Override
    public ArrayList<Couloir> couloirs(){
        return null;
    }

    @Override
    public void jouer() {
      

    }

    @Override
    public boolean aGagne(Joueur j) {
        
        return false;
    }
    public static void main(String[] args) {
        Jeu jeu = new JeuImpl();
    }
}