public class Pion{
    private Position pos;
    private Couleur coul;
    private Position posInit;
    private Position positionCourante;

    public Pion(Position pos,Couleur coul){
        this.pos=pos;
        this.coul = coul;
    }
    public Objectif deplacer(Position pos){
        if (Plateau.estAtteignable(positionCourante,pos)){
            positionCourante = pos;
        }else{
            System.out.println("Deplacement impossible");
            
        }
        return null; // trouver quelle objectif retounée
    }
    public void poserA(Position pos){}
}