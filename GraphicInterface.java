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
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Ajout des zones de texte pour différents robots
        JTextArea codeArea1 = new JTextArea(20, 40);
        robotTabs.addTab("Robot 1", new JScrollPane(codeArea1));
        JTextArea codeArea2 = new JTextArea(20, 40);
        robotTabs.addTab("Robot 2", new JScrollPane(codeArea2));
        frame.add(robotTabs, BorderLayout.WEST);

        // Zone de jeu
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel, BorderLayout.CENTER);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton stepButton = new JButton("Pas");
        JButton stopButton = new JButton("Stop");

        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea currentCodeArea = (JTextArea) ((JScrollPane) robotTabs.getSelectedComponent()).getViewport().getView();
                executeNextInstruction(currentCodeArea);
                if (robotTabs.getSelectedIndex() == 0) { // Vérifie si le Robot 1 est sélectionné
                    gamePanel.setShouldDisplaySpider(true);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setShouldDisplaySpider(false); // Fait disparaître l'araignée
            }
        });


        buttonPanel.add(stepButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void executeNextInstruction(JTextArea codeArea) {
        String[] lines = codeArea.getText().split("\n");
        if (currentLine < lines.length) {
            String instruction = lines[currentLine];
            currentLine++;
            // TODO: Ajoutez la logique pour exécuter l'instruction
        }
    }

    private static void resetRobot(JTextArea codeArea) {
        currentLine = 0;
        // TODO: Ajoutez toute autre logique de réinitialisation nécessaire
    }

    static class GamePanel extends JPanel {
        private boolean shouldDisplaySpider = false;
        private int robotRow = 0; // Ligne initiale du robot
        private int robotCol = 0; // Colonne initiale du robot
        private final int gridSize = 5; // Taille de la grille 5x5
        private final int cellSize = 100; // Taille de chaque cellule de la grille

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
            // Dessiner la grille
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }

            // Dessiner le robot si shouldDisplaySpider est vrai
            if (shouldDisplaySpider) {
                int x = robotCol * cellSize + cellSize / 4; // Position x du robot dans la grille
                int y = robotRow * cellSize + cellSize / 4; // Position y du robot dans la grille
                g.setColor(Color.BLACK);
                g.fillOval(x, y, cellSize / 2, cellSize / 2); // Utilisez une forme simple pour le robot
            }
        }
    }



}
