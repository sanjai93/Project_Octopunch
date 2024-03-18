public class COPY{
        /**
     * Copie la valeur du premier argument dans le 2nd.
     *
     * @param A
     * @param B
     * @param Reg
     * */
    public void COPY(String A, Register B, PrimarRegister Reg){
        if (Reg.contains(A)){//Si A correspond à X,T,F (inutile de vérifier B=M,line  le fait déja )
            B.Set(Reg.getRegister(A).getValue());//Set B avec la valeur du registre correspondant aux String
        }

        else {//On a donc un int par élimination
            int A1=Integer.parseInt(A);//Transforme le string en int
            B.Set(A1);//Set B avec la valeur du registre correspondant aux String
        }

    }
}
