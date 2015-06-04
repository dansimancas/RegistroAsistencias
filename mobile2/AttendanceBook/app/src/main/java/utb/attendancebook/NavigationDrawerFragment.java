package utb.attendancebook;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import utb.attendancebook.help.HelpActivity;
import utb.attendancebook.navigationdrawer.NavigationDrawerAdapter;
import utb.attendancebook.navigationdrawer.NavigationDrawerItem;
import utb.attendancebook.profile.ProfileActivity;
import utb.attendancebook.settings.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerAdapter.ClickListener {

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter mAdapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View mContainerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<NavigationDrawerItem> getItems(){
        List<NavigationDrawerItem> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_profile, R.drawable.ic_home, R.drawable.ic_settings, R.drawable.ic_help, R.drawable.ic_exit};
        String[] titles = {"Profile", "Home", "Settings", "Help", "Exit"};

        for(int i = 0; i < icons.length && i < titles.length; i++){
            NavigationDrawerItem current = new NavigationDrawerItem();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawer_list);
        mAdapter = new NavigationDrawerAdapter(getActivity(), getItems());
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(mContainerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        switch (position){
            case 0:
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
            case 4:
                Context context = getActivity();
                context.getSharedPreferences("TokenStorage", 0).edit().clear().commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}
