package nataliia.semenova.examgame.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nataliia.semenova.examgame.R;

public class PlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_play);

        findViewById(R.id.ib_play).setOnClickListener(view -> {
            Intent intent = new Intent(PlayActivity.this, CutScene1Activity.class);
            startActivity(intent);
        });
    }
}
