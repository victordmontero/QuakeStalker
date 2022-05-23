package com.example.quakestalker.ui;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quakestalker.R;
import com.example.quakestalker.databinding.ActivityDetailBinding;
import com.example.quakestalker.models.Geometry;
import com.example.quakestalker.models.Properties;
import com.google.android.gms.maps.GoogleMap;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private GoogleMap map;
    private Properties properties;
    private Geometry geometry;
    private List<Double> coordinates;

    private ActivityDetailBinding binding;

    private org.osmdroid.views.MapView osmMap = null;
    private CompassOverlay mCompassOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        setViews();
        setMap();
    }

    private void setMap() {
        binding.osmMap.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        IMapController mapController = binding.osmMap.getController();
        mapController.setZoom(8.0);
        mapController.setCenter(new GeoPoint(coordinates.get(1), coordinates.get(0)));

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        String title = properties.getTitle();
        String description = properties.getDetail().toString();
        OverlayItem overlay = new OverlayItem(title, description, new GeoPoint(coordinates.get(1), coordinates.get(0)));
        //overlay.setMarker(getDrawable(android.R.drawable.ic_dialog_map));
        items.add(overlay); // Lat/Lon decimal degrees

//the overlay
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                }, this);
        mOverlay.setFocusItemsOnTap(true);

        binding.osmMap.getOverlays().add(mOverlay);
    }

    private void setViews() {
        properties = getIntent().getParcelableExtra("property");
        geometry = getIntent().getParcelableExtra("geometry");
        coordinates = new ArrayList<Double>();
        coordinates.add(getIntent().getDoubleExtra("longitude", 0));
        coordinates.add(getIntent().getDoubleExtra("latitude", 0));

        Double mag = (double) Math.round(properties.getMag() * 100.0) / 100.0;
        binding.magnitudeDetail.setText(mag.toString());
        binding.placeDetail.setText(properties.getPlace());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MM/yy");
        String date = dateFormat.format(new Date(properties.getTime()));

        binding.dateDetail.setText(date);
        binding.osmMap.setTileSource(TileSourceFactory.MAPNIK);
//        binding.osmMap.setBuiltInZoomControls(true);
        //binding.osmMap.setMultiTouchControls(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.osmMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.osmMap.onPause();
    }
}
