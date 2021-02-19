import java.util.ArrayList;

public interface Jeu {

    public void modifierCouloirs(PositionInsertion pos);

    public void enregistrer(Joueur joueur, Couleur couleur);

    public ArrayList<Couloir> couloirs();
}