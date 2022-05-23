package com.example.quakestalker.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quakestalker.R;
import com.example.quakestalker.databinding.ActivityMainBinding;
import com.example.quakestalker.di.DaggerEarthquakeComponent;
import com.example.quakestalker.di.EarthquakeComponent;
import com.example.quakestalker.di.EarthquakeModule;
import com.example.quakestalker.models.Feature;
import com.example.quakestalker.viewmodels.EarthquakeViewModel;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.push.Push;
import com.microsoft.appcenter.push.PushListener;
import com.microsoft.appcenter.push.PushNotification;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    RecyclerView recyclerView;
    QuakeRecyclerViewAdapter adapter;
    @Inject EarthquakeViewModel earthquakeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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

        EarthquakeComponent component = DaggerEarthquakeComponent.builder()
                .earthquakeModule(new EarthquakeModule(this))
                .build();
        component.inject(this);

        earthquakeViewModel.init();
        earthquakeViewModel.getFeatures().observe(this, new Observer<List<Feature>>() {
            @Override
            public void onChanged(List<Feature> features) {
                adapter.submitList(features);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = binding.recyclerView;
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

        requestPermissionsIfNecessary(new String[] {
                // if you need to show the current location, uncomment the line below
                Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}
