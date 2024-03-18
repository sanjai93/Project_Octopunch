import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Jeu{

        public static void main (String [] args){

                PrimarRegister register = new PrimarRegister();
                ArrayList<Commande> listeInstruction = new ArrayList<>();
                
                boolean HaveF1 =false;


                


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
                Scanner scan  = new Scanner(System.in); 
                Scanner nextLine = new Scanner(System.in); //Scanne la touche entrer a chaque parcours de la liste d'instruction
                Scanner Choix = new Scanner(System.in);


                boolean victoire = false; //Représente la condition de victoire
                boolean quit2=false; //Represente la condition de répétition de niveau
                boolean quit1=false;// Represente la condition de fermeture du jeux

                Commande commande;
                Commande instruction;

                String level;
                String quitORtry;
                String nomInstruction ;
                ListIterator<Commande> iterator;

                COPY copy=new COPY();
                ADDI addi=new ADDI();
                SUBI subi=new SUBI();
                MULI muli=new MULI();
                DIVI divi=new DIVI();





                int arg;
                int i,position;
                


                while(!quit1){
                        System.out.println();
                        System.out.println("Choose game Level or quit with q \n Level 1\n Level 2\n Level 3\n Level 4\n");
                        level = scanner.nextLine();
                        
                        // choix du niveau //
                        switch(level){
                                
                                case "1":
                                        new Lvl1();
                                               
                                break;

                                case "2":
                        
                            
                                        new Lvl2();   
                             
                                break;

                                case "3":
                                        new Lvl3();
                                break;

                                case "4":
                                        new Lvl4();
                                break;

                                case "q":
                                        quit1=true;
                                        break;
                                default : System.out.println("Choose Level 1 to 4 !!!");

                        }      
                }
                Choix.close();
                scan.close();
                nextLine.close();
                scanner.close();
        }



    


    }
