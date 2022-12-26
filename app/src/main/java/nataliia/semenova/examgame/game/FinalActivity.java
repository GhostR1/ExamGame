package nataliia.semenova.examgame.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nataliia.semenova.examgame.R;
import nataliia.semenova.examgame.game.cutscene.CutScene1Activity;

public class FinalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_final);

        boolean isPassed = getIntent().getBooleanExtra("isPassed", false);
        if(isPassed) {
            findViewById(R.id.tv_passed).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv_failed).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.ib_play).setOnClickListener(v -> {
            Intent intent = new Intent(FinalActivity.this, CutScene1Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        findViewById(R.id.ib_exit).setOnClickListener(v -> this.finish());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
