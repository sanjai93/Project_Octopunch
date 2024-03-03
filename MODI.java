public class MODI {
    public void MODI(String A, String B, Register C, PrimarRegister Reg){
        int valA,valB;
        if(Reg.contains(A)){
            valA=Reg.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Reg.contains(B)){
            valB=Reg.getRegister(B).getValue();
        }
        else{
            valB=Integer.parseInt(B);
        }

        C.Set(valA%valB);
    }
}
