/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.util.*;

public class GameEngine {
    
    private Box[][] boxes;
    private Box emptyBox;
    private PlayArea playArea;
    private int init;
    private int movesCount;
    private Statistics statistics;

    GameEngine(PlayArea playArea, Statistics statistics) {
        this.playArea = playArea;
        this.statistics = statistics;
        init = 0;
        movesCount = 0;
        boxes = getRandomBoxes();
    }
    
    public Box[][] getBoxes(){
        return boxes;
    }
    
    public int getMovesCount() {
        return movesCount;
    }
    
    private boolean canMakeMove(int positionX, int positionY) {
        int differenceX = Math.abs(emptyBox.getPositionX() - positionX);
        int differenceY = Math.abs(emptyBox.getPositionY() - positionY);
        if (differenceX <= 1 && differenceY <= 1 && !(differenceX == 1 && differenceY == 1))
            return true;
        return false;
    }
    
    private boolean checkWinningStatus() {
        boolean hasWon = true;
        
        int num = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("" + num + "  " +boxes[i][j].getText().trim());
                try {
                    if (Integer.parseInt(boxes[i][j].getText().trim()) != num) {
                        hasWon = false;
                    }
                } catch (NumberFormatException e) {
                    hasWon  = false;
                    System.out.println("Empty String at " + i + " " + j);
                }
                num++;
                if(num==9) return hasWon;
            }
        }
        
        return hasWon;
    }
    
    public void makeMove(int positionX, int positionY) {
        if (canMakeMove(positionX, positionY)) {
            Box box = boxes[positionX][positionY];
            emptyBox.setText(box.getText());
            System.out.println("Clicked on : " + box.getText());
            emptyBox.setEnabled(true);
            box.setEnabled(false);
            box.setText("");
            emptyBox = box;
            if(init==1) {
                movesCount++;
                playArea.setCount(movesCount);
            }
            if (checkWinningStatus() && init!=0) {
                System.out.println("You Win");
                playArea.showWinningDialog();
            }
        }
    }    

    private ArrayList<Box> getValidMoves(Box boxes[][]) {
        ArrayList<Box> validMoves = new ArrayList<Box>();
        int emptyBoxX = emptyBox.getPositionX();
        int emptyBoxY = emptyBox.getPositionY();
        
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if(Math.abs(j)==Math.abs(i)) continue;
                int x = emptyBoxX + i;
                int y = emptyBoxY + j;
                System.out.println("do "+x+y);
                if(x < 3 && x >= 0 && y < 3 && y >= 0) {
                    validMoves.add(boxes[x][y]);
                }
            }
        }

        return validMoves;
    }
    
    private Box[][] getRandomBoxes() {
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(i);
        }
        
        Box[][] boxes = new Box[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int boxValue = list.get(3*i+j) + 1;
                boxes[i][j] = new Box(i, j, boxValue);
                if (boxValue == 9) {
                    boxes[i][j].setEnabled(false);
                    boxes[i][j].setText("");
                    emptyBox = boxes[i][j];
                }
            }
        }
        
        this.boxes = boxes;
       // makeMove(2, 1);
        //time to randomize the boxes by making a random number of moves
        Random random = new Random();
        int minMovesCount = 100;
        int movesCount =  minMovesCount + 2*random.nextInt(600);
        
        //IF DEBUG
        //movesCount = 0;
        
        for (int i = 0; i < movesCount; i++) {
            ArrayList<Box> validMoves = getValidMoves(boxes);
            int r = random.nextInt(validMoves.size());
            //System.out.println(""+validMoves.size());
            Box randomBox = validMoves.get(r);
            //System.out.println(""+randomBox.getPositionX() + randomBox.getPositionY() + randomBox.getText());
            makeMove(randomBox.getPositionX(), randomBox.getPositionY());
        }
        init = 1;
        return boxes;
    }

    void setPlayerData(String name) {
        PlayerData playerData = new PlayerData(name, movesCount);
        statistics.addPlayerData(playerData);
    }
}
