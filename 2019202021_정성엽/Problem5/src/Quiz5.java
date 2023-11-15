import java.util.Collections;
import java.util.Vector;

public class Quiz5 {
    public void printVector(Vector<StudyInfo> v){
        int size = v.size();
        System.out.println("=============Before Sort=============");
        for(int i=0;i<size;i++){
            String subject = v.get();
            String grade = v.get(2*i +1);
            System.out.println(subject+" "+grade);
        }
        System.out.println("=====================================");
    }

    public Vector<String> sortVector(Vector<StudyInfo> v){
        Collections.sort(v);
        System.out.println("==============After Sort============");

        System.out.println("====================================");

    }

    public int findVector(String s, Vector<String> v){
        System.out.println();
    }



    public static void main(String[] args) {
        String subjectGrade[][]=new String[][]{{"Computer Network","A"},{"Signal Processing", "C"},{"JAVA","A"},{"C Language", "A"},{"Data Structure","B"},{"Database","D"}};
        Vector<StudyInfo> v = new Vector<>();
        for(int i=0;i<6;i++){
            v.add(i,new StudyInfo(subjectGrade[i][0],subjectGrade[i][1]));
        }

        printVector(v);

        v = sortVector(v);
        printVecotr(v);



    }
}
