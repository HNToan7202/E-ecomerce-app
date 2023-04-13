package vn.iotstar.mylibrary;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SlideAnimation extends Animation {
    private int mFromHeight;
    private int mToHeight;
    private View mView;

    public SlideAnimation(View view, int mFromHeight, int mToHeight){
        mView = view;
        this.mFromHeight = mFromHeight;
        this.mToHeight = mToHeight;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return super.willChangeBounds();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        if(mView.getHeight() != mToHeight){
            newHeight = (int) (mFromHeight +(mToHeight-mFromHeight)*interpolatedTime);

        }
    }
}
