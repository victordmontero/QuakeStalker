package com.example.quakestalker.di;


import com.example.quakestalker.models.Feature;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EarthquakeModule {

    List<Feature> featureList;

    public EarthquakeModule(List<Feature> featureList) {
        this.featureList = featureList;
    }

    @Provides
    @Singleton
    public List<Feature> provideFeatures()
    {
        return featureList;
    }
}
