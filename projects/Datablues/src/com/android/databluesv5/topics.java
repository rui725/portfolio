package com.android.databluesv5;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class topics extends ExpandableListActivity implements
		OnChildClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try{
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		getActionBar().setTitle("Home");
		
		/*getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setTitle("FAQs/Topics");*/
		
		
		setContentView(R.layout.topic_layout);
		ExpandableListView expandbleLis = getExpandableListView();
		expandbleLis.setDividerHeight(2);
		expandbleLis.setGroupIndicator(null);
		expandbleLis.setClickable(true);

		setGroupData();
		setChildGroupData();

		NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
		mNewAdapter
				.setInflater(
						(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
						this);
		getExpandableListView().setAdapter(mNewAdapter);
		expandbleLis.setOnChildClickListener(this);
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), "ERROR" + e,Toast.LENGTH_LONG).show();
			
		}
	}

	public void setGroupData() {
		groupItem.add("What is Data Structure?");
		groupItem.add("What is a Link List?");		
		groupItem.add("What is a Stack");
		groupItem.add("What is a Tree");
		groupItem.add("What is Sorting");
		
	}

	ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();

	public void setChildGroupData() {
		/**
		 * Add Data For Data Structure
		 */
		ArrayList<String> child = new ArrayList<String>();
		child.add("- A data structure is a specialized format for organizing and storing data. General data structure types include the array, the file, the record, the table, the tree, and so on. Any data structure is designed to organize data to suit a specific purpose so that it can be accessed and worked with in appropriate ways. In computer programming, a data structure may be selected or designed to store data for the purpose of working on it with various algorithms.");		
		childItem.add(child);

		/**
		 * Add Data For LinkLIST
		 */
		child = new ArrayList<String>();
		child.add("- A linked list is a data structure consisting of a group of nodes which together represent a sequence. Under the simplest form, each node is composed of a data and a reference (in other words, a link) to the next node in the sequence; more complex variants add additional links. This structure allows for efficient insertion or removal of elements from any position in the sequence.");		
		childItem.add(child);
		/**
		 * Add Data For STACK
		 */
		child = new ArrayList<String>();
		child.add("- A stack is a particular kind of abstract data type or collection in which the principal (or only) operations on the collection are the addition of an entity to the collection, known as push and removal of an entity, known as pop.[1] The relation between the push and pop operations is such that the stack is a Last-In-First-Out (LIFO) data structure. In a LIFO data structure, the last element added to the structure must be the first one to be removed. This is equivalent to the requirement that, considered as a linear data structure, or more abstractly a sequential collection, the push and pop operations occur only at one end of the structure, referred to as the top of the stack. Often a peek or top operation is also implemented, returning the value of the top element without removing it.");
		
		childItem.add(child);
		/**
		 * Add Data For Trees
		 */
		child = new ArrayList<String>();
		child.add("A tree data structure can be defined recursively (locally) as a collection of nodes (starting at a root node), where each node is a data structure consisting of a value, together with a list of references to nodes (the \"children\"), with the constraints that no reference is duplicated, and none points to the root.");		
		childItem.add(child);
		/**
		 * Add Data For Sorting
		 */
		child = new ArrayList<String>();
		child.add("A sorting algorithm is an algorithm that puts elements of a list in a certain order. The most-used orders are numerical order and lexicographical order. Efficient sorting is important for optimizing the use of other algorithms (such as search and merge algorithms) which require input data to be in sorted lists; it is also often useful for canonicalizing data and for producing human-readable output.");		
		childItem.add(child);
		
		
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home: finish();
		break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		//Toast.makeText(topics.this, "Clicked On Child",
			//	Toast.LENGTH_SHORT).show();
		return true;
	}
	
}

class NewAdapter extends BaseExpandableListAdapter {
    int gid;
	public ArrayList<String> groupItem, tempChild;
	public ArrayList<Object> Childtem = new ArrayList<Object>();
	public LayoutInflater minflater;
	public Activity activity;

	public NewAdapter(ArrayList<String> grList, ArrayList<Object> childItem) {
		groupItem = grList;
		this.Childtem = childItem;
	}

	public void setInflater(LayoutInflater mInflater, Activity act) {
		this.minflater = mInflater;
		activity = act;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		tempChild = (ArrayList<String>) Childtem.get(groupPosition);
		TextView text = null;
		ImageView iv1 = null;
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.child_row, null);
		}
		text = (TextView) convertView.findViewById(R.id.tvTopic1);
		text.setText(tempChild.get(childPosition));
		if(groupPosition ==1){
			//LINKLIST
			iv1 =(ImageView)convertView.findViewById(R.id.ivTopic1);
			iv1.setImageResource(R.drawable.linklist);
			iv1.setVisibility(iv1.VISIBLE);
		}else if(groupPosition==2){
			//STACKS
			iv1 =(ImageView)convertView.findViewById(R.id.ivTopic1);
			iv1.setImageResource(R.drawable.stack);
			iv1.setVisibility(iv1.VISIBLE);
		}else if(groupPosition==3){
			//TREES
			iv1 =(ImageView)convertView.findViewById(R.id.ivTopic1);
			iv1.setImageResource(R.drawable.trees);
			iv1.setVisibility(iv1.VISIBLE);
		}else if(groupPosition==4){
			//HEAP
			iv1 =(ImageView)convertView.findViewById(R.id.ivTopic1);
			iv1.setImageResource(R.drawable.sorting);
			iv1.setVisibility(iv1.VISIBLE);
		}else if(groupPosition==0){
			iv1 =(ImageView)convertView.findViewById(R.id.ivTopic1);
			iv1.setVisibility(iv1.GONE);
		}
		gid = groupPosition;
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(activity,gid+"",
					//	Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) Childtem.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return groupItem.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {		
		super.onGroupExpanded(groupPosition);
		
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.group_row, null);
		}
		((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);
		if(((CheckedTextView) convertView).isChecked()){
			((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_up_float, 0);
		}else{
			((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}

