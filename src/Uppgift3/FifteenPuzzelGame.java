package Uppgift3;

import javax.swing.*;
import java.awt.*;

public class FifteenPuzzelGame extends JFrame {

    JFrame jFrame = new JFrame("Fifteen Puzzle");
    JPanel jPanelMain = new JPanel();
    JPanel jPanel = new JPanel();
    JPanel jPanel1 = new JPanel();
    JButton[][] jButtons = new JButton[4][4];
    JButton nyttSpelButton = new JButton();
    JButton finishButton = new JButton();

    public FifteenPuzzelGame() {

        jFrame.setPreferredSize(new Dimension(650, 670));
        jFrame.setBackground(Color.cyan);
        jFrame.setVisible(true);

        jPanelMain.setPreferredSize(new Dimension(650, 670));
        jPanelMain.setBackground(Color.gray);
        jPanelMain.setLayout(new FlowLayout());

        jPanel.setPreferredSize(new Dimension(550, 550));
        jPanel.setBackground(Color.BLUE);
        jPanel.setLayout(new GridLayout(4, 4));
        jPanel.setEnabled(false);

        jPanel1.setPreferredSize(new Dimension(550, 50));
        jPanel1.setLayout(new GridLayout(1, 0));
        jPanel1.setBackground(Color.BLUE);

        jPanel1.add(nyttSpelButton, BorderLayout.LINE_START);
        nyttSpelButton.setText("Nytt Spel");
        nyttSpelButton.setBackground(Color.YELLOW);
        nyttSpelButton.setForeground(Color.black);
        nyttSpelButton.setSize(140, 140);
        nyttSpelButton.setHorizontalAlignment(0);


        jPanel1.add(finishButton, BorderLayout.AFTER_LAST_LINE);
        finishButton.setText("Klart");
        finishButton.setBackground(Color.YELLOW);
        finishButton.setForeground(Color.black);
        finishButton.setSize(140, 140);
        finishButton.setHorizontalAlignment(0);
        finishButton.setEnabled(false);
        finishButton.setBackground(Color.lightGray);

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

    public static void main(String[] args) {
        FifteenPuzzelGame Game = new FifteenPuzzelGame();

    }
}
