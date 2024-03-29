import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Lvl2 {

    public Lvl2() {
        PrimarRegister register = new PrimarRegister();
        ArrayList<Commande> listeInstruction = new ArrayList<>();
        int fichierL2 = 953;



        // Matrices pour le niveau 2 //
        matrice matLevel11;

        matrice matLevel12;

        matrice matLevel13;

        matrice matLevel14;

     

        @SuppressWarnings("resource")
        Scanner NextInstru = new Scanner(System.in); //Scanne la touche entré à chaque parcours de la liste d'instruction
        Scanner Choix = new Scanner(System.in);//quitter ou retry
        Scanner speed = new Scanner(System.in);



        
        boolean HaveF2 = false; //Etat de la possesion du fichier
        boolean quit = false; // Souhaite quitter le niveau
        boolean quit2 = false; // Souhaite quitter le TRC
        boolean TRC;//Représente l'etat du cycle en cours


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
        int TRC1,TRC2=15;// Test run complete 
        do {
            //Création du niveau 2
            matLevel11 = new matrice(5, 5);
            matLevel11.defElement(3, 2, "R");

            matLevel12 = new matrice (5,5);

            matLevel13 = new matrice (5,5);
            
            matLevel14= new matrice(5,5);
            matLevel14.defElement(4,4,"F(9)");

            register.getRegister("X").Set(0);
            register.getRegister("F").Set(0);
            register.getRegister("T").Set(0);
            register.getRegister("M").Set(0);

            HaveF2 = false;
            quit = false;
            

            TRC1=0;
            cycle=0;
           

            System.out.println("Ça commence !\n");

            //affichage de matrice
            matLevel11.afficher();
            System.out.println("\n---100---");
            matLevel12.afficher();
            System.out.println("\n---800---");
            matLevel13.afficher();
            System.out.println("\n---666---");
            matLevel14.afficher();

            System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
            " M=" + register.getRegister("M").getValue());
            if(!HaveF2){ 
                System.out.println("FOLDER : NONE\n");
            } //Si il à pas de fichier 
            else{
                System.out.println(" FOLDER :"+fichierL2+"\n");
            }

            System.out.println("Multiply by itself the value of file 9 in X register");
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
            quit2=false;
            while (TRC && TRC1<TRC2 && !quit2){ //Nombre de tests < nombre de tests requis pour gagner && L'utilisateur veut pas quitter
                TRC = false; //Le but est qu'il devient true
                cycle=0; //Initialise le compteur de cycle a 0
                iterator = listeInstruction.listIterator();
                position = 0;//Pour savoir ou sommes nous dans la liste
                while (iterator.hasNext()) {
                    instruction = iterator.next();
                    nomInstruction = instruction.getName();
                    System.out.println();

                    switch (nomInstruction) {
                        // Traitement des commandes //
                        case "LINK":
                            arg = Integer.parseInt(instruction.getArguments().get(0));

                                                                        /* Cas unique s'appliquant seulement pour le niveau 2
                                                                        par rapport au remplissage de la matrice */
                            if (arg == 100 && matLevel11.parcour("R")) {
                                matLevel11.defElement(3, 2, "*");
                                matLevel12.defElement(4, 1, "R");
                            }

                            if (arg == 800 && matLevel12.parcour("R")) {
                                matLevel12.defElement(4, 1, "*");
                                matLevel13.defElement(1, 2, "R");
                            }

                            if (arg == 666 && matLevel13.parcour("R")) {
                                matLevel13.defElement(1, 2, "*");
                                matLevel14.defElement(1, 2, "R");
                            }
                            
                            break;

                        case "GRAB" :
                            arg = Integer.parseInt(instruction.getArguments().get(0));
                            //Si le robot est dans la matrice + argument rentré est bine celui du fichier + Tu n'a pas de fichier 
                            if ( matLevel14.parcour("R") && arg == 9 && !HaveF2){
                                    
                                    HaveF2=true; //Tu à maintenant un fichier
                                    register.getRegister("M").Set(fichierL2);//M prend la valeur à l'interieur du fichier
                                    matLevel14.defElement(1,2,"*");
                                    matLevel14.defElement(4,3,"R");
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
                            int cond = register.getRegister("X").getValue();

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
                        int value = register.getRegister("X").getValue();
                        if(value == 908209){
                                TRC = true;//Le cycle est valide.
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
                    System.out.println("\n---100---");
                    matLevel12.afficher();
                    System.out.println("\n---800---");
                    matLevel13.afficher();
                    System.out.println("\n---666---");
                    matLevel14.afficher();
                    position++;
                    cycle++;
                    System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
                    " M=" + register.getRegister("M").getValue());
                    if(!HaveF2){ 
                        System.out.println("FOLDER : NONE\n");
                    } //Si il à pas de fichier 
                    else{
                        System.out.println(" FOLDER : "+fichierL2+"\n");
                    }

                    System.out.println("Multiply by itself the value of file 9 in X register");
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
                            quit2=true; //On veut quitter la boucle TRC
                            break;
                        case "q": case "Q":
                            quit2=true; // Quitte la boucle TRC
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
                            quit2=true;
                            quit=true;
                            break;
                        default:
                            quit2=true;
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
                if(quitORtry.equals("q") || quitORtry.equals("Q")){ //Si l'utilisateur appuie sur q, on quitte la boucle lvl 2
                    quit=true;
                }
                listeInstruction.removeAll(listeInstruction);//Remis à vide, car la partie est terminée
            }


        }while (!quit);

    }
}
