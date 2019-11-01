package com.example.quakestalker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.quakestalker.R;
import com.example.quakestalker.di.DaggerEarthquakeComponent;
import com.example.quakestalker.di.EarthquakeComponent;
import com.example.quakestalker.di.EarthquakeService;
import com.example.quakestalker.models.Feature;
import com.example.quakestalker.viewmodels.EarthquakeViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuakeRecyclerViewAdapter adapter;
    //    @Inject EarthquakeService service;
    EarthquakeViewModel earthquakeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquakeViewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);
        earthquakeViewModel.init();
        earthquakeViewModel.getFeatures().observe(this, new Observer<List<Feature>>() {
            @Override
            public void onChanged(List<Feature> features) {
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuakeRecyclerViewAdapter(earthquakeViewModel.getFeatures().getValue());

        adapter.setListener(new QuakeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Feature feature) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("property",feature.getProperties());
                startActivity(i);
            }
        });

        recyclerView.setAdapter(adapter);

    }
}
