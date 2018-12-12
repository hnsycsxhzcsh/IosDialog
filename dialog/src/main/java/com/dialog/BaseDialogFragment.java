package com.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by HARRY on 2018/3/23 0023.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected static final int SIZE_DEFAULT = -1;
    protected static final int SIZE_LOADING = 1;

    protected static final float COLOR_DEFAULT = 1.0f;
    protected static final float COLOR_TRANSPARENT = 0.0f;

    private int size = -1;
    private float color = 1.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        init(view);
        initDialog();
        return view;
    }

    public abstract int getLayoutId();

    protected abstract void init(View view);

    private void initDialog() {
        //对话框内部背景设置透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //设置dialog弹出动画
//        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        //点击外部不消失
//        getDialog().setCanceledOnTouchOutside(false);

        //点击返回键不消失
//        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    return true;
//                }
//                return false;
//            }
//        });

        // 设置宽度为屏宽, 靠近屏幕底部,从底部弹出
//        Window window = getDialog().getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.gravity = Gravity.BOTTOM; // 紧贴底部
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
//        window.setAttributes(lp);
    }

    @Override
    public void onStart() {
        super.onStart();
        //修改长宽代码加上后会导致，edittext无法自动弹出软键盘，现在去掉
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            if (size == SIZE_LOADING) {
////                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.5), (int) (dm.widthPixels * 0.5));
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            } else {
//                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
//            }
//        }

        if (color != COLOR_DEFAULT) {
            //如果过不是默认颜色，就设置指定color背景颜色
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = color;

            window.setAttributes(windowParams);
        }
    }

    protected void initWindowSize(int size) {
        this.size = size;
        initWindowSize();
    }

    protected void initWindowSize() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            if (size == SIZE_LOADING) {
//                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.5), (int) (dm.widthPixels * 0.5));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }
    }

    protected void setOutsideColor(float color) {
        this.color = color;
    }


    //解决activity 和fragment onSaveInstanceState 后show报错问题,
    // 但是弹出dialog之后要点击两次返回键才推出，所以暂时不用此方法
//    @Override
//    public void show(FragmentManager manager, String tag) {
//        try {
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.add(this, tag).addToBackStack(null);
//            ft.commitAllowingStateLoss();
//        } catch (IllegalStateException e) {
//
//        }
//    }

//    boolean mIsStateAlreadySaved = false;
//    boolean mPendingShowDialog = false;
//
//    @Override
//    public void onResume() {
//        onResumeFragments();
//        super.onResume();
//    }
//
//    public void onResumeFragments() {
//        mIsStateAlreadySaved = false;
//        if (mPendingShowDialog) {
//            mPendingShowDialog = false;
//            showSnoozeDialog();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mIsStateAlreadySaved = true;
//    }

//    private void showSnoozeDialog() {
//        if (mIsStateAlreadySaved) {
//            mPendingShowDialog = true;
//        } else {
//            FragmentManager fm = getFragmentManager();
//            ResolveShowBugDialogFragment snoozeDialog = new ResolveShowBugDialogFragment();
//            snoozeDialog.show(fm, "BaseDialogFragment");
//        }
//    }
}
