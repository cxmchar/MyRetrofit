package demo.cxm.myretrofit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<Bean.ResultsBean> mresultsBeans;

    public MyAdapter(Context context,ArrayList<Bean.ResultsBean> resultsBeans){
        this.mcontext = context;
        this.mresultsBeans = resultsBeans;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.list_item,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Bean.ResultsBean getbeans = mresultsBeans.get(i);
        //加载图片
        viewHolder.tvTitle.setText(getbeans.getWho());
        viewHolder.tvDescription.setText(getbeans.getCreatedAt());
        Glide.with(mcontext)
                .load(getbeans.getUrl())
                //
                //.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                //
                .thumbnail(0.9f)
                .placeholder(R.mipmap.ic_launcher)
                //转化为drawable
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        viewHolder.img.setImageDrawable(resource);
                    }
                });
        //viewHolder.tvTitle.setText(getbeans.getWho());
        //viewHolder.tvDescription.setText(getbeans.getType());
        }

    //update,notify
    public void addListChapter(ArrayList<Bean.ResultsBean> getresultsBeans) {
        //uodate
        this.mresultsBeans.clear();
        this.mresultsBeans.addAll(getresultsBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mresultsBeans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvTitle;
        TextView tvDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_iv);
            tvTitle = itemView.findViewById(R.id.item_tv_name);
            tvDescription = itemView.findViewById(R.id.item_tv_detale);
        }
    }

}
