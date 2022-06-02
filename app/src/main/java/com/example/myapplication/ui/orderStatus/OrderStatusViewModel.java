package com.example.myapplication.ui.orderStatus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderStatusViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OrderStatusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is order fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}