import java.util.*;

public class JeuImpl implements Jeu {
    private Plateau plateau = new Plateau();
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
        preparer();

        // TODO : tu peux le faire ici.
        // PS : Plateau fait tout en haut (directement au début : ligne 4, création et initialisation).

        this.joueurCourant = this.joueurs.get(0);
        for (Joueur joueur : this.joueurs){
            if (joueur.getAge() < this.joueurCourant.getAge() ){
                this.joueurCourant = joueur;
                // ...
            }
        }
        
        /*while(! aGagné(this.joueurCourant)){
            System.out.println(this.plateau.toString());
            joueurCourant.joue();
            joueurCourant = prochainJoueur();
        }*/

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
        // ..
    }

    @Override
    public void enregistrer(){
        // Donner un pion au joueur.
        Joueur joueur;
        //Scanner sc = new Scanner(System.in);
        Position pos = null;

        //Récupération des éléments afin de créer les joueurs
        this.nbJoueur = MenuCreation.getNbJoueurs();
        int[] listeAge = MenuCreation.listeAgeToInt();
        String[] listeCouleur = MenuCreation.listeCouleursToString();
        for(int i=0;i<nbJoueur;i++){
            System.out.println(listeAge[i]);
            System.out.println(listeCouleur[i]);
        }

        //Création des joueurs
        for(int i=0;i<nbJoueur;i++){
            //Génération de la position du pion du joueur
            if (i == 0) pos = new Position(0,0);
            else if (i == 1) pos = new Position(6,6);
            else if (i == 2) pos = new Position(0,6);
            else new Position(6,0);
            //Création du pion
            Pion pion = new PionImpl(pos, Couleur.getCouleur(listeCouleur[i]),this.plateau);
            //Création du joueur et ajout
            joueur = new JoueurImpl(pion, listeAge[i], this);
            this.joueurs.add(joueur);
        }

        preparer();
        //sc.close();
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

        // Initialise le couloir supplementaire !
        Orientation[] orientations = Orientation.values();
        Forme[] formes = Forme.values();
        Random r = new Random();
        int or, f;
        
        or = r.nextInt(Orientation.NB);
        f = r.nextInt(Forme.NB);
        this.supplementaire = new CouloirMobile(orientations[or], formes[f], Objectif.VIDE);
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


    public Plateau getPlateau(){
        return this.plateau;
    }

    public static void main(String[] args){
        Jeu jeu = new JeuImpl();
        System.out.println(jeu);
    }


}