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

public class NineBoxPuzzle extends JPanel {

    private JFrame frame;
    private Statistics statistics;
    private NineBoxPuzzle panel;
    
    NineBoxPuzzle(JFrame frame, Statistics statistics) {
        super();
        this.frame = frame;
        this.statistics = statistics;
        panel = this;
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagLayout = new GridBagConstraints();
        gridBagLayout.fill = GridBagConstraints.VERTICAL;

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(new PlayArea(frame, panel, statistics));
            }
            
        });
        gridBagLayout.weightx = 0.5;
        gridBagLayout.gridx = 0;
        gridBagLayout.gridy = 0;
        gridBagLayout.insets = new Insets(5,5,5,5);
        add(startButton, gridBagLayout);
        
        JButton scoresButton = new JButton("View Scores");
        scoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(statistics);
                statistics.showList();
                statistics.setVisible(true);
            }
            
        });
        gridBagLayout.gridy = 1;
        add(scoresButton, gridBagLayout);
        
        JButton exitButton = new JButton(" Exit ");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.dispose();    
            }
            
        });
        gridBagLayout.gridy = 2;
        add(exitButton, gridBagLayout);
    }
    
    public void setMenu() {
        NineBoxPuzzle panel = this;
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(new PlayArea(frame, panel, statistics));
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
        
        menubar.add(options);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        Statistics statistics = new Statistics(frame);
        frame.setTitle("Nine Box Puzzle!");
        NineBoxPuzzle panel = new NineBoxPuzzle(frame, statistics);
        statistics.setMainMenu(panel);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setMenu();
    }

}
