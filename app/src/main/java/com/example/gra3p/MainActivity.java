package com.example.gra3p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private  TextView textView;
    private Button button;
    private ImageView imageView;
    private  int REQUEST_LOCATION_PERMISSON=0;
    private Location ostatnialokalizacja;
    private FusedLocationProviderClient fusedLocation;
    private int aktualny=0;
    private Lokalizacja szukaneLokalizacje[]={new Lokalizacja(R.drawable.szkola,"nasza szkola",50.33462 ,18.78136),
            new Lokalizacja(R.drawable.kosciol,"Wawrzyniec",676,88)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        button=(Button) findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Klik");
                pobierzLokalizacje();
            }
        });
    }
    private void pobierzLokalizacje(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSON);
        }
        else {
            Log.d("Lokazliacja","Udzielono zgody");
            fusedLocation= LocationServices.getFusedLocationProviderClient(this);
            fusedLocation.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    ostatnialokalizacja=location;
                    double dl_gr=ostatnialokalizacja.getLongitude();
                    double sz_gr=ostatnialokalizacja.getLatitude();
                    textView.setText("długość geograficzna"+dl_gr+"\n szerokość geograficzna"+sz_gr);
                    double szukanaDlugosc=szukaneLokalizacje[aktualny].getDlug();
                    double szukanaSzerkosc=szukaneLokalizacje[aktualny].getSzer();
                    if((int)Math.round(dl_gr*10000)==(int)Math.round(szukanaDlugosc*10000))
                    {
                        if((int)Math.round(sz_gr*10000)==(int)Math.round(szukanaSzerkosc*10000))
                        aktualny++;
                        wyswietlkolejnemiejsce(aktualny);
                    }
           else
                    {
                        Toast.makeText(MainActivity.this,"jestes zbyt daleko",Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
    private void wyswietlkolejnemiejsce(int i){
        imageView.setImageResource(szukaneLokalizacje[i].getIdZdjecia());
        imageView.setContentDescription(szukaneLokalizacje[i].getOpiszdj());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            pobierzLokalizacje();
        }
        else
        {
            Toast.makeText(this,"przykro nam,nie możemy ci pomóc",Toast.LENGTH_SHORT).show();
        }
    }
}