package com.wayfinder.rinno.wayfinderentregafinal.activitys;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.model.StoreNumber;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiendaMapaActivity extends AppCompatActivity {

    @BindView(R.id.ivCerrar)
    ImageView ivCerrar;
    @BindView(R.id.tvEmailDireccion)
    TextView tvEmailDireccion;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvWebDireccion)
    TextView tvWebDireccion;
    @BindView(R.id.tvWeb)
    TextView tvWeb;
    @BindView(R.id.tvTelefonoNumber)
    TextView tvTelefonoNumber;
    @BindView(R.id.tvTelefono)
    TextView tvTelefono;
    @BindView(R.id.tvLocalNumber)
    TextView tvLocalNumber;
    @BindView(R.id.tvLocal)
    TextView tvLocal;
    @BindView(R.id.btirTiendaMapaFinal)
    LinearLayout btirTiendaMapaFinal;
    @BindView(R.id.tv_nombre_tienda)
    TextView tvNombreTienda;

    private String nombreTienda;
    private String storeNumbert;
    private String phone;
    private String nodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_mapa);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        nombreTienda = getIntent().getStringExtra("nombreTienda");
        storeNumbert = getIntent().getStringExtra("local");
        phone = getIntent().getStringExtra("telefono");
        nodeId = getIntent().getStringExtra("nodeId");

        tvNombreTienda.setText(nombreTienda);
        tvLocalNumber.setText(storeNumbert);
        tvTelefonoNumber.setText(phone);

    }

    @OnClick({R.id.ivCerrar, R.id.btirTiendaMapaFinal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCerrar:
                finish();
                break;
            case R.id.btirTiendaMapaFinal:
                EventBus.getDefault().postSticky(new StoreNumber(Integer.parseInt(nodeId) - 1));
                finish();
                break;
        }
    }
}
