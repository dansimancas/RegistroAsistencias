package utb.attendancebook.courses;

/**
 * Created by jairo on 25/03/15.
 */
public class CourseItem {
    private String subject_name;
    private String uri;
    private String nrc;

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

}