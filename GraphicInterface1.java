import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

class GraphicInterface1 {
    private static int currentLine = 0;
    private static JTabbedPane robotTabs = new JTabbedPane();
    private static GamePanel gamePanel1; // Déclaration statique
    private static GamePanel gamePanel2; // Déclaration statique
    private static GamePanel gamePanel3; // Déclaration statique
    private static PrimarRegister register = new PrimarRegister();
    private static String[] lines;
    private static boolean HaveF1=false;
    private static boolean TRC,MARK=false;
    private static int position=0;
    private static int fichierL1=123;
    private static ArrayList<line> convert = new ArrayList<>();
    private static ListIterator<line> iterator;
    private static boolean run;
    private static int robotRow = 0; // Ligne initiale du robot
    private static int robotCol = 0; // Colonne initiale du robot
    private static boolean mat1=false,mat2=false,mat3=false;
    private static int Mlabel, posMark ;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exapunks Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1850, 750);

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
        gamePanel1.setShouldDisplaySpider(true); // Ne pas afficher le robot araignée dans cette grille
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

         //Methode 
         COPY copy = new COPY();
         ADDI addi = new ADDI();
         SUBI subi = new SUBI();
         MULI muli = new MULI();
         DIVI divi = new DIVI();
         MODI modi = new MODI();
         SWIZ swiz = new SWIZ();
         TEST test = new TEST();

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



        // Récupérer le texte de codeArea1
       

       


        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le texte de codeArea1
                JTextArea currentCodeArea = (JTextArea) ((JScrollPane) robotTabs.getSelectedComponent()).getViewport().getView();
                String codeText = currentCodeArea.getText();
                run=true;
                gamePanel1.setShouldDisplaySpider(true);
                gamePanel2.setShouldDisplaySpider(false);
                gamePanel3.setShouldDisplaySpider(false);
                mat1=true;
                mat2=false;
                mat3 =false;
                                    
                // Découper le texte en lignes
                lines = codeText.split("\n");
               
                 // Découper le texte en lignes
    
                 convert.clear();
                for(String li : lines){
                    convert.add(new line(li));
                    
                }
                VerifLine coco =new VerifLine(convert);
                if(!coco.legal()){
                    convert.clear();
                    run=false;
                }
                else{
                    iterator = convert.listIterator();
                    int pos=0;
                    for(String li : lines){
                        String[] stp = li.split("\\s+");
                        pos++;
                        if(stp[0].equals("MARK")){
                            Mlabel= Integer.parseInt(stp[1]);
                            MARK=true;
                            posMark= pos;
                        }

                    }
                           
                    
                }

            }
        });

        
 
        
         
        
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(run){
                    if(iterator.hasNext()){
                        line instruction = iterator.next();

                        String[] nomInstruction = instruction.ToSrting().split("\\s+");  

                        int arg;


                        System.out.println(nomInstruction[0]);
                        switch (nomInstruction[0]) {
                            // Traitement des commandes //
                            case "LINK":
                        
                                arg = Integer.parseInt(nomInstruction[1]);
                                System.out.println(arg);
                                if (arg == 500 && mat1){
                                    mat1=false;
                                    mat2=true;
                                    gamePanel1.setShouldDisplaySpider(false);
                                    gamePanel2.setShouldDisplaySpider(true); // Ne pas afficher le robot araignée d
                                
                                }
                                if (arg == 400 && mat2){
                                    mat2=false;
                                    mat3=true;
                                    gamePanel2.setShouldDisplaySpider(false);
                                    gamePanel3.setShouldDisplaySpider(true); // Ne pas afficher le robot araignée d
                                
                                }
                                
                                break;

                            case "GRAB" :
                                arg = Integer.parseInt(nomInstruction[1]);
                                //Si le robot est dans la matrice + argument rentré est bine celui du fichier + Tu n'a pas de fichier 
                                if (  arg == 5 && !HaveF1){
                                        
                                    HaveF1=true; //Tu à maintenant un fichier
                                    register.getRegister("M").Set(fichierL1);//M prend la valeur à l'interieur du fichier
                            
                                }

                                break;
                            
                            case "ADDI":
                                addi.ADDI(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;
                            case "MULI":

                                muli.MULI(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;
                            case "SUBI":

                                subi.SUBI(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;

                            case "DIVI":
                                divi.DIVI(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;
                            case "MODI":

                                modi.MODI(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;
                            case "SWIZ":

                                swiz.SWIZ(nomInstruction[0], nomInstruction[1], register.getRegister(nomInstruction[2]), register);
                                break;

                            case "TEST":
                                test.TEST(nomInstruction[0], nomInstruction[1], nomInstruction[2], register);
                            break;


                            case "JUMP":
                            int nombreInstructionsASauter = Integer.parseInt(nomInstruction[1]);

                            if(MARK && nombreInstructionsASauter==Mlabel){ //Il y a un MARK dans les instructions
                            
                                while (position<= posMark){

                                    iterator.next();
                                    position++;
                                }
                                while (position>= posMark) {
                                    iterator.previous();
                                    position--;
                                }
                            }
                            else{
                                for (int i = 0; i < nombreInstructionsASauter && position < convert.size() - 2 && iterator.hasNext(); i++) {
                                    iterator.next(); // Passez à l'instruction suivante
                                    position++;
                                }
                                
                            }
                            // Sort de la boucle si nous avons atteint la fin de la liste
                            break;
                        case "FJMP":
                            int cond = register.getRegister("T").getValue();

                            if (cond == 0) {
                                int nombreInstructionsASauter_BIS = Integer.parseInt(nomInstruction[1]);
                                if(MARK && nombreInstructionsASauter_BIS==Mlabel){ //Il y a un MARK dans les instructions
                                
                                    while (position<=posMark){
                                        iterator.next();
                                        position++;
                                    }
                                    while (position>=posMark) {
                                        iterator.previous();
                                        position--;
                                    }
                                }
                                
                                else{
                                    for (int i = 0; i < nombreInstructionsASauter_BIS && position < convert.size() - 2; i++) {
                                        if (iterator.hasNext()) {
                                            iterator.next(); // Passez à l'instruction suivante
                                            position++;
                                        }
                                    }
                                }
                            }

                            break;

                            case "MARK":
                                break;



                            case "HALT" :
                            int value = register.getRegister("T").getValue();
                            if(value == 246){
                                    TRC = true;//Le cycle est valide.
                            }
                            break;

                            case "COPY":
                                copy.COPY(nomInstruction[0], register.getRegister(nomInstruction[1]), register);
                                break;

                            case "NOOP":
                                break;//Saute un cycle sans rien faire



                        }
                        //affichage de la matrice apres l'instruction
                        position++;
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
        // Panel principal pour le panneau de contrôle
        JPanel controlPanel = new JPanel(new BorderLayout());
    
        // Panel pour les étiquettes et les champs de texte
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 4, 5, 5)); // GridLayout pour aligner les composants
    
        // Création des étiquettes pour X, T, F et M
        JLabel labelX = new JLabel("X:");
        JLabel labelT = new JLabel("T:");
        JLabel labelF = new JLabel("F:");
        JLabel labelM = new JLabel("M:");
    
        // Récupération des valeurs initiales depuis register
        int initialValueX = register.getRegister("X").getValue();
        int initialValueT = register.getRegister("T").getValue();
    
        // Création des champs de texte avec les valeurs initiales
        JTextField fieldX = new JTextField(String.valueOf(initialValueX), 5);
        JTextField fieldT = new JTextField(String.valueOf(initialValueT), 5);
        JTextField fieldF = new JTextField("0", 5); // La valeur de F est initialement 0
        JTextField fieldM = new JTextField("0", 5); // La valeur de M est initialement 0
    
        // Ajout des étiquettes et des champs de texte au panel de champs
        fieldsPanel.add(labelX);
        fieldsPanel.add(fieldX);
        fieldsPanel.add(labelT);
        fieldsPanel.add(fieldT);
        fieldsPanel.add(labelF);
        fieldsPanel.add(fieldF);
        fieldsPanel.add(labelM);
        fieldsPanel.add(fieldM);
    
        // Ajout du panel de champs au panel principal
        controlPanel.add(fieldsPanel, BorderLayout.CENTER);
    
        // Retourne le panel principal
        return controlPanel;
    }
    private static GamePanel activeGamePanel = gamePanel1; // Commencer avec le robot dans le premier GamePanel
    private static void executeCommand(String command) {
        int newRow = activeGamePanel.robotRow;
        int newCol = activeGamePanel.robotCol;

        switch (command.toUpperCase()) {
            case "UP":
                newRow--;
                break;
            case "DOWN":
                newRow++;
                break;
            case "LEFT":
                newCol--;
                break;
            case "RIGHT":
                newCol++;
                break;
        }

        // Vérifier si le robot doit passer à une autre grille
        if (newRow < 0 || newRow >= activeGamePanel.getGridSize() || newCol < 0 || newCol >= activeGamePanel.getGridSize()) {
            // Exemple de logique pour changer de grille, à adapter selon vos besoins
            if (activeGamePanel == gamePanel1 && command.toUpperCase().equals("RIGHT")) {
                activeGamePanel.setShouldDisplaySpider(false); // Masquer le robot dans la grille actuelle
                activeGamePanel = gamePanel2; // Changer pour la grille suivante
                activeGamePanel.setShouldDisplaySpider(true); // Afficher le robot dans la nouvelle grille
                // Réinitialiser la position du robot pour la nouvelle grille
                newRow = activeGamePanel.robotRow = 0; // Ou toute autre logique de position initiale
                newCol = activeGamePanel.robotCol = 0; // Ou toute autre logique de position initiale
            }
            // Ajouter d'autres conditions pour les mouvements entre les autres grilles
        } else {
            // Déplacer le robot dans la grille active
            activeGamePanel.moveRobot(newRow, newCol);
        }
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
        private int robotRow = 0; // Ligne initiale du robot
        private int robotCol = 0; // Colonne initiale du robot
        private final int gridSize = 5; // Taille de la grille 5x5
        private int cellSize; // Taille de chaque cellule de la grille modifiable

        public GamePanel(int cellSize) {
            this.cellSize = cellSize;
        }

        public int getCellSize() {
            return cellSize;
        }

        
        public int getRobotRow() {
            return robotRow;
        }

        public int getRobotCol() {
            return robotCol;
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
       
                robotRow = newRow;
                robotCol = newCol;
                repaint();
            
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
