package com.example.cornerfinder.ui.hotspots;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HotspotsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HotspotsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hotspots fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}