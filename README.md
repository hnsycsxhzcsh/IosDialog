# IosDialog
此项目是实现android应用dialog弹窗仿ios效果，如果有帮助到大家希望点下右上角Star，谢谢！
apk下载地址：https://github.com/hnsycsxhzcsh/IosDialog/blob/master/iosdialog.apk
效果图
#![image](https://github.com/hnsycsxhzcsh/IosDialog/blob/master/img/18tkr-gc4gb.gif)


一、项目的gradle可直接引用，方法为

1、根目录的build.gradle中设置

    allprojects {
        repositories {
            maven { url "https://jitpack.io" }
        }
    }

2、app的build.gradle中引用

implementation "com.github.hnsycsxhzcsh:IosDialog:V1.1"

二、实现方法，以EnsureDialog为例，其他dialog的实现请下载代码查看

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


具体代码实现方式可以浏览我的博客地址：
https://blog.csdn.net/m0_38074457/article/details/84979890
