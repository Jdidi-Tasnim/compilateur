package Comp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Affichage de la version du compilateur
        System.out.println("Compilateur version 0.0 par Ahmad/Tasnim/Achraf");
        System.out.println();
        
        // Demande du nom du fichier contenant le code à compiler
        System.out.print("Veuillez entrer le nom du fichier contenant le code à compiler : ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        
        // Création de l'instance du compilateur syntaxique
        A_syn compiler = new A_syn();
        // Lancement de l'analyse lexicale et syntaxique du fichier
        compiler.analyzeSLnew(fileName);
    }
}
