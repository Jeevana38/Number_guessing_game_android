package com.example.number_guessing_game_afame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int score = 5;//setting maximum score to 5. can be customized
    private int attempts = 5;//monitor total number of attempts left to guess. can be customized
    private int random_number = createRandomNumber();//function to genereate random number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView scoreTextView = findViewById(R.id.score);//score component
        TextView statusTextView = findViewById(R.id.status);//status component
        EditText numberEditText = findViewById(R.id.number);//field to enter number
        Button submitButton = findViewById(R.id.submit);
        Button restartButton = findViewById(R.id.restart);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String user_string = numberEditText.getText().toString(); //retrieve user entered string
                if (!user_string.isEmpty()) {
                    int user_number = Integer.parseInt(user_string);

                    if (user_number == random_number) { //check if user's number is equal to random number

                     statusTextView.setText("Correct! you won. Play Again!");
                        scoreTextView.setText("score: "+String.valueOf(score));
                        attempts= 5;
                        score = 5;
                        random_number = createRandomNumber();
                    } else if (user_number < random_number) { //if user's number is less than system number
                        statusTextView.setText("Entered number is less than the Target");
                        scoreTextView.setText("");
                        attempts--;
                        score--;
                    } else {//if user number is greater than system number
                        statusTextView.setText("Entered number is greater than target");
                        scoreTextView.setText("");
                        attempts--;
                        score--;
                    }

                    if (attempts == 0) {
                        statusTextView.setText("OOPS! Attempts limit reached. Start Over!");
                        scoreTextView.setText("score: "+String.valueOf(score));
                        attempts = 5;
                        score = 5;
                        random_number= createRandomNumber();
                    }
                } else {
                    statusTextView.setText("Please enter valid number");
                    scoreTextView.setText("");
                }
            }
            });


        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset score, attempts left, and generate a new number
                score = 5;
                scoreTextView.setText("");
                statusTextView.setText("");
                attempts = 5;
                numberEditText.setText("");
                random_number = createRandomNumber();

            }
        });
    }
    private int createRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1; //generating random number in the given range
    }
}