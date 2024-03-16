import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicInterface {
    private static int currentLine = 0;
    private static JTabbedPane robotTabs = new JTabbedPane();
    private static String texte;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exapunks Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 770);

        // Ajout des zones de texte pour différents robots
        JTextArea codeArea1 = createRobotCodeArea();
        robotTabs.addTab("Robot 1", new JScrollPane(codeArea1));
        frame.add(robotTabs, BorderLayout.WEST);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();

        JButton runButton = createStyledButton("Run");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le texte de la boîte de texte
                texte = codeArea1.getText();
                System.out.println("Le code à exécuter :");
                String[] lines = texte.split("\n");
                int i=0;
                for(String co : lines){
                    System.out.println(i+":"+co);
                    i++;
                }
                
                
        
            }
        });

       
        JButton stepButton = createStyledButton("Step");
        


        JButton stopButton = createStyledButton("Stop");

        buttonPanel.add(runButton);
        buttonPanel.add(stepButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Panneau principal avec layout personnalisé
        JPanel mainPanel = new JPanel(null); // Utilisez null layout pour positionnement manuel
        frame.add(mainPanel, BorderLayout.CENTER);

        // Création et configuration des GamePanel
        GamePanel gamePanel1 = new GamePanel(40, 5); // Utilisation d'une taille de cellule réduite comme exemple
        GamePanel gamePanel2 = new GamePanel(40, 5); // Idem
        GamePanel gamePanel3 = new GamePanel(40, 5); // Idem

        // Configuration des positions de manière à ne pas les empiler
        positionGamePanels(mainPanel, gamePanel1, gamePanel2, gamePanel3);

        // Ajouter les zones de jeu au panneau principal
        mainPanel.add(gamePanel1);
        mainPanel.add(gamePanel2);
        mainPanel.add(gamePanel3);

        // Ajouter le panneau principal à la fenêtre
        frame.add(mainPanel, BorderLayout.CENTER);

        // Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu Jeu
        JMenu gameMenu = new JMenu("Jeu");
        JMenuItem startGame = new JMenuItem("Démarrer le jeu");
        JMenuItem endGame = new JMenuItem("Terminer le jeu");
        gameMenu.add(startGame);
        gameMenu.add(endGame);

        // Menu Difficulté
        JMenu difficultyMenu = new JMenu("Difficulté");
        JMenuItem easy = new JMenuItem("Facile");
        JMenuItem medium = new JMenuItem("Moyen");
        JMenuItem hard = new JMenuItem("Difficile");
        difficultyMenu.add(easy);
        difficultyMenu.add(medium);
        difficultyMenu.add(hard);

        // Ajouter les menus à la barre de menu
        menuBar.add(gameMenu);
        menuBar.add(difficultyMenu);

        // Ajouter la barre de menu à la fenêtre
        frame.setJMenuBar(menuBar);

        stepButton.addActionListener(e -> {
            // Ajoutez ici le code pour gérer l'événement de clic sur le bouton "Step"
            // Par exemple, exécuter la prochaine instruction du code du robot
            JTextArea currentCodeArea = (JTextArea) ((JScrollPane) robotTabs.getSelectedComponent()).getViewport().getView();
            executeNextInstruction(currentCodeArea);
            gamePanel1.repaint(); // Assurez-vous de redessiner la grille après chaque étape
        });

        stopButton.addActionListener(e -> {
            // Ajoutez ici le code pour gérer l'événement de clic sur le bouton "Stop"
            // Par exemple, arrêter l'exécution du code du robot
        });

        frame.setVisible(true);
    }

    private static void executeNextInstruction(JTextArea codeArea) {
        String[] lines = codeArea.getText().split("\n");
        if (currentLine < lines.length) {
            String instruction = lines[currentLine];
            currentLine++;
        }
    }

    private static JTextArea createRobotCodeArea() {
        JTextArea codeArea = new JTextArea(20, 40);
        codeArea.setForeground(new Color(255, 255, 255)); // Couleur du texte en blanc
        codeArea.setBackground(new Color(0, 0, 0)); // Arrière-plan noir
        codeArea.setCaretColor(Color.WHITE); // Couleur du curseur en blanc
        codeArea.setFont(new Font("Monospaced", Font.BOLD, 12)); // Police monospace en gras, taille 12
        codeArea.setLineWrap(true); // Activation du retour à la ligne automatique
        codeArea.setWrapStyleWord(true); // Séparation des mots pour les retours à la ligne automatiques
        codeArea.setBorder(BorderFactory.createCompoundBorder(
                codeArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Ajouter un peu d'espace autour du texte
        return codeArea;
    }

    private static void positionGamePanels(JPanel mainPanel, GamePanel... gamePanels) {
        int x = 10; // Position initiale x
        int y = 10; // Position initiale y
        int offset = 220; // Décalage pour la position suivante

        for (GamePanel gamePanel : gamePanels) {
            gamePanel.setBounds(x, y, gamePanel.getCellSize() * gamePanel.getGridSize(), gamePanel.getCellSize() * gamePanel.getGridSize());
            mainPanel.add(gamePanel);
            x += offset; // Décaler horizontalement pour le prochain panel
            y += offset;
        }
    }


    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        button.setForeground(new Color(255, 255, 255)); // Texte en blanc
        button.setFont(new Font("Arial Narrow", Font.BOLD, 14)); // Police Arial Narrow, gras, taille 14
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 2)); // Bordure violette
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100,40)); // Taille préférée du bouton
        return button;
    }

    static class GamePanel extends JPanel {
        private final int cellSize; // Taille d'une cellule
        private final int gridSize; // Taille de la grille
        private final JTextField[][] textFields; // Tableau de champs de texte

        public GamePanel(int cellSize, int gridSize) {
            this.cellSize = cellSize;
            this.gridSize = gridSize;
            this.textFields = new JTextField[gridSize][gridSize];
            setLayout(new GridLayout(gridSize, gridSize)); // Définit le layout de la grille
            initTextFields(); // Initialise les champs de texte dans la grille
        }

        // Initialise les champs de texte dans la grille
        private void initTextFields() {
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    JTextField textField = new JTextField();
                }
            }
        }

        public int getCellSize() {
            return cellSize;
        }

        public int getGridSize() {
            return gridSize;
        }

        // Redessine la grille
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dessine une grille vide de champs de texte
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    int x = j * cellSize;
                    int y = i * cellSize;
                    g.drawRect(x, y, cellSize, cellSize); // Dessine un rectangle pour chaque cellule
                }
            }
        }
    }
}
