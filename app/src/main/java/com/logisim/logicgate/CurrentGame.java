package com.logisim.logicgate;

import android.content.res.Resources;
import android.graphics.Canvas;
import java.util.LinkedHashSet;
import java.util.Set;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class CurrentGame  {
    public Set<Tool> usedTools = new LinkedHashSet<>();
    public Set<Wire> savedWire = new LinkedHashSet<>();
    private int toolCount = 0;
    private int connections = 0;
    final Paint paint = new Paint();
    public Tool currentTool;


    public CurrentGame(){


    }


    public void draw(Canvas canvas){
        if (toolCount > 0) {
            drawTools(canvas);
            drawWires(canvas);
        }


    }

    public void drawTools(Canvas canvas){
        for (Tool usedTool : usedTools){
            usedTool.drawTool(canvas,paint);

        }
    }

    public void drawWires(Canvas canvas){
        if(connections > 0){
            for (Wire usedWire : savedWire){
                usedWire.drawLine(canvas,paint);

            }
        }

    }


    public void addTool(Resources res, int image ){
        currentTool = new Tool(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void addNOT(Resources res, int image){
        currentTool = new NOT(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void addAND(Resources res, int image){
        currentTool = new AND(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void addOR(Resources res, int image){
        currentTool = new OR(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void addButton(Resources res, int image){
        currentTool = new Button(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void addLED(Resources res, int image){
        currentTool = new LED(res, image);
        usedTools.add(currentTool);
        toolCount++;
    }

    public void handleDeleteGate(MotionEvent me){
    }


    //left off trying to figure out how to draw wiring
    public WireState handleWireEvent( MotionEvent me, WireState wireState ){


        int touchedY = (int) me.getY();
        int touchedX = (int) me.getX();

        if(wireState.getFirstRectFound()){
            for (Tool tools : usedTools){
                if(tools.checkHitBoxes(touchedX,touchedY) != null){
                    wireState.setEndRect(tools.checkHitBoxes(touchedX,touchedY));

                    savedWire.add( new Wire(wireState.startRectX(), wireState.startRectY() , wireState.endRectX() , wireState.endRectY()));

                    System.out.println("second wire found");
                    connections++;
                    wireState.invalidate();
                    return wireState;
                }
            }
            System.out.println("second rect not found");

        }


        if(wireState.getFirstRectFound() == false){
            for (Tool tool : usedTools){
                if(tool.checkHitBoxes(touchedX,touchedY) != null){
                    Rect temp = tool.checkHitBoxes(touchedX,touchedY);

                    System.out.println("first wire hit box found");

                    if (temp == null){
                        System.out.println("first rect null");
                    }
                    return (new WireState(temp));
                }
            }
        }


        return wireState;
    }

    public void handleMoveEvent(MotionEvent me ) {

        try{
            Thread.sleep(50);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        final int action = me.getAction();
        switch ( action ) {
            case MotionEvent.ACTION_DOWN:
                currentTool.x = me.getX();
                currentTool.y = me.getY();
                break;
            case MotionEvent.ACTION_UP:
                currentTool.x = me.getX();
                currentTool.y = me.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentTool.x = me.getX();
                currentTool.y = me.getY();
                break;
        }



    }
}
