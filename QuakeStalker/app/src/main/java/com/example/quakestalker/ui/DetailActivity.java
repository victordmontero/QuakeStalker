package com.example.quakestalker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quakestalker.R;
import com.example.quakestalker.models.Geometry;
import com.example.quakestalker.models.Properties;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private GoogleMap map;
    private Properties properties;
    private Geometry geometry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setViews();
        setMap();
    }

    private void setMap() {
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setViews() {
        properties = getIntent().getParcelableExtra("property");
        geometry = getIntent().getParcelableExtra("geometry");

        ((TextView) findViewById(R.id.magnitude_detail))
                .setText(properties.getMag().toString());

        ((TextView) findViewById(R.id.place_detail)).setText(properties.getPlace());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MM/yy");
        String date = dateFormat.format(new Date(properties.getTime()));

        ((TextView) findViewById(R.id.date_detail)).setText(date);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //earthquake coordinates
        LatLng earthquake = new LatLng(geometry.getCoordinates().get(0),
                geometry.getCoordinates().get(1));
        map.addMarker(new MarkerOptions().position(earthquake).title(properties.getMag().toString()));
        map.moveCamera(CameraUpdateFactory.newLatLng(earthquake));

        map.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
    }
}
