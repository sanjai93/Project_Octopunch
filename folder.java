public class folder {
    private int ID;
    private int contenu;

    public  folder(){
        this.ID=0;
        this.contenu=0;
    }
    public folder(int id, int Contenu){
        this.ID=id;
        this.contenu=Contenu;
    }

    public void setContenu(int contenu) {
        this.contenu = contenu;
    }

    public int getID(){
        return this.ID;
    }

    public int getContenu() {
        return contenu;
    }
}
