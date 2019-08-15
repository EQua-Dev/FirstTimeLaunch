package com.example.onboardingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

//  we extend the PagerAdapter to the SliderAdapter class
public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

//    we create a constructor of the class and pass the context of this class in order to enable us refer this class context in another class
    public SliderAdapter (Context context){
        this.context = context;
    }

//    we create an array to contain the icons of all the slides
    public int[] slide_images = {

            R.drawable.img_eat,
            R.drawable.img_sleep,
            R.drawable.img_code
    };

//     we create an array to contain the headings of all the slides
    public String[] slide_headings = {

            "EAT" ,
            "SLEEP" ,
            "CODE"
    };

//     we create an array to contain the descriptions of all the slides

    public String[] slide_description = {

            "this is a dummy description, another dummy sentence, then the third dummy statement, more dummy statement" +
                    "final dummy statement",
            "this is a dummy description, another dummy sentence, then the third dummy statement, more dummy statement" +
                    "final dummy statement",
            "this is a dummy description, another dummy sentence, then the third dummy statement, more dummy statement" +
                    "final dummy statement"
    };

//    we return the number of slide headings in our headings array
    @Override
    public int getCount() {
        return slide_headings.length;
    }


//    we indicate that our main view for the sliders will contain a relative layout
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }


//    this method inflates all the data/ slide effects into this adapter
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

//        we create view variables and cast them to the slide_layout
        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        TextView slideDescription = view.findViewById(R.id.slide_desc);

//        we set the cast image to the images array and pass the current position of the current slide for the needed details to be cast to it and selected automatically
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);

//        we add the view of the slide_layout to the container of this method
        container.addView(view);

        return view;
    }

//    this method ensures that once we get to the last slide we are prevented from getting an error
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

//        we set that in this method the object which is the relative layout is removed
        container.removeView((RelativeLayout)object);
    }
}
