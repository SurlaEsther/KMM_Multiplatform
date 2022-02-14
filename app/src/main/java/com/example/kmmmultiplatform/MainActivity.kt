package com.example.kmmmultiplatform
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    private val introSliderAdapter = IntroSliderAdapter(
    listOf(
        IntroSlide(
            "abc",
            "hello worlfhjmdbhkmn",
            R.drawable.house
        ),
        IntroSlide(
            "abc",
            "hello worldhujdhjhcdiunpiuv",
            R.drawable.wifi
        ),
        IntroSlide(
            "abc",
            "hellonu0ajp;oiwmdpioi",
            R.drawable.router
        ),
        IntroSlide(
            "abc",
            "helb80hwpivenlo",
            R.drawable.image
        )
    )
)


override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val  introSliderViewPager=findViewById<ViewPager2>(R.id.introSliderViewPager)
    introSliderViewPager.adapter=introSliderAdapter
    setupIndicators()
    setCurrentIndicator(0)
   introSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentIndicator(position)
        }
    })

    val buttonNext=findViewById<Button>(R.id.buttonNext)
    buttonNext.setOnClickListener{
        if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount)
        {
            introSliderViewPager.currentItem += 1
        }
        else {
            Intent(applicationContext,AnotherActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }
    }
    val textSkipIntro=findViewById<TextView>(R.id.textSkipIntro)
    textSkipIntro.setOnClickListener{
        Intent(applicationContext,AnotherActivity::class.java).also{
            startActivity(it)
            finish()
        }
    }

    }
    val indicatorsContainer= findViewById<LinearLayout>(R.id.indicatorsContainer)
    private fun setupIndicators()
    {
        val indicators=arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }

            indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index:Int)
    {
        val childCount=indicatorsContainer.childCount
        for(i in 0 until childCount)
        {
            val imageView= indicatorsContainer[i] as ImageView
            if(i==index)
            {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else
            {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}