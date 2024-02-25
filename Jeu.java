import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
        Scanner scan  = new Scanner(System.in); 
        Commande commande;

        while(!victoire){
                System.out.println("Choose game Level\n Level 1\n Level 2\n Level 3\n");
                int level = scanner.nextInt();
                ArrayList<Commande> Instru;
                
                // choix du niveau //
                switch(level){
                        
                        case 1: 
                        System.out.println("Ca commence !\n");
                        
                        // Tant que l'utilisateur ne réussi pas à résoudre le niveau //
                        do {
                                matLevel11.afficher();
                                System.out.println("\nLINK 500");
                                matLevel12.afficher();
                                System.out.println("\nLINK 400");
                                matLevel13.afficher();
                                System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
                                 " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");
                                
                                // Stockage des Commandes et argument de l'utilisateur dans un tableau jusqu'a que Halt soit entrée //
                                do{
                                        

                                        
                                        
                                        commande=Commande.create();
                                        listeInstruction.add(commande);
                
                                        if(commande.getName().equals("HALT")){//Si jamais on tombe sur HALT vérifions si tout le monde est OK
                                                for(Commande e : listeInstruction){
                                                        if(!e.getLegal()){
                                                                System.out.println("L'une des lignes rentrée est incorrect");
                                                                listeInstruction.removeAll(listeInstruction);// On efface tout, pour l'instant..
                                                               
                                                                break;
                                                
                                                        }
                                                }
                                        }

                                }while (!(( commande.getName().equals("HALT")) && (listeInstruction.size()>1))  );//Tu sors pas tant que tu as halt + une taille>1

                                /*  Lecture des commandes 
                                    L'utilisation d'un Iterateur serait plus convenable
                                     notamment pour le cas des Jump et Fjump
                                     ou on va utiliser l'iterateur pour directement sauter des Instructions 
                                */
                                ListIterator<Commande> iterator = listeInstruction.listIterator();
                                while (iterator.hasNext()) {
                                        Commande instruction = iterator.next(); 
                                        String nomInstruction = instruction.getName();
                                        
                                        switch ( nomInstruction ) {
                                                // Traitement des commandes //
                                                case "LINK" :
                                                        int arg = Integer.parseInt(instruction.getArguments().get(0));
                                                        
                                                        /* Cas unique s'appliquant seulement pour le niveau 1 
                                                        par rapport au remplissage de la matrice */
                                                        if (arg == 500 && matLevel11.parcour("R")){
                                                                matLevel11.defElement(0, 0,"*");
                                                                matLevel12.defElement(4, 1,"R");
                                                        }

                                                        if(arg == 400 && matLevel12.parcour("R")){
                                                                matLevel12.defElement(4,1,"*");
                                                                matLevel13.defElement(1,2,"R");
                                                        }
                                                        break;
                                                
                                                case "GRAB" :
                                                        if ( matLevel12.parcour("R") ){
                                                                if(Integer.parseInt(instruction.getArguments().get(0)) == 5){
                                                                        System.out.println("30");
                                                                }
                                                        }
                                                        break;
                                        
                                                case "ADDI":
                                                        ADDI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)),register) ;
                                                        break;
                                                case "MULI" : 
                                                        MULI(instruction.getArguments().get(0),instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)),register);
                                                        break;
                                                case "SUBI" : 
                                                        SUBI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)),register);
                                                        break;
                                                case "JUMP" : 
                                                        int nombreInstructionsASauter = Integer.parseInt(instruction.getArguments().get(0));
                                                        for (int i = 0; i < nombreInstructionsASauter; i++) {   
                                                                if (iterator.hasNext()) {
                                                                iterator.next(); // Passez à l'instruction suivante
                                                                } else {
                                                                        break; // Sort de la boucle si nous avons atteint la fin de la liste
                                                                }
                                                        }
                                                        break;

                                                case "FJUMP" : 
                                                int cond = register.getRegister("T").getValue();
                                                
                                                if(cond == 0){ 
                                                int nombreInstructionsASauter_BIS = Integer.parseInt(instruction.getArguments().get(0));
                                                        for (int i = 0; i < nombreInstructionsASauter_BIS; i++) {   
                                                                if (iterator.hasNext()) {
                                                                iterator.next(); // Passez à l'instruction suivante
                                                                } else {
                                                                        break; // Sort de la boucle si nous avons atteint la fin de la liste
                                                                }
                                                        }
                                                }
                                                
                                                break;

        
                                                case "HALT" :
                                                        int value = register.getRegister("T").getValue();
                                                        if(value == 900){
                                                                victoire = true;
                                                                System.out.println("Bien joué");
                                                        }
                                                        break;


                                                        
                                                        
        
                                                case "COPY" : 
                                                COPY(instruction.getArguments().get(0), register.getRegister(instruction.getArguments().get(1)), register);
                                                        
                                               
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
        scan.close();
        scanner.close();
}

    //les méthodes qui suivent ne peuvent pas etre lancées si line n'est pas true donc inutile de faire trop de vérification ici

    /**
     * Copie la valeur du premier argument dans le 2nd.
     *
     * @param A
     * @param B
     * */
    public static void COPY(String A, register B, PrimarRegister Register){
        if (Register.contains(A)){//Si A correspond à X,T,F (inutile de vérifier B=M,line  le fait déja )
            B.Set(Register.getRegister(A).getValue());//Set B avec la valeur du registre correspondant aux String
        }

        else {//On à donc un int par élimination
            int A1=Integer.parseInt(A);//Transforme le string en int
            B.Set(A1);//Set B avec la valeur du registre correspondant aux String
        }

    }
    /**
     * Additionne les 2 valeurs des 2 premiers arguments afin de les mettre dans le 3e.
     *
     * @param A
     * @param B
     * @param C
     * */
    public static void ADDI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA+valB);
    }

    public static void SUBI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA-valB);
    }

    public static void MULI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA*valB);
    }

    }
