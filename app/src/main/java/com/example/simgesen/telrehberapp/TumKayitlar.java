package com.example.simgesen.telrehberapp;

/**
 * Created by SimgeSen on 23.12.2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.sql.SQLException;


public class TumKayitlar extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kisiler);

        TextView tv=(TextView)findViewById(R.id.tvTumKayitlar);

        Veritabani db=new Veritabani(TumKayitlar.this);
        try {
            db.baglantiyiAc();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tumKayitlar=db.tumKayitlar();

        db.baglantiyiKapat();
        tv.setText(tumKayitlar);

    }
}