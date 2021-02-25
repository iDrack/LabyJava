public class PionImpl implements Pion {
    private Couleur couleur;
    private Position positionInitiale;
    private Position positionCourante;
    private Plateau plateau;

    public PionImpl(Position pos, Couleur coul, Plateau plateau){
        this.positionInitiale = pos;
        this.positionCourante = pos;
        this.couleur = coul;
        this.plateau = plateau;
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

    @Override
    public Objectif deplacer(Position pos){
        // Le pion délègue son déplacement au plateau, qui a une vue globale de la situation : 
        // Il est le seul objet à pouvoir vérifier si un chemin existe entre la position du pion et la position destination.
        if(plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
            return plateau.getObjectifCase(pos);
        } else {
            System.out.println("Deplacement impossible !!"); 
            return null; // Permettra de recommencer le deplacement.
        }
        // Si déplacement effectuer, s'il y a un objectif alors on le retourne sinon retourne "null".
    }

    public void poserA(Position pos){
        //if(plateau.estAtteignable(positionCourante, pos)){ // A voir mais pour Dimitri c'est logique.
            plateau.déplacer(pos, this);
            positionCourante = pos;
        //} 
        
        // Fonction qui sert a poser le pion sur le couloir qu'on insère si le pion se fait sortir du "plateau de jeu".
    }

    public String toString(){
        return "[Position initiale : " + this.positionInitiale + "\n" +
                "Position courante : " + this.positionCourante +  "\n" +
                "Couleur du pion : " + this.couleur + "]";
    }
}
