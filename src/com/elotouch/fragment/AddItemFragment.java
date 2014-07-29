package com.elotouch.fragment;

import com.elotouch.paypoint.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddItemFragment extends Fragment {
	
public AddItemFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_additem, container, false);
         
        return rootView;
    }

}
