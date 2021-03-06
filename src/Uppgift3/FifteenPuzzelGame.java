package Uppgift3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class FifteenPuzzelGame extends JFrame {

    JFrame jFrame = new JFrame("Fifteen Puzzle");
    JPanel jPanelMain = new JPanel();
    JPanel jPanel = new JPanel();
    JPanel jPanel1 = new JPanel();
    JButton[][] jButtons = new JButton[4][4];
    JButton nyttSpelButton = new JButton();
    JButton finishButton = new JButton();

    public FifteenPuzzelGame() {

        //Frame created
        jFrame.setPreferredSize(new Dimension(650, 670));
        jFrame.setBackground(Color.cyan);
        jFrame.setVisible(true);

        // Main Panel created
        jPanelMain.setPreferredSize(new Dimension(650, 670));
        jPanelMain.setBackground(Color.gray);
        jPanelMain.setLayout(new FlowLayout());

        //new panel created
        jPanel.setPreferredSize(new Dimension(550, 550));
        jPanel.setBackground(Color.BLUE);
        jPanel.setLayout(new GridLayout(4, 4));
        jPanel.setEnabled(false);

        //new panel1 created
        jPanel1.setPreferredSize(new Dimension(550, 50));
        jPanel1.setLayout(new GridLayout(1, 0));
        jPanel1.setBackground(Color.BLUE);


        jPanel1.add(nyttSpelButton, BorderLayout.LINE_START);
        nyttSpelButton.setText("Nytt Spel");
        nyttSpelButton.setBackground(Color.YELLOW);
        nyttSpelButton.setForeground(Color.black);
        nyttSpelButton.setSize(140, 140);
        nyttSpelButton.setHorizontalAlignment(0);
        nyttSpelButton.addMouseListener(ml1);

        jPanel1.add(finishButton, BorderLayout.AFTER_LAST_LINE);
        finishButton.setText("Klart");
        finishButton.setBackground(Color.YELLOW);
        finishButton.setForeground(Color.black);
        finishButton.setSize(140, 140);
        finishButton.setHorizontalAlignment(0);
        finishButton.setEnabled(false);
        finishButton.setBackground(Color.lightGray);
        finishButton.addMouseListener(ml2);

        int count = 1;
        for (int raw = 0; raw < 4; raw++) {
            for (int column = 0; column < 4; column++) {

                jButtons[raw][column] = new JButton();
                jButtons[raw][column].setBackground(Color.cyan);
                jButtons[raw][column].setForeground(Color.black);
                jButtons[raw][column].setText(String.valueOf((count)));
                jButtons[raw][column].setVisible(true);

                if (raw == 3 && column == 3) {
                    jButtons[raw][column].setText("");
                    jButtons[raw][column].setBackground(Color.blue);
                    jButtons[raw][column].setVisible(false);
                }

                jPanel.add(jButtons[raw][column]);
                jPanel.setVisible(true);
                count++;
            }
        }

        jFrame.add(jPanelMain);
        jPanelMain.add(jPanel);
        jPanelMain.add(jPanel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

    public void shuffledButtons(JButton[][] jButtons) {

        Random random = new Random();
        for (int raw = 0; raw < 4; raw++) {
            for (int column = 0; column < 4; column++) {
                int shuffledRaw = random.nextInt(raw + 1);
                int shuffledColumn = random.nextInt(column + 1);

                JButton shuffledJbuttons = jButtons[raw][column];
                jButtons[raw][column] = jButtons[shuffledRaw][shuffledColumn];
                jButtons[shuffledRaw][shuffledColumn] = shuffledJbuttons;
                jPanel.add(jButtons[raw][column]);
                jButtons[shuffledRaw][shuffledColumn].addMouseListener(ml3);

            }
        }

        jPanel.updateUI();
    }
    public void moves(JPanel jPanel, JButton[][] jButtons, JButton jButton) {

        for (int raw = 0; raw < jButtons.length; raw++) {
            for (int column = 0; column < jButtons[raw].length; column++) {

                if (jButtons[raw][column].getBackground() == Color.BLUE) {
                    System.out.println("Blue button: " + jButtons[raw][column].getX() + "  " + jButtons[raw][column].getY());
                    System.out.println("Button clicked: " + jButton.getX() + "  " + jButton.getY() + "\n");

                    if (((jButtons[raw][column].getX() == jButton.getX()) && ((jButton.getY() - jButtons[raw][column].getY() <= 147) && (jButton.getY() - jButtons[raw][column].getY()) >= -147))
                            || ((jButtons[raw][column].getY() == jButton.getY()) && ((jButton.getX() - jButtons[raw][column].getX()) <= 147) && (jButton.getX() - jButtons[raw][column].getX()) >= -147)) {
                        jButtons[raw][column].setVisible(true);
                        jButtons[raw][column].setText(jButton.getText());
                        jButtons[raw][column].setBackground(Color.CYAN);

                        jButton.setBackground(Color.blue);
                        jButton.setText(" ");
                        jButton.setVisible(false);
                    }

                    SwingUtilities.updateComponentTreeUI(jPanel);
                }
            }
        }
    }

    public boolean checkBoard(JButton[][] jButtons) {
        int buttonText = 1;

        for (int raw = 0; raw < 4; raw++) {
            for (int column = 0; column < 4; column++) {
                if (jButtons[raw][column].getText().equals(Integer.toString(buttonText))) {
                    buttonText++;
                    if (buttonText == 15)
                        return true;
                }
            }
        }
        return false;
    }

    public void resetGame(JButton nyttSpelButton, JButton finishButton) {
        nyttSpelButton.setEnabled(true);
        nyttSpelButton.setBackground(Color.yellow);
        finishButton.setEnabled(false);
        finishButton.setBackground(Color.GRAY);
    }

    MouseAdapter ml1 = new MouseAdapter() {

        public void mouseClicked(MouseEvent e) {
            shuffledButtons(jButtons);
            jPanel.setEnabled(true);
            nyttSpelButton.setBackground(Color.LIGHT_GRAY);
            nyttSpelButton.setEnabled(true);
            finishButton.setEnabled(true);
        }
    };

    MouseAdapter ml2 = new MouseAdapter() {

        public void mouseClicked(MouseEvent e) {

            if (checkBoard(jButtons)) {
                JOptionPane.showMessageDialog(jPanel, "Grattis ! du har vunnit !");
                resetGame(nyttSpelButton, finishButton);

            }
        }
    };

    MouseAdapter ml3 = new MouseAdapter() {

        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton)
                moves(jPanel, jButtons, (JButton) source);
        }
    };



    public static void main(String[] args) {
        FifteenPuzzelGame Game = new FifteenPuzzelGame();

    }
}
