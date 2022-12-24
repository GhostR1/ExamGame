package nataliia.semenova.examgame.game.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class LabyrinthDraw {
    private final RectF drawRect = new RectF();

    private final Bitmap[] bitmaps;
    private final int[][] tileType;

    private final float screenWidth;
    private final float screenHeight;

    public LabyrinthDraw(Bitmap[] bitmaps, int[][] tileType, float xCellCountOnScreen, float yCellCountOnScreen, float screenWidth, float screenHeight){
        this.bitmaps = bitmaps;
        this.tileType = tileType;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        drawRect.set(0, 0, screenWidth / xCellCountOnScreen, screenHeight / yCellCountOnScreen);
    }

    public int getType(int x, int y){
        if(y < tileType.length && x < tileType[y].length) return tileType[y][x];
        return 0;
    }

    public float getCellWidth(){ return drawRect.width(); }
    public float getCellHeight(){ return drawRect.height(); }

    public void drawMaze(Canvas canvas, float viewX, float viewY){
        int tileX = 0;
        int tileY = 0;
        float xCoord = -viewX;
        float yCoord = -viewY;

        while(tileY < tileType.length && yCoord <= screenHeight){
            // Begin drawing a new column
            tileX = 0;
            xCoord = -viewX;

            while(tileX < tileType[tileY].length && xCoord <= screenWidth){
                // Check if the tile is not null
                if(bitmaps[tileType[tileY][tileX]] != null){

                    // This tile is not null, so check if it has to be drawn
                    if(xCoord + drawRect.width() >= 0 && yCoord + drawRect.height() >= 0){

                        // The tile actually visible to the user, so draw it
                        drawRect.offsetTo(xCoord, yCoord); // Move the rectangle to the coordinates
                        canvas.drawBitmap(bitmaps[tileType[tileY][tileX]], null, drawRect, null);
                    }
                }

                // Move to the next tile on the X axis
                tileX++;
                xCoord += drawRect.width();
            }

            // Move to the next tile on the Y axis
            tileY++;
            yCoord += drawRect.height();
        }
    }
}
