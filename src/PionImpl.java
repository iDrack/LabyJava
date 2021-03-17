/**
 * La classe PionImpl représente le pion d'un joueur. 
 * Cette classe implémente l'interface Pion.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class PionImpl implements Pion {
    /**
     * Couleur du pion.
     */
    private Couleur couleur;
    /**
     * Position initiale du pion (début de jeu).
     */
    private Position positionInitiale;
    /**
     * Position courante.
     */
    private Position positionCourante;
    /**
     * Plateau où il y a le pion.
     */
    private Plateau plateau;

    /**
     * Constructeur, initialise le pion.
     * @param pos Position 'pos' (Position).
     * @param coul Couleur 'coul' (Enum).
     * @param plateau Plateau 'plateau' (Plateau).
     */
    public PionImpl(Position pos, Couleur coul, Plateau plateau){
        this.positionInitiale = pos;
        this.positionCourante = pos;
        this.couleur = coul;
        this.plateau = plateau;
    }

    @Override
    public Objectif deplacer(Position pos){
        // Le pion délègue son déplacement au plateau, qui a une vue globale de la situation : 
        // Il est le seul objet à pouvoir vérifier si un chemin existe entre la position du pion et la position destination.
        if(plateau.estAtteignable(positionCourante, pos)){
            plateau.déplacer(pos,this);
            positionCourante = pos;
            System.out.println(plateau.getCouloirImpls()[getPositionCourante().getX()][getPositionCourante().getY()].getPions().toString());
            return plateau.getObjectifCase(pos);
        } else {
            System.out.println("Deplacement impossible !!"); 
            return null; // Permettra de recommencer le deplacement.
        }
        // Si déplacement effectuer, s'il y a un objectif alors on le retourne sinon retourne "null".
    }

    @Override
    public void poserA(Position pos){
        //if(plateau.estAtteignable(positionCourante, pos)){ // A voir mais pour Dimitri c'est logique.
           // plateau.déplacer(pos, this);
            positionCourante = pos;
            System.out.println(plateau.getCouloirImpls()[getPositionCourante().getX()][getPositionCourante().getY()].getPions().toString());
        //} 
    }

    @Override
    public Position getPositionInitiale(){
        return this.positionInitiale;
    }

    @Override
    public Couleur getCouleurPion(){
        return this.couleur;
    }

    @Override
    public Position getPositionCourante(){
        return this.positionCourante;
    }

    /**
     * Affiche les informations d'un pion (position initiale, courante, couleur).
     */
    public String toString(){
        return "[Position initiale : " + this.positionInitiale + "\n" +
                "Position courante : " + this.positionCourante +  "\n" +
                "Couleur du pion : " + this.couleur + "]";
    }
}
