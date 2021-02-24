// Orientation : NORD, SUD, EST, OUEST
public enum Orientation {
    NORD, SUD, EST, OUEST;
    public static final int NB = 4; 

    public static Orientation getOrientation(String str){
        switch(str){
            case "NORD" :
                return Orientation.NORD;
            case "SUD":
                return Orientation.SUD;
            case "EST":
                return Orientation.EST;
            case "OUEST":
                return Orientation.OUEST;
            default:
                return null;
        }
    }
}
