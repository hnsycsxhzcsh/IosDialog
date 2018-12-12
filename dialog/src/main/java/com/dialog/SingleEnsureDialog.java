package com.dialog;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.dialog.listener.ISingleDialogEnsureClickListener;

/**
 * Created by HARRY on 2018/3/23 0023.
 */

public class SingleEnsureDialog extends BaseDialogFragment {

    private TextView mTvEnsure;
    private ISingleDialogEnsureClickListener listener;
    private TextView mTvMessage;

    @Override
    public int getLayoutId() {
        return R.layout.layout_single_dialog_ensure;
    }

    @Override
    protected void init(View view) {
        mTvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);

        Bundle args = getArguments();
        String message = args.getString("message");

        if (args.containsKey("color")) {
            int color = args.getInt("color");
            mTvMessage.setTextColor(getResources().getColor(color));
        }

        mTvMessage.setText(Html.fromHtml(message));

        mTvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEnsureClick();
                    dismiss();
                }
            }
        });
    }

    public void setOnClickListener(ISingleDialogEnsureClickListener listener) {
        this.listener = listener;
    }
}
