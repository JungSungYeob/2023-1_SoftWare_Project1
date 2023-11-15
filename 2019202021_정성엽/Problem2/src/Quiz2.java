import java.util.Scanner;

public class Quiz2 {
    public static void main(String[] args) {
        int sudoku[][] = new int[][]{{4,9,2},{3,0,7},{8,1,6}};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number for Center >>");
        sudoku[1][1] = scanner.nextInt();

        boolean gotcha[] = new boolean[]{true,true,true};

        int sum=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                sum+=sudoku[i][j];
            }
        }

        if(sum == 45){
            gotcha[0]=true;
        }else{
            gotcha[0]=false;
        }
        int add=sudoku[0][0]+sudoku[0][1]+sudoku[0][2];
        for(int i=1;i<3;i++){
            if(sudoku[i][0]+sudoku[i][1]+sudoku[i][2]==add){
                gotcha[1]=true;
            }else {
                gotcha[1] = false;
                break;
            }
        }
        add=sudoku[0][0]+sudoku[1][0]+sudoku[2][0];
        for(int i=1;i<3;i++){
            if(sudoku[0][i]+sudoku[1][i]+sudoku[2][i]==add){
                gotcha[2]=true;
            }else {
                gotcha[2] = false;
                break;
            }
        }
        if(gotcha[0]==false||gotcha[1]==false||gotcha[2]==false){
            System.out.print("Wrong answer!");
        }else{
            System.out.print("Gotcha!");
        }
        scanner.close();
    }
}