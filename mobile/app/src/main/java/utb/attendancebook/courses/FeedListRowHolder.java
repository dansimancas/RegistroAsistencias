package utb.attendancebook.courses;

/**
 * Created by jairo on 25/03/15.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import utb.attendancebook.R;

public class FeedListRowHolder extends RecyclerView.ViewHolder {
    protected CardView cv;
    protected TextView subject_name;
    protected TextView nrc;

    public FeedListRowHolder(View view) {
        super(view);
        cv = (CardView) itemView.findViewById(R.id.cv);
        subject_name = (TextView) view.findViewById(R.id.subject_name);
        nrc = (TextView) view.findViewById(R.id.nrc);
    }

}