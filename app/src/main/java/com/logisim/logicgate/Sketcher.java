package com.logisim.logicgate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.SyncFailedException;


public class Sketcher extends View  {

    final Paint mBackgroundPaint;
    final CurrentGame[] games = new CurrentGame[3];
    private int currentGame = 0;
    private boolean drawingEnabled = false;
    private boolean wiringEnabled = false;
    private Canvas view = new Canvas();
    private WireState wireState = new WireState();
    private boolean deleteGate = false;


    public Sketcher(Context context, AttributeSet atrs) {
        super(context, atrs);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
        games[0] = new CurrentGame();

    }


    public void draw(Canvas canvas) {
        super.draw(canvas);


        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawRect(0, 0, width, height, mBackgroundPaint);

        games[currentGame].draw(canvas);

        invalidate();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(drawingEnabled) {
            games[currentGame].handleMoveEvent(event);
            draw(view);
        }
        if(wiringEnabled){

            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP ){
                System.out.println("event captured");
                wireState = games[currentGame].handleWireEvent(event, wireState);

            }

            if ( wireState.getSecondRectFound() ) {
                draw(view);
                disableWiring();
                wireState = new WireState();
            }

        }

        if(deleteGate){
            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP ){
            }
        }
        return true;
    }

    public void deleteGate(){
        deleteGate = true;
    }

    public void addTool(int image){
        games[currentGame].addTool(getResources() , image );
        enableDrawing();
    }

    public CurrentGame getCurrentGame() {
        return games[currentGame];
    }

    public void enableDrawing(){
        disableWiring();
        this.drawingEnabled = true;
    }
    public void disableDrawing(){

        this.drawingEnabled = false;
    }

    public void enableWiring(){
        disableDrawing();
        wiringEnabled = true;
    }
    public void disableWiring(){
        wiringEnabled = false;
    }
}

class WireState{
    Rect firstRect;
    Rect endRect;
    boolean firstRectFound;
    boolean secondRectFound = false;

    public WireState(Rect firstRect){
        this.firstRect = firstRect;
        firstRectFound = true;
    }

    public WireState(){
        firstRectFound = false;
    }

    public void setFirstRect(Rect firstRect) {
        this.firstRect = firstRect;
    }

    public void setEndRect(Rect endRect) {
        this.endRect = endRect;
        secondRectFound = true;
    }

    public Rect getEndRect() {
        return endRect;
    }

    public Rect getFirstRect() {
        return firstRect;
    }

    public void invalidate(){
        firstRectFound = false;
    }

    public boolean getSecondRectFound(){
        return secondRectFound;
    }


    public boolean getFirstRectFound(){
        return firstRectFound;
    }

    public float startRectX(){
        return firstRect.exactCenterX();
    }
    public float startRectY(){
        return firstRect.exactCenterY();
    }

    public float endRectX(){
        return endRect.exactCenterX();
    }
    public float endRectY(){
        return endRect.exactCenterY();
    }
}






