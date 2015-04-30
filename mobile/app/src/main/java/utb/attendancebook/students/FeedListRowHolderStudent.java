package utb.attendancebook.students;

/**
 * Created by daniela on 8/04/15.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import utb.attendancebook.R;


public class FeedListRowHolderStudent extends RecyclerView.ViewHolder {

    protected CardView cv;
    protected TextView student_name;
    protected TextView id;
    protected TextView program;
    protected TextView email;

    public FeedListRowHolderStudent(View view){
        super(view);
        cv = (CardView) itemView.findViewById(R.id.cv);
        student_name = (TextView) view.findViewById(R.id.student_name);
        id = (TextView) view.findViewById(R.id.id);
        program = (TextView) view.findViewById(R.id.program);
        email = (TextView) view.findViewById(R.id.email);
    }
}
