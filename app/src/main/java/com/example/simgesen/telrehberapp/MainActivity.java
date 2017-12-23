package com.example.simgesen.telrehberapp;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerlayout ;
    private ActionBarDrawerToggle mToogle ;

    EditText eIsim,eTelefon;
    Button bEkle,bGetir,aramabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eIsim= (EditText) findViewById(R.id.eIsim);
        eTelefon= (EditText) findViewById(R.id.eTelefon);

        //Button Kısmı
        bEkle= (Button) findViewById(R.id.bEkle);
        bGetir= (Button) findViewById(R.id.bGetir);
        aramabtn = (Button) findViewById(R.id.aramabtn);

        bEkle.setOnClickListener(this);
        bGetir.setOnClickListener(this);
        aramabtn.setOnClickListener(this);

        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToogle.onOptionsItemSelected(item)){
            switch (item.getItemId()){
                case R.id.hazirlayanlar:
                    Toast.makeText(this,"Hazırlayanlar Simge ŞEN,İrem KAYA",Toast.LENGTH_LONG).show();
                    break;
                case R.id.settings:
                    Toast.makeText(this,"Ayarlar",Toast.LENGTH_LONG).show();
                    break;
                case R.id.search:
                    Toast.makeText(this,"Search",Toast.LENGTH_LONG).show();
                    break;
                case R.id.cikis:
                    Toast.makeText(this,"Çıkış butonuna bastınız.",Toast.LENGTH_LONG).show();
                    break;
            }
        }
        switch (item.getItemId()){
            case R.id.paylas:
                Toast.makeText(this,"Paylaş butonu seçildi",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.oyver:
                Toast.makeText(this,"Oy vermek için Google Playe gidiniz",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.hakkinda:
                Toast.makeText(this,"Mobil Programlama final proje ödevidir",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bEkle:
                String ad = eIsim.getText().toString();
                String telefon = eTelefon.getText().toString();

                try {
                    Veritabani db = new Veritabani(MainActivity.this);
                    db.baglantiyiAc();
                    db.isimYasBilgisiniKaydet(ad, telefon);
                    db.baglantiyiKapat();
                } catch (Exception e) {
                    Dialog hata = new Dialog(this);
                    hata.setTitle("Ekleme İşlemi");
                    TextView tvHata = new TextView(this);
                    tvHata.setText(e.toString());
                    hata.setContentView(tvHata);
                    hata.show();

                } finally {
                    Dialog dialog = new Dialog(this);
                    dialog.setTitle("Ekleme İşlemi");
                    TextView tvSonuc = new TextView(this);
                    tvSonuc.setText("BAŞARILI");
                    dialog.setContentView(tvSonuc);
                    dialog.show();
                }
                break;

            case R.id.bGetir:

                Intent i = new Intent("android.umiitkose.androiddb.TUMKAYITLAR");
                startActivity(i);
                break;

            case R.id.aramabtn:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"));
                startActivity(intent);
                break;
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }


}
