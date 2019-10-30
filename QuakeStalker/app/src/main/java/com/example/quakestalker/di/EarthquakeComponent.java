package com.example.quakestalker.di;

import com.example.quakestalker.models.Feature;
import com.example.quakestalker.ui.MainActivity;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Singleton
@Component(modules = {EarthquakeModule.class})
public interface EarthquakeComponent {
    void inject(MainActivity target);

}
