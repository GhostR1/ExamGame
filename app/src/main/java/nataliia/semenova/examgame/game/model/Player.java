package nataliia.semenova.examgame.game.model;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import nataliia.semenova.examgame.R;

public class Player {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }
}
