package com.example.quakestalker.di;

import com.example.quakestalker.models.Feature;
import com.example.quakestalker.ui.MainActivity;
import com.example.quakestalker.viewmodels.EarthquakeViewModel;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Component
public interface EarthquakeComponent {

    EarthquakeService getEarthquakeService();

    void inject(EarthquakeViewModel target);

}
