import java.util.*;

public interface Jeu {
    public void enregistrer();

    public void modifierCouloirs(PositionInsertion pos, Orientation ori);

    public void preparer();

    public void jouer();

    public Joueur prochainJoueur();

    public boolean aGagné(Joueur joueur);

    public ArrayList<Couloir> couloirs();

    public ArrayList<Objectif> objectifs();

    public ArrayList<Pion> pions();

    public Joueur getJoueur();

    public Plateau getPlateau();

    public ArrayList<Joueur> getJoueurs();

    public CouloirMobile getSupplementaire();
}