package com.seniorproject.kabigonb.mahanoipro.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.seniorproject.kabigonb.mahanoipro.R;


public class RequestListItem extends BaseCustomViewGroup {

    TextView list_request_name,list_request_serviceName,list_request_location;

    public RequestListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public RequestListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public RequestListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public RequestListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_request, this);
    }

    private void initInstances() {
        // findViewById here
        list_request_name = findViewById(R.id.list_request_name);
        list_request_serviceName = findViewById(R.id.list_request_serviceName);
        list_request_location =  findViewById(R.id.list_request_location);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width*1/4;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        setMeasuredDimension(width,height);

    }

    public void setList_request_name(String text)
    {
        list_request_name.setText(text);
    }

    public void setList_request_serviceName(String text)
    {
        list_request_serviceName.setText(text);
    }

    public void setList_request_location(String text)
    {
        list_request_location.setText(text);
    }
}
