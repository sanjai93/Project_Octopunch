public class TestLine {
    private String instruction;
    private boolean legal;

    public static void main(String[] args) {

        String[] instru = {"le méchant garçon", "le Attention      .       Baltazar", "Yakari  , Yakari !"};
        
        for (String co : instru) {
            co=co.toUpperCase();
            String[] words = co.split("\\s+");
            
            if(words[0].equals("LE") && words.length==4){
            // Affichage des mots individuels
            for (String word : words) {
                System.out.println(word);
            }
            }
        }
    }
}
