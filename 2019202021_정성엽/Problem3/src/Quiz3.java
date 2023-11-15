import java.util.*;

class taxi implements Meter{
    int total=0;
    public  int fare(int distance){
        final int BASE = 4800;
        final int PLUS = 100;
        if(distance<=1600){
            total=BASE;
        }else{
            total=BASE;
            distance-=1600;
            total += (distance/131)*PLUS;
            if(distance%131!=0)
                total+=PLUS;
        }
        return 0;
    }
    public int getTotalFare(){
        return total;
    }
}
class deluxe implements Meter{
    int total =0;
    public  int fare(int distance){
        final int BASE = 6500;
        final int PLUS = 200;
        if(distance<=3000){
            total=BASE;
        }else{
            total=BASE;
            distance-=3000;
            total += (distance/151)*PLUS;
            if(distance%151!=0)
                total+=PLUS;
        }
        return 0;
    }
    public int getTotalFare(){
        return total;
    }

}

public class Quiz3{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("이동거리를 입력하세요(단위: m) >>");
        int distance = scanner.nextInt();

        taxi t = new taxi();
        deluxe d = new deluxe();

        t.fare(distance);
        d.fare(distance);
        int t_Total = t.getTotalFare();
        int d_Total = d.getTotalFare();

        System.out.println("기본 택시 요금은 "+t_Total+" 입니다.");
        System.out.println("모범 택시 요금은 "+d_Total+" 입니다.");


        scanner.close();
    }


}
