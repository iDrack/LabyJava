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

        this.joueurCourant = this.joueurs.get(0);
        for (Joueur joueur : this.joueurs){
            if (joueur.getAge() < this.joueurCourant.getAge() ){
                this.joueurCourant = joueur;
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
        return this.joueurs.get((this.joueurs.indexOf(this.joueurCourant) + 1) % this.nbJoueur);
    }

    @Override
    public void modifierCouloirs(PositionInsertion pos){
        if(pos != this.positionOrigine){
            this.supplementaire = this.plateau.modifierCouloirs(pos, this.supplementaire);
            this.positionOrigine = pos.oppose();
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
            this.nbJoueur = Integer.parseInt(sc.nextLine());
        }while(this.nbJoueur <= 0 || this.nbJoueur > 4);
        System.out.println();

        Couleur[] tabCouleurs = Couleur.values();
        ArrayList<String> arrayCouleurs = new ArrayList<String>();
        for (int i = 0; i < tabCouleurs.length; arrayCouleurs.add(tabCouleurs[i++].toString()));

        for(int i = 0; i < this.nbJoueur; i++){
            System.out.println("Joueur n°" + (i + 1) + ".");
            boolean choixCouleur = false;
            while(choixCouleur == false){
                System.out.println("Choisir une couleur ci-dessous : ");
                System.out.println(arrayCouleurs.toString());
                System.out.print("Votre choix : ");
                expr = sc.nextLine();
                expr = expr.toUpperCase(); 

                if(arrayCouleurs.contains(expr)){
                    choixCouleur = true;
                    arrayCouleurs.remove(expr);
                }
            }
                
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
            this.joueurs.add(joueur);
        }

        preparer();

        sc.close();
    }

    @Override
    public void preparer(){
        // Distribuer les objectifs aux joueurs et mettres les couloirs mobiles sur la plateau.

        // On a déjà mis les couloirs mobiles sur le plateau ..
        // On va donc distrubuer les objectifs : "Mélangé et distribué le même nombre à chaque joueur."
        Objectif[] tabObjectifs = Objectif.values();
        ArrayList<Objectif> arrayObjectifs = new ArrayList<Objectif>();
        for (int i = 0; i < (tabObjectifs.length - 1); arrayObjectifs.add(tabObjectifs[i++]));
        Collections.shuffle(arrayObjectifs);

        int nbObjParJoueur = arrayObjectifs.size() / nbJoueur;
        // Pour chaque joueur :
        for(int i = 0; i < this.nbJoueur; i++){
            Joueur leJoueur = this.joueurs.get(i);
            Stack<Objectif> objectifsJoueur = leJoueur.getStack();
            // Pour chaque objectif à donner :
            for(int j = 0; j < nbObjParJoueur; j++){
                Random r = new Random();
                int obj = r.nextInt(arrayObjectifs.size());
                objectifsJoueur.add(arrayObjectifs.get(obj));
                arrayObjectifs.remove(obj);
            }
            leJoueur.setStack(objectifsJoueur);
        }
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
            chaine += "--> Joueur " + (i + 1) + " : " + joueurs.get(i) + "\n";
        }
        
        return chaine;
    }

    public Joueur getJoueur(){
        return this.joueurCourant;
    }

    public static void main(String[] args){
        Jeu jeu = new JeuImpl();
        System.out.println(jeu);

        jeu.getJoueur().choisirPositionPion();
    }
}