package com.teamshunya.silencio.Classes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by himanshusingh on 17/03/17.
 */

public class CustomFontTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context) {
        super(context);

        applyCustomFont(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {
        /*
        * information about the TextView textStyle:
        * http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
        */
        switch (textStyle) {
            case Typeface.BOLD: // bold
//                return FontCache.getTypeface("SourceSansPro-Bold.ttf", context);
                return Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");

            case Typeface.ITALIC: // italic
//                return FontCache.getTypeface("SourceSansPro-Italic.ttf", context);
                return Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");

            case Typeface.BOLD_ITALIC: // bold italic
//                return FontCache.getTypeface("SourceSansPro-BoldItalic.ttf", context);
                return Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");

            case Typeface.NORMAL: // regular
            default:
//                return FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
                return Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
        }
    }
}
