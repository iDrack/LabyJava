public class Pion {
    private Position positionActuelle;
    private Couleur couleur;
    private Position posInitiale;
    private Position positionCourante;

    public Pion(Position pos, Couleur coul){
        this.positionActuelle = pos;
        this.couleur = coul;
    }

    public Objectif deplacer(Position pos){
        if (Plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
        }else{
            System.out.println("Deplacement impossible !!");
        }

        return null; // Trouver l'objectif à retouner !!
    }

    public void poserA(Position pos){
        // Todo
    }
}