package nataliia.semenova.examgame.game.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nataliia.semenova.examgame.R;
import nataliia.semenova.examgame.game.LabyrinthActivity;

public class LabyrinthView extends View {
    private LabyrinthDraw labyrinthDraw;
    private LabyrinthActivity context;
    private final Player player;
    private Point displaySize;

    public LabyrinthView(Context context) {
        super(context);
        this.context = (LabyrinthActivity) context;

        displaySize = getDisplaySize();
        initializeLabyrinthDraw();

        player = new Player(context, 500, 500, 25);
   }

    private void initializeLabyrinthDraw() {
        Bitmap[] bitmaps = {
                BitmapFactory.decodeResource(getResources(), R.drawable.sprite_sheet),
                BitmapFactory.decodeResource(getResources(), R.drawable.wall1),
                BitmapFactory.decodeResource(getResources(), R.drawable.wall2)
        };
        labyrinthDraw = new LabyrinthDraw(bitmaps, LabyrinthField.getField(), 30, 17, displaySize.x, displaySize.y-64);
    }

    private Point getDisplaySize() {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        labyrinthDraw.drawMaze(canvas, 1, 1);
        player.draw(canvas);
    }

    private void update() {
        player.update();
    }
}
