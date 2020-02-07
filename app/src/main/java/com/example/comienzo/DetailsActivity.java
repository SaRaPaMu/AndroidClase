package com.example.comienzo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.comienzo.model.Episodio;

public class DetailsActivity extends BasicApp {

    public static final String ITEM_CLICKADO = "item_clickado";
    Episodio item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);

        Intent intent = getIntent();
        item = (Episodio) intent.getSerializableExtra(ITEM_CLICKADO);

        initData();
    }

    void initData(){
        if (item == null){
            return;
        }
        TextView txt = findViewById(R.id.act_details_item_lbl);
        txt.setText("DETALLES: "+item.serie);
    }

}
