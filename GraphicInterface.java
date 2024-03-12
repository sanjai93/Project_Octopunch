import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GraphicInterface {
    private static int currentLine = 0;
    private static JTabbedPane robotTabs = new JTabbedPane();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Exapunks Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 770);

        // Ajout des zones de texte pour différents robots
        JTextArea codeArea1 = createRobotCodeArea();
        robotTabs.addTab("Robot 1", new JScrollPane(codeArea1));
        JTextArea codeArea2 = createRobotCodeArea();
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
        GamePanel gamePanel1 = new GamePanel(40); // Utilisation d'une taille de cellule réduite comme exemple
        GamePanel gamePanel2 = new GamePanel(40); // Idem
        GamePanel gamePanel3 = new GamePanel(40); // Idem

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

    private static JTextArea createRobotCodeArea() {
        JTextArea codeArea = new JTextArea(20, 40);
        // Vérifier si la police "Orbitron" est disponible, sinon utiliser "Monospaced"
        if (Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).contains("Orbitron")) {
            codeArea.setFont(new Font("Orbitron", Font.BOLD, 12)); // Utilisez Orbitron si disponible
        } else {
            codeArea.setFont(new Font("Monospaced", Font.BOLD, 12)); // Sinon, reculez sur Monospaced
        }
        codeArea.setForeground(new Color(255, 00, 00)); // Couleur du texte en vert lumineux, style terminal ou code éditeur
        codeArea.setBackground(new Color(0, 0, 0)); // Arrière-plan noir
        codeArea.setCaretColor(Color.WHITE); // Couleur du curseur
        codeArea.setBorder(BorderFactory.createCompoundBorder(
                codeArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Ajouter un peu d'espace autour du texte

        // Pour une bordure plus sophistiquée :
        codeArea.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2)); // Bordure grise foncée

        // Ajouter un effet de scroll
        JScrollPane scrollPane = new JScrollPane(codeArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80))); // Bordure du JScrollPane
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

    private static void resetRobot(JTextArea codeArea) {
        currentLine = 0;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 0, 51), 0, getHeight(), new Color(0, 0, 30));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setForeground(new Color(255, 255, 255)); // Texte en jaune clair
        button.setFont(new Font("Arial Narrow", Font.BOLD, 14)); // Police futuriste
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100,40));
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
                int centerX = startX + robotCol * cellSize + cellSize / 2; // Centre du robot dans la cellule
                int centerY = startY + robotRow * cellSize + cellSize / 2;
                int bodyRadius = cellSize / 4; // Rayon du corps de l'araignée robot

                // Corps de l'araignée robot
                g.setColor(new Color(60, 60, 60)); // Un gris métallique foncé
                g.fillOval(centerX - bodyRadius, centerY - bodyRadius, 2 * bodyRadius, 2 * bodyRadius);

                // Yeux de l'araignée robot
                g.setColor(Color.RED); // Utilisation de LED rouges pour les yeux
                int eyeRadius = bodyRadius / 4;
                int eyesCount = 8; // Une araignée a typiquement 8 yeux
                for (int i = 0; i < eyesCount / 2; i++) {
                    // Disposer les yeux autour du corps
                    double angle = Math.PI / (eyesCount / 2 - 1) * i;
                    int eyeX = centerX + (int) (bodyRadius * Math.cos(angle)) - eyeRadius / 2;
                    int eyeY = centerY - (int) (bodyRadius * Math.sin(angle)) - eyeRadius / 2;
                    g.fillOval(eyeX, eyeY, eyeRadius, eyeRadius);
                    g.fillOval(centerX - (eyeX - centerX) - eyeRadius, eyeY, eyeRadius, eyeRadius); // Miroir pour les yeux de l'autre côté
                }

                // Pattes de l'araignée robot
                g.setColor(new Color(80, 80, 80)); // Une teinte légèrement différente pour les pattes
                int legLength = cellSize / 2;
                for (int i = 0; i < 4; i++) { // 4 paires de pattes
                    double angle = Math.PI / 4 * i;
                    int legEndX = centerX + (int) (legLength * Math.cos(angle));
                    int legEndY = centerY - (int) (legLength * Math.sin(angle));
                    g.drawLine(centerX, centerY, legEndX, legEndY); // Pattes partant du centre du corps
                    g.drawLine(centerX, centerY, centerX - (legEndX - centerX), legEndY); // Pattes miroir de l'autre côté
                }
            }

        }


    }
}

