import java.rmi.UnexpectedException;

public class register{
    private String letter;
    private int value;

    /**
     * Crée un registre munit de ça lettre ainsi que de ça valeur
     * 
     * @param LETTRE La lettre du registre 
     * 
     * @throws NullPointerException Si LETTRE est null
     */

    public register(String LETTRE){//La classe ne pourra pas etre manipulabe par l'user ni par un quelconque aléatoire donc inutile de mettre des conditions sur la lettre
        if(LETTRE==null){
            throw new NullPointerException("register : Argument du String nul");
        }
        this.letter=LETTRE;
        this.value=0; 
    }
    /**
     * Change la valeur du registre
     * 
     * @param val
     *
     *
     */
    public void Set(int val){
        this.value=val;
    }

    /**
     * Test si 2 registre on la meme lettre 
     * 
     * @param o Object à tester
     * 
     * @return true si meme registre, false sinon
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof register)){
            return false;
        }
        register test= (register) o;
        if (this.getLetter().equals(test.getLetter())) {//Inutile de chercher + loin car la valeur du registre n'est pas importante ici
            return true;
        }
        return false;

    }
    /**
     * Renvoie la lettre du registre 
     * 
     * @return la lettre du registre
     */
    public String getLetter(){
        return letter;
    }

    /**
     * Renvoie la valeur du registre 
     * 
     * @return la valeur du registre
     */
    public int getValue(){
        return value;
    }


}
