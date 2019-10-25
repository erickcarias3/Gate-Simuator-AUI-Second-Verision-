package com.logisim.logicgate;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Button;

public class MainDriver extends Activity implements PopupMenu.OnMenuItemClickListener {

    Sketcher sketchView;
    Button wire;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sketchView = findViewById(R.id.sketcher);

        this.wire = (Button) findViewById(R.id.wireButton);

        wire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sketchView.enableWiring();
            }
        });

    }


    public void showPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        if(view == findViewById(R.id.addTool)){
            popup.inflate(R.menu.popup_menu_addtools);
        }
        if(view == findViewById(R.id.saveButton)){
            popup.inflate(R.menu.popup_menu_save);
        }
        if(view == findViewById(R.id.loadButton)){
            popup.inflate(R.menu.popup_menu_save);

        }
            popup.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.andgate:
                Toast.makeText(this, "andGate added", Toast.LENGTH_SHORT).show();
                sketchView.getCurrentGame().addAND( sketchView.getResources(), R.drawable.andgate);
                sketchView.enableDrawing();
                return true;
            case R.id.orgate:
                Toast.makeText(this, "orGate added", Toast.LENGTH_SHORT).show();
                sketchView.getCurrentGame().addOR( sketchView.getResources(), R.drawable.orgate);
                sketchView.enableDrawing();
                return true;
            case R.id.notgate:
                Toast.makeText(this, "notGate added", Toast.LENGTH_SHORT).show();
                sketchView.getCurrentGame().addNOT( sketchView.getResources(), R.drawable.notgate);
                sketchView.enableDrawing();
                return true;
            case R.id.button:
                Toast.makeText(this, "button added", Toast.LENGTH_SHORT).show();
                sketchView.getCurrentGame().addButton( sketchView.getResources(), R.drawable.offbutton);
                sketchView.enableDrawing();
                return true;
            case R.id.led:
                Toast.makeText(this, "led added", Toast.LENGTH_SHORT).show();
                sketchView.getCurrentGame().addLED( sketchView.getResources(), R.drawable.light);
                sketchView.enableDrawing();
                return true;
            case R.id.deleteGate:
                Toast.makeText(this, "delete Gate Pressed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.A:
                Toast.makeText(this, "game saved into A", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.B:
                Toast.makeText(this, "game saved into B", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.C:
                Toast.makeText(this, "game saved into C", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }
}
