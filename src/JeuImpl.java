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
    private boolean graphique = false;

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
        
        jouer();

        // ...
    }

    @Override
    public Joueur prochainJoueur(){
        return this.joueurs.get((this.joueurs.indexOf(this.joueurCourant) + 1) % this.nbJoueur);
    }

    @Override
    public void modifierCouloirs(PositionInsertion pos){
       // if(pos != this.positionOrigine){
            if(true){
            
            this.supplementaire = this.plateau.modifierCouloirs(pos, this.supplementaire);
            this.positionOrigine = pos.oppose();
            System.out.println("on insere a la position " + pos.getPosition().toString());
            System.out.println("l'oppose de l insertion est : " + pos.oppose().getPosition().toString());

            for (Pion pion : supplementaire.getPions()){
                System.out.println("pion de couleur " + pion.getCouleurPion().toString());
                
                
                this.plateau.addPionCouloir(pos.getPosition(), pion);
                pion.poserA(pos.getPosition());
                
            }

            supplementaire.suppToutPion();
            
        }
    }

    @Override
    public void jouer() {
        while(! aGagné(this.joueurCourant)){
            System.out.println(this.plateau.toString());
            joueurCourant.joue();
            joueurCourant = prochainJoueur();
        }
    }

    @Override
    public void enregistrer(){

        
        // Donner un pion au joueur.
        Joueur joueur;
        
        Position pos = null;
        if (graphique){
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
        }
//      Je laisse l'ancien code en comm, on sait jamais si cela peut servir plus tard
        else{
            Scanner sc = new Scanner(System.in);
            int age;
            String expr="";
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

                if (i == 0) {
                    pos = new Position(0,0); 
                }
                else if (i == 1) {pos = new Position(6,6);}
                else if (i == 2) {pos = new Position(0,6);}
                else {new Position(6,0);}

                Pion pion = new PionImpl(pos, Couleur.getCouleur(expr),this.plateau);
                joueur = new JoueurImpl(pion, age, this);
                pions.add(pion);
                this.plateau.addPionCouloir(pos,pion );
                this.joueurs.add(joueur);
            }
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