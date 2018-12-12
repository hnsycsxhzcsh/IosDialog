package com.iosdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dialog.EditQuantityDialog;
import com.dialog.EnsureDialog;
import com.dialog.LoadingDialog;
import com.dialog.SingleEnsureDialog;
import com.dialog.listener.IDialogEnsureClickListener;
import com.dialog.listener.IEditQuantityClickListener;
import com.dialog.listener.ISingleDialogEnsureClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mBtEnsure = findViewById(R.id.bt_ensure);
        Button mBtSingleEnsure = findViewById(R.id.bt_single_ensure);
        Button mBtLoading = findViewById(R.id.bt_loading);
        Button mBtEdit = findViewById(R.id.bt_edit_quantity);

        mBtEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EnsureDialog ensureDialog = new EnsureDialog();
                Bundle bundle = new Bundle();
                bundle.putString("message", "这是简单的dialog");
                ensureDialog.setArguments(bundle);
                ensureDialog.show(getSupportFragmentManager(), "ensure");

                ensureDialog.setOnClickListener(new IDialogEnsureClickListener() {
                    @Override
                    public void onEnsureClick() {
                        Toast.makeText(MainActivity.this, "点击了OK", Toast.LENGTH_SHORT).show();
                        ensureDialog.dismiss();
                    }

                    @Override
                    public void onCancelClick() {
                        Toast.makeText(MainActivity.this, "点击了Cancel", Toast.LENGTH_SHORT).show();
                        ensureDialog.dismiss();
                    }
                });
            }
        });

        mBtSingleEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SingleEnsureDialog ensureDialog = new SingleEnsureDialog();
                Bundle bundle = new Bundle();
                bundle.putString("message", "单个按钮的dialog.............");
                ensureDialog.setArguments(bundle);
                ensureDialog.show(getSupportFragmentManager(), "ensure");

                ensureDialog.setOnClickListener(new ISingleDialogEnsureClickListener() {
                    @Override
                    public void onEnsureClick() {
                        Toast.makeText(MainActivity.this, "点击了OK", Toast.LENGTH_SHORT).show();
                        ensureDialog.dismiss();
                    }
                });
            }
        });

        mBtLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog loadingDialog = new LoadingDialog();
                loadingDialog.show(getSupportFragmentManager(), "ensure");

            }
        });

        mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditQuantityDialog cartEditDialog = EditQuantityDialog.
                        newInstance(10,
                                "取消",
                                "确定");
                cartEditDialog.show(getSupportFragmentManager(), "ensure");
                cartEditDialog.setOnClickListener(new IEditQuantityClickListener() {
                    @Override
                    public void onEnsureClick(String value) {
                        Toast.makeText(MainActivity.this, "现在的值为" + value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelClick() {
                        Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onEnsureNull() {
                        super.onEnsureNull();
                        Toast.makeText(MainActivity.this, "数据不能为空", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
