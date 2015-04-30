package utb.attendancebook.courses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utb.attendancebook.R;
import utb.attendancebook.students.FeedListRowHolderStudent;
import utb.attendancebook.students.StudentItem;

/**
 * Created by daniela on 17/04/15.
 */
public class CourseInfoAdapter extends RecyclerView.Adapter<FeedListRowHolder> {

    private CourseItem courseItem;
    private Context mContext;

    public CourseInfoAdapter(Context context, CourseItem courseItem) {
        this.courseItem = courseItem;
        this.mContext = context;
    }


    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_course_info, null);
        FeedListRowHolder mh = new FeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        CourseItem courseItem = this.courseItem;
        feedListRowHolder.subject_name.setText(Html.fromHtml(courseItem.getSubjectName()));
        feedListRowHolder.nrc.setText("NRC: "+Html.fromHtml(courseItem.getNrc()));
        feedListRowHolder.nrc.setTag(Html.fromHtml(courseItem.getNrc()));
        feedListRowHolder.period.setText("Period: "+Html.fromHtml(courseItem.getPeriod()));
        feedListRowHolder.credits.setText("Credits: "+Html.fromHtml(courseItem.getCredits()));
        feedListRowHolder.week_hours.setText("Week hours: "+Html.fromHtml(courseItem.getWeekHours()));
        feedListRowHolder.subject.setText("Subject: "+Html.fromHtml(courseItem.getSubject()));
        feedListRowHolder.section.setText("Section: "+Html.fromHtml(courseItem.getSection()));
        feedListRowHolder.course.setText("Course: "+Html.fromHtml(courseItem.getCourse()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != courseItem ? 1 : 0);
    }

}
