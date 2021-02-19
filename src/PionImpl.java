public class PionImpl implements Pion {
    private Position positionActuelle;
    private Couleur couleur;
    private Position posInitiale;
    private Position positionCourante;
    private Plateau plateau;

    public PionImpl(Position pos, Couleur coul){
        this.positionActuelle = pos;
        this.couleur = coul;
    }

    @Override
    public Objectif deplacer(Position pos){
        if (Plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
        }else{
            System.out.println("Deplacement impossible !!");
        }

        return null; // Trouver l'objectif à retouner !!
    }

    public void poserA(Position pos){
        if(Plateau.estAtteignable(positionCourante, pos)){
            plateau.déplacer(pos, this);
            positionCourante = pos;
        }
        // Todo
    }
}
