import java.util.*;

public class JeuImpl implements Jeu {
    private Plateau plateau;
    private ArrayList<Couloir> couloirs = new ArrayList<Couloir>();
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>();
    private ArrayList<Pion> pions = new ArrayList<Pion>();
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    public JeuImpl(){
        int min = 110;
        Joueur joueurC = joueurs.get(0);
        for (Joueur joueur : joueurs){
            if (joueur.getAge() < min ){
                min = joueur.getAge();
                joueurC = joueur;
                // ...
            }
        }

        /*
            Joueur joueur;
            do {
                joueur = prochainJoueur;
                joueur.joué();
            } while(! aGagné(joueur))
        */

        // ...
    }

    public Joueur prochainJoueur(){
        return null;
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
    public void enregistrer(Joueur joueur, Couleur couleur){
        // Todo

        // Donner un pion au joueur.
    }

    public void preparer(){
        // Todo

        // Distribuer les objectifs aux joueurs et mettres les couloirs mobiles sur la plateau.
    }

    public boolean aGagné(Joueur joueur){
        // Todo
        return false;
    }

    @Override
    public ArrayList<Couloir> couloirs(){
        return null;
    }

    @Override
    public void jouer() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean aGagne(Joueur j) {
        // TODO Auto-generated method stub
        return false;
    }
}