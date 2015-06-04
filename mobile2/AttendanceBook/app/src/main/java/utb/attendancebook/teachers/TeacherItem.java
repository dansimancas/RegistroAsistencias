package utb.attendancebook.teachers;

/**
 * Created by daniela on 8/04/15.
 */
public class TeacherItem {
    private int teacher_picture;
    private int teacher_thumbnail;
    private String teacher_name;
    private String id;
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacher_name;
    }

    public void setTeacherName(String student_name) {
        this.teacher_name = student_name;
    }

    public int getTeacherThumbnail(){
        return teacher_thumbnail;
    }

    public void setTeacherThumbnail(int imageId){
        teacher_thumbnail = imageId;
    }

    public int getTeacherPicture(){
        return teacher_picture;
    }

    public void setTeacherPicture(int imageId){
        teacher_picture = imageId;
    }

}
