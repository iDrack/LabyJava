import java.util.*;

/**
 * Interface Couloir. Représente un couloir. Permet d'encapsuler nos couloirs.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public interface Couloir {
    /**
     * Fixe l'orientation d'un couloir.
     * @param orientation Une orientation de type Orientation (Enum) du couloir.
     */
    public void setOrientation(Orientation orientation);
    
    /**
     * Permet d'obtenir l'orientation d'un couloir.
     * @return Une orientation de type Orientation (Enum) du couloir.
     */
    public Orientation getOrientation();

    /**
     * Permet d'obtenir la forme d'un couloir.
     * @return La forme de type Forme (Enum) du couloir.
     */
    public Forme getForme();

    /**
     * Permet d'obtenir l'objectif d'un couloir.
     * @return L'objectif de type Objectif (Enum) du couloir.
     */
    public Objectif getObjectif();

    /**
     * Permet d'obtenir la liste des pions d'un couloir.
     * @return Une ArrayList de Pion (ArrayList<Pion>) du couloir.
     */
    public ArrayList<Pion> getPions();

    /**
     * Permet d'ajouter un pion à un couloir.
     * @param p Pion 'p' que l'on souhaite ajouter/placer sur le couloir.
     */
    public void addPion(Pion p);

    /**
     * Permet de supprimer un pion à un couloir.
     * @param p Pion 'p' que l'on souhaite supprimer/retirer du couloir.
     */
    public void supPion(Pion p);

    /**
     * Méthode pour supprimer tout les pions présent sur le couloir.
     */
    public void suppToutPion();
}