import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu{

public static void main (String [] args){

        PrimarRegister register = new PrimarRegister();
        ArrayList<Commande> listeInstruction = new ArrayList<>();

         
        // Matrices pour le niveau 1 //
        matrice matLevel11 = new matrice(5, 5);
        matLevel11.defElement(0, 0, "R");
        matLevel11.defElement(4,4,"P");

        matrice matLevel12 = new matrice (5,5);
        matLevel12.defElement(4,0,"P");
        matLevel12.defElement(4,4,"F");

        matrice matLevel13 = new matrice (5,5);
        matLevel13.defElement(1,1,"P");


        // Matrices pour le niveau 2 //
        matrice matLevel21 = new matrice(5, 5);
        matLevel21.defElement(0, 0, "R");
        matLevel21.defElement(0,4,"P");

        matrice matLevel22 = new matrice(5, 5);
        matLevel22.defElement(4,2,"P");

        matrice matLevel23 = new matrice(5, 5);;
        matLevel23.defElement(4,4,"F");



        // Matrices pour le niveau 3 //
        matrice matLevel31 = new matrice(5, 5);
        matLevel31.defElement(0, 0, "R");
        matLevel31.defElement(1,4,"P");

        matrice matLevel32 = new matrice(5, 5);
        matLevel32.defElement(1,0,"P");;
        matLevel32.defElement(4,4,"F");

        matrice matLevel33 = new matrice(5, 5);
        
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        boolean victoire = false;
        
        while(!victoire){
                System.out.println("Choose game Level\n Level 1\n Level 2\n Level 3\n");
                int level = scanner.nextInt();
                scanner.close();
                ArrayList<Commande> Instru;
                
                // choix du niveau //
                switch(level){
                        
                        case 1: 
                        System.out.println("Ca commence !");
                        
                        // Tant que l'utilisateur ne réussi pas à résoudre le niveau //
                        do {
                                matLevel11.afficher();
                                System.out.println("\nLink 500");
                                matLevel12.afficher();
                                System.out.println("\nLink 400");
                                matLevel13.afficher();
                                System.out.println("\nx=0 f=0 t=0 File:5");
                                
                                // Stockage des Commandes et argument de l'utilisateur dans un tableau jusqu'a que Halt soit entrée //
                                do{
                                        Commande co=Commande.create();

                                        listeInstruction.add(co);
                                }while (!listeInstruction.get(listeInstruction.size() - 1).getName().equals("HALT"));

                                /*  Lecture des commandes 
                                    L'utilisation d'un Iterateur serait plus convenable
                                     notamment pour le cas des Jump et Fjump
                                     ou on va utiliser l'iterateur pour directement sauter des Instructions 
                                */
                                for (Commande instruction : listeInstruction){
                                        String nomInstruction = instruction.getName();
                                        
                                        switch ( nomInstruction ) {
                                                // Traitement des commandes //
                                                case "Link" :
                                                        int arg = Integer.parseInt(instruction.getArguments().get(0));
                                                        
                                                        /* Cas unique s'appliquant seulement pour le niveau 1 
                                                        par rapport au remplissage de la matrice */
                                                        if (arg == 500 && matLevel11.elementPresent("R")){
                                                                matLevel11.defElement(0, 1,"*");
                                                                matLevel12.defElement(4, 1,"R");
                                                        }

                                                        if(arg == 400 && matLevel12.elementPresent("R")){
                                                                matLevel12.defElement(4,1,"*");
                                                                matLevel13.defElement(1,2,"R");
                                                        }
                                                        break;
                                                
                                                case "GRAB" :
                                                        if ( matLevel12.elementPresent("R") ){
                                                                if(Integer.parseInt(instruction.getArguments().get(0)) == 5){
                                                                        System.out.println("30");
                                                                }
        
                                                        }
        
                                                case "ADDI":
                                                        ADDI(instruction.getArguments().get(0), instruction.getArguments().get(1), instruction.getArguments().get(2));
        
                                                case "MULTI" : 
                                                        MULTI(instruction.getArguments().get(0), instruction.getArguments().get(1), instruction.getArguments().get(2));
        
                                                case "SUBI" : 
                                                        SUBI(instruction.getArguments().get(0), instruction.getArguments().get(1), instruction.getArguments().get(2));
        
                                                case "FJUMP" : 
                                                        FJUMP(instruction.getArguments().get(0));
        
                                                case "JUMP" : 
                                                        JUMP(instruction.getArguments().get(0));
        
                                                case "Halt" :
                                                        /* verifications du résultat 
                                                            Si réussite du niveau -> victoire == true */  
        
                                                case "Copy" : 
                                        }
                                }
                                
                        } while (!victoire);
                        break;

                        case 2:
                        System.out.println("Ca commence !");
                        do {
                                matLevel21.afficher();
                                System.out.println("\nLink 600");
                                matLevel22.afficher();
                                System.out.println("\nLink 100");
                                matLevel23.afficher();
                                System.out.println("\nx=0 f=0 t=0");
                
                                /* A completer */
                                victoire = true;    
                        } while (!victoire);
                        break;

                        case 3:
                        System.out.println("Ca commence !");
                        do {
                                matLevel31.afficher();
                                System.out.println("\nLink 400");
                                matLevel32.afficher();
                                System.out.println("\nLink 200");
                                matLevel33.afficher();
                                System.out.println("\nx=0 f=0 t=0");

                                /* A completer */
                                victoire = true;
                        } while (!victoire);
                        break;

                        default : System.out.println("Choose Level 1 to 3 !!!");

                }      
        }
}
}