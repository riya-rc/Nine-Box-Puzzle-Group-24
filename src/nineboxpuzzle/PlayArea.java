/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Riya
 */
public class PlayArea extends JPanel {
    private GameEngine myGameEngine;
    private Statistics statistics;
    private JFrame frame;
    private NineBoxPuzzle mainMenu;
    private JLabel score;
    
    PlayArea(JFrame frame, NineBoxPuzzle mainMenu, Statistics statistics) {
        score = new JLabel("Moves Count : 0");
        this.mainMenu = mainMenu;
        this.statistics = statistics;
        this.frame = frame;
        myGameEngine = new GameEngine(this, statistics);
        initializeComponents();
        setVisible(true);
        JPanel panel = this;
        
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(new PlayArea(frame, mainMenu, statistics));
                }
            }
        );
        options.add(newGameButton);
        
        JMenuItem statisticsButton = new JMenuItem("Show Scores");
        statisticsButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(statistics);
                    statistics.showList();
                    statistics.setVisible(true);
                }
            }
        );
        options.add(statisticsButton);
        
        JMenuItem mainMenuButton = new JMenuItem("Back To Main Menu");
        mainMenuButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(mainMenu);
                    mainMenu.setMenu();
                    mainMenu.setVisible(true);
                }
            }
        );
        options.add(mainMenuButton);
        
        menubar.add(options);
        menubar.add(score);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
    }
    
    private void initializeComponents() {
        setLayout(new GridLayout(3, 3));
        Box[][] boxes = myGameEngine.getBoxes();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                add(boxes[i][j]);
                boxes[i][j].addActionListener(new EventHandler(myGameEngine, i, j));
            }
        }
        setVisible(true);
    }

    public void showWinningDialog() {
        JTextField name = new JTextField();
        Object[] input = {
            "Enter your name : ", name
        };
        int option = JOptionPane.showConfirmDialog(this, input, "You Won! Submit Score", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            myGameEngine.setPlayerData(name.getText().trim());
            JOptionPane.showMessageDialog(this, "Success!\n");
            setVisible(false);
            frame.setContentPane(statistics);
            statistics.setVisible(true);
            statistics.showList();
        }
    }

    public void setCount(int movesCount) {
        score.setText("Moves Count : " + movesCount);
    }
}
