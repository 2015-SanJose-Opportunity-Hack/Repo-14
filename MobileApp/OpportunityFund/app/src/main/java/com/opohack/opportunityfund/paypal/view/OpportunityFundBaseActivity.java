package com.opohack.opportunityfund.paypal.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.opohack.opportunityfund.paypal.R;


public class OpportunityFundBaseActivity extends AppCompatActivity {

    static final String TAG = "OpportunityFundBaseActivity";

    private OpportunityFundBaseActivity mActivity;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;
    //private ListView mSliderList;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private LinearLayout mContentLayout;

    private RequestQueue mRequestQueue;

    private boolean bErrorDialogShowing = false;

    public OpportunityFundBaseActivity() {
        mActivity = this;
    }

    public OpportunityFundBaseActivity getActivity() {
        return mActivity;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity_fund_base);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_opportunity_fund_base);

        // Add inflated UI to container
        mContentLayout = (LinearLayout) findViewById(R.id.content_layout);

        // Inflate actual UI provided by child activity
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View oView = inflater.inflate(layoutResID, null);

		/*
		 *  Even if you give match parent height in xml, still view becomes height becomes.
		 *  So adding this line
		 */
        oView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        mContentLayout.addView(oView);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            mTitle = mDrawerTitle = getTitle();
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

            //Initializing NavigationView
            mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {

                    //Checking if the item is in checked state or not, if not make it in checked state
                    if(menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);

                    //Closing drawer on item click
                    mDrawerLayout.closeDrawers();

                    //Check to see which item was being clicked and perform appropriate action
                    switch (menuItem.getItemId()){

                        default:
                            Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_SHORT).show();
                            return true;

                    }
                }
            });
            //mSliderList = (ListView) findViewById(R.id.slider_list);

            // set a custom shadow that overlays the main content when the drawer opens
            // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

            // set up the drawer's list view with items and click listener
            //mSliderList.setAdapter(SliderMenuListAdapter.getInstance(this));
            //mSliderList.setOnItemClickListener(new DrawerItemClickListener());

            // enable ActionBar app icon to behave as action to toggle nav drawer
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            // ActionBarDrawerToggle ties together the the proper interactions
            // between the sliding drawer and the action bar app icon
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,                   // host Activity
                    mDrawerLayout,
                    R.string.drawer_open,   // "open drawer" description for accessibility
                    R.string.drawer_close   // "close drawer" description for accessibility // DrawerLayout object
            ) {
                public void onDrawerClosed(View view) {
                    getSupportActionBar().setTitle(mTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }
    }

    /**
     *  The click listener for ListView in the slider menu
     */
   /* private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(mSliderList != null && mDrawerLayout != null) {
                mSliderList.setItemChecked(position, true);
                setTitle(getString(SliderMenuListAdapter.iSliderMenuItems[position]));
                mDrawerLayout.closeDrawer(mSliderList);
                handleMenuClick(position);

            }
        }
    }*/

    /**
     * Handles slider click. Open appropriate activity as per clicked slider list item position
     * @param position
     */
    private void handleMenuClick(int position) {
        switch(position) {
            case 0:

                break;

            default:
                break;
        }
    }

    /**
     * Displays Error Dialog to handle various exception As Network Unavailable, Cannot Connect to Internet, Connection Timeout etc
     * @param msg Error Message
     */
    public void showErrorDialog(final String msg) {
        bErrorDialogShowing = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(mActivity.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                bErrorDialogShowing = false;

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(OpportunityFundApplication.getInstance());
        }

        return mRequestQueue;
    }

    public void addToRequestQueue(Request req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void addToRequestQueue(Request req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opportunity_fund_base, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
