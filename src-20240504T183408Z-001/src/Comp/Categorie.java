package Comp;

public enum Categorie{
    EOF,
    $,
    NUL,
    ID,
    NBR,
    PV,
    OPPAff,
    OPPRel,
    OPPArit,
    PAR,
    FLT,
    KEYWORD,
    ;
	


/*La méthode java string toLowerCase () renvoie la chaîne en minuscules. En d'autres termes,
il convertit tous les caractères de la chaîne en minuscules. */

    public String toString() {
        return this.name().toLowerCase();
    }
    /*
La méthode equalsIgnoreCase() compare deux chaînes en ignorant les différences entre
minuscules et majuscules et renvoie « true » si les chaînes sont égales sinon renvoie « false ».
*/
    public static Categorie toCategorie(String s) {
        for(Categorie c:Categorie.values())
            if(c.toString().equalsIgnoreCase(s))
                return c;
        return null;
    }
   

    /*La méthode ordinal() permet de retrouver le numéro d'ordre d'un élément énuméré,
     dans la liste de tous les éléments d'une énumération. Le premier numéro d'ordre est 0.
    */
    public boolean estTerminal() {
    	int MIN = 0;
    	int MAX = this.values().length - 1;

    	System.out.println(MIN);
    	System.out.println(MAX);
        return ordinal()>=MIN && ordinal()<=MAX;}
    

    public boolean estNonTerminal() {
    	int MIN = 0;
    	int MAX = this.values().length - 1;

    	System.out.println(MIN);
    	System.out.println(MAX);
        return ordinal()>MAX;
    }
    public static void main(String[] args) {
		System.out.println(Categorie.EOF.toString());
	}
}



