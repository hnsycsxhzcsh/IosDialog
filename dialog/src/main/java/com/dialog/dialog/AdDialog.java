package com.dialog.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dialog.BaseDialogFragment;
import com.dialog.R;
import com.dialog.listener.IAdDialogClickListener;

/**
 * Created by HARRY on 2019/3/12 0012.
 */

public class AdDialog extends BaseDialogFragment {

    private ImageView mIvAd;
    private RelativeLayout mRlCancel;
    private IAdDialogClickListener listener;

    @Override
    public void onStart() {
        super.onStart();
        initWindowSize(SIZE_FULL_WINDOW);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ad_layout;
    }

    @Override
    protected void init(View view) {
        mRlCancel = view.findViewById(R.id.rl_cancel);
        mIvAd = view.findViewById(R.id.iv_ad);

        Bundle args = getArguments();
        if (args.containsKey("image")) {
            int image = args.getInt("image");
            mIvAd.setImageResource(image);
        }

        if (args.containsKey("url")) {
            String url = args.getString("url");
            loadImageView(getContext(), url, mIvAd);
        }

        mIvAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPicClick();
                    dismiss();
                }
            }
        });

        mRlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancelClick();
                    dismiss();
                }
            }
        });
    }

    public void loadImageView(Context context, String url, ImageView iv) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (context instanceof Activity) {
                    if (!((Activity) context).isDestroyed()) {
                        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
                    }
                } else {
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
                }
            } else {
                Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
            }
        }
    }

    public void setOnDialogClickListener(IAdDialogClickListener listener) {
        this.listener = listener;
    }
}
