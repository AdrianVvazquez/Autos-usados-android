package com.example.contextmenu;

import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CarControl implements Serializable {

    private ArrayList<Auto> data;
    private ArrayList<CarListItemView> dataShort_List;
    private int countAutos;

    public CarControl()
    {
        data = new ArrayList<>(10);
        dataShort_List = new ArrayList<>(10);
        countAutos = 0;
    }


    public int getCountAutos() { return countAutos; }

    public int generateNewId() {
        return countAutos + 1;
    }

    public boolean insertDataMany()
    {
//        2019-Ford-F-150-Raptor-front-three-quarter-1 ROJO
//        2021-Ford-F-150-STX-blue AZUL

        // We fill the list with sample data
        data.add(new Auto(1, "Ford", "Ford F-150 Raptor", "2019", 4, 8000, R.drawable.fordraptor));
        data.add(new Auto(2, "Ford", "Ford F-150 STX", "2021", 8, 900000, R.drawable.fordstx));
        data.add(new Auto(3, "Ford", "Ford F-150 Raptor", "2019", 4, 4000, R.drawable.fordraptor));
        data.add(new Auto(4, "Ford", "Ford F-150 STX", "2021", 6, 8000, R.drawable.fordstx));
        data.add(new Auto(5, "Ford", "Ford F-150 Raptor", "2019", 4, 5000, R.drawable.fordraptor));
        data.add(new Auto(6, "Ford", "Ford F-150 STX", "2021", 4, 120000, R.drawable.fordstx));
        data.add(new Auto(7, "Ford", "Ford F-150 Raptor", "2019", 4, 8000, R.drawable.fordraptor));
        data.add(new Auto(8, "Ford", "Ford F-150 STX", "2021", 8, 95000, R.drawable.fordstx));
        data.add(new Auto(9, "Ford", "Ford F-150 Raptor", "2019", 4, 8500, R.drawable.fordraptor));

        countAutos = data.size(); // 9

        return true;
    }

    public boolean insertOne(int Id, String name, String brand, String model, int cylinders, int price)
    {
        boolean ok = true;
        if (countAutos%2 == 0){
            data.add(new Auto(Id,brand,name,model,cylinders,price,R.drawable.fordstx));
//            dataShort_List.add(new CarListItemView(Id,brand,name,model,R.drawable.fordstx));
        } else {
            data.add(new Auto(Id,brand,name,model,cylinders,price,R.drawable.fordraptor));
//            dataShort_List.add(new CarListItemView(Id,brand,name,model,R.drawable.fordraptor));
        }
        countAutos += 1;

        return ok;
    }

    public Auto getAutoById(int Id)
    {
        Auto auto = null;
        for (int i=0; i<countAutos; i++)
        {
            if (data.get(i).getId() == Id)
            {
                auto = data.get(i);
                break;
            }
        }
        return auto;
    }

    public boolean updateOne(int Id, String name, String brand, String model, int cylinders, int price)
    {
        boolean ok = true;

        for (int i=0; i<countAutos; i++){
            if (data.get(i).getId() == Id){
                data.remove(i);
//                dataShort_List.remove(i);
//                data.get(a).setName(name);
//                data.get(a).setBrand(brand);
//                data.get(a).setModel(model);
//                data.get(a).setCylinders(cylinders);
//                data.get(a).setPrice(price);

//                ok = true;
                break;
            }

        }

        if (countAutos%2 == 0){ // Switch default images
            data.add(new Auto(Id,brand,name,model,cylinders,price,R.drawable.fordstx));
//            dataShort_List.add(new CarListItemView(Id,brand,name,model,R.drawable.fordstx));
        } else {
            data.add(new Auto(Id,brand,name,model,cylinders,price,R.drawable.fordraptor));
//            dataShort_List.add(new CarListItemView(Id,brand,name,model,R.drawable.fordraptor));
        }
//        countAutos += 1;

        return ok;
    }

    public boolean deleteOne(int Id){
        boolean ok = true;

        for (int i=0; i < countAutos; i++){
            if (data.get(i).getId() == Id){
                data.remove(i);
//                dataShort_List.remove(i);
                countAutos -= 1;
//                ok = true;

                break;
            }
        }

        return ok;
    }

//    Regresa la lista de autos para la pantalla principal
    public ArrayList<CarListItemView> getItemsView()
    {
        for (int i=0; i<countAutos; i++)
        {
            dataShort_List.add(new CarListItemView(
                data.get(i).getId(),
                data.get(i).getBrand(),
                data.get(i).getName(),
                data.get(i).getModel(),
                data.get(i).getImage()
            ));
        }

        return dataShort_List;
    }

    public boolean validateEmptyCarFields(TextView Id, EditText name, EditText brand, EditText model, int cylinders, EditText price)
    {
        boolean ok = true;
        if (Id.getText().toString().isEmpty())
        {
            ok = false;
        }
        if (name.getEditableText().toString().isEmpty())
        {
            ok = false;
        }
        if (brand.getEditableText().toString().isEmpty())
        {
            ok = false;
        }
        if (model.getEditableText().toString().isEmpty())
        {
            ok = false;
        }
        if (cylinders == 0)
        {
            ok = false;
        }
        if (price.getEditableText().toString().isEmpty())
        {
            ok = false;
        }

        return ok;
    }


}
