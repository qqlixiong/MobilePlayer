package com.example.mobileplayer2.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 字体是AwesomeFont的 TextView
 */

public class AwesomeFontTextView extends TextView {

    public static final String AWESOME_FONT = "fonts/fontawesome-webfont.ttf";

    public AwesomeFontTextView(Context context) {
        this(context,null);
    }

    public AwesomeFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(), AWESOME_FONT));
    }
}
