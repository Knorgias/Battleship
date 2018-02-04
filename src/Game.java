import java.util.Scanner;

public class Game {
    public static void main(String args[]){
        Board player1Board = new Board(); // First, I create a Board
        player1Board.placeShips();  // Then, I place the ships, hardcoded
        player1Board.printBoard(); //Then, I print the board's state

        Board player2Board = new Board();
        player2Board.placeShips();

        Player player1 = new Player("Kostas");
        System.out.println("INFO: Player 1 created\n----------");
        Player player2 = new Player("Player 2");
        System.out.println("INFO: Player 2 created\n----------");
        player1.setBoard(player1Board); // Gave my player the created board instance, so now I can use it
        player2.setBoard(player2Board);
        System.out.println("INFO: Boards initialized and assigned\n----------");

        Scanner scn = new Scanner(System.in);
        System.out.println("INFO: New console read object created\n----------");

        //System.out.println(player1Board.shipsDestroyed());
        //System.out.println(player2Board.shipsDestroyed());
        while(!player1Board.shipsDestroyed() && !player2Board.shipsDestroyed()){
            System.out.println(player1.name + " please provide your coordinates: \n");
            while(player1.shoot(scn.nextInt(), scn.nextInt())){
                System.out.println("You may play again. Good luck!\n");
            }
            System.out.println(player2.name + " please provide your coordinates: \n");
            while(player1.shoot(scn.nextInt(), scn.nextInt())){
                System.out.println("You may play again. Good luck!\n");
            }
        }
        System.out.println("*** GAME OVER! ***");

    }
}

/* Sea Class Start */
class Board {
    int seaMatrix[][] = new int[8][8];

    Board() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                seaMatrix[i][j] = 0;
            }
        }
    }

    void placeShips() {
        seaMatrix[1][0] = 1;
        seaMatrix[2][0] = 1;

        seaMatrix[0][2] = 1;
        seaMatrix[1][2] = 1;
        seaMatrix[2][2] = 1;

        seaMatrix[0][5] = 1;
        seaMatrix[0][6] = 1;
        seaMatrix[0][7] = 1;

        seaMatrix[4][2] = 1;
        seaMatrix[4][3] = 1;
        seaMatrix[4][4] = 1;
        seaMatrix[4][5] = 1;

        seaMatrix[6][1] = 1;
        seaMatrix[6][2] = 1;
        seaMatrix[6][3] = 1;
        seaMatrix[6][4] = 1;
        seaMatrix[6][5] = 1;
        seaMatrix[6][6] = 1;
    }

    void printBoard() {
        System.out.println("Your Sea: \n");
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(this.seaMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean shipsDestroyed(){
        boolean flag = true;
        for(int i=0 ; i<8 ; i++){
            for(int j=0; j<8 ; j++){
                if(this.seaMatrix[i][j] == 1){
                    flag = false;
                }
            }
        }
        return flag; // Default return = false
    }

}
/* Sea class End */

/* Player class Start */
class Player {
    String name = "Commander";
    Board board;

    /* Constructors */
    public Player(){
        this.name =name;
    }

    public Player(String myName){
        this.name = myName;
    }
    /* Constructors End */

    /* Methods */
    void setBoard(Board myBoard) {
        this.board = myBoard;
    }

    Boolean shoot(int coordX, int coordY){
        if(board != null){ //Only if a board was set
            if(board.seaMatrix[coordX][coordY] == 1){
                board.seaMatrix[coordX][coordY] = -1;
                System.out.println("Well done " + this.name + "! You hit a boat! You may play again.");
                return true;
            } else if(board.seaMatrix[coordX][coordY] == 0){
                System.out.println("Oops! You missed. Better luck next time.");
                return false;
            }
        }
        return false; // If all else failed, the return value is false
    }
    /* Methods end */
}
/* Player class end */

/* Shoot class Start */
class Shoot {

}
/* Shoot class End */