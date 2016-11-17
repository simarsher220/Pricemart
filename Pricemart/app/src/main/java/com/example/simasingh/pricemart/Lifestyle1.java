package com.example.simasingh.pricemart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lifestyle1 extends AppCompatActivity {

    ExpandableListView expandableListView;
    private HashMap<String, List<String>> childList;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifestyle1);
        expandableListView = (ExpandableListView) findViewById(R.id.lifestyle_listview);
        final List<String> Headings = new ArrayList<String>();
        List<String> Men = new ArrayList<String>();
        List<String> Women = new ArrayList<String>();
        List<String> Children = new ArrayList<String>();
        childList = new HashMap<String, List<String>>();
        String headings[] = getResources().getStringArray(R.array.lifestyle_header_titles);
        String men[] = getResources().getStringArray(R.array.lifestyle_men_child_titles);
        String women[] = getResources().getStringArray(R.array.lifestyle_women_child_titles);
        String children[] = getResources().getStringArray(R.array.lifestyle_children_child_titles);
        for(String title : headings){
            Headings.add(title);
        }
        for(String title : men){
            Men.add(title);
        }
        for(String title : women){
            Women.add(title);
        }
        for(String title : children){
            Children.add(title);
        }
        childList.put(Headings.get(0), Men);
        childList.put(Headings.get(1), Women);
        childList.put(Headings.get(2), Children);
        LifestyleListview lifestyleListview = new LifestyleListview(this, Headings, childList);
        expandableListView.setAdapter(lifestyleListview);
        i = new Intent(Lifestyle1.this, DisplayMenShoe.class);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch(groupPosition){
                    case 0: switch(childPosition){
                        case 0: i.putExtra("key","http://10.86.0.101/simar/mcdetails.php");
                            startActivity(i);break;
                        case 1: i.putExtra("key","http://10.86.0.101/simar/mfdetails.php");
                            startActivity(i);break;
                    }
                        break;

                    case 1: switch(childPosition){
                        case 0: i.putExtra("key","http://10.86.0.101/simar/wcdetails.php");
                            startActivity(i);break;
                        case 1: i.putExtra("key","http://10.86.0.101/simar/wfdetails.php");
                            startActivity(i);break;
                    }
                        break;

                    case 2: switch(childPosition){
                        case 0: i.putExtra("key","http://10.86.0.101/simar/ccdetails.php");
                            startActivity(i);break;
                        case 1: i.putExtra("key","http://10.86.0.101/simar/ctdetails.php");
                            startActivity(i);break;
                    }
                        break;
                }
                return false;
            }
        });
    }
}
