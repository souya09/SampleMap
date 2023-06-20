package jp.ac.hec.cm0107.samplemap;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

import jp.ac.hec.cm0107.samplemap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final double MIN_LATITUDE = -90;
    private static final double MAX_LATITUDE = 90;
    private static final double MIN_LONGITUDE = -90;
    private static final double MAX_LONGITUDE = 90;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(35.698303, 139.698116);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("ここはどこ？"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mapInit();
    }

    private void mapInit() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition.Builder builder =new CameraPosition.Builder();

        switch (pos){
            case 0:
                builder.target(new LatLng(35.698303, 139.698116));
                break;
            case 1:
                builder.target(new LatLng(35.6936568534803, 139.70058202231354));
                break;
            case 2:
                builder.target(new LatLng(35.89557259900669, 139.63067494369474));
                break;
            case  3:
                Random random = new Random();
                double lat  = MIN_LATITUDE + (MAX_LATITUDE -MIN_LATITUDE)*random.nextDouble();
                double lag  = MIN_LONGITUDE + (MAX_LONGITUDE-MIN_LONGITUDE)*random.nextDouble();
                LatLng targetPos = new LatLng(lat,lag);
                mMap.addMarker(new MarkerOptions().position(targetPos).title("ここはどこ"));
                builder.target(targetPos);
                builder.zoom(17.5f);
                break;
        }
        builder.zoom(15.5f);
        CameraPosition camerapos = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camerapos));
        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setRotateGesturesEnabled(true);
        ui.setScrollGesturesEnabled(true);
        ui.setTiltGesturesEnabled(true);
        ui.setZoomControlsEnabled(true);
        ui.setZoomGesturesEnabled(true);
    }
}