package com.example.quakestalker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quakestalker.di.DaggerEarthquakeComponent;
import com.example.quakestalker.di.EarthquakeComponent;
import com.example.quakestalker.di.EarthquakeService;
import com.example.quakestalker.models.Feature;

import java.util.List;

import javax.inject.Inject;

public class EarthquakeViewModel extends AndroidViewModel {
    @Inject EarthquakeService service;
    private MutableLiveData<List<Feature>> features;

    public EarthquakeViewModel(@NonNull Application application) {
        super(application);
//        service = new EarthquakeService();
        EarthquakeComponent component = DaggerEarthquakeComponent.create();
        component.inject(this);
    }

    public void init()
    {
        if(features != null)
            return;

        features = new MutableLiveData<>();
        features.setValue(service.queryEarthquakes());
    }

    public LiveData<List<Feature>> getFeatures(){
        return features;
    }

}
