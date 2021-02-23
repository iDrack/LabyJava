public enum Couleur {
    ROUGE,VERT,BLEU,JAUNE;

    public static Couleur getCouleur(String str){
        switch(str){
            case "ROUGE" :
                return Couleur.ROUGE;
            case "VERT":
                return Couleur.VERT;
            case "JAUNE":
                return Couleur.JAUNE;
            case "BLEU":
                return Couleur.BLEU;
            default:
                return null;
        }
    }
}