public class COPY{
        /**
     * Copie la valeur du premier argument dans le 2nd.
     *
     * @param A
     * @param B
     * @param Register
     * */
    public void COPY(String A, register B, PrimarRegister Register){
        if (Register.contains(A)){//Si A correspond à X,T,F (inutile de vérifier B=M,line  le fait déja )
            B.Set(Register.getRegister(A).getValue());//Set B avec la valeur du registre correspondant aux String
        }

        else {//On à donc un int par élimination
            int A1=Integer.parseInt(A);//Transforme le string en int
            B.Set(A1);//Set B avec la valeur du registre correspondant aux String
        }

    }
}
