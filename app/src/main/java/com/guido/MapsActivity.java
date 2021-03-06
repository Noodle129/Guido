package com.guido;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.guido.DirectionHelpers.FetchURL;
import com.guido.DirectionHelpers.TaskLoadedCallback;
import com.guido.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    MarkerOptions bj,sm,rv,gt;

    private FusedLocationProviderClient servicoLocalizacao;
    private double latitude, longitude;
    private boolean permitiuGPS = false;
    Location ultimaPosicao;



    Button getDirection;
    private Polyline currentPolyline;
    List <Marker> markerPoints= new ArrayList<>();

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Inicializar o fragmento aonde o mapa est?? localizado dentro da Activity [c??digo original]
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(MapsActivity.this).execute(getUrl(bj.getPosition(), sm.getPosition(), "driving"), "driving");
            }
        });

        //Chama o servi??o de localiza????o do Andrdoid e atribui ao nosso objeto
        servicoLocalizacao = LocationServices.getFusedLocationProviderClient(this);

        //Verificar se o  usu??rio j?? deu permiss??o para o uso do GPS
        //No caso do GPS, quando o usu??rio clicar para permitir ou n??o o acesso aos dados de localiza????o,
        //ser?? executado o m??todo onRequestPermissionsResults. Dentro desse m??todo j?? podemos pegar
        //os dados de latitude e longitude.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
        }else{
            permitiuGPS = true;
        }

        //Recupera????o do gerenciador de localiza????o
        LocationManager gpsHabilitado = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Verifica????o se o GPS est?? habilitado, caso n??o esteja...
        if(!gpsHabilitado.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //... abre a tela de configura????es na op????o para habilitar o GPS ou n??o
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            Toast.makeText(getApplicationContext(), "Para este aplicativo ?? necess??rio habilitar o GPS", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(@NonNull LatLng latLng) {
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(latLng);
            markerOptions.title(latLng.latitude + " : " + latLng.longitude);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            mMap.addMarker(markerOptions);
        }
    });


        //[C??digo original]
        //Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        adicionaComponentesVisuais();
        recuperarPosicaoAtual();

        
        LatLng bomJesus = new LatLng(41.5546,-8.3775);
        MarkerOptions bj = new MarkerOptions().position(bomJesus).title("Marker in GoodJesus");
        mMap.addMarker(bj);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bomJesus));
        LatLng sameiro = new LatLng(41.5418245,-8.3719921);
        MarkerOptions sm = new MarkerOptions().position(sameiro).title("Marker in Sameiro");
        mMap.addMarker(sm);
        LatLng gota = new LatLng(41.5578162,-8.4013489);
        MarkerOptions gt = new MarkerOptions().position(gota).title("Marker in Gota");
        mMap.addMarker(gt);
        LatLng rodovia = new LatLng(41.5540646,-8.4027909);
        MarkerOptions rv = new MarkerOptions().position(rodovia).title("Marker in Rodovia");
        mMap.addMarker(rv);

    }


    //M??todo que ir?? receber todas as atualiza????es enviadas pelo GPS, isto ??, se mudar algum
    //ponto de latitude ou longitude o GPS ir?? informar o celular e o m??todo getLastLocation()
    //ir?? recuperar esse valor

    private void recuperarPosicaoAtual() {
        try {
            //Testa se a pessoa permitiu o uso dos dados de localiza????o
            if (permitiuGPS) {
                Task locationResult = servicoLocalizacao.getLastLocation();
                //Assim que os dados estiverem recuperados
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task) {
                        if (task.isSuccessful()) {
                            //Recupera os dados de localiza????o da ??ltima posi????o
                            ultimaPosicao = (Location) task.getResult();

                            //Se for um valor v??lido
                            if(ultimaPosicao != null){
                                //Move a c??mera para o ponto recuperado e aplica um Zoom de 15 (valor padr??o)
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(ultimaPosicao.getLatitude(),
                                                ultimaPosicao.getLongitude()), 15));
                            }
                        } else {
                            //Exibe um Toast se o valor que recuperou do GPS n??o ?? v??lido
                            Toast.makeText(getApplicationContext(), "N??o foi poss??vel recuperar a posi????o.", Toast.LENGTH_LONG).show();
                            //Escreve o erro no LogCat
                            Log.e("TESTE_GPS", "Exception: %s", task.getException());
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("TESTE_GPS", e.getMessage());
        }
    }

    //Adiciona o bot??o para centralizar o mapa na posi????o atual. Esse bot??o ?? aquele parecido com um
    //alvo que fica no canto superior direito do mapa.
    private void adicionaComponentesVisuais() {
        //Se o objeto do mapa n??o existir, encerra o carregamento no return
        if (mMap == null) {
            return;
        }
        //Try/catch somente para n??o aparecer algum erro ao usu??rio com rela????o ?? permiss??o
        try {
            //Teste para verificar se o usu??rio j?? permitiu o acesso ao GPS, caso sim...
            if (permitiuGPS) {
                //Adiciona o bot??o que quando clicado vai para a posi????o atual do celular/GPS
                mMap.setMyLocationEnabled(true);
                //Habilita o bot??o para ser clicado
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else { //Se caso o usu??rio n??o permitiu o acesso aos dados de localiza????o
                mMap.setMyLocationEnabled(false); //Remove o bot??o
                mMap.getUiSettings().setMyLocationButtonEnabled(false); //Desabilita o bot??o

                //Limpa a ??ltima posi????o recuperada pois n??o ?? poss??vel acessar o GPS sem a permiss??o
                ultimaPosicao  = null;

                //Pede a permiss??o novamente
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
                }
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
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


