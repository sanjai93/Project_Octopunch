import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class GraphicInterface {
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

        JPanel mainPanel = new JPanel(new BorderLayout()); // BorderLayout pour le panneau principal

        // Panneau qui contient les zones de jeu
        JPanel gamePanelsContainer = new JPanel(new GridLayout(1, 3, 10, 10)); // GridLayout pour aligner les zones de jeu

        //  bordure du gamePanelsContainer
        Border outerBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0); // Marge autour
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5); // Bordure extérieure
        gamePanelsContainer.setBorder(BorderFactory.createCompoundBorder(lineBorder, outerBorder));

        GamePanel gamePanel1 = new GamePanel(40);
        GamePanel gamePanel2 = new GamePanel(40);
        gamePanel2.setShouldDisplaySpider(false); // Ne pas afficher le robot araignée dans cette grille
        GamePanel gamePanel3 = new GamePanel(40);
        gamePanel3.setShouldDisplaySpider(false); // Ne pas afficher le robot araignée dans cette grille
        GamePanel activeGamePanel = gamePanel1;
        gamePanel1.setDoors(new Point(4, 2), null); // Porte vers le gamePanel2 uniquement
        gamePanel2.setDoors(new Point(0, 2), new Point(4, 2)); // Portes vers gamePanel1 et gamePanel3
        gamePanel3.setDoors(null, new Point(0, 2)); // Porte vers le gamePanel2 uniquement


        // Conteneur
        gamePanelsContainer.add(gamePanel1);
        gamePanelsContainer.add(gamePanel2);
        gamePanelsContainer.add(gamePanel3);

        // Ajout du conteneur au panneau principal
        mainPanel.add(gamePanelsContainer, BorderLayout.CENTER);

        // Zone de texte pour les instructions du robot
        JTextArea instructionsArea = new JTextArea(3, 20); //
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);

        // Ajout du panneau principal à la fenêtre
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

        // Ajout des menus à la barre de menu
        menuBar.add(gameMenu);
        menuBar.add(difficultyMenu);

        // Ajout de la barre de menu à la fenêtre
        frame.setJMenuBar(menuBar);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel selectedPanel = (JPanel) robotTabs.getSelectedComponent();
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
                    // Affichage du contenu de currentCodeArea
                    String codeText = currentCodeArea.getText();
                    String[] lines = codeText.split("\n");
                    for (int i = 1; i < lines.length; i++) {
                        System.out.println((i + 1) + ": " + lines[i]);
                    }
                }
            }
        });


        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel selectedPanel = (JPanel) robotTabs.getSelectedComponent();
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
                    String codeText = currentCodeArea.getText();
                    String[] lines = codeText.split("\n");
                    // Afficher chaque ligne avec son numéro dans la console
                    for (int i = 1; i < lines.length; i++) {
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
        JTextArea codeArea = new JTextArea(20,40);
        codeArea.setText("Insérer vos instructions ici... ");
        JScrollPane codeScrollPane = new JScrollPane(codeArea);
        controlPanel.add(codeScrollPane, BorderLayout.CENTER);
        if (Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).contains("Orbitron")) {
            codeArea.setFont(new Font("Orbitron", Font.BOLD, 12)); // Utiliser Orbitron si disponible
        } else {
            codeArea.setFont(new Font("Monospaced", Font.BOLD, 12)); // Sinon, utiliser Monospaced
        }
        codeArea.setForeground(new Color(255, 0, 0));
        codeArea.setBackground(new Color(0, 0, 0));
        codeArea.setCaretColor(Color.WHITE);
        codeArea.setBorder(BorderFactory.createCompoundBorder(
                codeArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        codeScrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        controlPanel.add(codeScrollPane, BorderLayout.CENTER);



        // Panel pour les champs X, T, F, et M
        JPanel fieldsPanel = new JPanel(new FlowLayout());

        // Valeurs entières initiales pour les champs
        int initialValueX = 0;
        int initialValueT = 0;
        int initialValueF = 0;
        int initialValueM = 0;

// Création et ajout des champs avec des valeurs entières
        JTextField fieldX = new JTextField("0", 5); fieldX.setEditable(false); // Champ pour X
        JTextField fieldT = new JTextField("0", 5); fieldT.setEditable(false); // Champ pour T
        JTextField fieldF = new JTextField("0", 5); fieldF.setEditable(false); // Champ pour F
        JTextField fieldM = new JTextField("0", 5); fieldM.setEditable(false); // Champ pour M

        int valueX;
        try {
            valueX = Integer.parseInt(fieldX.getText());
        } catch (NumberFormatException e) {
            // Gestion de l'erreur si la chaîne ne peut pas être convertie en entier
            valueX = 0; // Ou toute autre valeur par défaut/logique de gestion d'erreur
        }

        // Ajouter les champs au panel de champs
        fieldsPanel.add(new JLabel("X:")); fieldsPanel.add(fieldX);
        fieldsPanel.add(new JLabel("T:")); fieldsPanel.add(fieldT);
        fieldsPanel.add(new JLabel("F:")); fieldsPanel.add(fieldF);
        fieldsPanel.add(new JLabel("M:")); fieldsPanel.add(fieldM);

        // Ajout du panel de champs au panel principal
        controlPanel.add(fieldsPanel, BorderLayout.SOUTH);

        return controlPanel;
    }
    private static GamePanel activeGamePanel = gamePanel1;

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

        gamePanel1.setShouldDisplaySpider(activeGamePanel == gamePanel1);
        gamePanel2.setShouldDisplaySpider(activeGamePanel == gamePanel2);
        gamePanel3.setShouldDisplaySpider(activeGamePanel == gamePanel3);
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
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    static class GamePanel extends JPanel {
        private boolean shouldDisplaySpider = true;
        private int robotRow = 1;
        private int robotCol = 1;
        private final int gridSize = 5; // Taille de la grille 5x5
        private int cellSize;
        private Point doorToNext;
        private Point doorToPrevious;



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

        public void setDoors(Point toNext, Point toPrevious) {
            this.doorToNext = toNext;
            this.doorToPrevious = toPrevious;
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

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(startX + i * cellSize, startY + j * cellSize, cellSize, cellSize);
                    g.setColor(Color.MAGENTA);
                    g.drawRect(startX + i * cellSize, startY + j * cellSize, cellSize, cellSize);
                }
            }

            if (shouldDisplaySpider) {
                int centerX = startX + robotCol * cellSize + cellSize / 2;
                int centerY = startY + robotRow * cellSize + cellSize / 2;
                int bodyRadius = cellSize / 4;

                // Corps de l'araignée robot
                g.setColor(Color.WHITE);
                g.fillOval(centerX - bodyRadius, centerY - bodyRadius, 2 * bodyRadius, 2 * bodyRadius);

                // Yeux de l'araignée robot
                g.setColor(Color.RED); // Utilisation de LED rouges pour les yeux
                int eyeRadius = bodyRadius / 4;
                int eyesCount = 8;
                for (int i = 0; i < eyesCount / 2; i++) {

                    double angle = Math.PI / (eyesCount / 2 - 1) * i;
                    int eyeX = centerX + (int) (bodyRadius * Math.cos(angle)) - eyeRadius / 2;
                    int eyeY = centerY - (int) (bodyRadius * Math.sin(angle)) - eyeRadius / 2;
                    g.fillOval(eyeX, eyeY, eyeRadius, eyeRadius);
                    g.fillOval(centerX - (eyeX - centerX) - eyeRadius, eyeY, eyeRadius, eyeRadius);

                }

                // Pattes de l'araignée robot
                g.setColor(new Color(80, 80, 80));
                int legLength = cellSize / 2;
                for (int i = 0; i < 4; i++) {
                    double angle = Math.PI / 4 * i;
                    int legEndX = centerX + (int) (legLength * Math.cos(angle));
                    int legEndY = centerY - (int) (legLength * Math.sin(angle));
                    g.drawLine(centerX, centerY, legEndX, legEndY);
                    g.drawLine(centerX, centerY, centerX - (legEndX - centerX), legEndY);
                }
            }

            Graphics2D g2d = (Graphics2D) g;
            if (doorToNext != null) {
                // Dessin de la porte vers la zone suivante
                int xNext = startX + doorToNext.x * cellSize;
                int yNext = startY + doorToNext.y * cellSize;
                g2d.setColor(Color.BLUE);
                g2d.fillRect(xNext, yNext, cellSize, cellSize); // Corps de la porte

                g2d.setColor(Color.GRAY);
                g2d.fillOval(xNext + (3*cellSize/4), yNext + cellSize/2, cellSize/10, cellSize/10); // Poignée de la porte

                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xNext, yNext, cellSize, cellSize); // Cadre de la porte
            }

            if (doorToPrevious != null) {
                // Dessin de la porte vers la zone précédente
                int xPrev = startX + doorToPrevious.x * cellSize;
                int yPrev = startY + doorToPrevious.y * cellSize;
                g2d.setColor(Color.CYAN);
                g2d.fillRect(xPrev, yPrev, cellSize, cellSize); // Corps de la porte

                g2d.setColor(Color.GRAY);
                g2d.fillOval(xPrev + cellSize/4, yPrev + cellSize/2, cellSize/10, cellSize/10); // Poignée de la porte

                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xPrev, yPrev, cellSize, cellSize); // Cadre de la porte
            }
        }


    }

}
