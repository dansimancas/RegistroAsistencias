package utb.attendancebook.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import utb.attendancebook.R;
import utb.attendancebook.profile.ProfileActivity;

/**
 * Created by daniela on 19/05/15.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavigationDrawerViewHolder> {

    List<NavigationDrawerItem> mItems = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;
    private ClickListener mClickListener;

    //Initialize the inflater
    public NavigationDrawerAdapter (Context context, List<NavigationDrawerItem> items){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    //Initialize the view
    @Override
    public NavigationDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Represents LinearLayout in the navigation_drawer_row
        View view = mInflater.inflate(R.layout.navigation_drawer_row, parent, false);
        NavigationDrawerViewHolder holder = new NavigationDrawerViewHolder(view);
        return holder;
    }

    //Binding the ViewHolder created on the onCreate. Updating the ViewHolder to the current position.
    @Override
    public void onBindViewHolder(NavigationDrawerViewHolder holder, final int position) {
        NavigationDrawerItem current = mItems.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setClickListener(ClickListener mClickListener){
        this.mClickListener = mClickListener;
    }

    //View to represent the data in every row.
    class NavigationDrawerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Extracting the data from the row
        TextView title;
        ImageView icon;
        public NavigationDrawerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.list_text);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }

        @Override
        public void onClick(View v) {
            //mContext.startActivity(new Intent(mContext, ProfileActivity.class));
            if(mClickListener != null){
                mClickListener.itemClicked(v,getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
