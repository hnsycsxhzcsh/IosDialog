package com.dialog;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by HARRY on 2018/8/29 0029.
 */

public class LoadingDialog extends BaseDialogFragment {

    private RelativeLayout mRlMain;

    @Override
    public int getLayoutId() {
        return R.layout.layout_dialog_loading;
    }

    @Override
    protected void init(View view) {
//        setOutsideColor(COLOR_TRANSPARENT);
    }

    @Override
    public void onStart() {
        super.onStart();
        initWindowSize(SIZE_LOADING);
    }
}
