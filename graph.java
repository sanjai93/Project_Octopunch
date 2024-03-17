import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class graph {
    private static int currentLine = 0;
    private static JTabbedPane robotTabs = new JTabbedPane();
    private static GamePanel gamePanel1; // Déclaration statique
    private static GamePanel gamePanel2; // Déclaration statique
    private static GamePanel gamePanel3; // Déclaration statique

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exapunks Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1850, 750);

        // Ajout des panels de contrôle pour différents robots
        JPanel robotControlPanel1 = createRobotControlPanel();
        robotTabs.addTab("Robot 1", robotControlPanel1);

        JPanel robotControlPanel2 = createRobotControlPanel();
        robotTabs.addTab("Robot 2", robotControlPanel2);

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

        JPanel mainPanel = new JPanel(new BorderLayout()); // Utilisation d'un BorderLayout pour le panneau principal

        // Créer le panneau qui contiendra les zones de jeu
        JPanel gamePanelsContainer = new JPanel(new GridLayout(1, 3, 10, 10)); // GridLayout pour aligner les zones de
        // jeu

        // Configuration de la bordure du gamePanelsContainer pour entourer les 3
        // GamePanel
        Border outerBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0); // Marge autour
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5); // Bordure extérieure
        gamePanelsContainer.setBorder(BorderFactory.createCompoundBorder(lineBorder, outerBorder));

        GamePanel gamePanel1 = new GamePanel(40);
        GamePanel gamePanel2 = new GamePanel(40);
        gamePanel2.setShouldDisplaySpider(false); // Ne pas afficher le robot araignée dans cette grille
        GamePanel gamePanel3 = new GamePanel(40);
        gamePanel3.setShouldDisplaySpider(false); // Ne pas afficher le robot araignée dans cette grille
        GamePanel activeGamePanel = gamePanel1;

        // Ajouter les zones de jeu au conteneur
        gamePanelsContainer.add(gamePanel1);
        gamePanelsContainer.add(gamePanel2);
        gamePanelsContainer.add(gamePanel3);

        // Ajouter le conteneur des zones de jeu au panneau principal
        mainPanel.add(gamePanelsContainer, BorderLayout.CENTER);

        // Zone de texte pour les instructions du robot
        JTextArea instructionsArea = new JTextArea(3, 20); // Réduire le nombre de lignes pour diminuer la hauteur
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea); // Création de JScrollPane pour
        // instructionsArea
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

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le JPanel sélectionné dans le JTabbedPane
                JPanel selectedPanel = (JPanel) robotTabs.getSelectedComponent();
                // Trouver le JScrollPane à l'intérieur du JPanel
                JScrollPane scrollPane = null;
                for (Component comp : selectedPanel.getComponents()) {
                    if (comp instanceof JScrollPane) {
                        scrollPane = (JScrollPane) comp;
                        break;
                    }
                }
                if (scrollPane != null) {
                    JViewport viewport = scrollPane.getViewport();
                    JTextArea currentCodeArea = (JTextArea) viewport.getView();
                    // Ici, nous utilisons currentCodeArea pour afficher son contenu
                    String codeText = currentCodeArea.getText();
                    // Découper le texte en lignes
                    String[] lines = codeText.split("\n");
                    // Afficher chaque ligne avec son numéro dans la console
                    for (int i = 0; i < lines.length; i++) {
                        System.out.println((i + 1) + ": " + lines[i]);
                    }
                }
            }
        });


        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le JPanel sélectionné dans le JTabbedPane
                JPanel selectedPanel = (JPanel) robotTabs.getSelectedComponent();
                // Trouver le JScrollPane à l'intérieur du JPanel
                JScrollPane scrollPane = null;
                for (Component comp : selectedPanel.getComponents()) {
                    if (comp instanceof JScrollPane) {
                        scrollPane = (JScrollPane) comp;
                        break;
                    }
                }
                if (scrollPane != null) {
                    JViewport viewport = scrollPane.getViewport();
                    JTextArea currentCodeArea = (JTextArea) viewport.getView();
                    // Ici, nous utilisons currentCodeArea pour afficher son contenu
                    String codeText = currentCodeArea.getText();
                    // Découper le texte en lignes
                    String[] lines = codeText.split("\n");
                    // Afficher chaque ligne avec son numéro dans la console
                    for (int i = 0; i < lines.length; i++) {
                        System.out.println((i + 1) + ": " + lines[i]);
                    }
                }
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

    private static JPanel createRobotControlPanel() {
        // Panel principal pour la zone de texte et les champs
        JPanel controlPanel = new JPanel(new BorderLayout());

        // Création de la zone de texte pour le code
        JTextArea codeArea = new JTextArea();
        codeArea.setText("Votre code ici...");
        JScrollPane codeScrollPane = new JScrollPane(codeArea);
        controlPanel.add(codeScrollPane, BorderLayout.CENTER);

        // Panel pour les champs X, T, F, et M
        JPanel fieldsPanel = new JPanel(new FlowLayout());

        // Valeurs entières initiales pour les champs
        int initialValueX = 0;
        int initialValueT = 0;
        int initialValueF = 0;
        int initialValueM = 0;

// Création et ajout des champs avec des valeurs entières converties en String
        JTextField fieldX = new JTextField(String.valueOf(initialValueX), 5);
        fieldX.setEditable(false); // Rend le champ non modifiable
        JTextField fieldT = new JTextField(String.valueOf(initialValueT), 5);
        fieldT.setEditable(false); // Rend le champ non modifiable
        JTextField fieldF = new JTextField(String.valueOf(initialValueF), 5);
        fieldF.setEditable(false); // Rend le champ non modifiable
        JTextField fieldM = new JTextField(String.valueOf(initialValueM), 5);
        fieldM.setEditable(false); // Rend le champ non modifiable

        int valueX;
        try {
            valueX = Integer.parseInt(fieldX.getText());
        } catch (NumberFormatException e) {
            // Gestion de l'erreur si la chaîne ne peut pas être convertie en entier
            valueX = 0; // Ou toute autre valeur par défaut/logique de gestion d'erreur
        }

        // Ajout des champs au panel de champs
        fieldsPanel.add(new JLabel("X:"));
        fieldsPanel.add(fieldX);
        fieldsPanel.add(new JLabel("T:"));
        fieldsPanel.add(fieldT);
        fieldsPanel.add(new JLabel("F:"));
        fieldsPanel.add(fieldF);
        fieldsPanel.add(new JLabel("M:"));
        fieldsPanel.add(fieldM);

        // Ajout du panel de champs au panel principal
        controlPanel.add(fieldsPanel, BorderLayout.SOUTH);

        return controlPanel;
    }
    private static GamePanel activeGamePanel = gamePanel1; // Commencer avec le robot dans le premier GamePanel

    private static void setActiveGamePanel(String panelName) {
        switch (panelName) {
            case "gamePanel1":
                activeGamePanel = gamePanel1;
                break;
            case "gamePanel2":
                activeGamePanel = gamePanel2;
                break;
            case "gamePanel3":
                activeGamePanel = gamePanel3;
                break;
            default:
                System.out.println("Nom de panneau invalide.");
                break;
        }
        // Assurez-vous que le robot est visible seulement dans la grille active
        gamePanel1.setShouldDisplaySpider(activeGamePanel == gamePanel1);
        gamePanel2.setShouldDisplaySpider(activeGamePanel == gamePanel2);
        gamePanel3.setShouldDisplaySpider(activeGamePanel == gamePanel3);
    }

    private static JTextArea createRobotCodeArea() {
        JTextArea codeArea = new JTextArea(20, 40);
        // Vérifier si la police "Orbitron" est disponible, sinon utiliser "Monospaced"
        if (Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                .contains("Orbitron")) {
            codeArea.setFont(new Font("Orbitron", Font.BOLD, 12)); // Utilisez Orbitron si disponible
        } else {
            codeArea.setFont(new Font("Monospaced", Font.BOLD, 12)); // Sinon, reculez sur Monospaced
        }
        codeArea.setForeground(new Color(255, 00, 00)); // Couleur du texte en vert lumineux, style terminal ou code
        // éditeur
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
            gamePanel.setBounds(x, y, gamePanel.getCellSize() * gamePanel.getGridSize(),
                    gamePanel.getCellSize() * gamePanel.getGridSize());
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
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    static class GamePanel extends JPanel {
        private boolean shouldDisplaySpider = true;
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

        public static void moveRobotTo(int newRow, int newCol) {
            if (newRow >= 0 && newRow < activeGamePanel.getGridSize() && newCol >= 0 && newCol < activeGamePanel.getGridSize()) {
                activeGamePanel.robotRow = newRow;
                activeGamePanel.robotCol = newCol;
                activeGamePanel.repaint();
            } else {
                System.out.println("Coordonnées hors de la grille.");
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
                    g.fillOval(centerX - (eyeX - centerX) - eyeRadius, eyeY, eyeRadius, eyeRadius); // Miroir pour les
                    // yeux de l'autre
                    // côté
                }

                // Pattes de l'araignée robot
                g.setColor(new Color(80, 80, 80)); // Une teinte légèrement différente pour les pattes
                int legLength = cellSize / 2;
                for (int i = 0; i < 4; i++) { // 4 paires de pattes
                    double angle = Math.PI / 4 * i;
                    int legEndX = centerX + (int) (legLength * Math.cos(angle));
                    int legEndY = centerY - (int) (legLength * Math.sin(angle));
                    g.drawLine(centerX, centerY, legEndX, legEndY); // Pattes partant du centre du corps
                    g.drawLine(centerX, centerY, centerX - (legEndX - centerX), legEndY); // Pattes miroir de l'autre
                    // côté
                }
            }

        }

    }
}
