package com.example.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailScreen extends Activity {

    private TextView txtId;
    private TextView txtName;
    private TextView txtBrand;
    private TextView txtModel;
    private TextView txtCylinders;
    private TextView txtPrice;
    private ImageView imageCarView;

    private final CarControl control = MainActivity.control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);

        //Locate the controls
        txtId = (TextView) findViewById(R.id.TxtId);
        txtBrand = (TextView)findViewById(R.id.TxtBrand);
        txtName = (TextView)findViewById(R.id.TxtName);
        txtModel = (TextView)findViewById(R.id.TxtModel);
        txtCylinders = (TextView)findViewById(R.id.TxtCylinders);
        txtPrice = (TextView)findViewById(R.id.TxtPrice);
        imageCarView = (ImageView) findViewById(R.id.LblCarImg_Big);

        //We recover the information passed in the intent
        Bundle bundle = this.getIntent().getExtras();
        //We get de object from the list
        Auto auto = control.getAutoById(bundle.getInt("idShowItem"));

        if (auto == null ){ // item doesn't exist. Redirect
            Toast.makeText(getBaseContext(), "Error",Toast.LENGTH_SHORT).show();
            return;
        }

        //We build the message to show
        txtId.setText(String.valueOf(auto.getId()));
        txtName.setText(auto.getName());
        txtBrand.setText(auto.getBrand());
        txtModel.setText(auto.getModel());
        txtCylinders.setText(String.valueOf(auto.getCylinders()));
        txtPrice.setText("$ " + auto.getPrice() +".00");
        imageCarView.setImageResource(auto.getImage());
    }



}
