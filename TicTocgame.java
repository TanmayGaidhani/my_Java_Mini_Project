import java.util.Scanner;
public class TicTocgame{
    char tictoc[][]=new char[3][3];

    public static void Display(char tictoc[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(tictoc[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void Replace(char arr[][], char find, char replace){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(arr[i][j] == find){
                    arr[i][j] = replace;
                    return; 
                }
            }
        }
    }

    public boolean CheckforWin(){
        return(CheckforRow() || CheckforColumn() || CheckforDiagonal());
    }

    public boolean Check(char c1, char c2, char c3){
        return (c1 == c2 && c2 == c3);
    }

    public boolean CheckforRow(){
        for(int i=0;i<3;i++){
            if(Check(tictoc[i][0], tictoc[i][1], tictoc[i][2])){
                return true;
            }
        }
        return false;
    }

    public boolean CheckforColumn(){
        for(int i=0;i<3;i++){
            if(Check(tictoc[0][i], tictoc[1][i], tictoc[2][i])){  // Fixed here
                return true;
            }
        }
        return false;
    }

    public boolean CheckforDiagonal(){
        return(Check(tictoc[0][0], tictoc[1][1], tictoc[2][2]) || Check(tictoc[0][2], tictoc[1][1], tictoc[2][0]));
    }

    public static void main(String[] args) {
        TicTocgame game = new TicTocgame();
        Scanner sc = new Scanner(System.in);
        String player1, player2;
        char player1mark, player2mark;

        System.out.print("Enter the name of First player: ");
        player1 = sc.nextLine();
        System.out.print("Enter the name of Second player: ");
        player2 = sc.nextLine();

        System.out.println(player1 + " Select the mark (O or X): ");
        player1mark = sc.next().charAt(0);

        while(player1mark != 'X' && player1mark != 'x' && player1mark != 'O' && player1mark != 'o'){
            System.out.print("Invalid input, please enter valid input (O or X): ");
            player1mark = sc.next().charAt(0);
        }

        if(player1mark == 'X' || player1mark == 'x'){
            player2mark = 'O';
        } else {
            player2mark = 'X';
        }

        // Initialize the board with 1 to 9
        int counter = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                game.tictoc[i][j] = Character.forDigit(++counter, 10);
            }
        }

        Display(game.tictoc);

        char input;
        for(int i=0;i<9;i++) {
            if(i % 2 == 0){
                System.out.print(player1 + " Turn: ");
                input = sc.next().charAt(0);
                Replace(game.tictoc, input, player1mark);
            } else {
                System.out.print(player2 + " Turn: ");
                input = sc.next().charAt(0);
                Replace(game.tictoc, input, player2mark);
            }

            Display(game.tictoc);

            // Check for a winner after every move
            if(game.CheckforWin()){
                if(i % 2 == 0){
                    System.out.println(player1 + " wins!");
                } else {
                    System.out.println(player2 + " wins!");
                }
                return;  // End the game
            }
        }
        System.out.println("Match is Draw");
    }
}
