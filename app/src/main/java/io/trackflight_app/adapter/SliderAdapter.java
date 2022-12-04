package io.trackflight_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import io.trackflight_app.R;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    int[] images;
    public  SliderAdapter(int[] images){
        this.images=images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imageslideritem,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder (ViewHolder viewHolder, int position) {

        ((Holder) viewHolder).imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public static class  Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public  Holder(View itemView){
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.image_view);

        }



    }
}
