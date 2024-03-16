import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Lvl4 {

    public Lvl4() {
        PrimarRegister register = new PrimarRegister();
        ArrayList<Commande> listeInstruction = new ArrayList<>();
        int fichierL1 = 1989;



        // Matrices pour le niveau 1 //
        matrice matLevel11;

        matrice matLevel12;





        @SuppressWarnings("resource")
        Scanner NextInstru = new Scanner(System.in); //Scanne la touche entré à chaque parcours de la liste d'instruction
        Scanner Choix = new Scanner(System.in);//quitter ou retry
        Scanner speed = new Scanner(System.in);



        
        boolean HaveF1 = false; //Etat de la possesion du fichier
        boolean quit = false; // Souhaite quitter le niveau
        boolean quit1 = false; // Souhaite quitter le TRC
        boolean TRC;//Représente l'etat du cycle en cours
        boolean victory=false;


        Commande commande;
        Commande instruction;

       
        String quitORtry; 
        String nomInstruction;
        String Speed;
        String NextInstruc;
        ListIterator<Commande> iterator;


        //Methode 
        COPY copy = new COPY();
        ADDI addi = new ADDI();
        SUBI subi = new SUBI();
        MULI muli = new MULI();
        DIVI divi = new DIVI();
        MODI modi = new MODI();
        SWIZ swiz = new SWIZ();
        TEST test = new TEST();


        int arg;
        int i, position;
        int cycle;
        int TRC1,TRC2=70;// Test run complete 
        do {
            //Création du niveau 1
            matLevel11 = new matrice(5, 5);
            matLevel11.defElement(0, 0, "R");
            matLevel11.defElement(4,4,"P");
            matLevel11.defElement(2,4,"F(?)");

            matLevel12 = new matrice (5,5);
            

            

            register.getRegister("X").Set(0);
            register.getRegister("F").Set(0);
            register.getRegister("T").Set(0);
            register.getRegister("M").Set(0);

            HaveF1 = false;
            quit = false;
            

            TRC1=0;
            cycle=0;
           

            System.out.println("Ca commence !\n");

            //affichage de matrice
            matLevel11.afficher();
            System.out.println("\n---???---");
            matLevel12.afficher();
           

            System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
            " M=" + register.getRegister("M").getValue());
            if(!HaveF1){ 
                System.out.println("FOLDER : NONE\n");
            } //Si il à pas de fichier 
            else{
                System.out.println(" FOLDER :"+fichierL1+"\n");
            }

            System.out.println("Find a way to bypass the SUPER-MEGA-ULTRA defense system and hack the internet in 4 cycles only !");
            System.out.println("Indice n°1 : The file number is double your last entry ");
            System.out.println("Indice n°2 : Password ? : F(?) ^ ? = 9891  ");
            System.out.println();
            System.out.println("Cycles :"+cycle);
            System.out.println("TEST RUN COMPLETE "+TRC1+"/"+TRC2);

            listeInstruction.removeAll(listeInstruction);
            // Stockage des Commandes et argument de l'utilisateur dans un tableau jusqu'a que Halt soit entrée //
            do {

                commande = Commande.create();
                listeInstruction.add(commande);

                if (commande.getName().equals("HALT")) {//Si jamais on tombe sur HALT vérifions si tout le monde est OK
                    for (Commande e : listeInstruction) {
                        if (!e.getLegal()) {
                            System.out.println("L'une des lignes rentrée est incorrect \n");
                            listeInstruction.removeAll(listeInstruction);// On efface tout, pour l'instant.

                            break;

                        }
                    }
                }

            } while (!((commande.getName().equals("HALT")) && (listeInstruction.size() > 0)));//Tu ne sors pas tant que tu as halt + une taille>1

            /*  Lecture des commandes
            L'utilisation d'un Iterateur serait plus convenable
            notamment pour le cas des Jump et Fjump
            ou on va utiliser l'iterateur pour directement sauter des Instructions
            */

            TRC = true;
            quit1=false;
            while (TRC && TRC1<TRC2 && !quit1){ //Nombre de tests < nombre de tests requis pour gagner && L'utilisateur veut pas quitter
                TRC = false; //Le but est qu'il devient true
                cycle=0; //Initialise le compteur de cycle a 0
                iterator = listeInstruction.listIterator();
                position = 0;//Pour savoir ou sommes nous dans la liste
                while (iterator.hasNext()) {
                    instruction = iterator.next();
                    nomInstruction = instruction.getName();
                    System.out.println();
                    cycle++;
                    switch (nomInstruction) {
                        // Traitement des commandes //
                        case "LINK":
                            arg = Integer.parseInt(instruction.getArguments().get(0));

                                                                       
                            if (arg == 1234 && matLevel11.parcour("R")) {
                                matLevel11.defElement(2, 3, "*");
                                matLevel11.defElement(0, 0, "*");
                                matLevel12.defElement(4, 1, "R");
                               
                             

                            }

                            break;

                        case "GRAB" :
                            arg = Integer.parseInt(instruction.getArguments().get(0));
                            //Si le robot est dans la matrice + argument rentré est bine celui du fichier + Tu n'a pas de fichier 
                            if ( matLevel11.parcour("R") && arg == 8 && !HaveF1){
                                    
                                    HaveF1=true; //Tu as maintenant un fichier
                                    register.getRegister("M").Set(fichierL1);//M prend la valeur à l'interieur du fichier
                                    matLevel11.defElement(0,0,"*");
                                    matLevel11.defElement(2,3,"R");
                            }

                            break;

                        case "ADDI":
                            addi.ADDI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;
                        case "MULI":

                            muli.MULI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;
                        case "SUBI":

                            subi.SUBI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;

                        case "DIVI":
                            divi.DIVI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;
                        case "MODI":

                            modi.MODI(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;
                        case "SWIZ":

                            swiz.SWIZ(instruction.getArguments().get(0), instruction.getArguments().get(1), register.getRegister(instruction.getArguments().get(2)), register);
                            break;

                            case "TEST":

                            test.TEST(instruction.getArguments().get(0), instruction.getArguments().get(1), instruction.getArguments().get(2), register);
                            break;
    

                        case "JUMP":
                            int nombreInstructionsASauter = Integer.parseInt(instruction.getArguments().get(0));
                            for (i = 0; i < nombreInstructionsASauter && position < listeInstruction.size() - 2 && iterator.hasNext(); i++) {
                                iterator.next(); // Passez à l'instruction suivante
                                position++;
                            }
                            // Sort de la boucle si nous avons atteint la fin de la liste
                            break;

                        case "FJMP":
                            int cond = register.getRegister("T").getValue();

                            if (cond == 0) {
                                int nombreInstructionsASauter_BIS = Integer.parseInt(instruction.getArguments().get(0));

                                for (i = 0; i < nombreInstructionsASauter_BIS && position < listeInstruction.size() - 2; i++) {
                                    if (iterator.hasNext()) {
                                        iterator.next(); // Passez à l'instruction suivante
                                        position++;
                                    } else {
                                        break; // Sort de la boucle si nous avons atteint la fin de la liste
                                    }
                                }
                            }

                            break;


                        case "HALT" :
                        System.out.println("oeoeoeeee");
                        if((cycle==4)&& matLevel12.parcour("R") ){
                            System.out.println("oeoeoeeee");
                            TRC=true;
                        }
                        break;

                        case "COPY":
                            copy.COPY(instruction.getArguments().get(0), register.getRegister(instruction.getArguments().get(1)), register);
                            break;

                        case "NOOP":
                            break;//Saute un cycle sans rien faire



                    }
                    //affichage de la matrice apres l'instruction
                    matLevel11.afficher();
                    System.out.println("\n---???---");
                    matLevel12.afficher();
                
                    position++;
                    
                    System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
                    " M=" + register.getRegister("M").getValue());
                    if(!HaveF1){ 
                        System.out.println("FOLDER : NONE\n");
                    } //Si il à pas de fichier 
                    else{
                        System.out.println(" FOLDER : "+fichierL1+"\n");
                    }

                    System.out.println("Find a way to bypass the SUPER-MEGA-ULTRA defense system and hack the internet in 4 cycles only !");
                    System.out.println("Indice n°1 : The file number is double your last entry ");
                    System.out.println("Indice n°2 : Password ? : F(?) ^ ? = 9891  ");
                    System.out.println();
                 
                    System.out.println("Cycles :"+cycle);
                    if(TRC){
                        TRC1++;
                    }
                    System.out.println("TEST RUN COMPLETE "+TRC1+"/"+TRC2);
            




                    System.out.println("\n___" + instruction.getName() + instruction.affichArgum() + "___"); //La ligne qui a provoqué les changements

                    if(iterator.hasNext()) { //Si jamais il reste encore des lignes
                        System.out.println("Entrée : Instruction suivante || q : quitter ");
                        NextInstruc=NextInstru.nextLine();
                        if (NextInstruc.equals("Q") || NextInstruc.equals("q")) {
                            
                            break;

                        }
                    


                    }


                }
                if(TRC){//Si le cycle est valide(Impossible à obtenir si on à pas minimum atteint le HALT)
                

                    System.out.println("RUN VALIDE !\n");
                    System.out.println("Entrée : Prochain cycles || s : Accélérer || r : Recommencer || q : Quitter  ");
                    Speed=speed.nextLine();
                    switch (Speed){
                        case "s": case "S":
                            for(i=TRC1; i < TRC2; i++ ){
                                TRC1++;
                                System.out.println("\n Cycles: " +TRC1);
                                try {
                                    Thread.sleep(100); // Pause d'une seconde
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "r" : case "R" :
                            TRC = false; //On recommence, on annule donc la validation du cycle
                            quit1=true; //On veut quitter la boucle TRC
                            break;
                        case "q": case "Q":
                            quit1=true; // Quitte la boucle TRC
                            quit=true; //Quitte le jeu
                            TRC =false; //on annule donc la validation du cycle
                            break;
                        default:
                            break;
                    }
                }
                else{
                    System.out.println("ECHEC DE CETTE RUN !");
                    System.out.println("Entrée : Continuer à codé (Recommencer) || q : quitter  ");
                    Speed=speed.nextLine();
                    switch (Speed){
                        case "q": case "Q":
                            quit1=true;
                            quit=true;
                            break;
                        default:
                            quit1=true;
                            break;
                    }
                }


            }

            
            if(TRC){// Si on a quitté en haut et que TRC est toujours true
                System.out.println("Mission Success");
                System.out.println("Auto Destruction ROBOT");
                for(int k=3; k > 0; k-- ){      // Affichage 3 petit points //
                        System.out.print(".");
                        try {
                                Thread.sleep(200); // 500 millisecondes
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
        
                }
                System.out.println();

                for(int k=3; k > 0; k-- ){
                    System.out.println(k);
                    try {
                        Thread.sleep(300); // Pause d'une seconde
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("Project OctoPunch complete :-)");
                System.out.println("Recommencer ?: \n q : quitter || Entrer: Réessayez");
                quitORtry= Choix.nextLine();
                if(quitORtry.equals("q") || quitORtry.equals("Q")){ //Si l'utilisateur appuie sur q, on quitte la boucle lvl 1
                    quit=true;
                
                }
                listeInstruction.removeAll(listeInstruction);//Remis à vide, car la partie est terminée
            }


        }while (!quit);

    }
}