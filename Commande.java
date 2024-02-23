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
    