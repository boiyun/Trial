package com.trial.base.widget.bottombar.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trial.base.R;
import com.trial.base.widget.bottombar.internal.RoundMessageView;
import com.trial.base.widget.bottombar.internal.Utils;


/**
 * 材料设计风格项
 */
public class MaterialItemView extends BaseTabItem {

    private final RoundMessageView mMessages;
    private final TextView mLabel;
    private final ImageView mIcon;

    private Drawable mDefaultDrawable;
    private Drawable mCheckedDrawable;

    private int mDefaultColor;
    private int mCheckedColor;


    private final int mTopMargin;
    private final int mTopMarginHideTitle;

    private boolean mHideTitle;
    private boolean mChecked;

    private final float mAnimatorValue = 1f;


    private boolean mTintIcon = true;

    public MaterialItemView(@NonNull Context context) {
        this(context, null);
    }

    public MaterialItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final float scale = context.getResources().getDisplayMetrics().density;

        mTopMargin = (int) (scale * 8);
        mTopMarginHideTitle = (int) (scale * 16);

        LayoutInflater.from(context).inflate(R.layout.item_material, this, true);

        mIcon = findViewById(R.id.icon);
        mLabel = findViewById(R.id.label);
        mMessages = findViewById(R.id.messages);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return MaterialItemView.class.getName();
    }

    public void initialization(String title, Drawable drawable, Drawable checkedDrawable, boolean tintIcon, int color, int checkedColor) {
        mTintIcon = tintIcon;

        mDefaultColor = color;
        mCheckedColor = checkedColor;

        if (mTintIcon) {
            mDefaultDrawable = Utils.tinting(drawable, mDefaultColor);
            mCheckedDrawable = Utils.tinting(checkedDrawable, mCheckedColor);
        } else {
            mDefaultDrawable = drawable;
            mCheckedDrawable = checkedDrawable;
        }

        mLabel.setText(title);
        mLabel.setTextColor(color);

        mIcon.setImageDrawable(mDefaultDrawable);

    }


    @Override
    public void setChecked(boolean checked) {
        if (mChecked == checked) {
            return;
        }

        mChecked = checked;

        if (mHideTitle) {
            mLabel.setVisibility(mChecked ? View.VISIBLE : View.INVISIBLE);
        }

        if (mChecked) {
            mIcon.setImageDrawable(mCheckedDrawable);
            mLabel.setTextColor(mCheckedColor);
        } else {
            mIcon.setImageDrawable(mDefaultDrawable);
            mLabel.setTextColor(mDefaultColor);
        }
    }

    @Override
    public void setMessageNumber(int number) {
        mMessages.setVisibility(View.VISIBLE);
        mMessages.setMessageNumber(number);
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
        mMessages.setVisibility(View.VISIBLE);
        mMessages.setHasMessage(hasMessage);
    }

    @Override
    public void setTitle(String title) {
        mLabel.setText(title);
    }

    @Override
    public void setDefaultDrawable(Drawable drawable) {
        if (mTintIcon) {
            mDefaultDrawable = Utils.tinting(drawable, mDefaultColor);
        } else {
            mDefaultDrawable = drawable;
        }

        if (!mChecked) {
            mIcon.setImageDrawable(mDefaultDrawable);
        }
    }

    @Override
    public void setSelectedDrawable(Drawable drawable) {
        if (mTintIcon) {
            mCheckedDrawable = Utils.tinting(drawable, mCheckedColor);
        } else {
            mCheckedDrawable = drawable;
        }

        if (mChecked) {
            mIcon.setImageDrawable(mCheckedDrawable);
        }
    }

    @Override
    public String getTitle() {
        return mLabel.getText().toString();
    }

    /**
     * 获取动画运行值[0,1]
     */
    public float getAnimValue() {
        return mAnimatorValue;
    }

    /**
     * 设置是否隐藏文字
     */
    public void setHideTitle(boolean hideTitle) {
        mHideTitle = hideTitle;

        LayoutParams iconParams = (LayoutParams) mIcon.getLayoutParams();

        if (mHideTitle) {
            iconParams.topMargin = mTopMarginHideTitle;
        } else {
            iconParams.topMargin = mTopMargin;
        }

        mLabel.setVisibility(mChecked ? View.VISIBLE : View.INVISIBLE);

        mIcon.setLayoutParams(iconParams);
    }

    /**
     * 设置消息圆形的颜色
     */
    public void setMessageBackgroundColor(@ColorInt int color) {
        mMessages.tintMessageBackground(color);
    }

    /**
     * 设置消息数据的颜色
     */
    public void setMessageNumberColor(@ColorInt int color) {
        mMessages.setMessageNumberColor(color);
    }
}
