package com.blueeagle.anydo.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.blueeagle.anydo.R;

public final class DialogFactory {

    public static Dialog createSimpleOkDialog(Context context, @StringRes int message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }
}
