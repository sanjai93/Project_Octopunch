import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import line.java;

public class Instruction {
    
    private String nom;
    private List<String> arguments;

    public Instruction(String nom, List<String> arguments) {
        this.nom = nom;
        this.arguments = arguments;
    }

    public String getName() {
        return nom;
    }

    public List<String> getArguments() {
        return arguments;
    }
    
    
    
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        String ligne= scan.nextLine();
        scan.close();

        boolean legal=false;
        int i;
    

        String[] instru = ligne.toUpperCase().trim().split("\\s+", 2);

        switch(instru[0]){

                case "LINK" :
                
                if (instru.length == 2) {

                    legal=true;

                    if (instru[1].charAt(0) == '-') {

                        if (!(instru[1].length() > 1) )
                        {
                            legal=false;
                        }
                        else{
                            
                            for (i = 0; i < instru[1].length(); i++) {
                                if (!Character.isDigit(instru[1].charAt(i)))
                                {
                                    legal = false;
                                }
                            }
                        }
                }
                if(legal==false){
                    System.out.println("Erreur instruction");
                }
        }
        else{
        System.err.println("Erreur instruction");
        }
                        break;

                
                
                
                
                
                
                case "GRAB" :

                if (instru.length == 2) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("grab");
            }
                break;

                case "JUMP" :

                if (instru.length == 2) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("jump ");
            }
                break;

                case "FJUMP" :

                if (instru.length == 2) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("fjump ");
            }
                break;

                case "COPY" :

                if (instru.length == 3) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("copy");
            }
                break;

                case "ADDI" :

                if (instru.length == 4) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("addi ");
            }
                break;

                case "MULTI" :
                
                if (instru.length == 4) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("multi");
            }
                break;

                case "SUBI" :

                if (instru.length == 4) {
                    String[] arg = instru[1].split(" ");
                    System.out.println("subi");

                    break;
            }
                
                
            
            default : System.err.println("Erreur instruction");
                
                
        }
    
        
    
    
    
    }


}