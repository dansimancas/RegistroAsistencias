package utb.attendancebook.courses;

/**
 * Created by daniela on 28/05/15.
 */
public class CourseItem {
    private int course_thumbnail;
    private String subject_name;
    private String uri;
    private String nrc;
    private String period;
    private String credits;
    private String week_hours;
    private String subject;
    private String section;
    private String course;

    public String getPeriod(){
        return period;
    }

    public void setPeriod(String period){
        this.period = period;
    }

    public String getCredits(){
        return credits;
    }

    public void setCredits(String credits){
        this.credits = credits;
    }

    public String getWeekHours(){
        return week_hours;
    }

    public void setWeekHours(String week_hours){
        this.week_hours = week_hours;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSection(){
        return section;
    }

    public void setSection(String section){
        this.section = section;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course = course;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getSubjectName() {
        return subject_name;
    }

    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getStudentThumbnail(){
        return course_thumbnail;
    }
    public void setStudentThumbnail(int imageId){
        course_thumbnail = imageId;
    }
}
