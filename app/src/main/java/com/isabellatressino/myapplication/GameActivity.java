package com.isabellatressino.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.isabellatressino.myapplication.databinding.ActivityGameBinding;
import com.isabellatressino.myapplication.databinding.CustomDialogBinding;

import java.util.Objects;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private int userScore = 0;
    private int appScore = 0;
    private int round = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setChoiceListeners();

        binding.ibReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newRound();
            }
        });

        binding.ibPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void setChoiceListeners() {
        View.OnClickListener choiceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == binding.ibScissors) {
                    selectButton(binding.ibScissors, "scissors");
                } else if (v == binding.ibRock) {
                    selectButton(binding.ibRock, "rock");
                } else if (v == binding.ibPaper) {
                    selectButton(binding.ibPaper, "paper");
                }
            }
        };

        binding.ibScissors.setOnClickListener(choiceListener);
        binding.ibRock.setOnClickListener(choiceListener);
        binding.ibPaper.setOnClickListener(choiceListener);
    }


    private void changeStatesButtons(Boolean isSelected, Boolean isEnabled) {
        binding.ibScissors.setSelected(isSelected);
        binding.ibScissors.setEnabled(isEnabled);

        binding.ibRock.setSelected(isSelected);
        binding.ibRock.setEnabled(isEnabled);

        binding.ibPaper.setSelected(isSelected);
        binding.ibPaper.setEnabled(isEnabled);
    }

    private void resetGame() {
        binding.ibGuess.setImageResource(R.drawable.guess);
        binding.tvGameResult.setText("");
        binding.ibReplay.setVisibility(View.GONE);

        changeStatesButtons(false, true);
        updateScoreRound();
    }

    private void newRound() {
        round++;
        resetGame();
    }

    private void newGame() {
        round = 1;
        appScore = 0;
        userScore = 0;
        resetGame();
    }

    private void selectButton(ImageButton selectedButton, String option) {
        changeStatesButtons(false, false);

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
            userScore++;
        } else if ((userChoice.equals("rock") && appChoice.equals("paper")) ||
                (userChoice.equals("scissors") && appChoice.equals("rock")) ||
                (userChoice.equals("paper") && appChoice.equals("scissors"))) {
            txt = "Você Perdeu";
            appScore++;
        } else {
            txt = "Empate";
            appScore++;
            userScore++;
        }

        binding.tvGameResult.setText(txt);
        binding.ibReplay.setVisibility(View.VISIBLE);
        updateScoreRound();
    }

    private void updateScoreRound() {
        binding.tvAppScore.setText(Integer.toString(appScore));
        binding.tvPlayerScore.setText(Integer.toString(userScore));
        binding.tvRoundNumber.setText(Integer.toString(round));
    }

    private void showCustomDialog() {
        CustomDialogBinding binding = CustomDialogBinding.inflate(LayoutInflater.from(this));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(binding.getRoot());

        AlertDialog alertDialog = builder.create();

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawableResource(R.drawable.dialog_shape);

        binding.ibCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        binding.btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                newGame();
            }
        });

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });

        alertDialog.show();
    }
}
