package com.example.lutemonbattle.images;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import com.example.lutemonbattle.R;

public class AttributeHomeImageManager {
    private final Context context;

    private static final String[][] ATTRIBUTE_MAPPING = {
            {"Fire", "ic_fire"},
            {"Water", "ic_water"},
            {"Wood", "ic_wood"},
            {"Rock", "ic_rock"},
            {"Wind", "ic_wind"},
            {"Light", "ic_light"},
            {"Dark", "ic_dark"}
    };

    public AttributeHomeImageManager(Context context) {
        this.context = context;
    }

    public void setAttributeIcon(ImageView imageView, String attribute) {
        int drawableResId = getDrawableResId(attribute);
        if (drawableResId != 0) {
            imageView.setImageResource(drawableResId);
        } else {
            // If not found, use default photo
            imageView.setImageResource(R.drawable.ic_default);
        }
    }
    @DrawableRes
    private int getDrawableResId(String attribute) {
        for (String[] mapping : ATTRIBUTE_MAPPING) {
            if (mapping[0].equalsIgnoreCase(attribute)) {
                return context.getResources().getIdentifier(
                        mapping[1],
                        "drawable",
                        context.getPackageName()
                );
            }
        }
        return 0;
    }
}
