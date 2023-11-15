public class StudyInfo {
    private String subject;
    private String grade;
    public StudyInfo(String subject, String grade){
        this.subject=subject;
        this.grade=grade;
    }
    public void SetGrade(String grade){
        this.grade = grade;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getGrade(){
        return this.grade;
    }

}
