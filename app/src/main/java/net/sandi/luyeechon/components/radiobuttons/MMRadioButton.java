package net.sandi.luyeechon.components.radiobuttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import net.sandi.luyeechon.utils.MMFontUtils;


/**
 * Created by aung on 6/25/16.
 */
public class MMRadioButton extends RadioButton {

    public MMRadioButton(Context context) {
        super(context);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }
}
