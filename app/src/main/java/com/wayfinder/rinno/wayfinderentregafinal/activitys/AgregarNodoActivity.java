package com.wayfinder.rinno.wayfinderentregafinal.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.wayfinder.rinno.wayfinderentregafinal.FirebaseApplication;
import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.model.NodeNumber;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarNodoActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.bt_cancelar)
    Button btCancelar;
    @BindView(R.id.bt_aceptar)
    Button btAceptar;
    @BindView(R.id.guideline5)
    Guideline guideline5;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.et_num_nodo)
    EditText etNumNodo;
    @BindView(R.id.til_num_nodo)
    TextInputLayout tilNumNodo;

    private String password;
    private int cont = 0;
    private int numeroNodo;
    private int floor;
    private float x;
    private float y;

    FirebaseApplication app;

    public static final String MyPreferences = "MyPrefs";
    public static final String nodoFinal = "nodoFinal";
    public static final String coordx = "coordx";
    public static final String coordy = "coordy";
    public static final String piso = "piso";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nodo);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        app = (FirebaseApplication) getApplicationContext();

        //etNumNodo.setText("600");

        password = "1234";

    }

    @OnClick({R.id.bt_cancelar, R.id.bt_aceptar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancelar:
                finish();
                break;
            case R.id.bt_aceptar:
                SharedPreferences.Editor editor = sharedPreferences.edit();

                numeroNodo = Integer.parseInt(etNumNodo.getText().toString()) - 1;
                editor.putInt(nodoFinal, numeroNodo);

                etNumNodo.setText("");
                //Toast.makeText(this, "Numero Nodo: "+app.arregloIdRuta.size(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < app.arregloIdRuta.size(); i++) {
                    if (app.arregloIdRuta.get(i).equals(String.valueOf(numeroNodo))) {
                        x = (float) app.arregloLocationX.get(numeroNodo);
                        editor.putFloat(coordx,x);
                        y = (float) app.arregloLocationY.get(numeroNodo);
                        editor.putFloat(coordy,y);
                        floor = Integer.parseInt((String) app.arregloFloor.get(numeroNodo));
                        editor.putInt(piso,floor);
                        EventBus.getDefault().postSticky(new NodeNumber((int) x, (int) y, floor, numeroNodo));
                        finish();
                        //startActivity(i);
                    }
                }

                editor.commit();
                hideSoftKeyboard();
                /*if (cont == 0) {
                    if (etPassword.getText().toString().equals("")) {
                        Toast.makeText(this, "Campo password vacio", Toast.LENGTH_SHORT).show();
                    } else if (!etPassword.getText().toString().equals("1234")) {
                        Toast.makeText(this, "Password Incorrecto", Toast.LENGTH_SHORT).show();
                    } else if (etPassword.getText().toString().equals("1234")) {
                        etPassword.setText("");
                        etNumNodo.setVisibility(View.VISIBLE);
                        cont = 1;
                    }
                } else {
                    numeroNodo = Integer.parseInt(etNumNodo.getText().toString()) - 1;
                    etNumNodo.setText("");
                    //Toast.makeText(this, "Numero Nodo: "+app.arregloIdRuta.size(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < app.arregloIdRuta.size(); i++) {
                        if (app.arregloIdRuta.get(i).equals(String.valueOf(numeroNodo))) {
                            x = (float) app.arregloLocationX.get(numeroNodo);
                            y = (float) app.arregloLocationY.get(numeroNodo);
                            floor = Integer.parseInt((String) app.arregloFloor.get(numeroNodo));
                            EventBus.getDefault().postSticky(new NodeNumber((int) x, (int) y, floor, numeroNodo));
                            finish();
                            //startActivity(i);
                        }
                    }
                }*/
                break;
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
