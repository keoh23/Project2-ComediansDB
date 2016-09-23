package com.example.owen.comediansdatabase;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Owen on 9/8/2016.
 */
public class MyDialogFragment extends DialogFragment{

    private EditText mComedianName;
    private EditText mComedianAge;
    private EditText mComedianNat;

    public MyDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newentry_frag, container);
        mComedianName = (EditText)view.findViewById(R.id.txt_your_name);
        mComedianAge = (EditText)view.findViewById(R.id.txt_your_age);
        mComedianNat = (EditText)view.findViewById(R.id.txt_your_nationality);

        SUCDatabaseHelper.addComedian(
                mComedianName.getText().toString(),
                mComedianAge.getText().toString(),
                mComedianNat.getText().toString());

//        getDialog().setTitle("Please enter your name");

        return view;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                SUCDatabaseHelper.addComedian(
                        mComedianName.getText().toString(),
                        mComedianAge.getText().toString(),
                        mComedianNat.getText().toString());

                if(getActivity() instanceof MainActivity) ((MainActivity)getActivity()).populateView();

                Log.e("LOG", editName.getText().toString());
                Log.e("LOG", editDetail.getText().toString());

            }
        });}
}