package com.example.admin.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //준비가 되면 저 밑 온맵레디 함수가 호출된다.

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {//map이 다 준비되었을 때 호출
        this.googleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//위성지도로 변환
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.513368,127.057729),17));//17은 확대 축소 레벨
        googleMap.getUiSettings().setZoomControlsEnabled(true);//확대 , 축소 버튼 (나타남)
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() { //map이 클릭될 때 호출
            @Override
            public void onMapClick(LatLng latLng) {
                    loc_mark=new GroundOverlayOptions();
                    loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.icon)).position(latLng,100f,100f); //latLng는 매개변수
                    googleMap.addGroundOverlay(loc_mark);
            }
        });
    }
    public static final int ITEM_SETELLITE=1; //final 상수는 항상 초기화를 시켜줘야 합니다.
    public static final int ITEM_NORMAL=2;
    public static final int ITEM_COEXMALL=3;
    public static final int ITEM_EVERLAND=4;
    public static final int ITEM_MARK_CLEAR=5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,ITEM_SETELLITE,0,"위성 지도"); //0다음에는 id값임.
        menu.add(0,ITEM_NORMAL,0,"일반 지도");
        SubMenu hotMenu=menu.addSubMenu("핫 플레이스");
        hotMenu.add(0,ITEM_COEXMALL,0,"코엑스몰");
        hotMenu.add(0,ITEM_EVERLAND,0,"에버랜드");
        menu.add(0,ITEM_MARK_CLEAR,0,"위치 아이콘 제거 ");

        //selectedNormal이라고 1,2,3대신에 상수를 넣으면..가독성이 좋아진다.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//옵션 아이템이 선택되었을 때 호출되는 메소드
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) { // 1,2,3중에 하나가 반환된다. (상수로 바꿈)
            case ITEM_SETELLITE:  googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //위성지도!!
                return true;
            case ITEM_NORMAL:  googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); //일반지도!!
                return true;
            case ITEM_COEXMALL:  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.513368,127.057729),17)); //코엑스! 17은 줌 시키는 레벨
                return true;
            case ITEM_EVERLAND:  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.294209,127.202609),17)); //코엑스!
                return true;
            case ITEM_MARK_CLEAR:  googleMap.clear(); //마크 지우기
                return true;
        }
        return false;//항목이 선택 되었을 때만 id별로 처리하는 이외에는 false
    }
}
