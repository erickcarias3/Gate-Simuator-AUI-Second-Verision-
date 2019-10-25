package com.logisim.logicgate;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Tool{

    private Bitmap tool;
    float x, y;
    Rect[] hitboxes;

    //create a bitmap with teh passed image and set the spawn coordiates
    public Tool(Resources res, int image){
       Bitmap temp = BitmapFactory.decodeResource(res, image);
       tool = Bitmap.createScaledBitmap(temp, 150,80,true);
       x = 250;
       y = 250;

    }

    // draws tool in the center of the touch location
    public void drawTool(Canvas canvas, Paint paint){
        canvas.drawBitmap(tool, x - (tool.getWidth()/2)  , y - (tool.getHeight()/2) , null);
    }

    public Rect checkHitBoxes(int touchedX, int touchedY){


        for(int i=0; i < this.hitboxes.length; i++){

            if(hitboxes[i].contains(touchedX,touchedY)){
                System.out.println("hitbox hit");
                return hitboxes[i];
            }

        }
        return null;
    }


}

interface Node{
    boolean eval();
}


class NOT extends Tool implements Node{

    private Rect input;
    private Rect output;
    private Node n;

    public NOT(Resources res, int image) {
        super(res, image);
        hitboxes = new Rect[2];

    }

    //this method calls the parent draw method and updates the port hitboxes
    @Override
    public void drawTool(Canvas canvas, Paint paint) {
        super.drawTool(canvas, paint);
        updatePorts(paint);
        canvas.drawRect(input,paint);
        canvas.drawRect(output,paint);
    }

    //updates location of port hit boxes on the tool
    private void updatePorts(Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        output = new Rect((int)x+55,(int)y-15,(int)x+55+20,(int)y+20);
        input = new Rect((int)x-77,(int)y-15,(int)x-77+20,(int)y+20);
        hitboxes[0] = output;
        hitboxes[1] = input;
    }

    private void drawInput(){

    }

    private void drawOutput(){

    }

    public void setSource(Node n){
        this.n = n;
    }

    @Override
    public boolean eval(){
        return !n.eval();
    }

    public Rect getOutput() {
        return output;
    }
}

class Button extends Tool implements Node{

    public boolean state;
    private Rect output;
    private Node n;

    public Button(Resources res, int image) {
        super(res, image);
        state = false;
        hitboxes = new Rect[1];

    }

    @Override
    public void drawTool(Canvas canvas, Paint paint) {
        super.drawTool(canvas, paint);
        updatePorts(paint);
        canvas.drawRect(output,paint);
    }

    private void updatePorts(Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        output = new Rect((int)x+15,(int)y+5,(int)x+15+20,(int)y+5+35);
        hitboxes[0] = output;
    }

    private void drawOutput(){

    }

    public void toggle(){
        this.state = !this.state;
    }

    @Override
    public boolean eval(){
        return state;
    }

    public Rect getOutput() {
        return output;
    }

}

class LED extends Tool {

    private Rect input;

    public LED(Resources res, int image) {
        super(res, image);
        hitboxes = new Rect[1];

    }

    @Override
    public void drawTool(Canvas canvas, Paint paint) {
        super.drawTool(canvas, paint);
        updatePorts(paint);
        canvas.drawRect(input,paint);
    }

    private void updatePorts(Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        input = new Rect((int)x-15,(int)y+25, (int)x-15+30,(int)y+25+35);
        hitboxes[0] = input;

    }

}

class AND extends Tool implements Node{

    private Rect inputX , inputY;
    private Rect output;
    private Node b, a;

    public AND(Resources res, int image) {
        super(res, image);
        hitboxes = new Rect[3];
    }

    @Override
    public void drawTool(Canvas canvas, Paint paint) {
        super.drawTool(canvas, paint);
        updatePorts(paint);
        canvas.drawRect(output,paint);
        canvas.drawRect(inputX,paint);
        canvas.drawRect(inputY,paint);
    }

    //updates location of port hit boxes on the tool
    private void updatePorts(Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        output = new Rect((int)x+50,(int)y-20,(int)x+50+25,(int)y+20);

        inputY = new Rect((int)x-75,(int)y+10,(int)x-75+25,(int)y+10+45);

        inputX = new Rect((int)x-75,(int)y-45,(int)x-75+25,(int)y-45+45);

        hitboxes[0] = output;
        hitboxes[1] = inputY;
        hitboxes[2] = inputX;
    }

    private void drawInput(){

    }

    private void drawOutput(){

    }

    public void setA(Node n){
        this.a = n;
    }

    public void setB(Node n){
        this.b = n;
    }

    @Override
    public boolean eval() {
        return a.eval() & b.eval();
    }

}

class OR extends Tool implements Node{

    private Rect inputX , inputY;
    private Rect output;
    private Node b, a;

    public OR(Resources res, int image) {
        super(res, image);
        hitboxes = new Rect[3];

    }

    @Override
    public void drawTool(Canvas canvas, Paint paint) {
        super.drawTool(canvas, paint);
        updatePorts(paint);
        canvas.drawRect(output,paint);
        canvas.drawRect(inputX,paint);
        canvas.drawRect(inputY,paint);
    }

    //updates location of port hit boxes on the tool
    private void updatePorts(Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.5f);
        output = new Rect((int)x+50,(int)y-20,(int)x+50+25,(int)y+20);

        inputY = new Rect((int)x-75,(int)y+10,(int)x-75+25,(int)y+10+45);

        inputX = new Rect((int)x-75,(int)y-45,(int)x-75+25,(int)y-45+45);

        hitboxes[0] = output;
        hitboxes[1] = inputY;
        hitboxes[2] = inputX;
    }

    private void drawInput(){

    }

    private void drawOutput(){

    }

    public void setA(Node n){
        this.a = n;
    }

    public void setB(Node n){
        this.b = n;
    }

    @Override
    public boolean eval() {
        return a.eval() | b.eval();
    }
}

class Wire{
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public Wire(float startX, float startY, float endX, float endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void drawLine(Canvas canvas, Paint paint){
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4.5f);

        canvas.drawLine(startX,startY,endX,endY,paint);
    }


}

