package com.isabellatressino.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.isabellatressino.myapplication.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ivScissors);
            }
        });

        binding.ivRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ivRock);
            }
        });

        binding.ivPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ivPaper);
            }
        });
    }

    private void selectButton(ImageButton selectedButton) {
        binding.ivScissors.setSelected(false);
        binding.ivRock.setSelected(false);
        binding.ivPaper.setSelected(false);

        selectedButton.setSelected(true);
    }
}