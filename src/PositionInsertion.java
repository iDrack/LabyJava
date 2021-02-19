public enum PositionInsertion{ 
    N1,N2,N3,E1,E2,E3,S3,S2,S1,O3,O2,O1;


    public PositionInsertion oppose(){
        PositionInsertion[] tab = {N1,N2,N3,E1,E2,E3,O3,O2,O1,E3,S3,S2,S1};
        for(int i=0;i<tab.length;i++){
            if (tab[i] == this ){
                return tab[12-i-1];
            }
        }
        return null;
    } 

}
    
