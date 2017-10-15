package org.tsofen.mentorim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;

import net.crofis.ui.dialog.CustomViewDialog;

import org.tsofen.fragments.BaseFragment;
import org.tsofen.fragments.PendingFragment;
import org.tsofen.fragments.UpcomingFragment;
import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.User;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BaseFragment fragments[];
    private boolean didLogout = true;
    FloatingActionButton fab;
    private NavigationView navigationView;

    private Map<Integer,MenuItem> associatedUsersMenuItems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new BaseFragment[]{new PendingFragment(),new UpcomingFragment(),null};

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this::addNew);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        final DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);


        drawerLayout.closeDrawers();
    }

    private void addNew(View view){
        Intent i = new Intent(this,MeetingCreateActivity.class);
        startActivityForResult(i,MeetingCreateActivity.REQUEST_CODE);
    }

    private void setupMenu(DataManager manager){


        User currentUser = manager.getUser();
        User[] associatedUsers = manager.getAssociatedUsers();
        boolean isMentor = currentUser.isMentor();

        Menu menu = navigationView.getMenu();
        View v = navigationView.getHeaderView(0);


        TextView fullName = v.findViewById(R.id.fullName);
        TextView email = v.findViewById(R.id.email);
        ImageView imageView = v.findViewById(R.id.imageView);

        //TODO: load user data to to header

        Glide.with(this)
                .load("https://avatars0.githubusercontent.com/u/17438617?s=400")
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

        fullName.setText(currentUser.getFullName());
        email.setText(currentUser.getEmail());

        //clear menu
        menu.clear();

        //add sub menu todo: replace strings with resources
        SubMenu usersMenu = menu.addSubMenu(isMentor ? "Mentees" : "Mentor");

        //add(groupId,itemId,order,title) -> MenuItem

        for (int i = 0; i < associatedUsers.length; i++) {
            User u = associatedUsers[i];
            final MenuItem item = usersMenu.add(0,i,0,u.getFullName()).setIcon(R.drawable.ic_account_circle_grey_500_48dp);

            String url = null;

            if(i == 0)
                url = "https://avatars0.githubusercontent.com/u/17438617?s=400";

            if(i == 1)
                url = "https://avatars3.githubusercontent.com/u/26304818?s=400";

            if(i == 2)
                url = "https://avatars3.githubusercontent.com/u/11991858?s=400";

            if(i == 3)
                url = "https://avatars2.githubusercontent.com/u/31918069?s=400";

            if(i == 4)
                url = "https://avatars1.githubusercontent.com/u/31919328?s=400";

            if(i == 5)
                url = "https://avatars3.githubusercontent.com/u/31918144?s=400";

            if (url != null)
                Glide.with(this).asDrawable().load(url).apply(RequestOptions.circleCropTransform()).into(new SimpleTarget<Drawable>(128,128) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        item.setIcon(resource);
                    }
                });

            item.setOnMenuItemClickListener(menuItem -> {
                Intent in = new Intent(this,ProfileController.class);
                in.putExtra(ProfileController.LayoutsMode.VIEW_OTHER, u);
                startActivity(in);
                return true;
            });
        }

    }

    private void loadData(DataManager manager){
        if(didLogout){
            //TODO: Load meetings from server.
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setOffscreenPageLimit(3);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
            didLogout = false;
            boolean isMentor = manager.getUser().isMentor();
            if(!isMentor){
                fab.setVisibility(View.GONE);
            }else{
                fab.setVisibility(View.VISIBLE);
            }

            setupMenu(manager);
        }

        APIManager.getInstance().getAssociatedUsers(manager.getUser().getId(),
                manager.getToken(),
                manager.getUser().isMentor(),
                (response1, users, ex1) -> {
            //store users
            manager.associatedUsers(users);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //create data manager instance
        final DataManager manager = DataManager.getInstance(this);

        //fetch token
        String token = manager.getToken();

        //check if token exists
        if (token == null)
            goToLogin();
        else
            loadData(manager);


    }

    private void goToLogin(){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout){
            DataManager.getInstance(this).destroy();
            goToLogin();
            didLogout = true;
            return true;
        }

        if(id == R.id.action_profile){
            startActivity(new Intent(MainActivity.this,ProfileController.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MeetingCreateActivity.REQUEST_CODE){
            if (resultCode == RESULT_OK){
                for (BaseFragment fragment : fragments) {
                    if(fragment != null){
                        fragment.onRefresh();
                    }
                }
            }
        }


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return new View(getContext());//rootView;
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Fragment f = fragments[position];

            return f != null ? f : PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.pending);
                case 1:
                    return getString(R.string.upcoming);
                case 2:
                    return getString(R.string.history);
            }
            return null;
        }
    }

}
