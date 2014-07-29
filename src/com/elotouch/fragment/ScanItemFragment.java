package com.elotouch.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.elotouch.paypoint.R;
import com.elotouch.redlaser.RedlaserScanner;


public class ScanItemFragment extends Fragment {

	
public ScanItemFragment(){}

    Button scanButton ;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_scanitem, container, false);
        scanButton =  (Button) rootView.findViewById(R.id.btnScan);
        scanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent redLaser_Intent = new Intent(getActivity(), RedlaserScanner.class);
				startActivity(redLaser_Intent);
				
				
			}
		});
        return rootView;
    }



}
