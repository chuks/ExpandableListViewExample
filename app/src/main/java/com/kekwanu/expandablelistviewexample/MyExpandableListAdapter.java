package com.kekwanu.expandablelistviewexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by onwuneme on 10/29/14.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final static String TAG = MyExpandableListAdapter.class.getCanonicalName();

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                       HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        Log.i(TAG,"getChild");

        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.i(TAG,"getChildId");

        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.i(TAG,"getChildView");

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.drawer_list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);

        convertView.setTag(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i(TAG,"getChildrenCount");

        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.i(TAG,"getGroup");

        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        Log.i(TAG,"getGroupCount");

        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.i(TAG,"getGroupId");

        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Log.i(TAG,"getGroupView");


        View view = convertView;
        TextView text = null;
        ImageView image = null;

        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.drawer_list_group, null);
        }

        TextView lblListHeader = (TextView) view.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

        TextView channelHeaderTextView = (TextView) view.findViewById(R.id.channel_header);
        int channelHeaderTextViewHeight = 0;


        if (headerTitle.equals("2014 FIFA World Cup")){
            channelHeaderTextView.setText(_context.getResources().getText(R.string.channels));
        }

        LinearLayout.LayoutParams channelHeaderTextViewLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                channelHeaderTextViewHeight
        );
        channelHeaderTextView.setLayoutParams(channelHeaderTextViewLp);
        view.setTag(headerTitle);

        //--
        String[] expandableList = _context.getResources().getStringArray(R.array.expandable);
        List<String> groupItems =  new ArrayList<String>(Arrays.asList(expandableList));

        /*boolean isExpandable = false;

        for (String groupItem : groupItems){
            if (groupItem.equals( headerTitle )){
                isExpandable = true;
            }
        }

        if (isExpandable){
            int imageResourceId = isExpanded ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float;
            image.setImageResource(imageResourceId);

            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.INVISIBLE);
        }*/

        return view;
    }

    @Override
    public boolean hasStableIds() {
        Log.i(TAG,"hasStableIds");

        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        Log.i(TAG, "isChildSelectable");

        return true;
    }

    public int dp2Px(int dp){
        Log.i(TAG, "dp2Px");

        final float scale = _context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}