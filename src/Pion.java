public interface Pion {
    public Objectif deplacer(Position pos);

    public void poserA(Position pos);

    public Position getPositionInitiale();

    public Couleur getCouleurPion();

    public Position getPositionActuelle();
}
