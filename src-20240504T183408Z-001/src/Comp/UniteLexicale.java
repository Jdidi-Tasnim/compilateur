package Comp;

public class UniteLexicale {
    private Categorie categorie; // Catégorie de l'unité lexicale
    private Object lexeme; // Lexème de l'unité lexicale (peut être de n'importe quel type)

    // Constructeur de la classe UniteLexicale
    public UniteLexicale(Categorie categorie, Object lexeme) {
        this.categorie = categorie; // Initialise la catégorie de l'unité lexicale
        this.lexeme = lexeme; // Initialise le lexème de l'unité lexicale
    }

    // Méthode permettant d'obtenir la catégorie de l'unité lexicale
    public Categorie getCategorie() {
        return categorie;
    }

    // Méthode permettant de retourner une représentation textuelle de l'unité lexicale
    public String toString() {
        return "<" + categorie.toString() + "," + lexeme + ">"; // Format : <catégorie,lexème>
    }
}

