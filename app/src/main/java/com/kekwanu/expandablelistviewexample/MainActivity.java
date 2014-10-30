package com.kekwanu.expandablelistviewexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

    private ExpandableListView mDrawerList;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareListData();

        mDrawerList  = (ExpandableListView) findViewById(R.id.listView);
        mDrawerList.setAdapter(new MyExpandableListAdapter(this, listDataHeader, listDataChild ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild  = new HashMap<String, List<String>>();

        // Adding child data
        String[] groupList = getResources().getStringArray(R.array.group_items);
        listDataHeader =  new ArrayList<String>(Arrays.asList(groupList));

        // Adding child data
        String[] worldCupTeamsList = getResources().getStringArray(R.array.brazil_2014_world_cup);
        ArrayList<String> worldCupTeams =  new ArrayList<String>(Arrays.asList(worldCupTeamsList));

        String[] mlsTeamsList = getResources().getStringArray(R.array.mls);
        ArrayList<String> mlsTeams =  new ArrayList<String>(Arrays.asList(mlsTeamsList));

        String[] eplTeamsList = getResources().getStringArray(R.array.english_premier_league);
        ArrayList<String> eplTeams =  new ArrayList<String>(Arrays.asList(mlsTeamsList));

        String[] bTeamsList = getResources().getStringArray(R.array.bundesliga);
        ArrayList<String> bTeams =  new ArrayList<String>(Arrays.asList(mlsTeamsList));

        listDataChild.put(listDataHeader.get(0), worldCupTeams); // Header, Child data
        listDataChild.put(listDataHeader.get(1), eplTeams); // Header, Child data
        listDataChild.put(listDataHeader.get(2), bTeams);
        listDataChild.put(listDataHeader.get(3), mlsTeams);
    }
}
