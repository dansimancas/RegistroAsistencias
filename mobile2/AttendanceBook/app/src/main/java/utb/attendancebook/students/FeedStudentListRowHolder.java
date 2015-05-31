package utb.attendancebook.students;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utb.attendancebook.R;

/**
 * Created by daniela on 29/05/15.
 */

public class FeedStudentListRowHolder extends RecyclerView.ViewHolder{

    protected ImageView student_thumbnail;
    protected ImageView student_picture;
    protected TextView student_name;
    protected TextView id;
    protected TextView program;
    protected TextView email;

    public FeedStudentListRowHolder(View view){
        super(view);
        student_thumbnail = (ImageView) view.findViewById(R.id.student_thumbnail);
        student_name = (TextView) view.findViewById(R.id.student_name);
        id = (TextView) view.findViewById(R.id.id);
        program = (TextView) view.findViewById(R.id.program);
        email = (TextView) view.findViewById(R.id.email);
    }
}

