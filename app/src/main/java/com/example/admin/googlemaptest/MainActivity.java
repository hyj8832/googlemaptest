package com.example.admin.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //준비가 되면 저 밑 온맵레디 함수가 호출된다.

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//위성지도로 변환
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.839269,128.631892),17));//17은 확대 축소 레벨
        //확대 , 축소 버튼 (나타남)
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
