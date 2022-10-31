package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    public static CarControl control = new CarControl(); // control:CarControl para usar el mismo en toda la app.
//    private static final int MNU_OPC1 = 1;
//    private static final int MNU_OPC2 = 2;
//    private static final int MNU_OPC3 = 3;
//    private static final int SMNU_OPC1 = 31;
//    private static final int SMNU_OPC2 = 32;

    private TextView lblMessage;
    private EditText searchCarId;
    private Button btnSearch, btnCancelSearch;
    private ListView lstList;

    // Lista de AutosView
    private final Bundle b = new Bundle();
    private ArrayList<CarListItemView> data = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control.insertDataMany(); // Default data

        lstList = (ListView)findViewById(R.id.LstList);
        lblMessage = (TextView)findViewById(R.id.LblMessage);
        searchCarId = (EditText)findViewById(R.id.SearchCarInputId);
        btnSearch = (Button)findViewById(R.id.BtnSearch);
        btnCancelSearch = (Button)findViewById(R.id.BtnCancelSearch);

        btnSearch.setOnClickListener(this);
        btnCancelSearch.setOnClickListener(this);

        // We get the MainScreen details Data
        data = control.getItemsView();
        // Returns a view for each object in a collection of data objects you provide, and can be used with list-based user interface widgets such as ListView or Spinner.
        CarItemViewAdapter adaptador1 = new CarItemViewAdapter(this, data);
        lstList.setAdapter(adaptador1);

        //We associate the contextual menus to the controls. Each label will have a contextual menu
        registerForContextMenu(lblMessage);
        registerForContextMenu(lstList);

//        Main screen list listener
        lstList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                int idCar = control.getAutoById(i+1).getId();
                //We create the Intent
                Intent intent = new Intent(MainActivity.this, DetailScreen.class); //We indicate the initial context and the destination activity
                //We add the information to the intent
                intent.putExtra("idShowItem", idCar);
                intent.putExtras(b);
                //We start the new activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Para tener varios menús contexuales en la misma pantalla se hace el inflater
        // Get reference to a basic menu without context
        MenuInflater inflater = getMenuInflater();
        // Displays main_menu structure
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Opciones de main_menu (NUEVO, BUSCAR, EDITAR)

        switch (item.getItemId()) {
            case R.id.MnuOpcAdd:
                Intent intent = new Intent(MainActivity.this, AddScreen.class); //We indicate the initial context and the destination activity
                startActivity(intent);

                return true;
            case R.id.MnuOpcSearch:
                lblMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                lblMessage.setPadding(40,25,0,25);
                lblMessage.setText(R.string.search_car_label);
                searchCarId.setVisibility(View.VISIBLE);
                btnSearch.setText(R.string.btn_search_car_label);
                btnSearch.setId(R.id.BtnSearch);
                btnSearch.setVisibility(View.VISIBLE);
                btnCancelSearch.setVisibility(View.VISIBLE);

                return true;
            case R.id.MnuOpcEdit:
                lblMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                lblMessage.setPadding(40,25,0,25);
                lblMessage.setText(R.string.search_car_label);
                searchCarId.setVisibility(View.VISIBLE);
                btnSearch.setText(R.string.btn_modify_car_label);
                btnSearch.setId(R.id.BtnModify);
                btnSearch.setVisibility(View.VISIBLE);
                btnCancelSearch.setVisibility(View.VISIBLE);
            default:
//                lblMessage.setText("Option another selected!");
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

//        DELETE A CAR
        if(item.getItemId() == R.id.MnuOpcDelete){

            int id = data.get(info.position).getListId();
//            if (!control.deleteOne(id)){
//                Toast.makeText(getBaseContext(), "Error",Toast.LENGTH_SHORT).show();
//                return true;
//            }
            control.deleteOne(id);

            String stringText = "Item id:"+" "+id+" "+"deleted. View Error";
            Toast.makeText(getBaseContext(), stringText,Toast.LENGTH_LONG).show();
//            data = control.getItemsView();
        }

        return super.onContextItemSelected(item);
    }

//    BUTTONS
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.BtnSearch) {

            if (searchCarId.getEditableText().toString().isEmpty()){
                return;
            }

            int id = Integer.parseInt(searchCarId.getEditableText().toString());

            Intent intent = new Intent(MainActivity.this, DetailScreen.class);
            intent.putExtra("idShowItem", id);
            intent.putExtras(b);

            startActivity(intent);
        }

        if(v.getId() == R.id.BtnCancelSearch){ // Cross icon
            lblMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            lblMessage.setText(R.string.hello);
            searchCarId.setText("");
            searchCarId.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
            btnCancelSearch.setVisibility(View.GONE);
        }

        if(v.getId() == R.id.BtnModify){

            if (searchCarId.getEditableText().toString().isEmpty()){
                return;
            }

            int id = Integer.parseInt(searchCarId.getEditableText().toString());

            Intent intent = new Intent(MainActivity.this, ModifyScreen.class);
            intent.putExtra("idModify", id);
            intent.putExtras(b);

            startActivity(intent);
        }
    }

}
