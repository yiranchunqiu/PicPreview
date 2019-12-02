package com.pxz.pxzpreviewpic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import uk.co.senab.photoview.PhotoView;

/**
 * 类说明：图片预览适配器
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/9/3 14:49
 */
public class PicturePreviewAdapter  extends PagerAdapter {
    private List<String> imagesUrl;
    private Context context;
    private LayoutInflater inflater;
    private PhotoViewClickListener photoViewClickListener;

    public PicturePreviewAdapter(List<String> imagesUrl, Context context) {
        this.imagesUrl = imagesUrl;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (imagesUrl == null || imagesUrl.size() == 0) ? 0 : imagesUrl.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        String url = imagesUrl.get(position);
        View contentView = inflater.inflate(R.layout.item_picture_preview, container, false);
        final PhotoView photoView = contentView.findViewById(R.id.photoView);
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .skipMemoryCache(true)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .into(new SimpleTarget<Bitmap>(480, 800) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        //失败
                    }

                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        photoView.setImageBitmap(resource);
                    }
                });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (photoViewClickListener != null) {
                    photoViewClickListener.OnLongListener(v, position);
                }
                return false;
            }
        });
        (container).addView(contentView, 0);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setPhotoViewClickListener(PhotoViewClickListener photoViewClickListener) {
        this.photoViewClickListener = photoViewClickListener;
    }

    public interface PhotoViewClickListener {
        void OnLongListener(View view, int position);
    }
}