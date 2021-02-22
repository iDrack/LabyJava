import java.util.*;

public interface Jeu {
    public void enregistrer(Joueur joueur, Couleur couleur);

    public void modifierCouloirs(PositionInsertion pos);

    public void preparer();

    public void jouer();

    public Joueur prochainJoueur();

    public boolean aGagne(Joueur j);

    public ArrayList<Couloir> couloirs();
}