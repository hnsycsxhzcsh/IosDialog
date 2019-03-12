package com.dialog.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dialog.BaseDialogFragment;
import com.dialog.R;
import com.dialog.listener.IDialogEnsureClickListener;

/**
 * Created by HARRY on 2018/3/31 0031.
 */

public class EnsureDialog extends BaseDialogFragment {

    private TextView mTvCancel;
    private TextView mTvEnsure;
    private TextView mTvMessage;
    private IDialogEnsureClickListener listener;

    @Override
    public int getLayoutId() {
        return R.layout.layout_dialog_ensure;
    }

    @Override
    protected void init(View view) {
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey("message")) {
                String message = args.getString("message");
                mTvMessage.setText(message);
            }

            if (args.containsKey("left")) {
                String left = args.getString("left");
                if (!TextUtils.isEmpty(left)) {
                    mTvCancel.setText(left);
                }
            }

            if (args.containsKey("right")) {
                String right = args.getString("right");
                if (!TextUtils.isEmpty(right)) {
                    mTvEnsure.setText(right);
                }
            }
        }

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancelClick();
                }
            }
        });

        mTvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEnsureClick();
                }
            }
        });


    }

    public void setOnClickListener(IDialogEnsureClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        initWindowSize();
    }
}
