/**
 * Interface Pion. Représente le pion d'un joueur. Permet d'encapsuler nos pions.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public interface Pion {
    /**
     * Permet de déplacer le pion lors d'une partie.
     * @param pos Position d'insertion du pion (Position).
     * @return Retourne un objectif, retourne "VIDE" (un objectif, Enum) s'il n'y a pas d'objectif : ça évite de mettre null.
     */
    public Objectif deplacer(Position pos);

    /**
     * Permet de poser le pion sur le couloir qu'on insère, 
     * si le pion se fait sortir du plateau de jeu.
     * @param pos Position d'insertion du pion (Position).
     */
    public void poserA(Position pos);

    /**
     * Permet d'avoir la position initiale du pion (pts de départ).
     * @return Position initiale (Position).
     */
    public Position getPositionInitiale();

    /**
     * Permet d'avoir la couleur du pion.
     * @return La couleur (Enum).
     */
    public Couleur getCouleurPion();

    /**
     * Permet d'avoir la position courante du pion.
     * @return La position courante (Position).
     */
    public Position getPositionCourante();
}
