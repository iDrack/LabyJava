import java.util.*;

/**
 * Interface Jeu. Représente le Jeu du labyrinth. Permet d'encapsuler notre jeu.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public interface Jeu {
    /**
     * Permet de demander le nombre de joueur et de les initialiser.
     */
    public void enregistrer();

    /**
     * Permet de modifier la position et l'orientation d'un couloir.
     * @param pos Nouvelle PositionInsertion 'pos' (Enum) du couloir.
     * @param ori Nouvelle Orientation 'ori' (Enum) du couloir.
     */
    public void modifierCouloirs(PositionInsertion pos, Orientation ori);

    /**
     * Attribut les objectifs au(x) joueur(s) et récupére le couloir disponible pour insertion.
     */
    public void preparer();

    /**
     * Permet de faire jourer le(s) joueur(s) jusqu'à la victoire.
     */
    public void jouer();

    /**
     * Permet d'obtenir le prochain joueur qui doit jouer.
     * @return Le joueur (Joueur) prochain.
     */
    public Joueur prochainJoueur();

    /**
     * Permet de vérifier si le joueur en paramètre a gagné ou non.
     * @param joueur Le joueur (Joueur) a vérifier.
     * @return Un boolean : vrai (true) si le joueur a gagné, non (false) sinon.
     */
    public boolean aGagné(Joueur joueur);

    /**
     * Permet d'avoir la liste des couloirs du jeu.
     * @return ArrayList de 'Couloir'.
     */
    public ArrayList<Couloir> couloirs();

    /**
     * Permet d'avoir la liste des objectifs du jeu.
     * @return ArrayList de 'Objectif'.
     */
    public ArrayList<Objectif> objectifs();

    /**
     * Permet d'avoir la liste des pions du jeu.
     * @return ArrayList de 'Pion'.
     */
    public ArrayList<Pion> pions();

    /**
     * Permet d'obtenir les joueurs.
     * @return Le joueur (Joueur).
     */
    public Joueur getJoueur();

    /**
     * Permet d'obtenir le plateau.
     * @return Le plateau (Plateau).
     */
    public Plateau getPlateau();

    /**
     * Permet d'avoir la liste des joueurs du jeu.
     * @return ArrayList de 'Joueur'.
     */
    public ArrayList<Joueur> getJoueurs();

    /**
     * Permet de récupéré le couloir mobile disponible pour l'insertion.
     * @return Le couloir mobile pour insertion (CouloirMobile).
     */
    public CouloirMobile getSupplementaire();
}