package utb.attendancebook.students;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import utb.attendancebook.R;


public class FeedStudentListRowHolder extends RecyclerView.ViewHolder {

    protected TextView student_name;
    protected TextView id;
    protected TextView program;
    protected TextView email;

    public FeedStudentListRowHolder(View view){
        super(view);
        student_name = (TextView) view.findViewById(R.id.student_name);
        id = (TextView) view.findViewById(R.id.id);
        //program = (TextView) view.findViewById(R.id.program);
        //email = (TextView) view.findViewById(R.id.email);
    }
}