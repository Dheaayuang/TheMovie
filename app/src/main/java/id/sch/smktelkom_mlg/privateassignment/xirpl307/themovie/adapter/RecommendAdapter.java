package id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.NowFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.RecommendFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.ScrollingActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.model.Results;

/**
 * Created by Dhea on 5/14/2017.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> mlist;
    RecommendFragment recommendFragment;
    NowFragment nowFragment;
    Context context;
    private int lastposition = -1;

    public RecommendAdapter(RecommendFragment recommendfragment, ArrayList<Results> mlist, Context context) {
        this.mlist = mlist;
        this.recommendFragment = recommendfragment;
        this.context = context;
    }

    public RecommendAdapter(NowFragment nowFragment, ArrayList<Results> mlist, Context context) {
        this.mlist = mlist;
        this.nowFragment = nowFragment;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        ViewHolder viewHolder =new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Results results = mlist.get(position);
        holder.tvName.setText(results.title);
        holder.tvDesc.setText(results.overview);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", results.title);
                intent.putExtra("poster_path", results.backdrop_path);
                intent.putExtra("description", results.overview);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_text);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            cardView = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
