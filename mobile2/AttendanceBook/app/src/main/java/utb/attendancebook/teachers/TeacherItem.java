package utb.attendancebook.teachers;

import utb.attendancebook.aidClasses.WordManagement;

/**
 * Created by daniela on 8/04/15.
 */
public class TeacherItem {
    private int teacher_picture;
    private int teacher_thumbnail;
    private String teacher_name;
    private String id;
    private String uri;
    private String type;
    private String school;
    private String department;
    private String email;

    public String getTeacherType(){return type;}

    public void setTeacherType(String type){
        this.type = WordManagement.WM.toLowerCase(type);
    }

    public String getTeacherSchool(){return school;}

    public void setTeacherSchool(String school){
        this.school = WordManagement.WM.toTitleCase(school);
    }

    public String getTeacherDepartment(){return department;}

    public void setTeacherDepartment(String department){
        this.department = WordManagement.WM.toTitleCase(department);
    }

    public String getTeacherEmail(){return email;}

    public void setTeacherEmail(String email){
        this.email = WordManagement.WM.toLowerCase(email);
    }

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

    public void setTeacherName(String names, String lastnames) {
        String fullName = WordManagement.WM.firstNameLastnames(names, lastnames);
        this.teacher_name = WordManagement.WM.toTitleCase(fullName);
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
