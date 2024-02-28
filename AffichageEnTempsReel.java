public class AffichageEnTempsReel {
    public static void main(String[] args) {
        // Utiliser une boucle pour afficher en temps réel
        for (int i = 0; i < 10; i++) {
            // Effacer la console (fonctionne sur certains terminaux)
            System.out.print("\033[H\033[2J popo");
            System.out.flush();

            // Afficher la valeur actuelle
            System.out.println("Valeur en temps réel : " + i);

            // Attendre une courte période avant la prochaine itération
            try {
                Thread.sleep(1000); // Attendre 1 seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
