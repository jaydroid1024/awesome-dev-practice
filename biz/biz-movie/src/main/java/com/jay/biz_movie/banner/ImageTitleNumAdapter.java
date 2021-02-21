package com.jay.biz_movie.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jay.biz_movie.R;
import com.jay.biz_movie.entity.ActorsItem;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 自定义布局，图片+标题+数字指示器
 */
public class ImageTitleNumAdapter extends BannerAdapter<ActorsItem, ImageTitleNumAdapter.BannerViewHolder> {

    String moviePic = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p725871968.webp";

    public ImageTitleNumAdapter(List<ActorsItem> mDatas) {
        //设置数据，也可以调用banner提供的方法
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        //注意布局文件，item布局文件要设置为match_parent，这个是viewpager2强制要求的
        //或者调用BannerUtils.getView(parent,R.layout.biz_movie_banner_image_title_num);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biz_movie_banner_image_title_num, parent, false);
        return new BannerViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindView(BannerViewHolder holder, ActorsItem data, int position, int size) {
        //图片加载自己实现
        Glide.with(holder.itemView)
                .load(data.getRole_image())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        Glide.with(holder.itemView)
                .load(data.getActor_image())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.actor_image);


        holder.name.setText(data.getRole_name());

        if (data.getRole_nickname().isEmpty()) {
            holder.niceName.setText(data.getActor_name());
        } else {
            holder.niceName.setText(data.getRole_nickname());
        }

        holder.dsc.setText(data.getRole_descr());
        //可以在布局文件中自己实现指示器，亦可以使用banner提供的方法自定义指示器，目前样式较少，后面补充
        holder.numIndicator.setText((position + 1) + "/" + size);
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, actor_image;
        TextView name;
        TextView niceName;
        TextView dsc;
        TextView numIndicator;
        CardView cd_actor;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            cd_actor = view.findViewById(R.id.cd_actor);
            actor_image = view.findViewById(R.id.image_actor);
            imageView = view.findViewById(R.id.image);
            name = view.findViewById(R.id.tv_name);
            niceName = view.findViewById(R.id.tv_nickname);
            dsc = view.findViewById(R.id.tv_descr);
            numIndicator = view.findViewById(R.id.numIndicator);
        }
    }

}
