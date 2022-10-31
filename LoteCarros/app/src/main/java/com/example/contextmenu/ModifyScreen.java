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

public class ModifyScreen extends Activity implements OnClickListener {

    private TextView carId, errorTxt, modifySuccessTxt;
    private RadioGroup radioGroup;
    private EditText carName, carBrand, carModel, carPrice, carImage;
    private RadioButton cylindersChekBox_4, cylindersChekBox_6, cylindersChekBox_8;
    private Button btnSaveCar;

    private int cylinders = 0;
    private final CarControl control = MainActivity.control;
//    private Auto auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_screen);

        radioGroup = (RadioGroup)findViewById(R.id.CylindersRadioGroup);

        carId = (TextView)findViewById(R.id.CarIdTxt);
        carName = (EditText)findViewById(R.id.CarNameTxt);
        carBrand = (EditText)findViewById(R.id.CarBrandTxt);
        carModel = (EditText)findViewById(R.id.CarModelTxt);
        cylindersChekBox_4 = (RadioButton) findViewById(R.id.CarCylinders_4);
        cylindersChekBox_6 = (RadioButton) findViewById(R.id.CarCylinders_6);
        cylindersChekBox_8 = (RadioButton) findViewById(R.id.CarCylinders_8);
        carPrice = (EditText)findViewById(R.id.CarPrice);
//      imageTextView = (TextView)findViewById(R.id.NewCarImage);

        errorTxt = (TextView)findViewById(R.id.ErrorOnModifyTxt);
        modifySuccessTxt = (TextView)findViewById(R.id.SuccessModifiedTxt);

        btnSaveCar = (Button)findViewById(R.id.BtnSaveModifiedCar);
        btnSaveCar.setOnClickListener(this);

        Bundle bundle = this.getIntent().getExtras();
        Auto auto = control.getAutoById(bundle.getInt("idModify"));

        if (auto == null ){ // item doesn't exist. Redirect
//            Intent intent = new Intent(this, Error404Page.class);
//            startActivity(intent);
            Toast.makeText(getBaseContext(), "Error",Toast.LENGTH_SHORT).show();
            return;
        }

        clearInputs();

        carId.setText(String.valueOf(auto.getId()));
        carName.setText(auto.getName());
        carBrand.setText(auto.getBrand());
        carModel.setText(auto.getModel());
        carPrice.setText(String.valueOf(auto.getPrice()));
        cylinders = auto.getCylinders();

        if (cylinders == 4){
            radioGroup.check(R.id.CarCylinders_4);
        }
        if (cylinders == 6){
            radioGroup.check(R.id.CarCylinders_6);
        }
        if (cylinders == 8){
            radioGroup.check(R.id.CarCylinders_8);
        }

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
        if(v.getId()==R.id.BtnSaveModifiedCar)
        {
            Boolean validateEmptyFields = control.validateEmptyCarFields(carId, carName, carBrand, carModel, cylinders, carPrice);

            if (validateEmptyFields == false) {
                errorTxt.setText("Faltan campos");
                return;
            }

            int id = Integer.parseInt(carId.getText().toString());
            String name = carName.getEditableText().toString();
            String brand = carBrand.getEditableText().toString();
            String model = carModel.getEditableText().toString();
            int price = Integer.parseInt(carPrice.getEditableText().toString());

            control.updateOne(id, name, brand, model, cylinders, price);

//                Update screen
            clearInputs();
            int newId = control.generateNewId();
            carId.setText(String.valueOf(newId));
            modifySuccessTxt.setText(R.string.success_example_text_modify);
        }
    }

    private void clearInputs(){
        carName.setText("");
        carBrand.setText("");
        carModel.setText("");
        radioGroup.clearCheck();
        carPrice.setText("");

        errorTxt.setText("");
        modifySuccessTxt.setText("");
    }


}

