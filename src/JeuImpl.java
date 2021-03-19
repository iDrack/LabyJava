import java.util.*;

/**
 * La classe JeuImpl représente le jeu du labyrinthe. 
 * Cette classe implémente l'interface Jeu.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class JeuImpl implements Jeu {
    /**
     * Plateau du jeu.
     */
    private Plateau plateau = new Plateau();
    /**
     * Liste de couloirs (ArrayList).
     */
    private ArrayList<Couloir> couloirs = new ArrayList<Couloir>();
    /**
     * Liste d'objectifs (ArrayList).
     */
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>();
    /**
     * Liste de pions (ArrayList).
     */
    private ArrayList<Pion> pions = new ArrayList<Pion>();
    /**
     * Couloir mobile d'insertion.
     */
    private CouloirMobile supplementaire;
    /**
     * Position de l'insertion réalisé au tour d'avant.
     */
    private PositionInsertion positionOrigine;
    /**
     * Liste de joueurs (ArrayList).
     */
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    /**
     * Le joueur courant.
     */
    private Joueur joueurCourant;
    /**
     * Nombre total de joueurs.
     */
    private int nbJoueur = 0;

    /**
     * Constructeur, prépare et enregistre le jeu.
     */
    public JeuImpl(){
        enregistrer();
        preparer();

        this.joueurCourant = this.joueurs.get(0);
        for (Joueur joueur : this.joueurs){
            if (joueur.getAge() < this.joueurCourant.getAge() ){
                this.joueurCourant = joueur;
            }
        }
    }

    @Override
    public void enregistrer(){
        Joueur joueur;
        Position pos = null;
            
        // Récupération des éléments afin de créer les joueurs.
        this.nbJoueur = MenuCreation.getNbJoueurs();
        int[] listeAge = MenuCreation.listeAgeToInt();
        String[] listeCouleur = MenuCreation.listeCouleursToString();
        for(int i = 0;i < nbJoueur; i++){
            System.out.println(listeAge[i]);
            System.out.println(listeCouleur[i]);
        }

        // Création des joueurs.
        for(int i = 0; i < nbJoueur; i++){
            // Génération de la position du pion du joueur.
            if (i == 0) pos = new Position(0,0);
            else if (i == 1) pos = new Position(6,6);
            else if (i == 2) pos = new Position(0,6);
            else pos = new Position(6,0);
            // Création du pion.
            Pion pion = new PionImpl(pos, Couleur.getCouleur(listeCouleur[i]),this.plateau);
            // Création du joueur et ajout.
            joueur = new JoueurImpl(pion, listeAge[i], this);
            this.joueurs.add(joueur);
        }

        preparer();
    }

    @Override
    public void modifierCouloirs(PositionInsertion pos, Orientation ori){
        if(true){
            this.supplementaire.setOrientation(ori);
            this.supplementaire = this.plateau.modifierCouloirs(pos, this.supplementaire);
            this.positionOrigine = pos.oppose();
            System.out.println("Insertion en " + pos.getPosition().toString());
            System.out.println("Opposé : " + this.positionOrigine.getPosition().toString());

            for (Pion pion : supplementaire.getPions()){
                System.out.println("Pion : " + pion.getCouleurPion().toString());
                
                this.plateau.addPionCouloir(pos.getPosition(), pion);
                pion.poserA(pos.getPosition());
            }

            supplementaire.suppToutPion();
        }
    }

    @Override
    public void preparer(){
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
    public void jouer() {
        if(aGagné(this.joueurCourant)){
            // Lancement de l'écran de victoire !
            VueJeu vue = MainWindow.instance.getMenuJeu();
            vue.getPage().setContentPane(new EcranFin(vue.getPage(), joueurCourant));
        } else {
            System.out.println(this.plateau.toString());
            joueurCourant = prochainJoueur();
            VueJeu vue = MainWindow.instance.getMenuJeu();
            vue.reset(false);
        }
    }

    @Override
    public Joueur prochainJoueur(){
        return this.joueurs.get((this.joueurs.indexOf(this.joueurCourant) + 1) % this.nbJoueur);
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

    @Override
    public Joueur getJoueur(){
        return this.joueurCourant;
    }

    @Override
    public Plateau getPlateau(){
        return this.plateau;
    }

    @Override
    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    @Override
    public CouloirMobile getSupplementaire(){
        return supplementaire;
    }

    /**
     * Affiche les données du jeu (nombre de joueurs, les joueurs).
     */
    public String toString(){        
        String chaine = "Nb joueurs : " + this.nbJoueur + "\n" +
                        "Joueurs : \n";
        for(int i = 0; i < this.nbJoueur; i++){
            chaine += "--> Joueur " + (i + 1) + " : " + joueurs.get(i) + "\n";
        }
        
        return chaine;
    }
}