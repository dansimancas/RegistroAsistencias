package utb.attendancebook.students;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import utb.attendancebook.R;

/**
 * Created by daniela on 8/04/15.
 */

public class StudentListAdapter extends RecyclerView.Adapter<FeedStudentListRowHolder> {

    private List<StudentItem> studentItemList;
    private Context mContext;

    public StudentListAdapter(Context context, List<StudentItem> studentItemList) {
        this.studentItemList = studentItemList;
        this.mContext = context;
    }

    @Override
    public FeedStudentListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_student_list, null);
        FeedStudentListRowHolder mh = new FeedStudentListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedStudentListRowHolder feedStudentListRowHolder, int i) {
        StudentItem studentItem = studentItemList.get(i);
        feedStudentListRowHolder.student_name.setText(Html.fromHtml(studentItem.getStudentName()));
        feedStudentListRowHolder.id.setText(Html.fromHtml(studentItem.getId()));
        feedStudentListRowHolder.id.setTag(Html.fromHtml(studentItem.getId()));
        feedStudentListRowHolder.student_name.setTag(Html.fromHtml(studentItem.getId()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != studentItemList ? studentItemList.size() : 0);
    }

}