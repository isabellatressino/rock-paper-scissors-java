package com.isabellatressino.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.isabellatressino.myapplication.databinding.ActivityGameBinding;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ibScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ibScissors, "scissors");
            }
        });

        binding.ibRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ibRock, "rock");
            }
        });

        binding.ibPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(binding.ibPaper, "paper");
            }
        });
    }

    private void selectButton(ImageButton selectedButton, String option) {
        binding.ibScissors.setSelected(false);
        binding.ibScissors.setEnabled(false);

        binding.ibRock.setSelected(false);
        binding.ibRock.setEnabled(false);

        binding.ibPaper.setSelected(false);
        binding.ibPaper.setEnabled(false);

        selectedButton.setSelected(true);

        checkVictory(option);
    }

    private String ramdomChoice() {
        String[] options = {"rock", "scissors", "paper"};
        int ramdomNumber = new Random().nextInt(3);
        switch (ramdomNumber) {
            case 0:
                binding.ibGuess.setImageResource(R.drawable.rock);
                break;
            case 1:
                binding.ibGuess.setImageResource(R.drawable.scissors);
                break;
            case 2:
                binding.ibGuess.setImageResource(R.drawable.paper);
                break;
        }

        return options[ramdomNumber];
    }

    private void checkVictory(String userChoice) {
        String appChoice = ramdomChoice();

        String txt = "";

        if ((userChoice.equals("rock") && appChoice.equals("scissors")) ||
                (userChoice.equals("scissors") && appChoice.equals("paper")) ||
                (userChoice.equals("paper") && appChoice.equals("rock"))) {
            txt = "Você Ganhou";
        } else if ((userChoice.equals("rock") && appChoice.equals("paper")) ||
                (userChoice.equals("scissors") && appChoice.equals("rock")) ||
                (userChoice.equals("paper") && appChoice.equals("scissors"))) {
            txt = "Você Perdeu";
        } else {
            txt = "Empate";
        }

        binding.tvGameResult.setText(txt);
        binding.ibReplay.setVisibility(View.VISIBLE);

    }


}