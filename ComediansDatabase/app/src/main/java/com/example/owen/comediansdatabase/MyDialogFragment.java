package com.example.owen.comediansdatabase;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Owen on 9/8/2016.
 */
public class MyDialogFragment extends DialogFragment{

    private EditText mEditText;

    public MyDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newentry_frag,
                container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Please enter your name");

        return view;
    }
}