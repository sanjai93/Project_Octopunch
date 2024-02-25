

public class Test {
    public static void main(String[] args) {
        PrimarRegister register = new PrimarRegister();
        System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
        " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");

        COPY("10", register.getRegister("F"), register);

        System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
        " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");

        ADDI("5", "F", register.getRegister("X"), register);

        
        System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
        " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");

        SUBI("X", "F", register.getRegister("T"), register);

        System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
        " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");

        MULI("-2", "T", register.getRegister("X"), register);

        
        System.out.println("\nX=" +register.getRegister("X").getValue() + " F=" +register.getRegister("F").getValue()+ " T=" +register.getRegister("T").getValue()+ 
        " M=" + register.getRegister("M").getValue()+"\nCalcul le double du fichier dans le registre T");
    }

    public static void COPY(String A, register B, PrimarRegister Register){
        if (Register.contains(A)){//Si A correspond à X,T,F (inutile de vérifier B=M,line  le fait déja )
            B.Set(Register.getRegister(A).getValue());//Set B avec la valeur du registre correspondant aux String
        }

        else {//On à donc un int par élimination
            int A1=Integer.parseInt(A);//Transforme le string en int
            B.Set(A1);//Set B avec la valeur du registre correspondant aux String
        }

    }
    /**
     * Additionne les 2 valeurs des 2 premiers arguments afin de les mettre dans le 3e.
     *
     * @param A
     * @param B
     * @param C
     * */
    public static void ADDI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA+valB);
    }

    public static void SUBI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA-valB);
    }

    public static void MULI(String A, String B, register C, PrimarRegister Register){
        int valA,valB;
        if(Register.contains(A)){
            valA=Register.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Register.contains(B)){
            valB=Register.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(A);
        }

        C.Set(valA*valB);
    }

    
}
