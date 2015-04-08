package utb.recyclerviewexample;

/**
 * Created by jairo on 25/03/15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<FeedListRowHolder> {

    private List<CourseItem> courseItemList;
    private Context mContext;

    public CourseAdapter(Context context, List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_list_row, null);
        FeedListRowHolder mh = new FeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        CourseItem courseItem = courseItemList.get(i);
        feedListRowHolder.subject_name.setText(Html.fromHtml(courseItem.getSubjectName()));
        feedListRowHolder.nrc.setText(Html.fromHtml(courseItem.getNrc()));
        feedListRowHolder.subject_name.setTag(Html.fromHtml(courseItem.getNrc()));
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