import java.util.ArrayList;

public class VerifLine {
    private boolean legal;

    public VerifLine(ArrayList<line> Line){
        this.legal=true;
        for(line L : Line){
            if(!L.Legal()){
                System.out.println("L'une des lignes rentrée est incorrect \n");
                this.legal=false;
            }
        }
        
        
    }

    public boolean legal(){
        return this.legal;
    }
    public static void main(String[] args) {
        ArrayList<line> coco = new ArrayList<>();
    
        coco.add(new line("MARK 5"));
        coco.add(new line("ADDI X X X"));
        coco.add(new line("TEST 5 > 10"));
        VerifLine yeah =new VerifLine(coco);
        System.out.println(yeah.legal());
    }
}
