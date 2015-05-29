package utb.attendancebook.students;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by daniela on 8/04/15.
 */
public class StudentItem {
    private int student_picture;
    private int student_thumbnail;
    private String student_name;
    private String id;
    private String program;
    private String email;
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

    public String getStudentName() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public String getProgram(){
        return program;
    }

    public void setProgram(String program){
        this.program = program;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getStudentThumbnail(){
        return student_thumbnail;
    }

    public void setStudentThumbnail(int imageId){
        student_thumbnail = imageId;
    }

    public int getStudentPicture(){
        return student_picture;
    }

    public void setStudentPicture(int imageId){
        student_picture = imageId;
    }

}