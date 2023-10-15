import java.util.Scanner;
public class ConnectFour
{
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        // Start here!
        System.out.println("Enter 1 for single player game vs. CPU and 2 for two player game");
        int gameChoice = 0;
        while (gameChoice != 1 && gameChoice != 2) {
            gameChoice = input.nextInt();
            if (gameChoice == 1) {
                singlePlayerConnectFour();
            } else if (gameChoice == 2) {
                twoPlayerConnectFour();
            } else {
                System.out.println("Try again.");
            }
        }
    }
    
    public static void singlePlayerConnectFour() {
        int playerChoice = 0;
        System.out.println("Enter 1 to be player 1 and 2 to be player 2");
        while (playerChoice != 1 && playerChoice != 2) {
            playerChoice = input.nextInt();
            if (playerChoice == 1) {
                System.out.println("Cool, you are player 1, and I will be player 2.");
            } else if (playerChoice == 2) {
                System.out.println("Cool, you are player 2, and I will be player 1.");
            } else {
                System.out.println("Try again.");
            }
        }
        GameBoardAI myGameBoardAI = new GameBoardAI();
        int playCol = -1;
        while (!myGameBoardAI.fullBoard()) {
            if (playerChoice == 1) {
                myGameBoardAI.printBoard();
                System.out.println("Player "+ String.valueOf((myGameBoardAI.getTurn())%2+1) + ", enter an integer from 0-6 to choose a column to play");
                playCol = input.nextInt();
                while (playCol < 0 || playCol > 6 || myGameBoardAI.fullCol(myGameBoardAI, playCol)) {
                    System.out.println("Try again.");
                    playCol = input.nextInt();
                }
                myGameBoardAI.drop(playCol);
                
                if (myGameBoardAI.checkWin()) {
                    myGameBoardAI.printBoard();
                    System.out.println("Player " + String.valueOf((myGameBoardAI.getTurn()-1)%2+1) + " wins.");
                    break;
                }
                //System.out.println("Turn:" + myGameBoardAI.getTurn());
                int AIcol = myGameBoardAI.chooseCol(myGameBoardAI, 1);
                //System.out.println("AI Move: " + AIcol);
                //int pauseInt = input.nextInt();
                myGameBoardAI.drop(AIcol);
                
                //int myRand = (int) (Math.random()*7);
                //myGameBoardAI.drop(myRand);
                //System.out.println("Turn:" + myGameBoardAI.getTurn());
                //int pauseInt = input.nextInt();
                if (myGameBoardAI.checkWin()) {
                    myGameBoardAI.printBoard();
                    System.out.println("Player " + String.valueOf((myGameBoardAI.getTurn()-1)%2+1) + " wins.");
                    break;
                }
                //int pauseInt = input.nextInt();
            } else {
                
                //System.out.println("Turn:" + myGameBoardAI.getTurn());
                int AIcol = myGameBoardAI.chooseCol(myGameBoardAI, 0);
                //System.out.println("AI Move: " + AIcol);
                //int pauseInt = input.nextInt();
                myGameBoardAI.drop(AIcol);
                
                if (myGameBoardAI.checkWin()) {
                    myGameBoardAI.printBoard();
                    System.out.println("Player " + String.valueOf((myGameBoardAI.getTurn()-1)%2+1) + " wins.");
                    break;
                }
                //int myRand = (int) (Math.random()*7);
                //myGameBoardAI.drop(myRand);
                //System.out.println("Turn:" + myGameBoardAI.getTurn());
                //int pauseInt = input.nextInt();
                
                myGameBoardAI.printBoard();
                System.out.println("Player "+ String.valueOf((myGameBoardAI.getTurn())%2+1) + ", enter an integer from 0-6 to choose a column to play");
                playCol = input.nextInt();
                while (playCol < 0 || playCol > 6 || myGameBoardAI.fullCol(myGameBoardAI, playCol)) {
                    System.out.println("Try again.");
                    playCol = input.nextInt();
                }
                //int pauseInt = input.nextInt();
                myGameBoardAI.drop(playCol);
                
                if (myGameBoardAI.checkWin()) {
                    myGameBoardAI.printBoard();
                    System.out.println("Player " + String.valueOf((myGameBoardAI.getTurn()-1)%2+1) + " wins.");
                    break;
                }
                
                
            }
                
            
            //if (myGameBoardAI.checkWin()) {
            //    myGameBoardAI.printBoard();
            //    System.out.println("Player " + String.valueOf((myGameBoardAI.getTurn()-1)%2+1) + " wins.");
            //    break;
            //}
        }
        //System.out.println("Under development; try two player for now");
    }
    
    
    public static void twoPlayerConnectFour() {
        GameBoard myGameBoard = new GameBoard();
        int playCol = -1;
        while (!myGameBoard.fullBoard()) {
            myGameBoard.printBoard();
            System.out.println("Player "+ String.valueOf((myGameBoard.getTurn())%2+1) + ", enter an integer from 0-6 to choose a column to play");
            playCol = input.nextInt();
            while (playCol < 0 || playCol > 6 || myGameBoard.fullCol(myGameBoard, playCol)) {
                System.out.println("Try again.");
                playCol = input.nextInt();
            }
            myGameBoard.drop(playCol);
            if (myGameBoard.checkWin()) {
                myGameBoard.printBoard();
                System.out.println("Player " + String.valueOf((myGameBoard.getTurn()-1)%2+1) + " wins.");
                break;
            }
        }
    }
}