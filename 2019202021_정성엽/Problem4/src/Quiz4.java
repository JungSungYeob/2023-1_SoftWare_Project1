import java.util.*;

public class Quiz4 {
    public static void main(String[] args) {
        String input = "Good Luck for the Coding Test!";
        ArrayList<String> arr = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(input, " ");
        int i=0;
        while(stringTokenizer.hasMoreTokens()){
            arr.add(i,stringTokenizer.nextToken());
        }

        Collections.reverse(arr);

        Iterator<String> it = arr.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }

}
