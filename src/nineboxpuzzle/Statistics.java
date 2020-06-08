/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Statistics extends JPanel {
    private ArrayList<PlayerData> list;
    private final JFrame frame;
    private NineBoxPuzzle mainMenu;
    
    Statistics(JFrame frame) {
        this.frame = frame;
        list = new ArrayList<>();
        list.add(new PlayerData("Mr BatMan", 14));
        list.add(new PlayerData("James Bond", 22));
        list.add(new PlayerData("Joamenda James", 10));
        list.add(new PlayerData("Bruce Truce", 38));
        list.add(new PlayerData("Mr BatMan", 16));
        list.add(new PlayerData("Bruce Truce", 32));
        list.add(new PlayerData("Mr BatMan", 34));
        list.add(new PlayerData("Bruce Truce", 20));
    }
    
    public void setMainMenu(NineBoxPuzzle panel) {
        mainMenu = panel;
    }
    
    public void addPlayerData(PlayerData playerData) {
        list.add(playerData);
    }
    
    public void showList() {
        
        JPanel panel = this;
        panel.removeAll();
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(new PlayArea(frame, mainMenu, (Statistics)panel));
                }
            }
        );
        options.add(newGameButton);
        
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
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
        
        Collections.sort(list, null);
        String col[] = {"Rank", "Name", "Number Of Moves"};
        DefaultTableModel model = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        int rank = 1;
        for (PlayerData playerData:list) {
            String row[] = new String[3];
            row[0] = ""+rank++;
            row[1] = playerData.getName();
            row[2] = ""+playerData.getScore();
            model.addRow(row);
        }
        
        JTable myTable = new JTable(model);
        setLayout(new BorderLayout());
        add(new JScrollPane(myTable), BorderLayout.CENTER);
        setVisible(true);
    }

}
