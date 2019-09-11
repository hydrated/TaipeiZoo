package com.example.taipeizoo.dagger;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.taipeizoo.viewmodel.PlantViewModel;
import com.example.taipeizoo.viewmodel.ViewModelFactory;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ZooFieldViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsZooFieldViewModel(ZooFieldViewModel zooFieldViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PlantViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindPlantViewModel(PlantViewModel plantViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
