import java.util.Scanner;


public class Niveau{
        //String line

public static void main (String [] args){
        matrice matLevel1 = new matrice(5, 5);
        matLevel1.defElement(0, 0, "x");
        matLevel1.defElement(0,4,"P");
        matLevel1.defElement(3,3 , "P");
        matLevel1.defElement(4,4,"F");

       
        matrice matLevel2 = new matrice(5, 5);
        matLevel2.defElement(0, 0, "x");
        matLevel2.defElement(0,4,"P");
        matLevel2.defElement(3,3 , "P");
        matLevel2.defElement(4,4,"F");

        matrice matLevel3 = new matrice(5, 5);
        matLevel3.defElement(0, 0, "x");
        matLevel3.defElement(01,4,"P");
        matLevel3.defElement(3,3 , "P");
        matLevel3.defElement(4,4,"F");

        
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        boolean victoire = false;
        matrice actual=matLevel1;
        int i=0,j=0;
        int posFichierI=0,posFIchierJ=0;
        
        while(!victoire){
                System.out.println("Choose game Level\n Level 1\n Level 2\n Level 3\n");
                int level = scanner.nextInt();
                
                switch(level){
                        
                        case 1:
                      


                        System.out.println("Ca commence !");
                        victoire = false;
                        while (!victoire) {
                             
                                matLevel1.afficher();
                                System.out.println();
                                //I
                                String p = scanner.nextLine();// Instruction.get phrase etc...
                                String[] argum = p.split("\\s+");
                                switch (argum[0]) {
                                        case "LINK" :
                                        
                                        
                                        if(actual.parcour(argum[1])){
                                                actual.permutElem(i,j,0,4 );
                                                matLevel1.afficher();
                                                System.out.println();
                                                
                                                victoire=true;
                                
                                        }
                                        break;

                                        default : 
                                                //Instruction.GET (LINKig)

                                                System.out.println("Marche po");
        
        
                                }
                        }
                     
                        
                        break;

                        case 2:
                        System.out.println("Ca commence !");
                        matLevel2.afficher();
                        /* A completer */
                        victoire = true;
                        break;

                        case 3:
                        System.out.println("Ca commence !");
                        matLevel3.afficher();
                        /* A completer */
                        victoire = true;
                        break;

                        default : System.out.println("Choose Level 1 to 3 !!!");

                }
               
        }
}

}