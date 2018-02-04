import java.util.Scanner;

/* Game class */
public class Game {
    /* Main */
    public static void main(String args[]){
        /* Initializations */
        Board player1Board = new Board(); // First, I create a Board
        player1Board.placeShipsConfig1();  // Then, I place the ships, hardcoded
        player1Board.printBoardState(); //Then, I print the board's state

        Board player2Board = new Board();
        player2Board.placeShipsConfig2();

        Player player1 = new Player("Kostas");
        System.out.println("INFO: Player 1 created\n----------");
        Player player2 = new Player("Player 2");
        System.out.println("INFO: Player 2 created\n----------");
        player1.setBoard(player1Board); // Gave my player the created board instance, so now I can use it
        player2.setBoard(player2Board);
        System.out.println("INFO: Boards initialized and assigned\n----------");

        Scanner scn = new Scanner(System.in);
        System.out.println("INFO: New console read object created\n----------");

        String attackMsg = " prepare to attack!\n(2 coordinates from 1-8. Example: 2, ENTER, 3, ENTER): \n";
        String playAgainMsg = "You may play again, good luck!\n(2 coordinates from 1-8. Example: 2, ENTER, 3, ENTER): \n";
        /* Initializations end */

        while(!player1Board.shipsDestroyed() && !player2Board.shipsDestroyed()){ //Until either board's ships are destroyed

            /* Player 1 turn */
            System.out.println(player1.name + attackMsg);
            while(player1.shoot(scn.nextInt(), scn.nextInt())){ // We get a return statement of "true" while the player "hits", so we can play again
                player1.board.printPlayerBoardState();
                System.out.println(playAgainMsg);
            }
            player1.board.printPlayerBoardState();

            /*Player 2 turn */
            System.out.println(player2.name + attackMsg);
            while(player1.shoot(scn.nextInt(), scn.nextInt())){
                player2.board.printPlayerBoardState();
                System.out.println(playAgainMsg);
            }
            player2.board.printPlayerBoardState();
        }
        /* End of game message refinement based on winner */
        if(player1Board.shipsDestroyed()){
            System.out.println("*** GAME OVER! ***\n*** " + player1.name + " wins!");
        } else if(player2Board.shipsDestroyed()){
            System.out.println("*** GAME OVER! ***\n*** " + player2.name + " wins!");
        } else {
            System.out.println("*** GAME OVER! ***\n*** No winner..!? - You must have incurred Poseidon's wrath! ***");
        }

    }
    /* Main end */
}
/* Game class end */

/* Board class */
class Board {
    char seaMatrix[][] = new char[8][8]; // 2-D array of characters (for more variety of appearance)

    Board() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                seaMatrix[i][j] = '-';
            }
        }
    }

    void placeShipsConfig1() {
        seaMatrix[1][0] = 'o';
        seaMatrix[2][0] = 'o';

        seaMatrix[0][2] = 'o';
        seaMatrix[1][2] = 'o';
        seaMatrix[2][2] = 'o';

        seaMatrix[0][5] = 'o';
        seaMatrix[0][6] = 'o';
        seaMatrix[0][7] = 'o';

        seaMatrix[4][2] = 'o';
        seaMatrix[4][3] = 'o';
        seaMatrix[4][4] = 'o';
        seaMatrix[4][5] = 'o';

        seaMatrix[6][1] = 'o';
        seaMatrix[6][2] = 'o';
        seaMatrix[6][3] = 'o';
        seaMatrix[6][4] = 'o';
        seaMatrix[6][5] = 'o';
        seaMatrix[6][6] = 'o';
    }

    void placeShipsConfig2() {
        /* 2-point ship */
        seaMatrix[1][7] = 'o';
        seaMatrix[2][7] = 'o';

        /* 3-point ships */
        seaMatrix[4][1] = 'o';
        seaMatrix[4][2] = 'o';
        seaMatrix[4][3] = 'o';

        seaMatrix[4][5] = 'o';
        seaMatrix[4][6] = 'o';
        seaMatrix[4][7] = 'o';

        /*4-point ship */
        seaMatrix[6][2] = 'o';
        seaMatrix[6][3] = 'o';
        seaMatrix[6][4] = 'o';
        seaMatrix[6][5] = 'o';

        /* 6-point ship */
        seaMatrix[0][0] = 'o';
        seaMatrix[0][1] = 'o';
        seaMatrix[0][2] = 'o';
        seaMatrix[0][3] = 'o';
        seaMatrix[0][4] = 'o';
        seaMatrix[0][5] = 'o';
    }

    void printBoardState() {
        System.out.println("True state of board: \n");
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(this.seaMatrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    void printPlayerBoardState() {
        System.out.println("Player's state of board: \n");
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(this.seaMatrix[i][j] == 1) {
                    System.out.print("0" + "\t");
                } else {
                    System.out.print(this.seaMatrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    boolean shipsDestroyed(){
        //boolean flag = true;
        for(int i=0 ; i<8 ; i++){
            for(int j=0; j<8 ; j++){
                if(this.seaMatrix[i][j] == 'o'){
                    return false;
                }
            }
        }
        return true; // Default return = false
    }

}
/* Board class end */

/* Player class */
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

    boolean shoot(int coordX, int coordY){
        if((coordX < 1 || coordX > 8) || (coordY < 1 || coordY > 8)){
            return false;
        } else {
            //Recalibration
            coordX -= 1;
            coordY -= 1;
            if (board != null) { //CHECK: Only if a board was set
                if (board.seaMatrix[coordX][coordY] == 'o') { // If we hit part of a ship
                    board.seaMatrix[coordX][coordY] = 'x';  //Destroy it
                    System.out.println("Poseidon's with you! " + this.name + "! You hit a boat.");
                    return true;
                } else if (board.seaMatrix[coordX][coordY] == '-') { // If we did not hit a ship
                    board.seaMatrix[coordX][coordY] = '?';  //Mark it wih a '?'
                    System.out.println("You missed! The goddess of victory is flying away.");
                    return false;
                }
            }
            return false; // If all else failed, the return value is "false"
        }
    }
    /* Methods end */
}
/* Player class end */