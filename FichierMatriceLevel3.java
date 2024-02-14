public class matrice {
    private String[][] matrice;
    private int lignes;
    private int colonnes;

    public matrice(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        matrice = new String[lignes][colonnes];

        for (int i = 0; i < this.lignes; i++) {
            for (int j = 0; j < this.colonnes; j++) {
                    this.defElement(i, j, "*");
            }
        }
           
    }

    public int getLignes(){
        return lignes;
    }

    public int getColonnes(){
        return colonnes;
    }

    // retourne l'élément d'une case particulière
    public String getElement(int i, int j) {
        return matrice[i][j];
    }

    // Définit un élément de la matrice
    public void defElement(int i, int j, String valeur) {
        matrice[i][j] = valeur;
    }

    public void afficher() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    

    public static void main(String[] args) {
        matrice mat = new matrice(5, 5);

        mat.afficher();
        
    }
}
