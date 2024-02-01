import java.util.ArrayList;


public class XA{
    ArrayList<line> RobotBOX = new ArrayList<>();
    PrimarRegister Register;

    /**
     * Initialise une classe XA(le robot) avec une ligne vide et des registres à 0.
     */
    public XA(){
    }

    public boolean addline(String LINE){
        return RobotBOX.add(new line(LINE));
    }

    /**
     * Copie la valeur du registre A dans B.(Pas besoin de vérifier les register, la fonction ne se lancera pas
     * si line est faux
     * @param A
     * @param B
     * @return true
     */
    public void COPY(register A, register B ){

        B.Set(A.getValue());

    }

    public void ADDI(register A, register B, register C){
        int valeur= A.getValue()+B.getValue();
        C.Set(valeur);
    }

    public ArrayList<line> getRobotBOX() {
        return RobotBOX;
    }

    public PrimarRegister getRegister() {
        return Register;
    }
}
