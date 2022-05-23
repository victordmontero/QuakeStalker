package com.example.quakestalker.di;


import androidx.lifecycle.ViewModelProviders;

import com.example.quakestalker.models.Feature;
import com.example.quakestalker.ui.MainActivity;
import com.example.quakestalker.viewmodels.EarthquakeViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EarthquakeModule {

    MainActivity activity;

    public EarthquakeModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public EarthquakeViewModel provideEarthquakeViewModel(EarthquakeService service){
        EarthquakeViewModel viewModel = ViewModelProviders
                .of(activity)
                .get(EarthquakeViewModel.class);
        viewModel.setService(service);
        return viewModel;
    }

}
