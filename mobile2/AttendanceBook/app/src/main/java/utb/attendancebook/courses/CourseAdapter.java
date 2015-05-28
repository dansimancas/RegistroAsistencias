package utb.attendancebook.courses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import utb.attendancebook.R;

/**
 * Created by daniela on 28/05/15.
 */
public class CourseAdapter extends RecyclerView.Adapter<FeedCourseListRowHolder> {

    private List<CourseItem> courseItemList;
    private Context mContext;

    public CourseAdapter(Context context, List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
        this.mContext = context;
    }

    @Override
    public FeedCourseListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_list_row, null);
        FeedCourseListRowHolder mh = new FeedCourseListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedCourseListRowHolder feedCourseListRowHolder, int i) {
        CourseItem courseItem = courseItemList.get(i);
        feedCourseListRowHolder.subject_name.setText(Html.fromHtml(courseItem.getSubjectName()));
        feedCourseListRowHolder.nrc.setText(Html.fromHtml(courseItem.getNrc()));
        feedCourseListRowHolder.nrc.setTag(Html.fromHtml(courseItem.getNrc()));
        feedCourseListRowHolder.subject_name.setTag(Html.fromHtml(courseItem.getNrc()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != courseItemList ? courseItemList.size() : 0);
    }
}