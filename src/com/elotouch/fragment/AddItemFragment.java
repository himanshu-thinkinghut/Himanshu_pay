package com.elotouch.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.elotouch.paypoint.R;

public class AddItemFragment extends Fragment {

	String[] itemList = new String[] { "Store *", "Name *", "Category", "Option",
			"Tax", "Size", "Cost", "Barcode", "Quantity", "Photo" };
	ListView addItemList;

	public AddItemFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_additem, container,
				false);
		BindListView(rootView);
		return rootView;
	}

	private void BindListView(View rootView) {
		// TODO Auto-generated method stub
		addItemList = (ListView) rootView.findViewById(R.id.additem_list);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				itemList);
		addItemList.setAdapter(adapter);
		addItemList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				
				String value =(String)parent.getItemAtPosition(position);
				int item_postion = position;
				
				Toast.makeText(getActivity(), "You Click On "+value, Toast.LENGTH_SHORT).show();
			
				if(item_postion == 1)
				{
					 Fragment frag = new InventoryFragment();
					 android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		             ft.replace(R.id.frame_container, frag);
		             ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		             ft.addToBackStack(null);
		             ft.commit();
				}
               
			}
		});
	}

}
