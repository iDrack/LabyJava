import java.util.ArrayList;

public interface Jeu {


    public void enregistrer(Joueur joueur, Couleur couleur);

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
    public ArrayList<Couloir> couloirs();
}