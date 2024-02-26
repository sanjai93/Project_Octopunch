import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Commande{
    
    private String nom;
    private List<String> arguments;
    private boolean legal; //Juste un boolean pour voir si la l'agument rentrer est legal


    public Commande(String Nom, List<String> arguments) {
        this.nom = Nom;
        this.arguments = arguments;
       
    }

    public String getName() {
        return this.nom;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public boolean getLegal(){
        return this.legal;
    }

    public boolean SetLegal(boolean bool){
        return this.legal = bool;
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
        line LINE= new line(scan.nextLine());//LINE est automatiquement soit true soit false apres que le code ait été rentré
        scan.close();    
        String ligne = LINE.ToSrting();
        String[] instru = ligne.toUpperCase().trim().split("\\s+");
        ArrayList<String> aux = new ArrayList<>();
        Commande ret;
        if (LINE.Legal()){
            switch (instru[0]) {
                case "LINK": case "GRAB" :
                           
                    aux.add(instru[1]);
                    
                    ret =new Commande(instru[0],aux);
                    ret.SetLegal(true);
                    return ret;
                              
                case "ADDI" : case "MULTI" : case "SUBI" :
                    aux.add(instru[1]);
                    aux.add(instru[2]);
                    aux.add(instru[3]);
                    ret = new Commande(instru[0],aux);
                    ret.SetLegal(true);
                    return ret;

                case "HALT" :

                    ret=new Commande(instru[0], Collections.emptyList());
                    ret.SetLegal(true);
                    return ret;

                case "COPY" : 
                    aux.add(instru[1]);
                    aux.add(instru[2]);
                    ret = new Commande(instru[0],aux);
                    ret.SetLegal(true);
                    return ret;
            }
        }
        ret = new Commande(ligne, Collections.emptyList()); //Cas ou la ligne est pas legal 
        ret.SetLegal(false);// On change le boolean en false
        return ret;

    }
    public static void main(String[] args) {
        Commande tyty=create();
        if(tyty !=null)
            System.out.println(tyty.getName()+tyty.affichArgum());
        else
            System.out.println("Erreur d'instruction");
    }
       

}