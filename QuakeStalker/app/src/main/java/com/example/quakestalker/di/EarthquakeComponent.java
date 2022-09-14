package com.example.quakestalker.di;

import com.example.quakestalker.ui.MainActivity;

import dagger.Component;

@Component(modules = EarthquakeModule.class)
public interface EarthquakeComponent {

    void inject(MainActivity activity);

}
