import java.util.Scanner;
public class GameBoardAI extends GameBoard {
    
    private int count1;
    private int count2;
    
    public GameBoardAI() {
        super();
    }
    
    public int getCount1() {
        return count1;
    }
    
    public int getCount2() {
        return count2;
    }
    
    public void setCount1(int newCount1) {
        count1 = newCount1;
    }
    public void setCount2(int newCount2) {
        count2 = newCount2;
    }
    
    //AI methods apply when it is the AI's turn as implemented in ConnectFour.java
    //GRADER: If code doesn't work, comment out below two AI methods and it should work
    //Task: Complete single layer/full board handling
    public GameBoardAI copyBoard(GameBoardAI myGameBoardAI, int AIturn) {
        GameBoardAI newBoard = new GameBoardAI();
        String[][] theGameBoard = myGameBoardAI.getMyBoard();
        for (int i = 0; i < theGameBoard.length; i++) {
            for (int j = 0; j < theGameBoard[i].length; j++) {
                newBoard.setMyBoard(i,j,theGameBoard[i][j]);
            }
        }
        if (AIturn == 1) {
            newBoard.setTurn(newBoard.getTurn()+1);
        }
        return newBoard;
    }
    
    public int chooseCol(GameBoardAI myGameBoard, int AIturn) {
        boolean continueFlag = true;
        String[][] mainBoard = myGameBoard.getMyBoard();
        GameBoardAI simulationBoard = copyBoard(myGameBoard, AIturn);
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < mainBoard[0].length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < mainBoard[0].length; j++) {
                if (!simulationBoard.fullBoard() && !fullCol(myGameBoard, i)) {
                    simulationBoard.drop(i);
                    continueFlag = false;
                    //recursiveCounter++;
                    if (simulationBoard.checkWin()) {
                        return i;
                    }
                } else {
                    continue;
                }
                
                if (!simulationBoard.fullBoard() && !fullCol(myGameBoard, j)) {
                    simulationBoard.drop(j);
                    continueFlag = false;
                    //recursiveCounter++;
                } else {
                    continue;
                }
                //System.out.println(i);
                
                int score = scoreBoard(simulationBoard, i);
                if (score <= min) {
                    min = score;
                    //System.out.println("minValue: " + min);
                }
                //recursiveCounter = 0;
                simulationBoard = copyBoard(myGameBoard, AIturn);
            }
            System.out.println();
            
            if (min > max && !fullCol(myGameBoard, i)) {
                max = min;
                //System.out.println("maxValue: " + max);
                index = i;
                //System.out.println("Index Con: " + index);
            }
            //System.out.println("Index ");
            System.out.println();
        }
        //System.out.println("Returned index: " + index);
        return index;
    }
    
    public boolean fullCol(GameBoard myGameBoard, int col) {
        return super.fullCol(myGameBoard, col);
    }
    
    public int scoreBoard(GameBoardAI myGameBoardAI, int myCol) {
        int score = 0;
        //myGameBoardAI.printBoard();
        //System.out.println("Made it here");
        if (getTurn() % 2 == 0) {
            //Score from red perspective
            //System.out.println("Made it here");
            if (myGameBoardAI.checkLineHorizontal(4)) {
                //score += 1000*myGameBoardAI.getCount1();
                score -= 500*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(4)) {
                //score += 1000*myGameBoardAI.getCount1();
                score -= 500*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(4)) {
                //score += 1000*myGameBoardAI.getCount1();
                score -= 500*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            if (myGameBoardAI.checkLineWinnableHorizontal(3)) {
                score -= 50*myGameBoardAI.getCount2();
                score += 5*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            if (myGameBoardAI.checkLineHorizontal(3)) {
                score -= 30*myGameBoardAI.getCount2();
                score += 3*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(3)) {
                score -= 50*myGameBoardAI.getCount2();
                score += 5*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(3)) {
                score -= 50*myGameBoardAI.getCount2();
                score += 5*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineHorizontal(2)) {
                score -= 2*myGameBoardAI.getCount2();
                score += 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(2)) {
                score -= 2*myGameBoardAI.getCount2();
                score += 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(2)) {
                //System.out.println("Made it here1");
                score -= 2*myGameBoardAI.getCount2();
                score += 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myCol == 3) {
            score += 1;
            }
        } else {
            //Score from yellow perspective
            //System.out.println("Made it here");
            if (myGameBoardAI.checkLineHorizontal(4)) {
                score -= 500*myGameBoardAI.getCount1();
                //score += 1000*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(4)) {
                score -= 500*myGameBoardAI.getCount1();
                //score += 1000*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(4)) {
                score -= 500*myGameBoardAI.getCount1();
                //score += 1000*myGameBoardAI.getCount2();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineWinnableHorizontal(3)) {
                score += 5*myGameBoardAI.getCount2();
                score -= 50*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            if (myGameBoardAI.checkLineHorizontal(3)) {
                score += 3*myGameBoardAI.getCount2();
                score -= 30*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(3)) {
                score += 5*myGameBoardAI.getCount2();
                score -= 50*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(3)) {
                score += 5*myGameBoardAI.getCount2();
                score -= 50*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineHorizontal(2)) {
                score += 2*myGameBoardAI.getCount2();
                score -= 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineVertical(2)) {
                score += 2*myGameBoardAI.getCount2();
                score -= 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myGameBoardAI.checkLineDiag(2)) {
                //System.out.println("Made it here2");
                score += 2*myGameBoardAI.getCount2();
                score -= 2*myGameBoardAI.getCount1();
                myGameBoardAI.setCount1(0);
                myGameBoardAI.setCount2(0);
            }
            
            if (myCol == 3) {
            score -= 1;
            }
        }
        
        
        
        
        //System.out.println("Column: " + myCol + "; Score: " + score);
        //Scanner input = new Scanner(System.in);
        //int myInt = input.nextInt();
        return score;
    }
    
    //Making board scoring methods
    public boolean checkLineWinnableHorizontal(int lineSize) {
        String horizontalString = new String();
        String redWinString = new String();
        String yellowWinString = new String();
        String[][] myBoard = getMyBoard();
        
        for (int i = 0; i < lineSize; i++) {
            redWinString += "