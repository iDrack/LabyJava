public class PionImpl implements Pion {
    private Position positionActuelle;
    private Couleur couleur;
    private Position positionInitiale;
    private Position positionCourante;
    private Plateau plateau;

    public PionImpl(Position pos, Couleur coul){
        this.positionInitiale = pos;
        this.positionActuelle = pos;
        this.couleur = coul;
    }

    public Position getPositionInitiale(){
        return this.positionInitiale;
    }

    public Couleur getCouleurPion(){
        return this.couleur;
    }

    public Position getPositionActuelle(){
        return this.positionActuelle;
    }

    @Override
    public Objectif deplacer(Position pos){
        if(plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
        } else {
            System.out.println("Deplacement impossible !!"); // Recommencer le déplacement ?!
        }

        return null; // Trouver l'objectif à retouner !!
        // Si déplacement effectuer, s'il y a un objectif alors on le retourne sinon retourne "null".
    }

    public void poserA(Position pos){
        // Le pion délègue son déplacement au plateau, qui a une vue globale de la situation : 
        // Il est le seul objet à pouvoir vérifier si un chemin existe entre la position du pion et la position destination.
        if(plateau.estAtteignable(positionCourante, pos)){
            plateau.déplacer(pos, this);
            positionCourante = pos;
        } // Sinon, recommencer ?! 
        
        // Todo
    }
}
