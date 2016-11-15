package com.example.simasingh.pricemart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Electronics extends AppCompatActivity {

    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        expandableListView = (ExpandableListView) findViewById(R.id.electronics_listview);
        List<String> Headings = new ArrayList<String>();
        List<String> LargeAppliances = new ArrayList<String>();
        List<String> MobilesAndAccessories = new ArrayList<String>();
        List<String> ComputerAccessories = new ArrayList<String>();
        HashMap<String, List<String>> ChildList= new HashMap<String, List<String>>();
        String headings[] = getResources().getStringArray(R.array.electronics_header_titles);
        String large_appliances[] = getResources().getStringArray(R.array.electronics_large_appliances);
        String mobiles_and_accessories[] = getResources().getStringArray(R.array.electronics_mobiles_and_accessories);
        String computer_accessories[] = getResources().getStringArray(R.array.electronics_computer_accessories);
        for(String title : headings){
            Headings.add(title);
        }
        for(String title : large_appliances){
            LargeAppliances.add(title);
        }
        for(String title : mobiles_and_accessories){
            MobilesAndAccessories.add(title);
        }
        for(String title : computer_accessories){
            ComputerAccessories.add(title);
        }
        ChildList.put(Headings.get(0), MobilesAndAccessories);
        ChildList.put(Headings.get(1), ComputerAccessories);
        ChildList.put(Headings.get(2), LargeAppliances);
        ElectronicsListview electronicsListview = new ElectronicsListview(this, Headings, ChildList);
        expandableListView.setAdapter(electronicsListview);
    }
}
