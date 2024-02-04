import java.util.ArrayList;

public class Boxe {
    private Doors porte = new Doors();
    private ArrayList<XA> Robot=new ArrayList<XA>();

    private folder Fichier= new folder();

    public Boxe(){

    }
    public void AddXA(XA R) {
        Robot.add(R);
    }


    public ArrayList<XA> getRobot() {
        return Robot;
    }

    public Doors getPorte() {
        return porte;
    }

    public folder getFichier() {
        return Fichier;
    }
    //Link n'est pas complet il ne vérifie pas si la porte existe,A LANCER AVANT D'EXECUTER
    public void Link2() {
        for(XA XA : Robot){
            for (int i =0;i<XA.getRobotBOX().size();i++){
                ArrayList<line> L = new ArrayList<>(XA.getRobotBOX());
                String[] argum = L.get(i).ToSrting().split("\\s+");// "mange" les espaces : "cacao au lait"=> argum[0]=cacao; argum[1]=au;etc
                if(argum[0].equals("LINK")){
                    int tmp= Integer.parseInt(argum[0]);
                    if(tmp!= porte.getNuméro()){
                        XA.RobotBOX.get(i).setLegal(false);

                    }

                }
            }

        }
    }

}
