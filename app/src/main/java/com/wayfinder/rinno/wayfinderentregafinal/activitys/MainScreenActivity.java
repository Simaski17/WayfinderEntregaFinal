package com.wayfinder.rinno.wayfinderentregafinal.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.wayfinder.rinno.wayfinderentregafinal.FirebaseApplication;
import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.RGlobal;
import com.wayfinder.rinno.wayfinderentregafinal.model.MessageNew;
import com.wayfinder.rinno.wayfinderentregafinal.model.NodeNumber;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreenActivity extends AppCompatActivity {

    @BindView(R.id.ivLogoApumanque)
    ImageView ivLogoApumanque;
    @BindView(R.id.btirMapa)
    LinearLayout btirMapa;
    @BindView(R.id.rl_tiendas)
    RelativeLayout rlTiendas;
    @BindView(R.id.rl_comida)
    RelativeLayout rlComida;
    @BindView(R.id.rl_descuentos)
    RelativeLayout rlDescuentos;
    @BindView(R.id.rl_promociones)
    RelativeLayout rlPromociones;

    private String mensaje;
    private int cont;
    private int x;
    private int y;
    private int floor;
    private int numeroNodo;
    private int numeroNodo2;

    public float coordx = 0;
    public float coordy = 0;
    public int piso = 0;

    public static final String MyPreferences = "MyPrefs";

    SharedPreferences sharedPreferences;

    FirebaseApplication app;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);



        ButterKnife.bind(this);

        app = (FirebaseApplication) getApplicationContext();
        RGlobal.appF = app;

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        numeroNodo2 = sharedPreferences.getInt("nodoFinal", 0);
        coordx = sharedPreferences.getFloat("coordx", 0);
        coordy = sharedPreferences.getFloat("coordy", 0);
        piso = sharedPreferences.getInt("piso", 0);




    }

    @OnClick({R.id.ivLogoApumanque, R.id.btirMapa, R.id.rl_tiendas, R.id.rl_comida, R.id.rl_descuentos, R.id.rl_promociones})
    public void onViewClicked(View view) {

        Bundle params = new Bundle();
        params.putInt("ButtonID",view.getId());

        switch (view.getId()) {
            case R.id.ivLogoApumanque:
                cont++;
                if (cont == 5) {
                    Intent intent = new Intent(this, AgregarNodoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btirMapa:
                mensaje = "mapa";
                opcionMensaje(mensaje);
                irMainActivity();
                break;
            case R.id.rl_tiendas:
                mensaje = "tiendas";
                opcionMensaje(mensaje);
                irMainActivity();
                break;
            case R.id.rl_comida:
                mensaje = "comida";
                opcionMensaje(mensaje);
                irMainActivity();
                break;
            case R.id.rl_descuentos:
                mensaje = "descuentos";
                opcionMensaje(mensaje);
                irMainActivity();
                break;
            case R.id.rl_promociones:
                mensaje = "promociones";
                opcionMensaje(mensaje);
                irMainActivity();
                break;
        }
        Log.d("TAG","Boton Presionado "+mensaje);
        mFirebaseAnalytics.logEvent(mensaje,params);
    }

    private void irMainActivity() {
        if (numeroNodo2 == 0 && floor == 0) {
            Toast.makeText(this, "Error Debe Ingresar un Nodo", Toast.LENGTH_SHORT).show();
        } else if (floor > 0) {
            Intent intent = new Intent(this, MenuScreenActivity.class);
            startActivity(intent);
            // overridePendingTransition(0, 0);
        } else {
            EventBus.getDefault().postSticky(new NodeNumber((int) coordx, (int) coordy, piso, numeroNodo2));
            Intent intent = new Intent(this, MenuScreenActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);

        }
    }

    public void opcionMensaje(String mensaje) {
        EventBus.getDefault().postSticky(new MessageNew(mensaje));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageMain(NodeNumber event) {
        x = event.getX();
        y = event.getY();
        floor = event.getFloor();
        numeroNodo = event.getNumeroNodo();
    }


    @Override
    public void onResume() {
        super.onResume();
        cont = 0;
        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}
