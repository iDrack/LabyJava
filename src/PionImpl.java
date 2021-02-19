public class PionImpl implements Pion{
    Plateau plateau;
    private Position positionActuelle, posInitiale, positionCourante;
    private Couleur couleur;

    public PionImpl(Position pos, Couleur coul){
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
        if(Plateau.estAtteignable(positionCourante, pos)){
            plateau.deplacer(pos,this);
            positionCourante = pos; 
        }
    }
}
