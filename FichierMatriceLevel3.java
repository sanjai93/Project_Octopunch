public class matrice extends fichier{
    private int [][] matrice;
    private int lignes;
    private int colonnes;
    public matrice (int ligne, int colonne){
        this.lignes = ligne;
        this.colonnes = colonne;
        matrice = new int[ligne][colonne];
    }
    public int[][] getMatrice() {
        return matrice;
    }

    // retourne l'élément dune case particulière //
    public int getElement(int i, int j){
        return matrice[i][j];
    }

    // Defini un élément de la matrice //
    public void defElement(int i, int j, int valeur){
        matrice[i][j] = valeur;
    }

    public void afficher() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println();
        }
    }
}