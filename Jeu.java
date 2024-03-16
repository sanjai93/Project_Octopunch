import java.util.Scanner;

public class Jeu{

        public static void main (String [] args){

                Scanner scanner = new Scanner(System.in);
                Scanner scan  = new Scanner(System.in); 
                Scanner nextLine = new Scanner(System.in); //Scanne la touche entrer a chaque parcours de la liste d'instruction
                Scanner Choix = new Scanner(System.in);

                boolean quit1=false;// Represente la condition de fermeture du jeux
                String level;

                while(!quit1){
                        System.out.println();
                        System.out.println("Choose game Level or quit with q \n Level 1\n Level 2\n Level 3\n");
                        level = scanner.nextLine();
                        
                        // choix du niveau //
                        switch(level){
                                
                                case "1":
                                        new Lvl1();   
                                        break;

                                case "2":
                                        // new Lvl2();
                                        break;
                                
                                case "3":
                                        new Lvl3();
                                        break;

                                case "q":
                                        quit1=true;
                                        break;
                                
                                        default : System.out.println("Choose Level 1 to 3 !!!");

                        }   
                }
                Choix.close();
                scan.close();
                nextLine.close();
                scanner.close();
        }
}
