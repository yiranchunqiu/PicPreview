package com.pxz.pxzpreviewpic;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * 类说明：图片预览页面
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/9/3 14:48
 */
public class PicturePreviewActivity extends AppCompatActivity {
    private View vView;
    private PhotoViewPager vpImg;
    private ImageView ivLeft;
    private TextView tvTitle;
    /**
     * 适配器
     */
    private PicturePreviewAdapter picturePreviewAdapter;
    /**
     * 第几个
     */
    private int postion;
    /**
     * 图片列表
     */
    private ArrayList<String> stringUrl = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        //设置透明状态栏
        StatusBarUtils.setStatusBarFullTransparent(this);
        // 设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initGetAdData();
        initView();
        initNewAdapter();
        initOnClick();
    }

    private void initGetAdData() {
        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                postion = getIntent().getExtras().getInt("postion", 0);
                stringUrl = getIntent().getExtras().getStringArrayList("stringUrl");
            }
        }
    }

    private void initView() {
        vView = findViewById(R.id.v_view);
        vpImg = findViewById(R.id.vp_img);
        ivLeft = findViewById(R.id.iv_left);
        tvTitle = findViewById(R.id.tv_title);
    }

    private void initNewAdapter() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            vView.setVisibility(View.GONE);
        } else {
            vView.setVisibility(View.VISIBLE);
        }
        picturePreviewAdapter = new PicturePreviewAdapter(stringUrl, getApplicationContext());
        vpImg.setAdapter(picturePreviewAdapter);
        vpImg.setCurrentItem(postion);
        tvTitle.setText(MessageFormat.format("{0}/{1}", postion + 1, stringUrl.size()));
    }

    private void initOnClick() {
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        vpImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                postion = position;
                tvTitle.setText(MessageFormat.format("{0}/{1}", postion + 1, stringUrl.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        picturePreviewAdapter.setPhotoViewClickListener(new PicturePreviewAdapter.PhotoViewClickListener() {
            @Override
            public void OnLongListener(View view, final int position) {
                //长按保存
                AlertDialog.Builder builder = new AlertDialog.Builder(PicturePreviewActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否保存图片？");
                builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final String imagePath = SavePicUtil.getImagePath(stringUrl.get(position), PicturePreviewActivity.this);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (imagePath != null) {
                                            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
                                            if (bitmap != null) {
                                                SavePicUtil.saveImageToGallery(PicturePreviewActivity.this, bitmap);
                                                Toast.makeText(PicturePreviewActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(PicturePreviewActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(PicturePreviewActivity.this, "图片异常", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }
}