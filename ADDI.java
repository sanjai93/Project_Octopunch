public class ADDI {
    /**
     * Additionne les 2 valeurs des 2 premiers arguments afin de les mettre dans le 3e.
     *
     * @param A
     * @param B
     * @param C
     * @param Regiter
     * */
    public void ADDI(String A, String B, register C, PrimarRegister Register){
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
           
            valB=Integer.parseInt(B);
        }
        C.Set(valA+valB);
    }

}
