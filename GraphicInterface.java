import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicInterface {
    private static int currentLine = 0;
    private static JTabbedPane robotTabs = new JTabbedPane();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Exapunks Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1100);

        // Ajout des zones de texte pour différents robots
        JTextArea codeArea1 = new JTextArea(20, 40);
        robotTabs.addTab("Robot 1", new JScrollPane(codeArea1));
        JTextArea codeArea2 = new JTextArea(20, 40);
        robotTabs.addTab("Robot 2", new JScrollPane(codeArea2));
        frame.add(robotTabs, BorderLayout.WEST);


        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton runButton = createStyledButton("Run");
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
        GamePanel gamePanel1 = new GamePanel(60); // Utilisation d'une taille de cellule réduite comme exemple
        GamePanel gamePanel2 = new GamePanel(60); // Idem
        GamePanel gamePanel3 = new GamePanel(60); // Idem

        // Configuration des positions de manière à ne pas les empiler
        positionGamePanels(mainPanel, gamePanel1, gamePanel2, gamePanel3);

        // Ajouter les zones de jeu au panneau principal
        mainPanel.add(gamePanel1);
        mainPanel.add(gamePanel2);
        mainPanel.add(gamePanel3);

        // Zone de texte pour les instructions du robot
        JTextArea instructionsArea = new JTextArea(3, 20); // Réduire le nombre de lignes pour diminuer la hauteur
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea); // Création de JScrollPane pour instructionsArea
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);

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

        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea currentCodeArea = (JTextArea) ((JScrollPane) robotTabs.getSelectedComponent()).getViewport().getView();
                executeNextInstruction(currentCodeArea);
                // Mettez à jour l'état de `gamePanel1` ou d'autres panels ici au lieu de `game1`
                gamePanel1.setShouldDisplaySpider(true);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utilisez `gamePanel1` ici au lieu de `game1`
                gamePanel1.setShouldDisplaySpider(false);
            }
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

    private static void positionGamePanels(JPanel mainPanel, GamePanel... gamePanels) {
        int x = 10; // Position initiale x
        int y = 10; // Position initiale y
        int offset = 300; // Décalage pour la position suivante

        for (GamePanel gamePanel : gamePanels) {
            gamePanel.setBounds(x, y, gamePanel.getCellSize() * gamePanel.getGridSize(), gamePanel.getCellSize() * gamePanel.getGridSize());
            mainPanel.add(gamePanel);
            x += offset; // Décaler horizontalement pour le prochain panel
            y += offset;
        }
    }

    private static void resetRobot(JTextArea codeArea) {
        currentLine = 0;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 0, 51)); // Fond bleu foncé
        button.setForeground(new Color(255, 255, 153)); // Texte en jaune clair
        //  Police Arial Narrow
        button.setFont(new Font("Arial Narrow", Font.BOLD, 12));
        return button;
    }

    static class GamePanel extends JPanel {
        private boolean shouldDisplaySpider = false;
        private int robotRow = 1; // Ligne initiale du robot
        private int robotCol = 1; // Colonne initiale du robot
        private final int gridSize = 5; // Taille de la grille 5x5
        private int cellSize; // Taille de chaque cellule de la grille modifiable

        public GamePanel(int cellSize) {
            this.cellSize = cellSize;
        }

        public int getCellSize() {
            return cellSize;
        }

        public int getGridSize() {
            return gridSize;
        }

        public void setShouldDisplaySpider(boolean shouldDisplaySpider) {
            this.shouldDisplaySpider = shouldDisplaySpider;
            repaint();
        }

        public void moveRobot(int newRow, int newCol) {
            // Assurez-vous que les nouvelles coordonnées sont dans les limites de la grille
            if (newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize) {
                robotRow = newRow;
                robotCol = newCol;
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Calculer le point de départ pour centrer la grille
            int totalGridWidth = gridSize * cellSize;
            int totalGridHeight = gridSize * cellSize;
            int startX = (getWidth() - totalGridWidth) / 2;
            int startY = (getHeight() - totalGridHeight) / 2;

            // Dessiner la grille avec des cases noires et des bordures vertes
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(startX + i * cellSize, startY + j * cellSize, cellSize, cellSize);
                    g.setColor(Color.GREEN);
                    g.drawRect(startX + i * cellSize, startY + j * cellSize, cellSize, cellSize);
                }
            }

            if (shouldDisplaySpider) {
                int x = robotCol * cellSize;
                int y = robotRow * cellSize;
                // Corps du robot
                g.setColor(Color.ORANGE);
                g.fillRect(x + 20, y + 20, cellSize - 40, cellSize - 40);
                // Tête du robot
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x + 30, y + 10, cellSize - 60, 20);
                // Yeux du robot
                g.setColor(Color.GREEN);
                g.fillOval(x + 30, y + 15, 10, 10);
                g.fillOval(x + cellSize - 40, y + 15, 10, 10);
                // Bras du robot
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x + 10, y + 30, 10, 20);
                g.fillRect(x + cellSize - 20, y + 30, 10, 20);
            }
        }

    }



}
