import java.util.*;
import lib.Triangle;
public class Quiz1 {
    public static void main(String[] args) {
        System.out.print("Input Three Integer >>");
        Scanner scanner = new Scanner(System.in);
        int a, b, c;
        a=scanner.nextInt();
        b=scanner.nextInt();
        c=scanner.nextInt();

        Triangle triangle = new Triangle();
        boolean tri = triangle.TriangleCondition(a,b,c);
        boolean rightTri=triangle.RightTriangle(a,b,c);
        boolean equTri=triangle.EquilateralTriangle(a,b,c);

        if(tri == true){
            if(rightTri ==true){
                System.out.print("Three input numbers can make right triangle");
            }else if(equTri==true){
                System.out.print("Three input numbers can make equilateral triangle");
            }else{
                System.out.print("Three input numbers can make triangle");
            }

        }else{
            System.out.print("Three input numbers can't make triangle");
        }

        scanner.close();
    }
}
