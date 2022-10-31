package com.example.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddScreen extends Activity implements OnClickListener {

    private TextView newCarId, totalAutosTxt, errorTxt, saveSuccessTxt;
    private RadioGroup radioGroup;
    private EditText newCarName, newCarBrand, newCarModel, newCarPrice, newCarImage;
    private RadioButton checkBox, cylindersChekBox_4, cylindersChekBox_6, cylindersChekBox_8;
    private Button btnSaveNewCar;

    private int cylinders = 0;
    private final CarControl control = MainActivity.control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_screen);

        radioGroup = (RadioGroup)findViewById(R.id.CylindersRadioGroup);

        newCarId = (TextView)findViewById(R.id.NewCarId);
        newCarName = (EditText)findViewById(R.id.NewCarName);
        newCarBrand = (EditText)findViewById(R.id.NewCarBrand);
        newCarModel = (EditText)findViewById(R.id.NewCarModel);
        cylindersChekBox_4 = (RadioButton) findViewById(R.id.NewCarCylinders_4);
        cylindersChekBox_6 = (RadioButton) findViewById(R.id.NewCarCylinders_6);
        cylindersChekBox_8 = (RadioButton) findViewById(R.id.NewCarCylinders_8);
        newCarPrice = (EditText)findViewById(R.id.NewCarPrice);
//      imageTextView = (TextView)findViewById(R.id.NewCarImage);

        totalAutosTxt = (TextView)findViewById(R.id.TotalAutosTxt);
        errorTxt = (TextView)findViewById(R.id.ErrorOnSaveTxt);
        saveSuccessTxt = (TextView)findViewById(R.id.SuccessAddedTxt);

        btnSaveNewCar = (Button)findViewById(R.id.BtnSaveNewCar);
        btnSaveNewCar.setOnClickListener(this);

        clearInputs();
//        Id se genera para ser mostrado al iniciar la pantalla.
        int newId = control.generateNewId();
        newCarId.setText(String.valueOf(newId));

        int total = control.getCountAutos();
        totalAutosTxt.setText(String.valueOf(total));

        radioGroup.setOnCheckedChangeListener(
            new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (cylindersChekBox_4.isChecked() == true){
                        cylinders = 4;
                    }
                    if (cylindersChekBox_6.isChecked() == true){
                        cylinders = 6;
                    }
                    if (cylindersChekBox_8.isChecked() == true){
                        cylinders = 8;
                    }
                    Toast.makeText(getBaseContext(), cylinders+" "+"cilindros"+" "+"seleccionado.",Toast.LENGTH_SHORT).show();
                }
            }
        );

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.BtnSaveNewCar)
        {
            Boolean validateEmptyFields = control.validateEmptyCarFields(newCarId, newCarName, newCarBrand, newCarModel, cylinders, newCarPrice);

            if (validateEmptyFields == false) {
                errorTxt.setText("Faltan campos");
                return;
            }

            int id = Integer.parseInt(newCarId.getText().toString());
            String name = newCarName.getEditableText().toString();
            String brand = newCarBrand.getEditableText().toString();
            String model = newCarModel.getEditableText().toString();
            int price = Integer.parseInt(newCarPrice.getEditableText().toString());

            control.insertOne(id, name, brand, model, cylinders, price);

//                Update screen
            clearInputs();
            int total = control.getCountAutos();
            int newId = control.generateNewId();
            saveSuccessTxt.setText("Auto añadido.");
            newCarId.setText(String.valueOf(newId));
            totalAutosTxt.setText(String.valueOf(total));

        }
    }

    private void clearInputs(){
        newCarName.setText("");
        newCarBrand.setText("");
        newCarModel.setText("");
        radioGroup.clearCheck();
        newCarPrice.setText("");

        errorTxt.setText("");
        saveSuccessTxt.setText("");
    }


}

