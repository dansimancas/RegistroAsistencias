package utb.attendancebook.students;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import utb.attendancebook.R;

/**
 * Created by daniela on 29/05/15.
 */
public class StudentInfoAdapter extends RecyclerView.Adapter<FeedStudentListRowHolder>{

    private LayoutInflater mInflater;
    private StudentItem studentItem;
    private Context mContext;

    public StudentInfoAdapter(Context context, StudentItem studentItem) {
        this.studentItem = studentItem;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public FeedStudentListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.activity_student_info, viewGroup, false);
        FeedStudentListRowHolder mh = new FeedStudentListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedStudentListRowHolder feedStudentListRowHolder, int position) {
        StudentItem studentItem = this.studentItem;
        feedStudentListRowHolder.student_picture.setImageResource(studentItem.getStudentPicture());
        feedStudentListRowHolder.student_name.setText(Html.fromHtml(studentItem.getStudentName()));
        feedStudentListRowHolder.id.setText(Html.fromHtml(studentItem.getId()));
        feedStudentListRowHolder.program.setText(Html.fromHtml(studentItem.getProgram()));
        feedStudentListRowHolder.email.setText(Html.fromHtml(studentItem.getEmail()));
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
