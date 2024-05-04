package Comp;

import java.security.Principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class A_syn {


	Data data=new Data();

    public String[] LRGS = data.LRGS;
    public String[][] tableSLR =data.tableSLR;
 
    
    public Stack<String> stackState = new Stack<>();
    
    
    public Stack<String> analyse = new Stack<>();
    
    public Stack<String> stackSymbol = new Stack<>();
    ArrayList<String> ch=new ArrayList<String>();
    public String strInput ;
    
   
    public String action = "";
   
    
    
    int index = 0;

 // Méthode pour effectuer l'analyse lexicale
    public void analyzeSLnew(String f) {
    	
    	
    	
    	Scanner scanner=new Scanner(f);
    	String st="<eof,0>";
    	UniteLexicale u=scanner.lexemeSuivant();
    	String stt;
    	System.out.println();
    	System.out.println("------------------------Analyse lexical-----------------------------");
    	System.out.println("les unités lexicales:");
    	int err=0;
    	while(!(u.toString().equals(st))) 
    	{
    	  
    	  String t[]=u.toString().split(",");
    	  stt=t[1].substring(0,t[1].length()-1);
    	  if(t[0].equals("<NUL")) { System.out.println("!!!! erreur lexical : "+stt); err=1;}
    	  else System.out.println(u.toString());
    	  
          ch.add(stt);
          u=scanner.lexemeSuivant();
        }
    	ch.add("$");
    	
    	System.out.println("------------------------Fin de l'analyse lexical---------------------");
    	System.out.println();
	   
	     
    	if (err ==0) {
    		
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	System.out.println("------------------------Analyse syntaxique--------------------------");
    
    	
    	
        action = "";
        index = 0;
       
        analyse.push("0");
        
       
        this.AfficherSLR();
    
       while(index<ch.size()) 
        
        {
              
            String s = analyse.peek();
           
            
            //String act=Action(s,ch[index]);
            
 //_________________________________________________________________________
          
            if (Action(s,ch.get(index)).charAt(0) == 's') {
            	 
                   	
                           
                analyse.push(ch.get(index));
                analyse.push(Action(s, ch.get(index)).substring(1));
               
                              
              
                index++;
                action = "shift";
                
                AfficherSLR();
            }
            // Réduction
            else if (Action(s,ch.get(index)).charAt(0) == 'r') {
                //
                String str = LRGS[Integer.parseInt(Action(s, ch.get(index)).substring(1)) ];
               
                String tabparties[]= str.split("->");
                
                
                String Partiegauche=tabparties[0];
               // System.out.println("Partiegauche"+Partiegauche); 
                
                String Partiedroite=tabparties[1];
                //System.out.println("Partiedroite"+Partiedroite); 
                
                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;
               
               
                for (int i = 0; i < taillepile; i++) {
                	
                  
                    
                    analyse.pop();
                    
                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                //String tetesucc = analyse.peek();
                analyse.push(Action(sommetpile,Partiegauche));
                
               
                action = "reduce:" + str;
                AfficherSLR();
            } 
            //acceptation
            else if (Action(s,ch.get(index)) == "acc")
            	{
            	System.out.println("------------------analyze SLR successfully---------------------"); 
            	break;}
            	
            else
            	//erreur
            	{
            	
            	//System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index); 
            	System.out.println("----------Erreur syntaxique , analyze SLR failled------------------"); 
        	break;
            	}
               
        }
    	}
    	else System.out.println("tu doit corriger les erreurs lexicales !!!!!!");
    }
  
   
    
   
    
    
    
    
    public String Action(String s, String a) {
    	
        for (int i = 1; i <95 ; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 0; j <42; j++)
                {    
                    if (tableSLR[0][j].equals(a))
                    {   
                        return tableSLR[i][j];
                    }
                }
        return "err";
    }

    
 


    public void AfficherSLR() {
        //  SLR
    	

    	
    	
        
       
        strInput="";
        for(int i=index; i<ch.size();i++)
        	strInput= strInput+ ch.get(i);
       System.out.println();
       System.out.println("_________________________________________________________________________________________");
       System.out.println("pile:   "+ analyse );
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Entrée: "+ strInput);
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Action: "+ action);
        System.out.println("_________________________________________________________________________________________");
        System.out.println();
        System.out.println();
        System.out.println();
    }

   

    public void ouput() {
        
        
        System.out.println("**********Tableau SLR¨********");

        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j <7; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }
    
   
 

    
    
    
   public static void main(String[] args) {
	   A_syn as=new A_syn();
	   as.analyzeSLnew("code.txt");
	
} 
    

}





