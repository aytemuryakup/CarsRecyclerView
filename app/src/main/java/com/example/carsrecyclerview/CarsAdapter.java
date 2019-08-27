package com.example.carsrecyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private ArrayList<CarsModel> carsModels = new ArrayList<>();
    private Context context;


    public CarsAdapter(Context context, ArrayList<CarsModel> carsModels){
        this.carsModels = carsModels;
        this.context = context;


    }


    @NonNull
    @Override
    public CarsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_list_item, parent,false);
        return new CarsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarsAdapter.ViewHolder holder, int position) {



        holder.car_name.setText(carsModels.get(position).getBASLIK());
        holder.car_desc.setText(carsModels.get(position).getICERK());
        Glide.with(context)
                .load("http://www.battalgazi.bel.tr/HaberResim/800/99992ff452f9-26ed-45ca-aa0d-0a49655c1ff9.jpg")
                .into(holder.car_image);
       // Picasso.get().load(carsModels.get(position).getFOTO()).into(holder.car_image);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(carsModels.get(position).getFOTO())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed( GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.car_image);

    }

    @Override
    public int getItemCount() {
        return carsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView car_image;
        private TextView car_name,car_desc;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            car_image = (ImageView)itemView.findViewById(R.id.car_image);
            car_name = (TextView) itemView.findViewById(R.id.car_name);
            car_desc = (TextView) itemView.findViewById(R.id.car_desc);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress_load_photo);
        }
    }
}
