package com.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dialog.constant.DialogConstant;
import com.dialog.listener.IEditQuantityClickListener;

import java.math.BigDecimal;

/**
 * Created by HARRY on 2018/9/3 0003.
 */

public class EditQuantityDialog extends BaseDialogFragment {

    private TextView mTvCancel;
    private TextView mTvEnsure;
    private TextView mTvMessage;
    private IEditQuantityClickListener listener;
    private EditText mEtCart;
    private BigDecimal maxValue = new BigDecimal(99999);
    private Button mBtMinus;
    private Button mBtPlus;

    public static EditQuantityDialog newInstance() {
        EditQuantityDialog picker = new EditQuantityDialog();
        return picker;
    }

    public static EditQuantityDialog newInstance(int value, String left, String right) {
        EditQuantityDialog picker = new EditQuantityDialog();
        Bundle bundle = new Bundle();
        bundle.putString("left", left);
        bundle.putString("right", right);
        bundle.putSerializable("value", value);
        picker.setArguments(bundle);
        return picker;
    }

    public static EditQuantityDialog newInstance(int value, BigDecimal maxValue) {
        EditQuantityDialog picker = new EditQuantityDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("value", value);
        bundle.putSerializable("maxValue", maxValue);
        picker.setArguments(bundle);
        return picker;
    }

    public static EditQuantityDialog newInstance(String message, int value, BigDecimal maxValue) {
        EditQuantityDialog picker = new EditQuantityDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("value", value);
        bundle.putSerializable("maxValue", maxValue);
        picker.setArguments(bundle);
        return picker;
    }

    public static EditQuantityDialog newInstance(String message, String left, String right,
                                                 int value, BigDecimal maxValue) {
        EditQuantityDialog picker = new EditQuantityDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putString("left", left);
        bundle.putString("right", right);
        bundle.putInt("value", value);
        bundle.putSerializable("maxValue", maxValue);
        picker.setArguments(bundle);
        return picker;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_edit_quantity_dialog;
    }

    @Override
    protected void init(View view) {
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvEnsure = (TextView) view.findViewById(R.id.tv_ensure);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
        mEtCart = (EditText) view.findViewById(R.id.et_cart_dialog);
        mBtMinus = (Button) view.findViewById(R.id.bt_quantity_minus);
        mBtPlus = (Button) view.findViewById(R.id.bt_quantity_plus);

        mEtCart.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Bundle args = getArguments();
        if (args != null) {
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

            if (args.containsKey("maxValue")) {
                maxValue = (BigDecimal) args.getSerializable("maxValue");
            }

            if (args.containsKey("value")) {
                int value = args.getInt("value");
                mEtCart.setText("" + value);
                mEtCart.setSelection(String.valueOf(value).toString().length());
            }
        }

        mEtCart.addTextChangedListener(new TextWatcher() {
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
                    if (bigText.compareTo(new BigDecimal(0)) == DialogConstant.BIGDECIMAL_COMPARE_EQUAL ||
                            bigText.compareTo(new BigDecimal(0)) == DialogConstant.BIGDECIMAL_COMPARE_LESSTHAN) {
                        mEtCart.setText("1");
                        mEtCart.setSelection(1);
                    } else if (bigText.compareTo(maxValue) ==
                            DialogConstant.BIGDECIMAL_COMPARE_GREATERTHAN) {
                        mEtCart.setText(maxValue.toString());
                        mEtCart.setSelection(maxValue.toString().length());
                    }
                }
            }
        });

        mBtMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mEtCart.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    BigDecimal bigValue = new BigDecimal(value);
                    if (bigValue.compareTo(new BigDecimal(1)) == DialogConstant.BIGDECIMAL_COMPARE_GREATERTHAN) {
                        String subtract = new BigDecimal(value).subtract(new BigDecimal(1)).toString();
                        mEtCart.setText(subtract);
                        mEtCart.setSelection(subtract.length());
                    }
                }
            }
        });

        mBtPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mEtCart.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    BigDecimal bigValue = new BigDecimal(value);
                    if (bigValue.compareTo(maxValue) == DialogConstant.BIGDECIMAL_COMPARE_LESSTHAN) {
                        String subtract = new BigDecimal(value).add(new BigDecimal(1)).toString();
                        mEtCart.setText(subtract);
                        mEtCart.setSelection(subtract.length());
                    }
                }
            }
        });

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
                if (!TextUtils.isEmpty(mEtCart.getText().toString())) {
                    if (listener != null) {
                        listener.onEnsureClick(mEtCart.getText().toString());
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

    public void setOnClickListener(IEditQuantityClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
