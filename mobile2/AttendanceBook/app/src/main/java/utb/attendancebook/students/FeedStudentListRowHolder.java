package utb.attendancebook.students;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import utb.attendancebook.R;

/**
 * Created by daniela on 29/05/15.
 */

public class FeedStudentListRowHolder extends RecyclerView.ViewHolder{

    protected ImageView student_thumbnail;
    protected ImageView student_picture;
    protected TextView student_name;
    protected TextView id;
    protected TextView program;
    protected TextView email;
    private String imageUri = "http://savio.utbvirtual.edu.co/user/external_render_pic.php?user=uws1&pass=uws1&wsusername=";

    public FeedStudentListRowHolder(View view){
        super(view);
        student_name = (TextView) view.findViewById(R.id.student_name);
        id = (TextView) view.findViewById(R.id.id);
        program = (TextView) view.findViewById(R.id.program);
        email = (TextView) view.findViewById(R.id.email);
        new DownloadImageTask((ImageView) view.findViewById(R.id.student_thumbnail)).execute(imageUri + id.getText().toString());
        //student_thumbnail = (ImageView) view.findViewById(R.id.student_thumbnail);
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

