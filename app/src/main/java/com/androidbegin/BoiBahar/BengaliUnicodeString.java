package com.androidbegin.BoiBahar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class BengaliUnicodeString {
    static Typeface tf;

    public static void getBengaliUTF(Context ctx, String text, View v) {
        if (text.equals(null)) {
            text = "";
        }
        GSUB.text = text.toCharArray();
        GSUB.newlength = text.length();

        int ll = 0;
        int ret = 2;

        while (((ret = GSUB.mygsub(GSUB.newlength)) == 2) && (ll < 10))
            ;
        text = new String(GSUB.text, 0, GSUB.newlength);
        text = new String(Shape.reorder(text.toCharArray()));
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(),
                    "solaimanlipinormal.ttf");

            if ((v instanceof TextView)) {
                TextView tv = (TextView) v;
                tv.setTypeface(tf);
                tv.setText(text);
            }
            if ((v instanceof Button)) {
                Button bv = (Button) v;
                bv.setTypeface(tf);
                bv.setText(text);
            }
            if ((v instanceof CheckBox)) {
                CheckBox cv = (CheckBox) v;
                cv.setTypeface(tf);
                cv.setText(text);
            }
            if ((v instanceof RadioButton)) {
                RadioButton rv = (RadioButton) v;
                rv.setTypeface(tf);
                rv.setText(text);
            }
        } catch (Exception localException) {
        }
    }
}
