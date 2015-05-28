package utb.attendancebook.courses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import utb.attendancebook.R;

/**
 * Created by daniela on 28/05/15.
 */
public class FeedCourseListRowHolder extends RecyclerView.ViewHolder {
    protected TextView subject_name;
    protected TextView nrc;
    protected TextView period;
    protected TextView credits;
    protected TextView week_hours;
    protected TextView subject;
    protected TextView section;
    protected TextView course;

    public FeedCourseListRowHolder(View view) {
        super(view);
        subject_name = (TextView) view.findViewById(R.id.subject_name);
        nrc = (TextView) view.findViewById(R.id.nrc);
        //period = (TextView) view.findViewById(R.id.period);
        //credits = (TextView) view.findViewById(R.id.credits);
        //week_hours = (TextView) view.findViewById(R.id.week_hours);
        //subject = (TextView) view.findViewById(R.id.subject);
        //section = (TextView) view.findViewById(R.id.section);
        //course = (TextView) view.findViewById(R.id.course);
    }
}
