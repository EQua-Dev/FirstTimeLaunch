package com.example.onboardingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private Button mNextBtn;
    private Button mPrevBtn;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart){
            slideActions();
        }else {
            Intent intent = new Intent(this, Launched.class);
            startActivity(intent);
        }
    }

    private void slideActions() {
        setContentView(R.layout.activity_main);



//        if (firstStart){
//            slideActions();
//        }
//
//        SharedPreferences sharedPreferences =
//                PreferenceManager.getDefaultSharedPreferences(this);
//
//        if (!sharedPreferences.getBoolean(
//                SliderAdapter.COMPL
//        ))

        mSlideViewPager = findViewById(R.id.view_pager);
        mDotLayout = findViewById(R.id.dot_layout);

        mNextBtn = findViewById(R.id.button_next);
        mPrevBtn = findViewById(R.id.button_previous);

//       we initialize the created SliderAdapter class and it to a referral variable 'sliderAdapter'
        sliderAdapter = new SliderAdapter(this);

//       we then set the adapter to our viewPager
        mSlideViewPager.setAdapter(sliderAdapter);

//       we then call the addDotsIndicator method in the onCreate
        addDotsIndicator(0);

//       we then set the onPageChangeListener to our onCreate
        mSlideViewPager.addOnPageChangeListener(viewListener);

//        we set onClickListeners to our buttons
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                increases the array position of the slide by 1 when the next button is clicked to navigate to the next slide
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                reduces the array position of the slide by 1 when the prev button is clicked to navigate to the previous slide
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    //    this method creates only the number of dots needed according to our array size
    public void addDotsIndicator(int position) {

        mDots = new TextView[3];

//        we remove all the dot views from our layout else multiple dots will be created
        mDotLayout.removeAllViews();

//        we define loop for every textView in the mDots array less than its total size
        for (int i = 0; i < mDots.length; i++) {

//            we create a new tv and cast it to this activity
            mDots[i] = new TextView(this);

//             we then put our dots using a html text
            mDots[i].setText(Html.fromHtml("&#8226;"));

//             we set the textSize to 35
            mDots[i].setTextSize(35);

//            we set the color of the text to a color from our color res
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

//             we set the properties attached to the mDots array to the layout in the xml
            mDotLayout.addView(mDots[i]);

        }

//        we check if the length of the mDots array is greater than zero (which is definitely true)
        if (mDots.length > 0) {

//            we set the color of the current position to pure white
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }


    //    we set an OnPageListener to add effects to indicate when the slide is changed
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //    this method is to indicate the array position of the selected slide
        @Override
        public void onPageSelected(int position) {
//        we pass the current i in the addDotsIndicator to be the position
            addDotsIndicator(position);

//        we set the selected page position to the mCurrentPage variable
            mCurrentPage = position;

//        we check if the position of the current page is 0 meaning that the user is on the first page
            if (position == 0) {

//            we enable the nextButton for navigation to the next slide
                mNextBtn.setEnabled(true);
//            we disable the prevButton as there is no previous slide
                mPrevBtn.setEnabled(false);
//            we make the prevButton invisible to prevent the user from clicking it
                mPrevBtn.setVisibility(View.INVISIBLE);

//            we then set the text of the nextButton to 'Next'
                mNextBtn.setText("Next");
//            and set the text of the prevButton to empty
                mPrevBtn.setText("");

            }
//        we check if the position of the current page is equal to total array size (meaning it is the last slide)
//        we put (-1) because array positioning starts from 0
            else if (position == mDots.length - 1) {

//            we enable the nextButton for navigation to the next function
                mNextBtn.setEnabled(true);
//            we enable the prevButton as there are now previous slide
                mPrevBtn.setEnabled(true);
//            we make the prevButton visible
                mPrevBtn.setVisibility(View.VISIBLE);

//            we then set the text of the nextButton to 'Finish' as there is no more next slide
                mNextBtn.setText("Finish");
//            and set the text of the prevButton to 'Prev' to navigate the previous slides
                mPrevBtn.setText("Prev");

                mNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Launched.class);
                        startActivity(intent);
                    }
                });
            } else {

//            we enable the nextButton for navigation to the next function
                mNextBtn.setEnabled(true);
//            we enable the prevButton for navigation to the previous function
                mPrevBtn.setEnabled(true);
//            we make the prevButton visible
                mPrevBtn.setVisibility(View.VISIBLE);

//            we then set the text of the nextButton to 'Next' to navigate the next slides
                mNextBtn.setText("Next");
//            and set the text of the prevButton to 'Prev' to navigate the previous slides
                mPrevBtn.setText("Prev");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}


