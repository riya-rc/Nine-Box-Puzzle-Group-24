/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener{
    
    private int positionX;
    private int positionY;
    private GameEngine gameEngine;

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.makeMove(positionX, positionY);
    }
    
    EventHandler(GameEngine gameEngine, int positionX, int positionY) {
        this.gameEngine = gameEngine;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
}
