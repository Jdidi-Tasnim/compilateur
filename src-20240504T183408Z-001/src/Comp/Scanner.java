package Comp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


/*La ArrayListclasse est un tableau redimensionnable , qui peut être trouvé dans le java.utilpackage.

La différence entre un tableau intégré et un tableau ArrayListen Java,
est que la taille d'un tableau ne peut pas être modifiée (si vous voulez ajouter ou supprimer des éléments dans / d'un tableau,
 vous devez en créer un nouveau).
Alors que des éléments peuvent être ajoutés et supprimés d'un ArrayListfichier quand vous le souhaitez.
*/

public class Scanner {
	
    private ArrayList<Character> fluxCaracteres; // Flux de caractères du fichier ou de l'entrée utilisateur
    private int indiceCourant; // Indice courant dans le flux de caractères
    private char caractereCourant; // Caractère courant
    private boolean eof; // Indicateur de fin de fichier
    private ArrayList<String> keywords; // Liste des mots-clés du langage
    private int nbl=0; // Nombre de lignes analysées
    
    // Constructeur par défaut
    public Scanner() {
        this("");
    }
 // Constructeur prenant en paramètre le nom du fichier à scanner
    public Scanner(String nomFich) {
    	
    	
    	keywords=new ArrayList<String>(
    		    Arrays.asList("if","then","else","while","do","for","new","true","false","return","class","package","tab","int","float","char"
    		    		,"string","print","et","ou"));
    	
    	
        BufferedReader f=null;
        int car=0;
        fluxCaracteres=new ArrayList<Character>();
        indiceCourant=0;
        eof=false;
        try {
            f=new BufferedReader(new FileReader(nomFich));
        }
        catch(IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f=new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while((car=f.read())!=-1)
                fluxCaracteres.add((char)car);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

 // Méthode pour obtenir le caractère suivant dans le flux
    public void caractereSuivant() {
		

        if(indiceCourant<fluxCaracteres.size())
            caractereCourant=fluxCaracteres.get(indiceCourant++);
        else 
            eof=true;
             
    }

    public void reculer() {
        if(indiceCourant>0)
            indiceCourant--;
    }
 // Méthode pour obtenir le lexème suivant dans le flux
   public UniteLexicale lexemeSuivant() {
		caractereSuivant(); 
		
		while(eof || Character.isWhitespace(caractereCourant)) { 
			
			if (eof) return new UniteLexicale(Categorie.EOF, "0");
			caractereSuivant();
		}
		
		if(Character.isLetter(caractereCourant))
			return getID();
		
		else if(Character.isDigit(caractereCourant))
			return getNombre();
		
		else if(caractereCourant==':')
			return getOPPAff();
		
		else if(caractereCourant==';')
			return new UniteLexicale(Categorie.PV, ";");
               
		else if(caractereCourant=='<' || caractereCourant=='>' ||caractereCourant=='=')
			return getOPPRel();
		
		else if(caractereCourant=='+'||caractereCourant=='-'||caractereCourant=='*'||
				caractereCourant=='/')
			return new UniteLexicale(Categorie.OPPArit,"OPPArit");
			
		else if(caractereCourant==')'||caractereCourant=='('||caractereCourant=='{'||caractereCourant=='}'
				||caractereCourant=='['||caractereCourant==']')
			return new UniteLexicale(Categorie.PAR,caractereCourant);
		else 
		{
		
		return new UniteLexicale(Categorie.NUL, caractereCourant);}
	}
	
// Méthode pour obtenir un identificateur ou un mot-clé
	public UniteLexicale getID() {
		int etat=0;
		
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
				case 0 : etat=1; 
						 sb.append(caractereCourant); 
						 break;
				case 1 : caractereSuivant();
						 if(eof)
							 etat=3;
						 else
							 if(Character.isLetterOrDigit(caractereCourant)) 
								 sb.append(caractereCourant);
						 
							 else
								 etat=2;
						 break;
				case 2 : reculer();
				         if (keywords.contains(sb.toString()))
				         return new UniteLexicale(Categorie.KEYWORD, sb.toString());
						 return new UniteLexicale(Categorie.ID,"id");
				
				case 3 : if (keywords.contains(sb.toString()))
			             return new UniteLexicale(Categorie.KEYWORD, sb.toString());
					     return new UniteLexicale(Categorie.ID,"id");
			}
		}
	}
	
	// Méthode pour obtenir un nombre
	public UniteLexicale getNombre() {
		int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) {
			case 0 : etat=1; 
					 sb.append(caractereCourant); 
					 break;
			case 1 : caractereSuivant();
					 if(eof) etat=3;
					 else if(Character.isDigit(caractereCourant)) 
							 sb.append(caractereCourant);
					 else if (caractereCourant=='.') {
						 caractereSuivant();
						 if(!Character.isDigit(caractereCourant))
							 
						 {
							 reculer();
						     etat=2;
						 }
						 else {
							 reculer();
							 sb.append(".");
							 
						 }
						 
					 } 
					 else etat=2;
					 break;
			case 2 : reculer();
			         if(sb.toString().contains("."))
					 return new UniteLexicale(Categorie.FLT,"FLT");
			         return new UniteLexicale(Categorie.NBR,"NBR");
			         
			case 3 : 
		         if(sb.toString().contains("."))
					 return new UniteLexicale(Categorie.FLT,"FLT");
		             return new UniteLexicale(Categorie.NBR,"NBR");
			}
		}
		
	}





	// Méthode pour obtenir l'opérateur d'affectation ":="
public UniteLexicale getOPPAff() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == ':') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;

                    } else
                        break;

                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                        
                    } else
                        break;
                
                case 2:
                    if (eof)
                        etat = 3;
                    else
                        etat = 5;
                case 3:
                   
                    return new UniteLexicale(Categorie.OPPAff,":=");
                case 4:
                    reculer();
                    return new UniteLexicale(Categorie.OPPAff,":=");
                
                     

            }

        }
}

//Méthode pour obtenir l'opérateur relationnel "<", ">", ou "="

public UniteLexicale getOPPRel() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                         etat = 1;

                          } else if (caractereCourant == '>') {
                               sb.append(caractereCourant);
                               caractereSuivant();
                               etat = 2;
                           }
                          else if (caractereCourant == '<') {
                               sb.append(caractereCourant);
                               caractereSuivant();
                               etat = 3;
                           }
                        else 
                        break;


                case 1:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        
                    else 
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        
                    
                
                case 2:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4 ;

                          } 
                         else
                             etat =5;

                   case 3:
                     if (eof)
                         break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 6 ;

                          } 
                      else if (caractereCourant == '>') {
                         sb.append(caractereCourant);
                         caractereSuivant();
                         etat = 7 ;

                          } 
                         else
                             etat =8;




                 case 4:
                    if (eof)
                    { return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        }
                        
                    else 
                    	reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
               case 5:
                    if (eof)
                    {return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        }
                    else 
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");

                case 6:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        }
                    else 
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                 case 7:
                    if (eof)
                    { return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                       }
                    else 
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                 case 8:
                    if (eof)
                        {return new UniteLexicale(Categorie.OPPRel, "OPPRel");
                        }
                    else 
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "OPPRel");

            }

        }
}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fluxCaracteres.toString();
	}
	
	}
/*to do: faire la table des mots cle et la table  */