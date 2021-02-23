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
}
