package lib;

public class Triangle {
    public boolean TriangleCondition(int a, int b, int c){//삼각형 판단
        if(a+b>c&&b+c>a&&c+a>b){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean RightTriangle(int a, int b, int c){//직각삼각형 판단
        int max=a;
        int ind =0;
        if(max<b){
            max=b;
            ind =1;
        }
        if(max<c){
            max=c;
            ind =2;
        }

        if(ind ==0){
            if(a*a == b*b + c*c){
                return true;
            }
            else
                return false;
        }else if(ind==1){
            if(b*b == a*a + c*c ){
                return true;
            }else
                return false;

        }else{
            if(c*c==a*a+b*b){
                return true;
            }else
                return false;
        }
    }

    public boolean EquilateralTriangle(int a, int b, int c){//정삼각형 판단
        if(a==b||b==c||c==a){
            return true;
        }else
            return false;
    }
}
