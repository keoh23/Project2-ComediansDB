package com.example.owen.comediansdatabase;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Owen on 9/8/2016.
 */
public class MyAlertDialogFragment extends DialogFragment {

    public MyAlertDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Alert!");
        alertDialog.setMessage("This is a notication dialog");
        return alertDialog.create();
    }
}
