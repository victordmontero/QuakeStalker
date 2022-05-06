package com.example.quakestalker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.push.Push;
import com.microsoft.appcenter.push.PushListener;
import com.microsoft.appcenter.push.PushNotification;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuakeRecyclerViewAdapter adapter;
    //    @Inject EarthquakeService service;
    EarthquakeViewModel earthquakeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Push.setListener(new PushListener() {
            @Override
            public void onPushNotificationReceived(Activity activity, PushNotification pushNotification) {
                String title = pushNotification.getTitle();
                String message = pushNotification.getMessage();

                if (message != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    if (title != null)
                        builder.setTitle(title);
                    builder.setMessage(message);

                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.show();
                }
            }
        });

        AppCenter.start(getApplication(),
                "1ff20481-a9cf-4c78-968f-245925ab8751",
                Analytics.class,
                Crashes.class,
                Push.class);

        earthquakeViewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);
        earthquakeViewModel.init();
        earthquakeViewModel.getFeatures().observe(this, new Observer<List<Feature>>() {
            @Override
            public void onChanged(List<Feature> features) {
                adapter.submitList(features);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuakeRecyclerViewAdapter();

        adapter.setListener(new QuakeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Feature feature) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("property", feature.getProperties());
                i.putExtra("geometry", feature.getGeometry());
                i.putExtra("longitude", feature.getGeometry().getCoordinates().get(0));
                i.putExtra("latitude", feature.getGeometry().getCoordinates().get(1));
                startActivity(i);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
