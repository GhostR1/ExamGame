package nataliia.semenova.examgame.game.labyrinth;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class LabyrinthDraw {
    private final RectF drawRect = new RectF();

    private final Bitmap[] bitmaps;
    private int[][] field;

    private final float screenWidth;
    private final float screenHeight;

    public LabyrinthDraw(Bitmap[] bitmaps, int[][] field,
                         float xCellCountOnScreen, float yCellCountOnScreen,
                         float screenWidth, float screenHeight) {
        this.bitmaps = bitmaps;
        this.field = field;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        drawRect.set(0, 0, screenWidth / xCellCountOnScreen, screenHeight / yCellCountOnScreen);
    }

    public void drawLabyrinth(Canvas canvas, float viewX, float viewY){
        int xCell;
        float xCoord;

        int yCell = 0;
        float yCoord = -viewY;

        while(yCell < field.length && yCoord <= screenHeight){
            xCell = 0;
            xCoord = -viewX;
            while(xCell < field[yCell].length && xCoord <= screenWidth){
                if(bitmaps[field[yCell][xCell]] != null){
                    if(xCoord + drawRect.width() >= 0 && yCoord + drawRect.height() >= 0){
                        drawRect.offsetTo(xCoord, yCoord);
                        canvas.drawBitmap(bitmaps[field[yCell][xCell]], null, drawRect, null);
                    }
                }
                xCell++;
                xCoord += drawRect.width();
            }
            yCell++;
            yCoord += drawRect.height();
        }
    }

    public void setField(int[][] field) {
        this.field = field;
    }
}
