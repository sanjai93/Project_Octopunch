import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        // Liste pour stocker les instructions
        List<Instruction> instructions = new ArrayList<>();

        String ligne;
        
        while (!(ligne = scan.nextLine()).equalsIgnoreCase("HALT")) {
             
            // pour l'instant j'ai mis HALT pour arreter le while (a changer car HALT est une commande en soi)
            
            String[] instru = ligne.toUpperCase().trim().split("\\s+", 2);
            String nomcommande = instru[0];
            List<String> arguments = new ArrayList<>();
            if (instru.length > 1) {
                String[] arg = instru[1].split(" ");
                for (String a : arg) {
                    arguments.add(a.trim());
                }
            }
            
            // Ajouter à la liste
            Instruction instruction = new Instruction(nomcommande, arguments);
            instructions.add(instruction);
        }

        // Affichage 
       for (Instruction instruction : instructions) {
            
           System.out.println(instruction.getName() + ", Arguments : " + instruction.getArguments());
        }

        scan.close();
    }
}