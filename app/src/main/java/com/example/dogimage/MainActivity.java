package com.example.dogimage;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.dogimage.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    MainViewModel mainViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.loadImageDog();

        mainViewModel.getIsError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if (isError) {
                    Toast.makeText(MainActivity.this, "Ошибка получения данных", Toast.LENGTH_LONG).show();
                }
            }
        });
        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading) binding.progressbar.setVisibility(View.VISIBLE);
                else {
                    binding.progressbar.setVisibility(View.GONE);
                }
            }
        });
        mainViewModel.getDogImage().observe(this, new Observer<DogImage>() {
            @Override
            public void onChanged(DogImage dogImage) {
                /*Picasso.get()
                        .load(dogImage.getMessage())
                        .into(binding.image);
*/
                Glide.with(MainActivity.this)

                        .load(dogImage.getMessage())
                        .optionalCenterCrop()

                        .transform(new CircleCrop())
                        .into(binding.image);
                String ss = dogImage.getMessage();
                String mass = ss.replaceAll("/"," ");
                String[] s= mass.split(" ");
                //Log.d(TAG,mass);

                binding.tvInfo.setText( s[s.length-2]);
                Log.d(TAG, dogImage.toString());
            }
        });

        binding.btnNextImage.setOnClickListener(v -> {
            mainViewModel.loadImageDog();
        });

        //loadImageDog();
    }


}