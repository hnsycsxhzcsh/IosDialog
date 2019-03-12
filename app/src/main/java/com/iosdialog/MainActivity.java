package com.iosdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dialog.dialog.AdDialog;
import com.dialog.dialog.EditDialog;
import com.dialog.dialog.EditQuantityDialog;
import com.dialog.dialog.EnsureDialog;
import com.dialog.dialog.LoadingDialog;
import com.dialog.dialog.SingleEnsureDialog;
import com.dialog.constant.DialogConstant;
import com.dialog.listener.IAdDialogClickListener;
import com.dialog.listener.IDialogEnsureClickListener;
import com.dialog.listener.IEditQuantityClickListener;
import com.dialog.listener.ISingleDialogEnsureClickListener;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mBtEnsure = findViewById(R.id.bt_ensure);
        Button mBtSingleEnsure = findViewById(R.id.bt_single_ensure);
        Button mBtLoading = findViewById(R.id.bt_loading);
        Button mBtEditQuantity = findViewById(R.id.bt_edit_quantity);
        Button mBtEdit = findViewById(R.id.bt_edit);

        showAdDialog();

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

        mBtEditQuantity.setOnClickListener(new View.OnClickListener() {
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

        mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog editDialog = EditDialog.newInstance(new BigDecimal(11), new BigDecimal(100), DialogConstant.NUMBER);
                editDialog.show(getSupportFragmentManager(), "ensure");
                editDialog.setOnClickListener(new IEditQuantityClickListener() {
                    @Override
                    public void onEnsureClick(String value) {
                        Toast.makeText(MainActivity.this, "现在的值是" + value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelClick() {
                        Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    /**
     * 显示广告dialog
     */
    private void showAdDialog() {
        String url = "http://d.lanrentuku.com/down/png/1311/origami_birds/bird_yellow.png";

        AdDialog dialog = new AdDialog();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
//        bundle.putInt("image", R.mipmap.ad);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "ensure");

        dialog.setOnDialogClickListener(new IAdDialogClickListener() {
            @Override
            public void onPicClick() {
                Toast.makeText(MainActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelClick() {
                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
