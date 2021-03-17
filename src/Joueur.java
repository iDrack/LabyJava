import java.util.*;

/**
 * Interface Joueur. Représente le Joueur. Permet d'encapsuler notre joueur.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public interface Joueur {
    /**
     * Permet d'avoir l'age du joueur.
     * @return L'âge du joueur (int).
     */
    public int getAge();

    /**
     * Permet au joueur de jouer son tour.
     */
    public void joue();

    /**
     * Permet d'avoir la pile d'objectifs du joueur.
     * @return La pile (Stack<Objectif>) d'objectifs du joueur.
     */
    public Stack<Objectif> getStack();

    /**
     * Permet d'attribuer une pile d'objectif au joueur.
     * @param objectifs Pile d'objectifs 'objectifs' (Stack<Objectif>) à donner au joueur.
     */
    public void setStack(Stack<Objectif> objectifs);

    /**
     * Permet de choisir la position d'insertion du pion du joueur.
     * @return Position d'insertion souhaitée.
     */
    public Position choisirPositionPion();

    /**
     * Permet d'obtenir le pion du joueur.
     * @return Pion du joueur.
     */
    public Pion getPion();
    
    /**
     * Permet de chosir l'orientation du couloir pour le joueur.
     * @return Une orientation (Orientation).
     */
    public Orientation choisirOrientationCouloir();

    /**
     * Permet de choisir la position d'insertion du couloir pour le joueur.
     * @return Une position d'insertion (PositionInsertion).
     */
    public PositionInsertion choisirPositionInsertionCouloir();
}