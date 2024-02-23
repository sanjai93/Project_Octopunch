import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Commande{
    
    private Instruction nom;
    private List<String> arguments;

    public Commande(Instruction nom, List<String> arguments) {
        this.nom = nom;
        this.arguments = arguments;
    }

    public String getName() {
        return nom.name();
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public String affichArgum(){
        String s=" ";
        for (String string : arguments) {
            s += string + " ";
        }
        return s;
    }
    

    public static Commande create(){
        Scanner scan = new Scanner(System.in);

        String ligne= scan.nextLine();
        scan.close();        

        String[] instru = ligne.toUpperCase().trim().split("\\s+");

        if(instru.length>=1){
            switch (instru[0]) {
                case "LINK": case "GRAB" :
                    if (instru.length==2) {
                        boolean isNumeric = instru[1].matches("[+-]?\\d*(\\.\\d+)?");
                        if(isNumeric){
                            ArrayList<String> aux = new ArrayList<>();
                            aux.add(instru[1]);
                            return new Commande(Instruction.valueOf(instru[0]),aux);
                        }
                    }                
                    break;
                case "ADDI" : case "MULTI" : case "SUBI" :
                    
                    if(instru.length==4){
                        if(instru[1].length() == 1 && instru[2].length() == 1 && instru[3].length() == 1){
                            switch (instru[1]) {
                                case "X":case "F":case "T":
                                switch (instru[2]) {
                                    case "X":case "F":case "T":
                                        switch (instru[3]) {
                                            case "X":case "F":case "T":
                                            ArrayList<String> aux=new ArrayList<>();
                                            aux.add(instru[1]);
                                            aux.add(instru[2]);
                                            aux.add(instru[3]);
                                            return new Commande(Instruction.valueOf(instru[0]),aux);
                                        }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                case "JUMP" : case "FJUMP" : case "HALT" :
                    if(instru.length==1)
                        return new Commande(Instruction.valueOf(instru[0]), Collections.emptyList());
                    break;
                case "COPY" : 
                    if(instru.length==3){
                        if(instru[1].equals("F") && ((instru[2].equals("X") || instru[2].equals("T")))){
                            ArrayList<String> aux=new ArrayList<>();
                            aux.add(instru[1]);
                            aux.add(instru[2]);
                            return new Commande(Instruction.valueOf(instru[0]),aux);
                        }
                    }           
                    break;         
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Commande tyty=create();
        if(tyty !=null)
            System.out.println(tyty.getName()+tyty.affichArgum());
        else
            System.out.println("Erreur d'instruction");
    }
       

}