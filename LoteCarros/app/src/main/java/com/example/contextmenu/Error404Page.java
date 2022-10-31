package com.example.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Error404Page extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_404_page);


        Intent i = this.getIntent();
    }

}
