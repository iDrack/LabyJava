import java.util.ArrayList;

public class Jeu {
    private Plateau plateau;
    private ArrayList<Couloir> couloirs = new ArrayList<Couloir>();
    private ArrayList<Objectif> objectifs = new ArrayList<Objectif>();
    private ArrayList<Pion> pions = new ArrayList<Pion>();
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    public Jeu(){
        int min = 110;
        Joueur joueurC = joueurs.get(0);
        for (Joueur joueur : joueurs){
            if (joueur.getAge() < min ){
                min = joueur.getAge();
                joueurC = joueur;
                // ...
            }
        }

        // ...
    }

    public void modifierCouloirs(PositionInsertion pos){
        if(pos != positionOrigine){
            supplementaire = plateau.modifierCouloirs(pos,supplementaire);
            positionOrigine = pos.oppose();
            for (Pion pion : supplementaire.getPion()){
                pion.poserA(pos);
            }
        }
    }

    public void preparer(){
        // Todo
    }

    public void jouer(){
        Joueur joueur;
        do {
            joueur = prochainJoueur();
            joueur.joue();
        } while(! aGagne(joueur));
    }

    public Joueur prochainJoueur(){
        // Todo
    }

    public Boolean aGagne(Joueur j){
        // Todo
    }
}