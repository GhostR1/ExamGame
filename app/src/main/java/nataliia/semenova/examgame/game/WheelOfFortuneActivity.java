package nataliia.semenova.examgame.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import nataliia.semenova.examgame.R;

public class WheelOfFortuneActivity extends AppCompatActivity {
    private double probability = 0;

    private TextView tvResult;
    private boolean isButtonPressed = false;

    private boolean isPassed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_fortune);
        
        setScoreAndProbability();
        tvResult = findViewById(R.id.ts_result);

        findViewById(R.id.ib_start).setOnClickListener(view -> {
            if(!isButtonPressed) {
                startAnimation();
                isButtonPressed = true;
            }
        });
    }

    // Animation of fast switching a result
    private void startAnimation() {
        new CountDownTimer(3000, 100) {
            @Override
            public void onTick(long l) {
                if(isPassed) {
                    tvResult.setText(R.string.passed);
                    tvResult.setTextColor(Color.GREEN);
                } else {
                    tvResult.setText(R.string.failed);
                    tvResult.setTextColor(Color.RED);
                }
                isPassed = !isPassed;
            }

            @Override
            public void onFinish() {
                isPassed = getResult();
                if(isPassed) {
                    tvResult.setText(R.string.passed);
                    tvResult.setTextColor(Color.GREEN);
                } else {
                    tvResult.setText(R.string.failed);
                    tvResult.setTextColor(Color.RED);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(WheelOfFortuneActivity.this, FinalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("isPassed", isPassed);
                startActivity(intent);
            }
        }.start();
    }

    // Result depends on count of pastry player collected
    private boolean getResult() {
        double random = Math.floor(Math.random() * 100) + 1;
        return random <= probability * 100 && random > (1 - probability) * 100;
    }

    private void setScoreAndProbability() {
        int score = getIntent().getExtras().getInt("score", 0);
        ((TextView)findViewById(R.id.tv_count))
                .setText(String.format(Locale.getDefault(), "Collected pastry: %d", score));
        probability = ((float) score /232)/0.6;
        ((TextView)findViewById(R.id.tv_probability))
                .setText(String.format(Locale.getDefault(), "Probability of passing: %f", probability));
    }

    @Override
    public void onBackPressed() {
        // Commented for disable backPressed action
        // super.onBackPressed();
    }
}
