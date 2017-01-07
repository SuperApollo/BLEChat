package com.example.administrator.apolloblechat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.apolloblechat.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/***
 * ImageLoader
 *
 * @author admin
 */
public class ImageLoaderUtils {
    private ImageLoadingListener imageLoadingListener = new AnimateFirstDisplayListener();
    private ImageLoader imageLoader;
    private DisplayImageOptions displayImageOptions, displayCircleImageOptions;
    private ImageLoaderConfiguration config;
    private Context context;
    private static ImageLoaderUtils instance;

    /**
     * Returns singleton class instance
     */
    public static ImageLoaderUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (ImageLoaderUtils.class) {
                if (instance == null) {
                    instance = new ImageLoaderUtils(context);
                }
            }
        }
        return instance;
    }

    /***
     * @param context
     */
    public ImageLoaderUtils(Context context) {
        super();
        this.context = context;
        initConfig();
    }

    /****
     *
     */
    @SuppressWarnings("deprecation")
    private void initConfig() {
        // TODO Auto-generated method stub
        imageLoader = ImageLoader.getInstance();
        displayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.icon_blechat_logo)
                .showImageForEmptyUri(R.mipmap.icon_blechat_logo)
                .showImageOnFail(R.mipmap.icon_blechat_logo).cacheInMemory()
                .cacheOnDisc().displayer(new SimpleBitmapDisplayer())
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();

        config = new ImageLoaderConfiguration.Builder(
                context.getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCacheFileCount(60)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        if (!imageLoader.isInited())
            imageLoader.init(config);
    }

    /****
     * @param url
     * @param imageView
     */
    public void loadImageView(String url, ImageView imageView) {
        imageLoader.displayImage(url, imageView, displayImageOptions,
                imageLoadingListener);
    }

    public void loadLocalCircleImageView(String url, ImageView imageView) {
        imageLoader.displayImage(url, imageView, displayCircleImageOptions);
    }

    public void loadLocalImageView(String url, ImageView imageView) {
        Bitmap bm = BitmapFactory.decodeFile(url);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bm);
    }

    /***
     * @author admin
     */
    public class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            // TODO Auto-generated method stub
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri
     * @param imageView
     */
    public void displayFromSDCard(String uri, ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        imageLoader.displayImage("file://" + uri, imageView);
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName 图片名称，带后缀的，例如：1.png
     * @param imageView
     */
    public void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        imageLoader.displayImage("assets://" + imageName,
                imageView);
    }

    /**
     * 从drawable中异步加载本地图片
     *
     * @param imageId
     * @param imageView
     */
    public void displayFromDrawable(int imageId, ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        imageLoader.displayImage("drawable://" + imageId,
                imageView);
    }

    /**
     * 从mipmap中异步加载本地图片
     *
     * @param imageId
     * @param imageView
     */
    public void displayFromMipmap(int imageId, ImageView imageView) {
        imageLoader.displayImage("mipmap://" + imageId,
                imageView);
    }

    /**
     * 从内容提提供者中抓取图片
     */
    public void displayFromContent(String uri, ImageView imageView) {
        // String imageUri = "content://media/external/audio/albumart/13"; //
        // from content provider
        imageLoader.displayImage("content://" + uri, imageView);
    }
}
