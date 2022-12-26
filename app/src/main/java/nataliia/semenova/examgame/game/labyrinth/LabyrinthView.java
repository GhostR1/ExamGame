package nataliia.semenova.examgame.game.labyrinth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.Locale;

import nataliia.semenova.examgame.R;
import nataliia.semenova.examgame.game.PlayActivity;
import nataliia.semenova.examgame.game.WheelOfFortuneActivity;

public class LabyrinthView extends View {
    private LabyrinthDraw labyrinthDraw;

    private int[][] labyrinthField;
    private Point currentPosition;

    private final LabyrinthActivity context;
    private final Point displaySize;

    private int count = 0;
    private int time = 30;
    private boolean isTimerDraw = false;

    public LabyrinthView(Context context) {
        super(context);
        this.context = (LabyrinthActivity) context;

        displaySize = getDisplaySize();
        initializeLabyrinthDraw();
        startTimer();
    }

    // Getting size for correct displaying of the field on any device
    private Point getDisplaySize() {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    private void initializeLabyrinthDraw() {
        Bitmap[] bitmaps = {
                BitmapFactory.decodeResource(getResources(), R.drawable.pastry),
                BitmapFactory.decodeResource(getResources(), R.drawable.player),
                BitmapFactory.decodeResource(getResources(), R.drawable.labyrinth),
                BitmapFactory.decodeResource(getResources(), R.drawable.background)
        };
        labyrinthField = LabyrinthField.getField();
        labyrinthDraw = new LabyrinthDraw(bitmaps, labyrinthField,
                labyrinthField[0].length, labyrinthField.length,
                displaySize.x, displaySize.y-64);
        currentPosition = new Point(labyrinthField[0].length - 2, labyrinthField.length - 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        labyrinthDraw.drawLabyrinth(canvas, 1, 1);
        drawControlButtons(canvas);
        drawScore();
        if (!isTimerDraw) {
            drawTimer();
        }
    }

    private void drawControlButtons(Canvas canvas) {
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageButton ibLeft = new ImageButton(context);
        ibLeft.setBackground(ResourcesCompat.getDrawable(
                getResources(), R.drawable.button_left, null
        ));
        ibLeft.setX(0);
        ibLeft.setY(displaySize.y - 480);
        ibLeft.setOnClickListener(view -> moveLeft(canvas));
        context.addContentView(ibLeft, layoutParams);

        ImageButton ibRight = new ImageButton(context);
        ibRight.setBackground(ResourcesCompat.getDrawable(
                getResources(), R.drawable.button_right, null
        ));
        ibRight.setX(320);
        ibRight.setY(displaySize.y - 480);
        ibRight.setOnClickListener(view -> moveRight(canvas));
        context.addContentView(ibRight, layoutParams);

        ImageButton ibUp = new ImageButton(context);
        ibUp.setBackground(ResourcesCompat.getDrawable(
                getResources(), R.drawable.button_up, null
        ));
        ibUp.setX(160);
        ibUp.setY(displaySize.y - 640);
        ibUp.setOnClickListener(view -> moveUp(canvas));
        context.addContentView(ibUp, layoutParams);

        ImageButton ibDown = new ImageButton(context);
        ibDown.setBackground(ResourcesCompat.getDrawable(
                getResources(), R.drawable.button_down, null
        ));
        ibDown.setX(160);
        ibDown.setY(displaySize.y - 320);
        ibDown.setOnClickListener(view -> moveDown(canvas));
        context.addContentView(ibDown, layoutParams);
    }

    private void drawScore() {
        TextView tvScore = new TextView(context);

        tvScore.setTextSize(20);
        tvScore.setText(String.format(Locale.getDefault(), "Score: %d", count));
        tvScore.setTextColor(Color.YELLOW);
        tvScore.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_gray));

        tvScore.setX(20);
        tvScore.setY(displaySize.y - 66);
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        context.addContentView(tvScore, layoutParams);
    }

    private void drawTimer() {
        TextView timeLeft = new TextView(context);
        timeLeft.setTextSize(20);
        if(time > 9) {
            timeLeft.setText(String.format(Locale.getDefault(), "Time left (seconds): %d", time));
        } else {
            timeLeft.setText(String.format(Locale.getDefault(), "Time left (seconds): 0%d", time));
        }
        timeLeft.setTextColor(Color.YELLOW);
        timeLeft.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_gray));

        timeLeft.setX(displaySize.x - 700);
        timeLeft.setY(displaySize.y - 66);
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        context.addContentView(timeLeft, layoutParams);
        isTimerDraw = true;
    }

    private void startTimer() {
        new CountDownTimer(time * 1000L, 1000) {
            @Override
            public void onTick(long l) {
                time--;
                isTimerDraw = false;
                invalidate();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(context, WheelOfFortuneActivity.class);
                intent.putExtra("score", count);
                context.startActivity(intent);
            }
        }.start();
    }

    private void moveLeft(Canvas canvas) {
        if(labyrinthField[currentPosition.y][currentPosition.x-1] == 0) {
            count++;
        }
        if(labyrinthField[currentPosition.y][currentPosition.x-1] == 0 ||
                labyrinthField[currentPosition.y][currentPosition.x-1] == 3) {
            currentPosition.x--;
            labyrinthField[currentPosition.y][currentPosition.x] = 1;
            labyrinthField[currentPosition.y][currentPosition.x + 1] = 3;
            labyrinthDraw.setField(labyrinthField);
            labyrinthDraw.drawLabyrinth(canvas, 1, 1);
            invalidate();
        }
    }

    private void moveRight(Canvas canvas) {
        if(labyrinthField[currentPosition.y][currentPosition.x+1] == 0) {
            count++;
        }
        if(labyrinthField[currentPosition.y][currentPosition.x+1] == 0 ||
                labyrinthField[currentPosition.y][currentPosition.x+1] == 3) {
            currentPosition.x++;
            labyrinthField[currentPosition.y][currentPosition.x] = 1;
            labyrinthField[currentPosition.y][currentPosition.x - 1] = 3;
            labyrinthDraw.setField(labyrinthField);
            labyrinthDraw.drawLabyrinth(canvas, 1, 1);
            invalidate();
        }
    }

    private void moveUp(Canvas canvas) {
        if(labyrinthField[currentPosition.y-1][currentPosition.x] == 0) {
            count++;
        }
        if(labyrinthField[currentPosition.y-1][currentPosition.x] == 0 ||
                labyrinthField[currentPosition.y-1][currentPosition.x] == 3) {
            currentPosition.y--;
            labyrinthField[currentPosition.y][currentPosition.x] = 1;
            labyrinthField[currentPosition.y+1][currentPosition.x] = 3;
            labyrinthDraw.setField(labyrinthField);
            labyrinthDraw.drawLabyrinth(canvas, 1, 1);
            invalidate();
        }
    }

    private void moveDown(Canvas canvas) {
        if(labyrinthField[currentPosition.y+1][currentPosition.x] == 0) {
            count++;
        }
        if(labyrinthField[currentPosition.y+1][currentPosition.x] == 0 ||
                labyrinthField[currentPosition.y+1][currentPosition.x] == 3) {
            currentPosition.y++;
            labyrinthField[currentPosition.y][currentPosition.x] = 1;
            labyrinthField[currentPosition.y-1][currentPosition.x] = 3;
            labyrinthDraw.setField(labyrinthField);
            labyrinthDraw.drawLabyrinth(canvas, 1, 1);
            invalidate();
        }
    }
}
