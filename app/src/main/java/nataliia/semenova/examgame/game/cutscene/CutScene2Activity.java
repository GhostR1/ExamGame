package nataliia.semenova.examgame.game.cutscene;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nataliia.semenova.examgame.R;
import nataliia.semenova.examgame.game.labyrinth.LabyrinthActivity;

public class CutScene2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cutscene_2);

        startTextAnimation();
        findViewById(R.id.ib_next_1).setOnClickListener(view -> {
            Intent intent = new Intent(CutScene2Activity.this, RulesLabActivity.class);
            startActivity(intent);
        });
    }

    private void startTextAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim_text_appearence);
        TextView textView = findViewById(R.id.tv_story);
        textView.startAnimation(anim);
    }

    @Override
    public void onBackPressed() {
        // Commented for disable backPressed action
        // super.onBackPressed();
    }
}


