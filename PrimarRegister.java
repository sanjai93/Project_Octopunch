import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

public class PrimarRegister {
     private List<Register> listeRegister;
     
    /**
     * PrimarRegister est une classe qui a uniquement pour but de regrouper les registres "Primaire"
     * Les registre X T F M sont les seuls et unique registre d'un XA.
     * 
     */
    public PrimarRegister() {
        this.listeRegister = new ArrayList<Register>();
        // Initialiser la liste avec des valeurs préremplies
        listeRegister.add(new Register("X"));
        listeRegister.add(new Register("T"));
        listeRegister.add(new Register("F"));
        listeRegister.add(new Register("M"));

        for(Register reg : listeRegister){
            reg.Set(0);//Au debut de chaque partie, les valeurs sont à 0, donc je les initiallise
        }
    }

    /**
     * Renvoie la liste de registe "Primaire"
     * 
     * @return la liste de registe "Primaire"
     */
    public List<Register> getListeRegisters() {
        return this.listeRegister;
    }

    public Register getRegister(String A){
        if(this.contains(A)){
            for(Register my : listeRegister){
                if (my.getLetter().equals(A)){
                    return my;
                }
            }
        }
        return null;
    }
     /**
     * Contains vérifie si le STRING ou le registre.getLetter insérer est bien l'un des registes "Primaire"
     * 
     * @param reg le String à tester
     * 
     * @return true si il est bien l'un des leurs, false sinon
     * 
     * @throws NullPointerException si le String rentré est nul
     *  
     */
    public boolean contains(String reg){
        if(reg==null){
            throw new NullPointerException("contains : Argument du String nul");
        }
        for(Register my : listeRegister){
            if(my.getLetter().equals(reg)){
                return true;
            }
        }
        return false;
    }
}

