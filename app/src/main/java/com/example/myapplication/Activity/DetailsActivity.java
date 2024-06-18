package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailBinding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.domain.PopularDomain;

public class DetailsActivity extends AppCompatActivity {
    private @NonNull ActivityDetailBinding binding;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBundles();
        managmentCart=new ManagmentCart(this);
        statusBarColor();
    }

    private void statusBarColor() {
        Window window = DetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailsActivity.this,R.color.white));
    }
    private void getBundles() {
        object=(PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.Itempic);

        binding.Titletxt.setText(object.getTitle());
        binding.Pricetxt.setText("$"+object.getPrice());
        binding.descriptionTxt.setText(object.getDescription());
        binding.Reviewtxt.setText(object.getReview()+" ");
        binding.Ratingtxt.setText(object.getScore()+ " ");

        binding.addToCartBtn.setOnClickListener(v -> {
            object.setNumberInChart(numberOrder);
            managmentCart.insertFood(object);
        });
        binding.backbtn.setOnClickListener(v -> finish());
    }
}
