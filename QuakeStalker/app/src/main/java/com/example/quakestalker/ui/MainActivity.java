package com.example.quakestalker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quakestalker.R;
import com.example.quakestalker.di.DaggerEarthquakeComponent;
import com.example.quakestalker.di.EarthquakeComponent;
import com.example.quakestalker.di.EarthquakeService;
import com.example.quakestalker.models.Feature;
import com.example.quakestalker.models.Properties;
import com.example.quakestalker.viewmodels.EarthquakeViewModel;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuakeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuakeRecyclerViewAdapter();

//        recyclerView.setAdapter(adapter);


        for (int i = 0; i < 15; i++) {
            Feature feature = new Feature();
            Properties properties = new Properties();
            properties.setMag(3.0 + (i/10.0));
            properties.setPlace("Santiago "+i);
            properties.setTime(new Timestamp(System.currentTimeMillis()).getTime());
            feature.setProperties(properties);

//            testList.add(feature);
        }

//        adapter.setQuakeList(testList);
        recyclerView.setAdapter(adapter);


    }
}
