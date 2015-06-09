package utb.attendancebook.students;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import utb.attendancebook.R;

/**
 * Created by daniela on 8/04/15.
 */

public class StudentListAdapter extends RecyclerView.Adapter<FeedStudentListRowHolder> {

    private List<StudentItem> studentItemList;
    private LayoutInflater mInflater;
    private Context mContext;

    public StudentListAdapter(Context context, List<StudentItem> studentItemList) {
        this.studentItemList = studentItemList;
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public FeedStudentListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.student_list_row, viewGroup, false);
        FeedStudentListRowHolder mh = new FeedStudentListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedStudentListRowHolder feedStudentListRowHolder, int i) {
        StudentItem studentItem = studentItemList.get(i);
        //feedStudentListRowHolder.student_thumbnail.setTag(Html.fromHtml(studentItem.getId()));
        feedStudentListRowHolder.student_name.setText(Html.fromHtml(studentItem.getStudentName()));
        //feedStudentListRowHolder.student_name.setTag(Html.fromHtml(studentItem.getId()));
        feedStudentListRowHolder.id.setText(Html.fromHtml(studentItem.getId()));
        //feedStudentListRowHolder.student_thumbnail.setImageResource(studentItem.getStudentThumbnail());
        //feedStudentListRowHolder.id.setTag(Html.fromHtml(studentItem.getId()));

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


