package com.example.quakestalker.viewmodels;

import com.example.quakestalker.di.EarthquakeService;

import javax.inject.Inject;

public class EarthquakeViewModel {
    private EarthquakeService service;

    public EarthquakeViewModel(EarthquakeService service) {
        this.service = service;
    }
}
