package com.guido;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.guido.databinding.ActivityMapsBinding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;


    private FusedLocationProviderClient servicoLocalizacao;
    private double latitude, longitude;
    private boolean permitiuGPS = false;
    Location ultimaPosicao;
    List <Marker> markerPoints= new ArrayList<>();

    private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker markerPerth;
    private Marker markerSydney;
    private Marker markerBrisbane;


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



        //Inicializar o fragmento aonde o mapa está localizado dentro da Activity [código original]
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Chama o serviço de localização do Andrdoid e atribui ao nosso objeto
        servicoLocalizacao = LocationServices.getFusedLocationProviderClient(this);

        //Verificar se o  usuário já deu permissão para o uso do GPS
        //No caso do GPS, quando o usuário clicar para permitir ou não o acesso aos dados de localização,
        //será executado o método onRequestPermissionsResults. Dentro desse método já podemos pegar
        //os dados de latitude e longitude.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
        }else{
            permitiuGPS = true;
        }

        //Recuperação do gerenciador de localização
        LocationManager gpsHabilitado = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Verificação se o GPS está habilitado, caso não esteja...
        if(!gpsHabilitado.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //... abre a tela de configurações na opção para habilitar o GPS ou não
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            Toast.makeText(getApplicationContext(), "Para este aplicativo é necessário habilitar o GPS", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //[Código original]
        //Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        adicionaComponentesVisuais();
        recuperarPosicaoAtual();
     /*   LatLng bomJesus = new LatLng(-8.3775, 41.5546);
        mMap.addMarker(new MarkerOptions().position(bomJesus).title("Marker in GoodJesus"));
        LatLng sameiro = new LatLng(-8.3719921, 41.5418245);
        mMap.addMarker(new MarkerOptions().position(sameiro).title("Marker in Sameiro"));
        LatLng gota = new LatLng(-8.4013489, 41.5578162);
        mMap.addMarker(new MarkerOptions().position(gota).title("Marker in Gota"));
        LatLng rodovia = new LatLng(-8.4027909, 41.5540646);
        mMap.addMarker(new MarkerOptions().position(rodovia).title("Marker in Rodovia"));
      */
        markerPerth = mMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth"));
        markerPerth.setTag(0);

        markerSydney = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney"));
        markerSydney.setTag(0);

        markerBrisbane = mMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane"));
        markerBrisbane.setTag(0);

    }




    //Método que irá receber todas as atualizações enviadas pelo GPS, isto é, se mudar algum
    //ponto de latitude ou longitude o GPS irá informar o celular e o método getLastLocation()
    //irá recuperar esse valor

    private void recuperarPosicaoAtual() {
        try {
            //Testa se a pessoa permitiu o uso dos dados de localização
            if (permitiuGPS) {
                Task locationResult = servicoLocalizacao.getLastLocation();
                //Assim que os dados estiverem recuperados
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task) {
                        if (task.isSuccessful()) {
                            //Recupera os dados de localização da última posição
                            ultimaPosicao = (Location) task.getResult();

                            //Se for um valor válido
                            if(ultimaPosicao != null){
                                //Move a câmera para o ponto recuperado e aplica um Zoom de 15 (valor padrão)
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(ultimaPosicao.getLatitude(),
                                                ultimaPosicao.getLongitude()), 15));
                            }
                        } else {
                            //Exibe um Toast se o valor que recuperou do GPS não é válido
                            Toast.makeText(getApplicationContext(), "Não foi possível recuperar a posição.", Toast.LENGTH_LONG).show();
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

    //Adiciona o botão para centralizar o mapa na posição atual. Esse botão é aquele parecido com um
    //alvo que fica no canto superior direito do mapa.
    private void adicionaComponentesVisuais() {
        //Se o objeto do mapa não existir, encerra o carregamento no return
        if (mMap == null) {
            return;
        }
        //Try/catch somente para não aparecer algum erro ao usuário com relação à permissão
        try {
            //Teste para verificar se o usuário já permitiu o acesso ao GPS, caso sim...
            if (permitiuGPS) {
                //Adiciona o botão que quando clicado vai para a posição atual do celular/GPS
                mMap.setMyLocationEnabled(true);
                //Habilita o botão para ser clicado
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else { //Se caso o usuário não permitiu o acesso aos dados de localização
                mMap.setMyLocationEnabled(false); //Remove o botão
                mMap.getUiSettings().setMyLocationButtonEnabled(false); //Desabilita o botão

                //Limpa a última posição recuperada pois não é possível acessar o GPS sem a permissão
                ultimaPosicao  = null;

                //Pede a permissão novamente
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
                }
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
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


