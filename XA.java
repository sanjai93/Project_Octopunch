import java.util.ArrayList;
import java.util.NoSuchElementException;


public class XA{
    ArrayList<line> RobotBOX = new ArrayList<>();
    PrimarRegister Register;
    private int nextIndex;
    private int lastIndex;
    private int previousIndex;


    /**
     * Initialise une classe XA(le robot) avec une ligne vide et des registres à 0.
     */
    public XA(){
        startIteration();
    }

    public boolean addline(String LINE){

        return RobotBOX.add(new line(LINE));
    }

    public void JUMP(int A){
        int A1=A;
        while (A1!=0){
            if(A1>0){
                next();
                A1--;
            }
            if (A1<0){
                previous();
                A1++;
            }
        }
    }

    public void FJMP(int A) {
        int A1 = A;
        if (Register.getRegister("T").getValue() == 0) {
            while (A1 != 0) {
                if (A1 > 0) {
                    next();
                    A1--;
                }
                if (A1 < 0) {
                    previous();
                    A1++;
                }
            }
        }
    }

    //les méthodes qui suivent ne peuvent pas etre lancées si line n'est pas true donc inutile de faire trop de vérification ici

    /**
     * Copie la valeur du premier argument dans le 2nd.
     *
     * @param A
     * @param B
     * */
    public void COPY(String A, register B ){
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
    public void ADDI(String A, String B, register C){
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

    public void SUBI(String A, String B, register C){
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

    public void MULI(String A, String B, register C){
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


    public ArrayList<line> getRobotBOX() {
        return RobotBOX;
    }

    public PrimarRegister getRegister() {
        return Register;
    }

    public void startIteration() {
        if(!hasPrevious()){

            previousIndex=-1;// Celui qui précede  avant l'actuel
            nextIndex=0;//Celui qui suit apres l'actuel
            lastIndex=-1;//Celui ou on était
        }
    }


    public boolean hasNext() {
        if(this.nextIndex< RobotBOX.size()){
            return true;
        }
        return false;
    }

    public line next() {
        if(!hasNext()){
            throw new NoSuchElementException("N'a pas de line plus recent");
        }
        lastIndex = nextIndex;
        nextIndex++;
        previousIndex ++;

        return RobotBOX.get(lastIndex());
    }

    public line getNext() {
        if(!hasNext()){
            throw new NoSuchElementException("N'a pas de line plus recent");
        }
        return RobotBOX.get(nextIndex());
    }

    private int nextIndex() {
        return hasNext() ? nextIndex : getRobotBOX().size();
    }

    public boolean hasPrevious() {
        return this.previousIndex > 0;
    }

    public line previous() {
        if(!hasPrevious()){
            throw new NoSuchElementException("N'a pas de line plus ancien.");
        }
        nextIndex--;
        lastIndex = previousIndex;
        previousIndex--;

        return RobotBOX.get(lastIndex--);

    }

    public line getPrevious() {
        if(!hasPrevious()){
            throw new NoSuchElementException("N'a pas de line plus ancien.");
        }
        return RobotBOX.get(previousIndex());
    }

    public int previousIndex(){
        return this.previousIndex;
    }

    public int lastIndex() {

        return lastIndex;
    }
}
