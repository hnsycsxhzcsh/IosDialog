package com.dialog.listener;

/**
 * Created by HARRY on 2018/3/23 0023.
 */

public abstract class IEditQuantityClickListener {
    public abstract void onEnsureClick(String value);

    public abstract void onCancelClick();

    public void onEnsureNull() {

    }
}
