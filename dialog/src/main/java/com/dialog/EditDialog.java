package com.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dialog.constant.DialogConstant;
import com.dialog.listener.IEditQuantityClickListener;

import java.math.BigDecimal;

/**
 * Created by HARRY on 2018/9/3 0003.
 */

public class EditDialog extends BaseDialogFragment {

    private TextView mTvMessage;
    private TextView mTvEnsure;
    private TextView mTvCancel;
    private EditText mEt;
    private BigDecimal maxValue;
    private BigDecimal initValue;
    private IEditQuantityClickListener listener;

    public static EditDialog newInstance(BigDecimal value, BigDecimal maxValue, String type) {
        EditDialog picker = new EditDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", value);
        bundle.putSerializable("maxValue", maxValue);
        bundle.putString("type", type);
        picker.setArguments(bundle);
        return picker;
    }

    public static EditDialog newInstance(String message, String left, String right,
                                         BigDecimal value, BigDecimal maxValue, String type) {
        EditDialog picker = new EditDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putString("left", left);
        bundle.putString("right", right);
        bundle.putSerializable("value", value);
        bundle.putSerializable("maxValue", maxValue);
        bundle.putString("type", type);
        picker.setArguments(bundle);
        return picker;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_edit_dialog;
    }

    @Override
    protected void init(View view) {
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
        mEt = (EditText) view.findViewById(R.id.et_cart_dialog);

        Bundle args = getArguments();
        if (args != null) {
            String type = args.getString("type");
            if (DialogConstant.NUMBER.equals(type)) {
                mEt.setInputType(InputType.TYPE_CLASS_NUMBER);
                initNumber(args);
            } else if (DialogConstant.NUMBERDECIMAL.equals(type)) {
                mEt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                initNumber(args);
            } else if (DialogConstant.ANY.equals(type)) {

            }

            if (args.containsKey("message")) {
                String message = args.getString("message");
                if (!TextUtils.isEmpty(message)) {
                    mTvMessage.setText(message);
                }
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
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                dismiss();
            }
        });

        mTvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mEt.getText().toString())) {
                    if (listener != null) {
                        listener.onEnsureClick(mEt.getText().toString());
                    }
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    dismiss();
                } else {
                    if (listener != null) {
                        listener.onEnsureNull();
                    }
                }
            }
        });
    }

    private void initNumber(Bundle args) {
        if (args.containsKey("maxValue")) {
            maxValue = (BigDecimal) args.getSerializable("maxValue");
        }
        if (args.containsKey("value")) {
            initValue = (BigDecimal) args.getSerializable("value");
            mEt.setText(initValue.toString());
            mEt.setSelection(initValue.toString().length());
        }

        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (!TextUtils.isEmpty(text)) {
                    BigDecimal bigText = new BigDecimal(text);
                    if (bigText.compareTo(new BigDecimal(0)) == DialogConstant.BIGDECIMAL_COMPARE_LESSTHAN) {
                        mEt.setText("0");
                        mEt.setSelection(1);
                    } else if (bigText.compareTo(maxValue) ==
                            DialogConstant.BIGDECIMAL_COMPARE_GREATERTHAN) {
                        mEt.setText(maxValue.toString());
                        mEt.setSelection(maxValue.toString().length());
                    }
                }
            }
        });
    }

    public void setOnClickListener(IEditQuantityClickListener listener) {
        this.listener = listener;
    }
}
