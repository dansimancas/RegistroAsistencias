package utb.attendancebook.students;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utb.attendancebook.R;

/**
 * Created by daniela on 15/04/15.
 */
public class StudentInfoAdapter extends RecyclerView.Adapter<FeedListRowHolderStudent> {

    private StudentItem studentItem;
    private Context mContext;

    public StudentInfoAdapter(Context context, StudentItem studentItem) {
        this.studentItem = studentItem;
        this.mContext = context;
    }


    @Override
    public FeedListRowHolderStudent onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_student_info, null);
        FeedListRowHolderStudent mh = new FeedListRowHolderStudent(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolderStudent feedListRowHolderStudent, int i) {
        StudentItem studentItem = this.studentItem;
        feedListRowHolderStudent.student_name.setText(Html.fromHtml(studentItem.getStudentName()));
        feedListRowHolderStudent.id.setText(Html.fromHtml(studentItem.getId()));
        feedListRowHolderStudent.program.setText(Html.fromHtml(studentItem.getProgram()));
        feedListRowHolderStudent.email.setText(Html.fromHtml(studentItem.getEmail()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != studentItem ? 1 : 0);
    }

}
