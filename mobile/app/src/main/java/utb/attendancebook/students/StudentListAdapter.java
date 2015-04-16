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

public class StudentListAdapter extends RecyclerView.Adapter<FeedListRowHolderStudent> {

    private List<StudentItem> studentItemList;
    private Context mContext;

    public StudentListAdapter(Context context, List<StudentItem> studentItemList) {
        this.studentItemList = studentItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolderStudent onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_student_list, null);
        FeedListRowHolderStudent mh = new FeedListRowHolderStudent(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolderStudent feedListRowHolderStudent, int i) {
        StudentItem studentItem = studentItemList.get(i);
        feedListRowHolderStudent.student_name.setText(Html.fromHtml(studentItem.getStudentName()));
        feedListRowHolderStudent.id.setText(Html.fromHtml(studentItem.getId()));
        feedListRowHolderStudent.id.setTag(Html.fromHtml(studentItem.getId()));
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
