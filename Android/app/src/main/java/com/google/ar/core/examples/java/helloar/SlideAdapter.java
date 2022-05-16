package com.google.ar.core.examples.java.helloar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.ar.core.examples.java.Model.Graffiti;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideAdapter extends PagerAdapter {
    Context context;
    List<Graffiti> graffitiList;
    LayoutInflater inflater;

    public SlideAdapter(Context context, List<Graffiti> graffitiList) {
        this.context = context;
        this.graffitiList = graffitiList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return graffitiList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.slide,container,false);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        Picasso.get().load(graffitiList.get(position).getImage()).into(imgslide);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

}
