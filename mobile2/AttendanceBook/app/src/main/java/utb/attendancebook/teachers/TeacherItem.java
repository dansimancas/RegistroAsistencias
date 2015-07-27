package utb.attendancebook.teachers;

import utb.attendancebook.aidClasses.WordManagement;

/**
 * Created by daniela on 8/04/15.
 */
public class TeacherItem {
    private int teacher_picture;
    private int teacher_thumbnail;
    private String teacher_name;
    private String teacher_lastName;
    private String teacher_fullName;
    private String id;
    private String uri;
    private String type;
    private String school;
    private String department;
    private String email;

    public void cleanStrings(){
        //Cleaning the names
        teacher_name = WordManagement.WM.toTitleCase(teacher_name);
        //Cleaning the lastNames
        teacher_lastName = WordManagement.WM.toTitleCase(teacher_lastName);
        //Setting up the fullName
        teacher_fullName = WordManagement.WM.firstNameLastnames(teacher_name,teacher_lastName);
        //Cleaning the type
        type = WordManagement.WM.toLowerCase(type);
        //Cleaning the school
        school = WordManagement.WM.toTitleCase(school);
        //Cleaning the department
        department = WordManagement.WM.toTitleCase(department);
        //Cleaning the email
        email = WordManagement.WM.toLowerCase(email);
    }

    public String getTeacherType(){return type;}

    public void setTeacherType(String type){
        this.type = type;
    }

    public String getTeacherSchool(){return school;}

    public void setTeacherSchool(String school){
        this.school = school;
    }

    public String getTeacherDepartment(){return department;}

    public void setTeacherDepartment(String department){
        this.department = department;
    }

    public String getTeacherEmail(){return email;}

    public void setTeacherEmail(String email){
        this.email = email;
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

    public String getTeacherFullName() {
        return teacher_fullName;
    }

    public void setTeacherName(String names) {
        this.teacher_name = names;
    }

    public void setTeacherLastName(String lastnames) {
        this.teacher_lastName = lastnames;
    }

    public int getTeacherThumbnail() {
        return teacher_thumbnail;
    }

    public void setTeacherThumbnail(int imageId) {
        teacher_thumbnail = imageId;
    }

    public int getTeacherPicture(){
        return teacher_picture;
    }

    public void setTeacherPicture(int imageId){
        teacher_picture = imageId;
    }

}
