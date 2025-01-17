package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button add_city_btn;
    Button delete_city_btn;
    Button confirm_btn;
    TextView city_input_controller;

    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Beirut"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        add_city_btn = findViewById(R.id.Add_City);
        delete_city_btn = findViewById(R.id.Delete);
        confirm_btn = findViewById(R.id.Confirm_button);

        city_input_controller = findViewById(R.id.city_input);

        add_city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city_input_controller.setVisibility(View.VISIBLE);
                confirm_btn.setVisibility(View.VISIBLE);
            }
        });

        delete_city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedIndex != -1) {
                    dataList.remove(selectedIndex);
                    cityAdapter.notifyDataSetChanged();
                    selectedIndex = -1; // Reset the selection
                    Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No city selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city_input_controller.setVisibility(View.INVISIBLE);
                confirm_btn.setVisibility(View.INVISIBLE);

                System.out.println(city_input_controller.getText());
                String input = String.valueOf(city_input_controller.getText());
                dataList.add(input);

                // Reset colors
                for (int i = 0; i < cityList.getChildCount(); i++) {
                    cityList.getChildAt(i).setBackgroundColor(0xFFFFFFFF); // Default color
                }
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (selectedIndex == i) {
                    // Deselect
                    selectedIndex = -1;
                    cityList.getChildAt(i).setBackgroundColor(0xFFFFFFFF);
                } else {
                    selectedIndex = i;

                    // Highlight the selected item
                    for (int j = 0; j < cityList.getChildCount(); j++) {
                        if (j == i) {
                            cityList.getChildAt(j).setBackgroundColor(0xFF9E9E9E); // Selected item color
                        } else {
                            cityList.getChildAt(j).setBackgroundColor(0xFFFFFFFF); // Default color
                        }
                    }
                }
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.Add_City) {
//
//        } else if (v.getId() == R.id.Add_City) {
//
//        } else
//    }
}