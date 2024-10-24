package com.example.kostki;

import androidx.lifecycle.ViewModel;

public class RzutyCount extends ViewModel {
    private int count = 0;

    public int getCount(){
        return count;
    }
    public void incrementCount(){
        count++;
    }
    public void resetCount() {
        count = 0;
    }

}
