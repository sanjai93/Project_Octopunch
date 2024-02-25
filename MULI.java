public class MULI {
    

    public void MULI(String A, String B, register C, PrimarRegister Register){
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

        C.Set(valA*valB);
    }
}
