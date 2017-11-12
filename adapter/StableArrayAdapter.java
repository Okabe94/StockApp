package com.example.okabe.nexttry.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> {

    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
    }
}
