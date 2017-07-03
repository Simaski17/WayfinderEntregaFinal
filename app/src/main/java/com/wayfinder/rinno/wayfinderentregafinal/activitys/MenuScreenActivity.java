package com.wayfinder.rinno.wayfinderentregafinal.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wayfinder.rinno.wayfinderentregafinal.FirebaseApplication;
import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.RGlobal;
import com.wayfinder.rinno.wayfinderentregafinal.adapters.ComidaViewAdapter;
import com.wayfinder.rinno.wayfinderentregafinal.adapters.DescuentosAdapter;
import com.wayfinder.rinno.wayfinderentregafinal.adapters.TiendasViewAdapter;
import com.wayfinder.rinno.wayfinderentregafinal.algoritmo.Astar;
import com.wayfinder.rinno.wayfinderentregafinal.algoritmo.Graph;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingInit;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingInitFixed;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingPointEndView;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingPointView;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingRectView;
import com.wayfinder.rinno.wayfinderentregafinal.canvas.DrawingView;
import com.wayfinder.rinno.wayfinderentregafinal.fastscroller.RecyclerViewFastScroller;
import com.wayfinder.rinno.wayfinderentregafinal.model.AlphabetItem;
import com.wayfinder.rinno.wayfinderentregafinal.model.Busqueda;
import com.wayfinder.rinno.wayfinderentregafinal.model.Contextual;
import com.wayfinder.rinno.wayfinderentregafinal.model.Descuentos;
import com.wayfinder.rinno.wayfinderentregafinal.model.Message;
import com.wayfinder.rinno.wayfinderentregafinal.model.MessageNew;
import com.wayfinder.rinno.wayfinderentregafinal.model.NodeNumber;
import com.wayfinder.rinno.wayfinderentregafinal.model.Nodes;
import com.wayfinder.rinno.wayfinderentregafinal.model.PopupMapa;
import com.wayfinder.rinno.wayfinderentregafinal.model.RubroOpcion;
import com.wayfinder.rinno.wayfinderentregafinal.model.StoreNumber;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;


import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static com.wayfinder.rinno.wayfinderentregafinal.RGlobal.arrDescuentosPestanasBotones;
import static com.wayfinder.rinno.wayfinderentregafinal.model.Globales.alto;
import static com.wayfinder.rinno.wayfinderentregafinal.model.Globales.ancho;

public class MenuScreenActivity extends AppCompatActivity {


    @BindView(R.id.iv_logoApumanque_menu_screen)
    ImageView ivLogoApumanqueMenuScreen;
    @BindView(R.id.ll_LogoApumanque_menu_screen)
    LinearLayout llLogoApumanqueMenuScreen;
    @BindView(R.id.iv_linea_roja_mapa)
    ImageView ivLineaRojaMapa;
    @BindView(R.id.iv_icono_mapa)
    ImageView ivIconoMapa;
    @BindView(R.id.tv_texto_mapa)
    TextView tvTextoMapa;
    @BindView(R.id.iv_flecha_roja_mapa)
    ImageView ivFlechaRojaMapa;
    @BindView(R.id.ll_mapa_opcion)
    LinearLayout llMapaOpcion;
    @BindView(R.id.iv_linea_roja_tiendas)
    ImageView ivLineaRojaTiendas;
    @BindView(R.id.iv_icono_tiendas)
    ImageView ivIconoTiendas;
    @BindView(R.id.tv_texto_tiendas)
    TextView tvTextoTiendas;
    @BindView(R.id.iv_flecha_roja_tiendas)
    ImageView ivFlechaRojaTiendas;
    @BindView(R.id.ll_tiendas_opcion)
    LinearLayout llTiendasOpcion;
    @BindView(R.id.iv_linea_roja_comida)
    ImageView ivLineaRojaComida;
    @BindView(R.id.iv_icono_comida)
    ImageView ivIconoComida;
    @BindView(R.id.tv_texto_comida)
    TextView tvTextoComida;
    @BindView(R.id.iv_flecha_roja_comida)
    ImageView ivFlechaRojaComida;
    @BindView(R.id.ll_comida_opcion)
    LinearLayout llComidaOpcion;
    @BindView(R.id.iv_linea_roja_descuentos)
    ImageView ivLineaRojaDescuentos;
    @BindView(R.id.iv_icono_descuentos)
    ImageView ivIconoDescuentos;
    @BindView(R.id.tv_texto_descuentos)
    TextView tvTextoDescuentos;
    @BindView(R.id.iv_flecha_roja_descuentos)
    ImageView ivFlechaRojaDescuentos;
    @BindView(R.id.ll_descuentos_opcion)
    LinearLayout llDescuentosOpcion;
    @BindView(R.id.iv_linea_roja_participa)
    ImageView ivLineaRojaParticipa;
    @BindView(R.id.iv_icono_participa)
    ImageView ivIconoParticipa;
    @BindView(R.id.tv_texto_participa)
    TextView tvTextoParticipa;
    @BindView(R.id.iv_flecha_roja_participa)
    ImageView ivFlechaRojaParticipa;
    @BindView(R.id.ll_participa_opcion)
    LinearLayout llParticipaOpcion;
    @BindView(R.id.ll_Botones_menu_izquierdo)
    LinearLayout llBotonesMenuIzquierdo;
    @BindView(R.id.rl_boton_azul_tienda)
    RelativeLayout rlBotonAzulTienda;
    @BindView(R.id.rl_boton_naranja_tienda)
    RelativeLayout rlBotonNaranjaTienda;
    @BindView(R.id.ll_az_tienda)
    LinearLayout llAzTienda;
    @BindView(R.id.ll_rubros_tienda)
    LinearLayout llRubrosTienda;
    @BindView(R.id.ll_piso_tienda)
    LinearLayout llPisoTienda;
    @BindView(R.id.rl_botones_tienda)
    LinearLayout rlBotonesTienda;
    @BindView(R.id.ll_piso_uno_opciones)
    LinearLayout llPisoUnoOpciones;
    @BindView(R.id.ll_piso_dos_opciones)
    LinearLayout llPisoDosOpciones;
    @BindView(R.id.ll_piso_tres_opciones)
    LinearLayout llPisoTresOpciones;
    @BindView(R.id.ll_botones_pisos_tienda_opciones)
    LinearLayout llBotonesPisosTiendaOpciones;
    @BindView(R.id.ll_botones_pisos_tienda)
    LinearLayout llBotonesPisosTienda;
    @BindView(R.id.ll_tienda_opciones)
    LinearLayout llTiendaOpciones;
    @BindView(R.id.ll_numero_tienda)
    LinearLayout llNumeroTienda;
    @BindView(R.id.ll_piso_pasillo_tienda)
    LinearLayout llPisoPasilloTienda;
    @BindView(R.id.ll_piso_piso_tienda)
    LinearLayout llPisoPisoTienda;
    @BindView(R.id.ll_botones_tienda_opciones)
    LinearLayout llBotonesTiendaOpciones;
    @BindView(R.id.ll_botones_tienda)
    LinearLayout llBotonesTienda;
    @BindView(R.id.recycler_view_tienda)
    RecyclerView recyclerViewTienda;
    @BindView(R.id.ll_tienda_botones)
    LinearLayout llTiendaBotones;
    @BindView(R.id.rl_tienda_botones)
    RelativeLayout rlTiendaBotones;
    @BindView(R.id.ll_tienda)
    LinearLayout llTienda;
    @BindView(R.id.rl_boton_azul_comida)
    RelativeLayout rlBotonAzulComida;
    @BindView(R.id.rl_boton_naranja_comida)
    RelativeLayout rlBotonNaranjaComida;
    @BindView(R.id.tv_texto_restaurantes)
    TextView tvTextoRestaurantes;
    @BindView(R.id.ll_restaurantes)
    LinearLayout llRestaurantes;
    @BindView(R.id.tv_texto_cafeterias)
    TextView tvTextoCafeterias;
    @BindView(R.id.ll_cafeterias)
    LinearLayout llCafeterias;
    @BindView(R.id.tv_texto_confiterias)
    TextView tvTextoConfiterias;
    @BindView(R.id.ll_confiterias)
    LinearLayout llConfiterias;
    @BindView(R.id.tv_texto_heladerias)
    TextView tvTextoHeladerias;
    @BindView(R.id.ll_heladerias)
    LinearLayout llHeladerias;
    @BindView(R.id.rl_botones_comida)
    LinearLayout rlBotonesComida;
    @BindView(R.id.tv_comida_opciones)
    TextView tvComidaOpciones;
    @BindView(R.id.ll_comida_botones)
    LinearLayout llComidaBotones;
    @BindView(R.id.recycler_view_comida)
    RecyclerView recyclerViewComida;
    @BindView(R.id.ll_comida)
    LinearLayout llComida;
    @BindView(R.id.tv_tienda_opciones2)
    TextView tvTiendaOpciones2;
    @BindView(R.id.ll_tienda_opciones2)
    LinearLayout llTiendaOpciones2;
    @BindView(R.id.ll_numero_tienda2)
    LinearLayout llNumeroTienda2;
    @BindView(R.id.ll_piso_pasillo_tienda2)
    LinearLayout llPisoPasilloTienda2;
    @BindView(R.id.ll_botones_tienda_opciones2)
    LinearLayout llBotonesTiendaOpciones2;
    @BindView(R.id.ll_botones_tienda2)
    LinearLayout llBotonesTienda2;
    @BindView(R.id.ll_participa)
    LinearLayout llParticipa;
    @BindView(R.id.iv_participa)
    SimpleDraweeView ivParticipa;
    @BindView(R.id.rl_boton_azul_descuento)
    RelativeLayout rlBotonAzulDescuento;
    @BindView(R.id.rl_boton_naranja_descuento)
    RelativeLayout rlBotonNaranjaDescuento;
    /*@BindView(R.id.ll_todos_descuentos)
    LinearLayout llTodosDescuentos;
    @BindView(R.id.ll_vestuario_descuento)
    LinearLayout llVestuarioDescuento;
    @BindView(R.id.ll_comida_descuento)
    LinearLayout llComidaDescuento;
    @BindView(R.id.ll_deporte_descuento)
    LinearLayout llDeporteDescuento;
    @BindView(R.id.ll_accesorios_descuento)
    LinearLayout llAccesoriosDescuento;*/
    @BindView(R.id.rl_botones_descuentos)
    LinearLayout rlBotonesDescuentos;
    @BindView(R.id.ll_botones_descuentos)
    LinearLayout llBotonesDescuentos;
    @BindView(R.id.recycler_view_descuentos)
    RecyclerView rvDescuentos;
    @BindView(R.id.ll_descuentos)
    LinearLayout llDescuentos;
    @BindView(R.id.iv_edittext_tienda)
    ImageView ivEdittextTienda;
    @BindView(R.id.et_edittext_tienda)
    EditText etEdittextTienda;
    @BindView(R.id.rl_edittext_tienda)
    RelativeLayout rlEdittextTienda;
    @BindView(R.id.iv_edittext_comida)
    ImageView ivEdittextComida;
    @BindView(R.id.et_edittext_comida)
    EditText etEdittextComida;
    @BindView(R.id.rl_edittext_comida)
    RelativeLayout rlEdittextComida;
    @BindView(R.id.btEscalera1)
    ImageView btEscalera1;
    @BindView(R.id.btEscalera2)
    ImageView btEscalera2;
    @BindView(R.id.btEscalera3)
    ImageView btEscalera3;
    @BindView(R.id.btEscalera4)
    ImageView btEscalera4;
    @BindView(R.id.rlFondoMapaServicios)
    RelativeLayout rlFondoMapaServicios;
    @BindView(R.id.rlEstasAqui)
    RelativeLayout rlEstasAqui;
    @BindView(R.id.rlPublicidadIcono)
    RelativeLayout rlPublicidadIcono;
    @BindView(R.id.rlImagenEstasAqui)
    RelativeLayout rlImagenEstasAqui;
    @BindView(R.id.rlFondoMapa)
    RelativeLayout rlFondoMapa;
    @BindView(R.id.ivContinuarRuta)
    SimpleDraweeView ivContinuarRuta;
    @BindView(R.id.ivContinuarRuta2)
    SimpleDraweeView ivContinuarRuta2;
    @BindView(R.id.layoutMapa)
    RelativeLayout layoutMapa;
    @BindView(R.id.iv_regresar)
    ImageView ivRegresar;
    @BindView(R.id.ll_mapa)
    LinearLayout llMapa;
    @BindView(R.id.rl_PisoTresa)
    RelativeLayout rlPisoTresa;
    @BindView(R.id.rl_PisoTres)
    RelativeLayout rlPisoTres;
    @BindView(R.id.ll_PisoTres)
    LinearLayout llPisoTres;
    @BindView(R.id.rl_PisoDosa)
    RelativeLayout rlPisoDosa;
    @BindView(R.id.rl_PisoDos)
    RelativeLayout rlPisoDos;
    @BindView(R.id.ll_PisoDos)
    LinearLayout llPisoDos;
    @BindView(R.id.rl_PisoUnoa)
    RelativeLayout rlPisoUnoa;
    @BindView(R.id.rl_PisoUno)
    RelativeLayout rlPisoUno;
    @BindView(R.id.ll_PisoUno)
    LinearLayout llPisoUno;
    @BindView(R.id.iv_flechaRojas1)
    ImageView ivFlechaRojas1;
    @BindView(R.id.tvInformacionAndBanos)
    TextView tvInformacionAndBanos;
    @BindView(R.id.iv_s1)
    ImageView ivS1;
    @BindView(R.id.iv_s1a)
    ImageView ivS1a;
    @BindView(R.id.iv_lineaRojas1)
    ImageView ivLineaRojas1;
    @BindView(R.id.llInformacionAndBanos)
    LinearLayout llInformacionAndBanos;
    @BindView(R.id.iv_flechaRojas2)
    ImageView ivFlechaRojas2;
    @BindView(R.id.tvBanosDamaAndAscensor)
    TextView tvBanosDamaAndAscensor;
    @BindView(R.id.iv_s2)
    ImageView ivS2;
    @BindView(R.id.iv_s2a)
    ImageView ivS2a;
    @BindView(R.id.iv_lineaRojas2)
    ImageView ivLineaRojas2;
    @BindView(R.id.llBanosDamaAndAscensor)
    LinearLayout llBanosDamaAndAscensor;
    @BindView(R.id.iv_flechaRojas3)
    ImageView ivFlechaRojas3;
    @BindView(R.id.tvBanosCaballerosAndEstacionamiento)
    TextView tvBanosCaballerosAndEstacionamiento;
    @BindView(R.id.iv_s3)
    ImageView ivS3;
    @BindView(R.id.iv_s3a)
    ImageView ivS3a;
    @BindView(R.id.iv_lineaRojas3)
    ImageView ivLineaRojas3;
    @BindView(R.id.llBanosCaballerosAndEstacionamiento)
    LinearLayout llBanosCaballerosAndEstacionamiento;
    @BindView(R.id.iv_flechaRojas4)
    ImageView ivFlechaRojas4;
    @BindView(R.id.tvCajerosAndDescuentos)
    TextView tvCajerosAndDescuentos;
    @BindView(R.id.iv_s4)
    ImageView ivS4;
    @BindView(R.id.iv_s4a)
    ImageView ivS4a;
    @BindView(R.id.iv_lineaRojas4)
    ImageView ivLineaRojas4;
    @BindView(R.id.llCajerosAndDescuentos)
    LinearLayout llCajerosAndDescuentos;
    @BindView(R.id.iv_flechaRojas5)
    ImageView ivFlechaRojas5;
    @BindView(R.id.tvAscensor)
    TextView tvAscensor;
    @BindView(R.id.iv_s5)
    ImageView ivS5;
    @BindView(R.id.iv_s5a)
    ImageView ivS5a;
    @BindView(R.id.iv_lineaRojas5)
    ImageView ivLineaRojas5;
    @BindView(R.id.llAscensor)
    LinearLayout llAscensor;
    @BindView(R.id.iv_flechaRojas6)
    ImageView ivFlechaRojas6;
    @BindView(R.id.tvCochesSillas)
    TextView tvCochesSillas;
    @BindView(R.id.iv_s6)
    ImageView ivS6;
    @BindView(R.id.iv_s6a)
    ImageView ivS6a;
    @BindView(R.id.iv_lineaRojas6)
    ImageView ivLineaRojas6;
    @BindView(R.id.llCochesSillas)
    LinearLayout llCochesSillas;
    @BindView(R.id.iv_flechaRojas7)
    ImageView ivFlechaRojas7;
    @BindView(R.id.tvGuardaPeques)
    TextView tvGuardaPeques;
    @BindView(R.id.iv_s7)
    ImageView ivS7;
    @BindView(R.id.iv_s7a)
    ImageView ivS7a;
    @BindView(R.id.iv_lineaRojas7)
    ImageView ivLineaRojas7;
    @BindView(R.id.llGuardaPeques)
    LinearLayout llGuardaPeques;
    @BindView(R.id.iv_flechaRojas8)
    ImageView ivFlechaRojas8;
    @BindView(R.id.tvDescuentosPromociones)
    TextView tvDescuentosPromociones;
    @BindView(R.id.iv_s8)
    ImageView ivS8;
    @BindView(R.id.iv_s8a)
    ImageView ivS8a;
    @BindView(R.id.iv_lineaRojas8)
    ImageView ivLineaRojas8;
    @BindView(R.id.llDescuentosPromociones)
    LinearLayout llDescuentosPromociones;
    @BindView(R.id.ll_servicios)
    LinearLayout llServicios;
    @BindView(R.id.rlPisos)
    RelativeLayout rlPisos;
    @BindView(R.id.rl_lineaGris)
    RelativeLayout rlLineaGris;
    @BindView(R.id.tv_numero_opciones)
    TextView tvNumeroOpciones;
    @BindView(R.id.tv_piso_pasillo_comida)
    TextView tvPisoPasilloComida;
    @BindView(R.id.tv_piso_piso_comida)
    TextView tvPisoPisoComida;
    @BindView(R.id.tv_tienda_opciones)
    TextView tvTiendaOpciones;
    @BindView(R.id.tv_numero_tiendas)
    TextView tvNumeroTiendas;
    @BindView(R.id.tv_piso_pasillo_tienda)
    TextView tvPisoPasilloTienda;
    @BindView(R.id.tv_piso_piso_tienda)
    TextView tvPisoPisoTienda;


    @BindView(R.id.tvPopupTextoTienda)
    TextView tvPopupTextoTienda;
    @BindView(R.id.tvPopupTextoNumeroLocal)
    TextView tvPopupTextoNumeroLocal;
    @BindView(R.id.rlPopupTienda)
    RelativeLayout rlPopupTienda;
    @BindView(R.id.rlPisosText)
    RelativeLayout rlPisosText;

    @BindView(R.id.fast_scroller_tienda)
    RecyclerViewFastScroller fastScrollerTienda;
    @BindView(R.id.rlFlechaIzquierdaDescuentos)
    RelativeLayout rlFlechaIzquierdaDescuentos;
    @BindView(R.id.rlFlechaDerechaDescuentos)
    RelativeLayout rlFlechaDerechaDescuentos;
    @BindView(R.id.hsvDescuentos)
    HorizontalScrollView hsvDescuentos;
    /*@BindView(R.id.rlPuntoEstasAquiAnim)
    RelativeLayout rlPuntoEstasAquiAnim;
    @BindView(R.id.rlImagenPuntoEstasAquiAnim)
    RelativeLayout rlImagenPuntoEstasAquiAnim;
    @BindView(R.id.rlPuntoEstasAqui)
    RelativeLayout rlPuntoEstasAqui;
    @BindView(R.id.rlImagenPuntoEstasAqui)
    RelativeLayout rlImagenPuntoEstasAqui;*/

    //Strings
    private String mensaje;
    private String img;
    private String idStair;

    private String nombreTienda;
    private String numeroTienda;
    private String numeroTelefono;
    private String numeroId;
    private String email;
    private String web;

    //Integer
    private int opciones = 0;
    private int opcionBusqueda = 0;
    private int xNodeInit;
    private int yNodeInit;
    private int numberFloorNodeInit;
    private int startRoute;
    private int endRoute;
    private int contadorPiso = 0;
    private int idCont;
    private int idContNode;
    private int rect;
    private int opcionPantalla = 0;


    private int xPop = 0;
    private int yPop = 0;
    boolean verTiendaActivate = false;

    //drawing view canvas
    private DrawingView drawView;
    private DrawingPointView drawViewPoint;
    private DrawingPointEndView drawViewPointEnd;
    private DrawingRectView drawViewRect;
    private DrawingInit drawViewInit;
    private DrawingInitFixed drawViewInitFixed;

    //ImageView
    private SimpleDraweeView draweeView;
    private SimpleDraweeView dvImgStore;
    private SimpleDraweeView dvCambiarPiso;
    private ImageView imageOne;

    //RelativeLayout
    private RelativeLayout layout;
    //private RelativeLayout rlPublicidadIcono;

    private TextView tvDnombreTienda;
    private TextView tvporcentaje;

    //List
    private List<Nodes> puntos = new ArrayList<Nodes>();
    public List<Graph.Edge<String>> edges = new ArrayList<Graph.Edge<String>>();
    private List<String> stockList = new ArrayList<String>();

    //ArraysList
    private ArrayList arregloStair = new ArrayList();
    private ArrayList arregloStair2 = new ArrayList();
    private ArrayList arregloRutaFinal = new ArrayList();
    private ArrayList arregloFloorEnd = new ArrayList();

    ArrayList<Stores> listaTiendas = new ArrayList<>();
    ArrayList<Stores> listaFinal = new ArrayList<>();

    //Map
    public Map<Integer, Nodes> services = new HashMap<Integer, Nodes>();

    //Arrays
    private String[] arrayServices = new String[8];
    private int[] rectDib = new int[10];

    //TextView
    TextView textViewOpcion;

    //Views
    View viewOpcion1;
    View viewOpcion2;
    View viewOpcion3;

    //Temporizador
    public Temporizador temporizador;

    private TiendasViewAdapter listaTiendasAdapter;
    private ComidaViewAdapter listaComidaAdapter;


    public ArrayList<Stores> messages = new ArrayList<Stores>();
    public ArrayList<Stores> busquedaTienda = new ArrayList<Stores>();
    public ArrayList<String> arrDescuentosPestanas = new ArrayList<String>();

    public ArrayList<String> arrTemporal = new ArrayList<String>();
    public ArrayList<String> arrTemporalDos = new ArrayList<String>();

    private LinearLayoutManager layoutManager;
    private GridLayoutManager lLayout;


    public EditText etInicio;
    public EditText etFinal;
    public Button btir;

    private List<String> mDataArray;
    private List<AlphabetItem> mAlphabetItems;

    public static final String MyPreferences = "MyPrefs";

    SharedPreferences sharedPreferences;

    public int piso = 0;

    ObjectAnimator objectAnimator;

    List<Descuentos> itemsDescuentos = new ArrayList<Descuentos>();
    List<Descuentos> rowItemDescuentos = new ArrayList<Descuentos>();

    DescuentosAdapter descuentosAdapter;

    //CONTEXTUAL
    private String categoriaForContextual;
    private ArrayList<String> arrForContextual;
    public Map<String, Stores> arrStoresForContextual = new HashMap<String, Stores>();
    private String porcentajeContextual;
    private String nombreTiendaContextual;
    private String nombreTiendaContextualFin;
    private String numeroTiendaContextualFin;
    private int numeroPisoContextual;
    private int finalContContextual;
    private float xx1;
    private float yy1;


    TiendasViewAdapter adaptadorBuscar;
    TiendasViewAdapter adaptadorGeneral;
    TiendasViewAdapter adaptadorTienda;
    TiendasViewAdapter adaptadorRubro;
    TiendasViewAdapter adapterPiso;

    RelativeLayout rltv;

    boolean gridMoveMax = false;
    int contArrow = 0;


    List<String> strAlphabets = new ArrayList<>();

    List<String> sections = new ArrayList<>(26);
    ArrayList<Integer> mSectionPositions;
    int pos = 0;

    private Animation animFadeIn;
    private Animation animFadeOut;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_menu_screen);
        ButterKnife.bind(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /*INICIALIZAR CANVAS PARA LOS DIBUJOS*/
        drawViewInit = (DrawingInit) findViewById(R.id.drawingInit);
        drawViewInitFixed = (DrawingInitFixed) findViewById(R.id.drawingInitFixed);
        drawView = (DrawingView) findViewById(R.id.drawing); //Ruta Principal
        drawViewPoint = (DrawingPointView) findViewById(R.id.drawingPoint); //Punto de inicio de la ruta y animacion fade-in fade-out
        drawViewPointEnd = (DrawingPointEndView) findViewById(R.id.drawingPointEnd); //Punto final donde termina la ruta
        drawViewRect = (DrawingRectView) findViewById(R.id.drawingRect); //Rectangulo donde tienda sea igual cuadrado perfecto
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


          /*INICIALIZAR IMAGEONE SERVICIOS*/
        imageOne = new ImageView(this);
        ///////////////////////////////////////////////////////

        /*Imagen Dinamica. Cambia con relacion a la escalera habilitada*/
        draweeView = (SimpleDraweeView) findViewById(R.id.ivContinuarRuta);
        draweeView.setVisibility(GONE);
        dvCambiarPiso = (SimpleDraweeView) findViewById(R.id.ivContinuarRuta2);
        dvCambiarPiso.setVisibility(GONE);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        gridMoveMax = false;

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == animFadeIn) {
                    draweeView.startAnimation(animFadeOut);
                    draweeView.startAnimation(animFadeIn);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);

        /*Imagen Dinamica. Cambia con relacion a la tienda. Si la tienda no es un cuadrado perfecto se utiliza esta imagen para iluminarla*/
        dvImgStore = (SimpleDraweeView) findViewById(R.id.dvImgStore);
        //dvImgStore.setVisibility(View.GONE);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*INICIALIZAR TEXTVIEW*/
        textViewOpcion = new TextView(this);

        /*INICIALIZAR VIEWS*/
        viewOpcion1 = new View(this);
        viewOpcion2 = new View(this);
        viewOpcion3 = new View(this);

        /*INICIALIZAR ARRAYS*/
        arrayServices = new String[]{"information", "bathroomWomen", "bathroomMen", "atm", "lift", "babyCarrierAndWheelChair", "keeper", "parkingAtm", "descuentos"};
        /////////////////////////////////////////////////////////////////////

        /*INICIALIZAR RELATIVELAYOUT*/
        layout = (RelativeLayout) findViewById(R.id.rlFondoMapaServicios);
        //rlPublicidadIcono = (RelativeLayout) findViewById(R.id.rlPublicidadIcono);
        tvDnombreTienda = (TextView) findViewById(R.id.tvDnombreTienda);
        tvporcentaje = (TextView) findViewById(R.id.tvDporcentaje);

        llAzTienda.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
        llRestaurantes.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
        //llTodosDescuentos.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));

        /*INICIALIZAR TEMPORIZADOR*/
        temporizador = new Temporizador(60000, 1000);
        temporizador.start();

        //initialDataComidas();


        //Revisado por Alvaro.

        etEdittextTienda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.e("TAG", "BEFORE: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.e("TAG", "OnTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.e("TAG", "AFTER: ");

                etEdittextTienda.removeTextChangedListener(this);
                recyclerViewTienda.setAdapter(adaptadorGeneral);
                buscarTienda(s.toString(), RGlobal.tiendas);

                etEdittextTienda.addTextChangedListener(this);
                temporizador.cancel();
                temporizador.start();

            }
        });

        etEdittextTienda.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
                boolean handled = false;

                if (EditorInfo.IME_ACTION_DONE == actionId || EditorInfo.IME_ACTION_UNSPECIFIED == actionId) {
                    hideSoftKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        etEdittextComida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                etEdittextComida.removeTextChangedListener(this);
                buscarTiendaComida(s.toString(), RGlobal.listaBuscarComida);
                etEdittextComida.addTextChangedListener(this);
                temporizador.cancel();
                temporizador.start();

            }
        });

        etEdittextComida.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
                boolean handled = false;

                if (EditorInfo.IME_ACTION_DONE == actionId || EditorInfo.IME_ACTION_UNSPECIFIED == actionId) {
                    hideSoftKeyboard();
                    handled = true;
                }
                return handled;
            }

        });


        /*Evento click icono continuar ruta*/
        draweeView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                cambiarFondoPiso();
                stairEvent();
            }
        });

        rlPublicidadIcono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPublicidadIcono.setVisibility(GONE);
            }
        });


        rlFondoMapa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    temporizador.cancel();
                    temporizador.start();
                    //     Toast.makeText(MenuScreenActivity.this, "X: " + event.getX(), Toast.LENGTH_SHORT).show();
                } else {
                    //     Toast.makeText(MenuScreenActivity.this, "Detenido, X:" + event.getX() + ", Y:" + event.getY(), Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


        arrDescuentosPestanas = new ArrayList<String>();
        //arrDescuentosPestanas.add("Todos");

        for (int i = 0; i < RGlobal.listaDescuentosCategoria.size(); i++) {
            for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                if (RGlobal.listaDescuentosCategoria.get(i).getStoreNumber().equals(RGlobal.appF.messages.get(j).getStoreNumber().toString())) {
                    arrDescuentosPestanas.add(RGlobal.listaDescuentosCategoria.get(i).getId().toString());
                }
            }

        }

        Set<String> s = new LinkedHashSet<String>(arrDescuentosPestanas);
        arrDescuentosPestanas.clear();
        arrDescuentosPestanas.addAll(s);
        arrDescuentosPestanasBotones.clear();
        for (int i = 0; i < arrDescuentosPestanas.size(); i++) {
            for (int j = 0; j < RGlobal.appF.categoriesBotones.size(); j++) {
                if (arrDescuentosPestanas.get(i).equals(RGlobal.appF.categoriesBotones.get(j).getName().toString())) {
                    arrDescuentosPestanasBotones.add(RGlobal.appF.categoriesBotones.get(j).getCategory().toString());
                }
            }
        }

        Collections.sort(arrDescuentosPestanasBotones, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        arrDescuentosPestanasBotones.add(0, "Todos");

        for (int i = 0; i < arrDescuentosPestanasBotones.size(); i++) {

            rltv = new RelativeLayout(this);
            rltv.setGravity(Gravity.CENTER_HORIZONTAL);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(450, 300);

            rltv.setLayoutParams(layoutParams);

            final TextView tvt = new TextView(this);
            tvt.setText(arrDescuentosPestanasBotones.get(i).toString());

            int paddingPixel = 25;
            float density = getApplicationContext().getResources().getDisplayMetrics().density;
            int paddingDp = (int) (paddingPixel * density);
            tvt.setPadding(paddingDp, paddingDp, paddingDp, 0);
            tvt.setTextSize(28);
            tvt.setTextColor(Color.WHITE);

            rltv.addView(tvt);
            layoutParams.setMargins(1, 0, 0, 0);
            rltv.setTag(i);

            rlBotonesDescuentos.addView(rltv, layoutParams);
            rlBotonesDescuentos.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wblr));

            if (rltv.getTag().equals(0)) {
                rltv.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
            } else {
                rltv.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
            }

            rltv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    temporizador.cancel();
                    temporizador.start();
                    recorre();
                    hsvDescuentos.scrollTo(0, 0);
                    hsvDescuentos.scrollTo((((int) v.getTag() - 1) * 450), 0);
                    v.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));

                    if (tvt.getText().toString().equals("Todos")) {
                        initialDataDescuento();
                    } else {
                        rowItemDescuentos = new ArrayList<>();
                        itemsDescuentos = new ArrayList<>();
                        itemsDescuentos.clear();
                        arrTemporal.clear();

                        for (int i = 0; i < RGlobal.appF.categoriesBotones.size(); i++) {
                            if (RGlobal.appF.categoriesBotones.get(i).getCategory().equals(tvt.getText().toString())) {
                                String categoriesID;
                                categoriesID = RGlobal.appF.categoriesBotones.get(i).getName().toString();
                                for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                                    if (RGlobal.appF.messages.get(j).getCategory().equals(categoriesID)) {
                                        arrTemporal.add(RGlobal.appF.messages.get(j).getStoreNumber().toString());
                                    }
                                }
                            }
                        }

                        itemsDescuentos.clear();
                        for (int i = 0; i < RGlobal.listaDescuentosCategoria.size(); i++) {
                            for (int j = 0; j < arrTemporal.size(); j++) {
                                if (RGlobal.listaDescuentosCategoria.get(i).getStoreNumber().equals(arrTemporal.get(j).toString())) {
                                    itemsDescuentos.add(new Descuentos(RGlobal.listaDescuentosCategoria.get(i).getImgURLWayfinder().toString()));
                                }
                            }
                        }
                        lLayout = new GridLayoutManager(getApplicationContext(), 3);
                        rvDescuentos.setHasFixedSize(true);
                        rvDescuentos.setLayoutManager(lLayout);

                        descuentosAdapter = new DescuentosAdapter(getApplicationContext(), itemsDescuentos);
                        rvDescuentos.setAdapter(descuentosAdapter);
                    }
                }
            });
        }

        hsvDescuentos.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollX = hsvDescuentos.getScrollX();
                if (scrollX == 0) {
                    gridMoveMax = false;
                    rlFlechaIzquierdaDescuentos.animate().alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            rlFlechaIzquierdaDescuentos.setVisibility(GONE);
                        }
                    });
                    contArrow = 0;
                } else if (scrollX == ((450 * (arrDescuentosPestanasBotones.size()) - ((3 * 450) + 220)))) {
                    gridMoveMax = true;
                    rlFlechaDerechaDescuentos.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            rlFlechaDerechaDescuentos.setVisibility(View.GONE);

                        }
                    });
                    contArrow = 0;
                } else {
                    gridMoveMax = false;
                    if (contArrow == 0) {
                        if (rlFlechaDerechaDescuentos.getVisibility() == View.GONE) {
                            rlFlechaDerechaDescuentos.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    rlFlechaDerechaDescuentos.setVisibility(View.VISIBLE);
                                }
                            });
                        } else if (rlFlechaIzquierdaDescuentos.getVisibility() == View.GONE) {
                            rlFlechaIzquierdaDescuentos.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    rlFlechaIzquierdaDescuentos.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        contArrow++;
                    }
                }
            }
        });

    }

    private void buscarTienda(String query, ArrayList<Stores> listaCompleta) {
        temporizador.cancel();
        temporizador.start();
        ArrayList<Stores> listaSugerida = new ArrayList<>();

        if (query.length() > 0) {
            for (Stores tienda : listaCompleta) {
                if ((StringUtils.stripAccents(tienda.getName()).toLowerCase().contains(query.toLowerCase())) || (tienda.getTags().toString().toLowerCase().contains(query.toLowerCase())) ||
                        (String.valueOf(tienda.getFloor()).toLowerCase().startsWith(query)) || ((tienda.getStoreNumber().toLowerCase().startsWith(query)))) {
                    listaSugerida.add(tienda);
                }
            }
            adaptadorBuscar = new TiendasViewAdapter(0, listaSugerida, this);
            recyclerViewTienda.setAdapter(adaptadorBuscar);
        } else {
            adaptadorBuscar = new TiendasViewAdapter(0, RGlobal.listaBuscarComida, this);
            recyclerViewTienda.setAdapter(adaptadorBuscar);
        }

    }

    private void buscarTiendaComida(String query, ArrayList<Stores> listaCompleta) {
        temporizador.cancel();
        temporizador.start();
        ArrayList<Stores> listaSugerida = new ArrayList<>();
        if (query.length() > 0) {
            for (Stores tienda : listaCompleta) {

                if ((StringUtils.stripAccents(tienda.getName()).toLowerCase().contains(query.toLowerCase())) || (tienda.getTags().toString().toLowerCase().contains(query.toLowerCase())) ||
                        (String.valueOf(tienda.getFloor()).toLowerCase().startsWith(query)) || ((tienda.getStoreNumber().toLowerCase().startsWith(query)))) {
                    listaSugerida.add(tienda);
                }
            }
            recyclerViewComida.setAdapter(new ComidaViewAdapter(listaSugerida, this));
        } else {
            recyclerViewComida.setAdapter(new ComidaViewAdapter(RGlobal.listaBuscarComida, this));
        }

    }


    public void recorre() {
        itemsDescuentos = new ArrayList<>();
        int count = rlBotonesDescuentos.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = rlBotonesDescuentos.getChildAt(i);
            if (view instanceof RelativeLayout) {
                RelativeLayout rl = (RelativeLayout) view;
                rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
            }
        }

    }

    public void lineaFlechaVisible(View op1, View op2, View op3, View op4, View op5, View op6, View op7, View op8, View op9, View op10) {
        op1.setVisibility(View.VISIBLE);
        op2.setVisibility(View.VISIBLE);
        op3.setVisibility(GONE);
        op4.setVisibility(GONE);
        op5.setVisibility(GONE);
        op6.setVisibility(GONE);
        op7.setVisibility(GONE);
        op8.setVisibility(GONE);
        op9.setVisibility(GONE);
        op10.setVisibility(GONE);
    }

    @OnClick({R.id.iv_logoApumanque_menu_screen, R.id.ll_home_opcion, R.id.ll_mapa_opcion, R.id.ll_tiendas_opcion, R.id.ll_comida_opcion, R.id.ll_descuentos_opcion, R.id.ll_participa_opcion})
    public void onViewClicked(View view) {

        Bundle params = new Bundle();
        params.putInt("ButtonID",view.getId());
        String mensaje = null;

        switch (view.getId()) {
            case R.id.ll_home_opcion:
            case R.id.iv_logoApumanque_menu_screen:
                draweeView.setVisibility(GONE);
                dvCambiarPiso.setVisibility(GONE);
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                hideSoftKeyboard();

                // overridePendingTransition(0, 0);
                finish();
                mensaje = "inicio";
                break;
            case R.id.ll_mapa_opcion:
                selectTab(llMapaOpcion);
                lineaFlechaVisible(ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
                llComida.setVisibility(GONE);
                llTienda.setVisibility(GONE);
                llParticipa.setVisibility(GONE);
                llDescuentos.setVisibility(GONE);
                rlBotonNaranjaTienda.setVisibility(GONE);
                rlEdittextTienda.setVisibility(GONE);
                rlBotonAzulTienda.setVisibility(View.VISIBLE);
                rlBotonNaranjaComida.setVisibility(GONE);
                rlEdittextComida.setVisibility(GONE);
                rlBotonAzulComida.setVisibility(View.VISIBLE);
                llMapa.setVisibility(View.VISIBLE);
                llServicios.setVisibility(View.VISIBLE);
                draweeView.setVisibility(GONE);
                dvCambiarPiso.setVisibility(GONE);
                limpiarTodo();
                sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
                contadorPiso = sharedPreferences.getInt("piso", 0);
                cambiarFondoPiso();
                hideSoftKeyboard();

                limpiarBuscadorTienda();
                mensaje = "Mapa_Inicio";

                break;
            case R.id.ll_tiendas_opcion:
                initialDataTiendas();
                hideSoftKeyboard();
                mensaje = "Tiendas_Inicio";

                break;
            case R.id.ll_comida_opcion:

                initialDataComidas();

                hideSoftKeyboard();
                mensaje = "Comida_Inicio";

                break;
            case R.id.ll_descuentos_opcion:
                mensaje = "Descuentos_Inicio";
                limpiarTodo();
                initialDataDescuento();
                opcionPantalla = 4;
                selectTab(llDescuentosOpcion);
                recorre();
                int count = rlBotonesDescuentos.getChildCount();
                for (int i = 0; i < count; i++) {
                    View vista = rlBotonesDescuentos.getChildAt(i);
                    if (vista instanceof RelativeLayout) {
                        if (i == 0) {
                            RelativeLayout rl = (RelativeLayout) vista;
                            rltv = rl;
                            hsvDescuentos.scrollTo(0, 0);
                            rlFlechaDerechaDescuentos.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        }
                    }
                }
                if (rltv.getTag().equals(0)) {
                    rltv.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
                } else {
                    rltv.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
                }
                lineaFlechaVisible(ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaParticipa, ivFlechaRojaParticipa);
                llComida.setVisibility(GONE);
                llTienda.setVisibility(GONE);
                llParticipa.setVisibility(GONE);
                llDescuentos.setVisibility(View.VISIBLE);
                rlBotonNaranjaTienda.setVisibility(GONE);
                rlEdittextTienda.setVisibility(GONE);
                rlBotonAzulTienda.setVisibility(View.VISIBLE);
                rlBotonNaranjaComida.setVisibility(GONE);
                rlEdittextComida.setVisibility(GONE);
                rlBotonAzulComida.setVisibility(View.VISIBLE);
                draweeView.setVisibility(GONE);
                dvCambiarPiso.setVisibility(GONE);
                hideSoftKeyboard();
                limpiar();
                break;
            case R.id.ll_participa_opcion:
                mensaje = "Participa_Inicio";
                opcionPantalla = 5;
                initialDataParticipa();

                selectTab(llParticipaOpcion);
                lineaFlechaVisible(ivLineaRojaParticipa, ivFlechaRojaParticipa, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa);
                llComida.setVisibility(GONE);
                llTienda.setVisibility(GONE);
                llDescuentos.setVisibility(GONE);
                llParticipa.setVisibility(View.VISIBLE);
                rlBotonNaranjaTienda.setVisibility(GONE);
                rlEdittextTienda.setVisibility(GONE);
                rlBotonAzulTienda.setVisibility(View.VISIBLE);
                rlBotonNaranjaComida.setVisibility(GONE);
                rlEdittextComida.setVisibility(GONE);
                rlBotonAzulComida.setVisibility(View.VISIBLE);
                draweeView.setVisibility(GONE);
                dvCambiarPiso.setVisibility(GONE);
                hideSoftKeyboard();
                limpiar();
                break;
        }
        Log.d("TAG","Boton Presionado "+mensaje);
        mFirebaseAnalytics.logEvent(mensaje,params);
    }

    @OnClick({R.id.rl_boton_azul_tienda, R.id.rl_boton_naranja_tienda, R.id.ll_az_tienda, R.id.ll_rubros_tienda, R.id.ll_piso_tienda, R.id.ll_piso_uno_opciones, R.id.ll_piso_dos_opciones, R.id.ll_piso_tres_opciones,
            R.id.rlFlechaIzquierdaDescuentos, R.id.rlFlechaDerechaDescuentos})
    public void onViewClickedTienda(View view) {
        switch (view.getId()) {
            case R.id.rl_boton_azul_tienda:
                rlBotonAzulTienda.setVisibility(GONE);
                rlBotonNaranjaTienda.setVisibility(View.VISIBLE);
                rlEdittextTienda.setVisibility(View.VISIBLE);
                Drawable drawable = etEdittextTienda.getBackground();
                drawable.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                etEdittextTienda.setBackgroundDrawable(drawable);
                etEdittextTienda.requestFocus();
                etEdittextTienda.setText("");
                showSoftKeyboard();
                selectTabTienda(llRubrosTienda, llPisoTienda, llAzTienda);
                tvTiendaOpciones.setText(R.string.tiendas);
                tvNumeroTiendas.setVisibility(View.VISIBLE);
                tvPisoPasilloTienda.setVisibility(View.VISIBLE);
                tvPisoPisoTienda.setVisibility(View.VISIBLE);
                llBotonesPisosTienda.setVisibility(GONE);
                llBotonesTienda2.setVisibility(GONE);
                llBotonesTienda.setVisibility(View.VISIBLE);
                listaTiendasAdapter = new TiendasViewAdapter(0, RGlobal.tiendas, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);
                //  fastScrollerTienda.setVisibility(GONE);
                break;
            case R.id.rl_boton_naranja_tienda:
                rlBotonNaranjaTienda.setVisibility(GONE);
                rlEdittextTienda.setVisibility(GONE);
                rlBotonAzulTienda.setVisibility(View.VISIBLE);
                selectTabTienda(llRubrosTienda, llPisoTienda, llAzTienda);
                hideSoftKeyboard();
                //  selectTabTienda(llRubrosTienda, llPisoTienda, llAzTienda);
                llBotonesPisosTienda.setVisibility(GONE);
                llBotonesTienda2.setVisibility(GONE);
                llBotonesTienda.setVisibility(View.VISIBLE);
                etEdittextTienda.setText("");
                //fastScrollerTienda.setVisibility(View.VISIBLE);


                break;
            case R.id.ll_az_tienda:
                recyclerViewTienda.setVisibility(View.VISIBLE);
                selectTabTienda(llRubrosTienda, llPisoTienda, llAzTienda);
                llBotonesPisosTienda.setVisibility(GONE);
                llBotonesTienda2.setVisibility(GONE);
                llBotonesTienda.setVisibility(View.VISIBLE);
                RGlobal.listaBuscarComida = RGlobal.tiendas;

                listaTiendasAdapter = new TiendasViewAdapter(0, RGlobal.tiendas, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);

                fastScrollerTienda.setVisibility(View.VISIBLE);


                tvTiendaOpciones.setText(R.string.tiendas);
                tvNumeroTiendas.setVisibility(View.VISIBLE);
                tvPisoPasilloTienda.setVisibility(View.VISIBLE);
                tvPisoPisoTienda.setVisibility(View.VISIBLE);
                hideSoftKeyboard();
                limpiarBuscadorTienda();
                break;

            case R.id.ll_rubros_tienda:

                fastScrollerTienda.setVisibility(View.GONE);

                selectTabTienda(llAzTienda, llPisoTienda, llRubrosTienda);
                llBotonesPisosTienda.setVisibility(GONE);
                llBotonesTienda2.setVisibility(GONE);
                llBotonesTienda.setVisibility(View.VISIBLE);

                for (Stores store : RGlobal.categorias) {
                    Log.d("Test", store.getName());
                }
                TiendasViewAdapter tt = new TiendasViewAdapter(1, RGlobal.categorias, getApplicationContext());

                recyclerViewTienda.setAdapter(tt);


                tvTiendaOpciones.setText("Rubros");
                tvNumeroTiendas.setVisibility(GONE);
                tvPisoPasilloTienda.setVisibility(GONE);
                tvPisoPisoTienda.setVisibility(GONE);
                hideSoftKeyboard();
                //   limpiarBuscadorTienda();
                break;
            case R.id.ll_piso_tienda:
                limpiarBuscadorTienda();
                fastScrollerTienda.setVisibility(View.GONE);

                selectTabTienda(llRubrosTienda, llAzTienda, llPisoTienda);

                llBotonesPisosTienda.setVisibility(View.VISIBLE);
                llBotonesTienda.setVisibility(GONE);
                llBotonesTienda2.setVisibility(View.VISIBLE);

                llPisoUnoOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.ovalo_relleno_azul));
                llPisoDosOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_dos));
                llPisoTresOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_tres));


                listaTiendasAdapter = new TiendasViewAdapter(2, RGlobal.tiendasPrimerPiso, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);


                hideSoftKeyboard();

                break;
            case R.id.ll_piso_uno_opciones:
                fastScrollerTienda.setVisibility(View.GONE);

                llPisoUnoOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.ovalo_relleno_azul));
                llPisoDosOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_dos));
                llPisoTresOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_tres));

                listaTiendasAdapter = new TiendasViewAdapter(2, RGlobal.tiendasPrimerPiso, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);

                hideSoftKeyboard();


                break;
            case R.id.ll_piso_dos_opciones:
                fastScrollerTienda.setVisibility(View.GONE);

                llPisoDosOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.ovalo_relleno_naranja));
                llPisoUnoOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_uno));
                llPisoTresOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_tres));

                listaTiendasAdapter = new TiendasViewAdapter(2, RGlobal.tiendasSegundoPiso, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);
                hideSoftKeyboard();


                break;
            case R.id.ll_piso_tres_opciones:
                hideSoftKeyboard();
                fastScrollerTienda.setVisibility(View.GONE);

                llPisoTresOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.ovalo_relleno_verde));
                llPisoDosOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_dos));
                llPisoUnoOpciones.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_piso_uno));

                listaTiendasAdapter = new TiendasViewAdapter(2, RGlobal.tiendasTercerPiso, getApplicationContext());
                recyclerViewTienda.setAdapter(listaTiendasAdapter);


                break;
            case R.id.rlFlechaIzquierdaDescuentos:
                try {
                    hsvDescuentos.smoothScrollBy(-450, 0);
                } catch (Exception ignored) {

                }
                break;
            case R.id.rlFlechaDerechaDescuentos:
                try {
                    hsvDescuentos.smoothScrollBy(450, 0);
                } catch (Exception ignored) {

                }

                break;
        }
    }

    @OnClick({R.id.rl_boton_azul_comida, R.id.rl_boton_naranja_comida, R.id.ll_restaurantes, R.id.ll_cafeterias, R.id.ll_confiterias, R.id.ll_heladerias})
    public void onViewClickedComida(View view) {
        switch (view.getId()) {
            case R.id.rl_boton_azul_comida:
                rlBotonAzulComida.setVisibility(GONE);
                rlBotonNaranjaComida.setVisibility(View.VISIBLE);
                rlEdittextComida.setVisibility(View.VISIBLE);
                Drawable drawable = etEdittextComida.getBackground();
                drawable.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                etEdittextComida.setBackgroundDrawable(drawable);
                etEdittextComida.requestFocus();
                showSoftKeyboard();


                break;
            case R.id.rl_boton_naranja_comida:

                listaComidaAdapter = new ComidaViewAdapter(RGlobal.listaBuscarComida, getApplicationContext());
                recyclerViewComida.setAdapter(listaComidaAdapter);
                hideSoftKeyboard();
                rlBotonAzulComida.setVisibility(View.VISIBLE);
                rlBotonNaranjaComida.setVisibility(GONE);
                rlEdittextComida.setVisibility(GONE);
                etEdittextComida.setText("");
                etEdittextComida.setText("");

                break;
            case R.id.ll_restaurantes:
                initialDataComidas();

                break;
            case R.id.ll_cafeterias:
                selectTabComida(llConfiterias, llHeladerias, llRestaurantes, llCafeterias);
                tvComidaOpciones.setText(R.string.cafeterias);
                RGlobal.listaBuscarComida = RGlobal.listaCafeteria;

                listaComidaAdapter = new ComidaViewAdapter(RGlobal.listaCafeteria, getApplicationContext());
                recyclerViewComida.setAdapter(listaComidaAdapter);

                hideSoftKeyboard();

                break;
            case R.id.ll_confiterias:
                selectTabComida(llHeladerias, llRestaurantes, llCafeterias, llConfiterias);
                tvComidaOpciones.setText(R.string.confiteria);
                RGlobal.listaBuscarComida = RGlobal.listaChocolateria;

                listaComidaAdapter = new ComidaViewAdapter(RGlobal.listaChocolateria, getApplicationContext());
                recyclerViewComida.setAdapter(listaComidaAdapter);

                hideSoftKeyboard();

                break;
            case R.id.ll_heladerias:
                selectTabComida(llRestaurantes, llCafeterias, llConfiterias, llHeladerias);
                tvComidaOpciones.setText(R.string.heladeria);
                RGlobal.listaBuscarComida = RGlobal.listaHeladeria;

                listaComidaAdapter = new ComidaViewAdapter(RGlobal.listaHeladeria, getApplicationContext());
                recyclerViewComida.setAdapter(listaComidaAdapter);
                hideSoftKeyboard();

                break;
        }
    }

    @OnClick({R.id.rl_boton_azul_descuento, R.id.rl_boton_naranja_descuento})
    public void onViewClickedDescuento(View view) {
        switch (view.getId()) {
            case R.id.rl_boton_azul_descuento:
                break;
            case R.id.rl_boton_naranja_descuento:
                break;
        }
    }

    @OnClick({R.id.btEscalera1, R.id.btEscalera2, R.id.btEscalera3, R.id.btEscalera4, R.id.ivContinuarRuta, R.id.iv_regresar})
    public void onViewClickedMapa(View view) {
        switch (view.getId()) {
            case R.id.btEscalera1:
                cambiarFondoPiso();
                stairEvent();
                break;
            case R.id.btEscalera2:
                cambiarFondoPiso();
                stairEvent();
                break;
            case R.id.btEscalera3:
                cambiarFondoPiso();
                stairEvent();
                break;
            case R.id.btEscalera4:
                cambiarFondoPiso();
                stairEvent();
                break;
            case R.id.ivContinuarRuta:
                break;
            case R.id.iv_regresar:
                if (opcionPantalla == 0) {
                    Intent intent = new Intent(this, MainScreenActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else if (opcionPantalla == 2) {
                    limpiar();
                    selectTab(llTiendasOpcion);
                    lineaFlechaVisible(ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
                    llMapa.setVisibility(GONE);
                    llServicios.setVisibility(GONE);
                    llComida.setVisibility(GONE);
                    llDescuentos.setVisibility(GONE);
                    llParticipa.setVisibility(GONE);
                    llTienda.setVisibility(View.VISIBLE);
                } else if (opcionPantalla == 3) {
                    limpiar();
                    selectTab(llComidaOpcion);
                    lineaFlechaVisible(ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
                    llMapa.setVisibility(GONE);
                    llServicios.setVisibility(GONE);
                    llComida.setVisibility(View.VISIBLE);
                    llDescuentos.setVisibility(GONE);
                    llParticipa.setVisibility(GONE);
                    llTienda.setVisibility(GONE);
                } else if (opcionPantalla == 4) {
                    selectTab(llDescuentosOpcion);
                    lineaFlechaVisible(ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaParticipa, ivFlechaRojaParticipa);
                    llMapa.setVisibility(GONE);
                    llServicios.setVisibility(GONE);
                    llComida.setVisibility(GONE);
                    llDescuentos.setVisibility(View.VISIBLE);
                    llParticipa.setVisibility(GONE);
                    llTienda.setVisibility(GONE);
                    limpiar();

                } else if (opcionPantalla == 5) {
                    limpiar();
                    selectTab(llParticipaOpcion);
                    lineaFlechaVisible(ivLineaRojaParticipa, ivFlechaRojaParticipa, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa);
                    llMapa.setVisibility(GONE);
                    llServicios.setVisibility(GONE);
                    llComida.setVisibility(GONE);
                    llDescuentos.setVisibility(GONE);
                    llParticipa.setVisibility(View.VISIBLE);
                    llTienda.setVisibility(GONE);
                }
                break;
        }
    }

    @OnClick({R.id.iv_regresar, R.id.ll_PisoTres, R.id.ll_PisoDos, R.id.ll_PisoUno, R.id.llInformacionAndBanos, R.id.llBanosDamaAndAscensor, R.id.llBanosCaballerosAndEstacionamiento, R.id.llCajerosAndDescuentos, R.id.llAscensor, R.id.llCochesSillas, R.id.llGuardaPeques, R.id.llDescuentosPromociones})
    public void onViewClickedServicios(View view) {
        switch (view.getId()) {
            case R.id.iv_regresar:
                break;
            case R.id.ll_PisoTres:
                contadorPiso = 3;
                quitarColorOpcionServicio(textViewOpcion, viewOpcion1, viewOpcion2, viewOpcion3);
                cambiarServiciosLayout(contadorPiso);
                cambiarFondoPiso();
                limpiarTodo();
                rlLineaGris.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_PisoDos:
                contadorPiso = 2;
                quitarColorOpcionServicio(textViewOpcion, viewOpcion1, viewOpcion2, viewOpcion3);
                cambiarServiciosLayout(contadorPiso);
                cambiarFondoPiso();
                limpiarTodo();
                rlLineaGris.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_PisoUno:
                contadorPiso = 1;
                quitarColorOpcionServicio(textViewOpcion, viewOpcion1, viewOpcion2, viewOpcion3);
                cambiarServiciosLayout(contadorPiso);
                cambiarFondoPiso();
                limpiarTodo();
                rlLineaGris.setVisibility(GONE);
                break;
            case R.id.llInformacionAndBanos:
                textViewOpcion = tvInformacionAndBanos;
                viewOpcion1 = ivS1a;
                viewOpcion2 = ivFlechaRojas1;
                viewOpcion3 = ivLineaRojas1;
                cambiarColorServicios(tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3,
                        tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                if (contadorPiso <= 2) {
                    cambiarServicios(arrayServices[0], 0);
                } else {
                    customDialog(5, "");
                }
                limpiar();
                break;
            case R.id.llBanosDamaAndAscensor:
                textViewOpcion = tvBanosDamaAndAscensor;
                viewOpcion1 = ivS2a;
                viewOpcion2 = ivFlechaRojas2;
                viewOpcion3 = ivLineaRojas2;
                cambiarColorServicios(tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3,
                        tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                if (contadorPiso == 1) {
                    cambiarServicios(arrayServices[1], 1);
                } else if (contadorPiso == 2) {
                    customDialog(1, "");
                    cambiarServicios(arrayServices[1], 1);
                } else {
                    cambiarServicios(arrayServices[2], 8);
                }
                limpiar();
                break;
            case R.id.llBanosCaballerosAndEstacionamiento:
                textViewOpcion = tvBanosCaballerosAndEstacionamiento;
                viewOpcion1 = ivS3a;
                viewOpcion2 = ivFlechaRojas3;
                viewOpcion3 = ivLineaRojas3;
                cambiarColorServicios(tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1,
                        tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                if (contadorPiso == 2) {
                    cambiarServicios(arrayServices[2], 2);

                } else if (contadorPiso == 1) {
                    customDialog(2, "");
                    cambiarServicios(arrayServices[2], 2);
                } else {
                    cambiarServicios(arrayServices[7], 9);
                }
                limpiar();
                break;
            case R.id.llCajerosAndDescuentos:
                textViewOpcion = tvCajerosAndDescuentos;
                viewOpcion1 = ivS4a;
                viewOpcion2 = ivFlechaRojas4;
                viewOpcion3 = ivLineaRojas4;
                cambiarColorServicios(tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1,
                        tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);

                cambiarServicios(arrayServices[3], 3);

                if (contadorPiso == 3) {
                    customDialog(6, "");
                }
                limpiar();
                break;
            case R.id.llAscensor:
                textViewOpcion = tvAscensor;
                viewOpcion1 = ivS5a;
                viewOpcion2 = ivFlechaRojas5;
                viewOpcion3 = ivLineaRojas5;
                cambiarColorServicios(tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1,
                        tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                cambiarServicios(arrayServices[4], 4);
                limpiar();
                break;
            case R.id.llCochesSillas:
                textViewOpcion = tvCochesSillas;
                viewOpcion1 = ivS6a;
                viewOpcion2 = ivFlechaRojas6;
                viewOpcion3 = ivLineaRojas6;
                cambiarColorServicios(tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6, tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1,
                        tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                if (contadorPiso == 1) {
                    cambiarServicios(arrayServices[5], 5);
                } else if (contadorPiso == 2) {
                    customDialog(4, "");
                    cambiarServicios(arrayServices[5], 5);
                } else if (contadorPiso == 3) {
                    customDialog(4, "");
                    cambiarServicios(arrayServices[5], 5);
                }
                limpiar();
                break;
            case R.id.llGuardaPeques:

                textViewOpcion = tvGuardaPeques;
                viewOpcion1 = ivS7a;
                viewOpcion2 = ivFlechaRojas7;
                viewOpcion3 = ivLineaRojas7;
                cambiarColorServicios(tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6, tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3, tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1,
                        tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8);
                if (contadorPiso == 1) {
                    cambiarServicios(arrayServices[6], 6);
                } else if (contadorPiso == 2) {
                    customDialog(3, "");
                    cambiarServicios(arrayServices[6], 6);
                } else if (contadorPiso == 3) {
                    customDialog(3, "");
                    cambiarServicios(arrayServices[6], 6);
                }
                limpiar();
                break;
            case R.id.llDescuentosPromociones:
                textViewOpcion = tvDescuentosPromociones;
                viewOpcion1 = ivS8a;
                viewOpcion2 = ivFlechaRojas8;
                viewOpcion3 = ivLineaRojas8;
                cambiarColorServicios(tvDescuentosPromociones, ivS8a, ivFlechaRojas8, ivLineaRojas8, tvGuardaPeques, ivS7a, ivFlechaRojas7, ivLineaRojas7, tvCochesSillas, ivS6a, ivFlechaRojas6, ivLineaRojas6,
                        tvAscensor, ivS5a, ivFlechaRojas5, ivLineaRojas5, tvCajerosAndDescuentos, ivS4a, ivFlechaRojas4, ivLineaRojas4, tvBanosCaballerosAndEstacionamiento, ivS3a, ivFlechaRojas3, ivLineaRojas3,
                        tvBanosDamaAndAscensor, ivS2a, ivFlechaRojas2, ivLineaRojas2, tvInformacionAndBanos, ivS1a, ivFlechaRojas1, ivLineaRojas1);
                cambiarServicios(arrayServices[8], 10);
                limpiar();
                break;
        }
    }

    private void selectTab(View v) {
        temporizador.cancel();
        temporizador.start();
        View parent = llBotonesMenuIzquierdo;
        ArrayList<View> touchables = parent.getTouchables();
        for (View b : touchables) {
            if (b instanceof LinearLayout) {
                b.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo));
            }
        }
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.recyclerOpciones));
        hideSoftKeyboard();
    }

    /*public void puntoEstasAqui(float x, float y) {
        float xi;
        float yi;

        xi = x;
        yi = y;

        rlPuntoEstasAqui.setVisibility(View.VISIBLE);
        rlPuntoEstasAquiAnim.setVisibility(View.VISIBLE);
        rlPuntoEstasAqui.setX(xi - 9);
        rlPuntoEstasAqui.setY(yi - 13);
        rlPuntoEstasAquiAnim.setX(xi - 17);
        rlPuntoEstasAquiAnim.setY(yi - 20);
    }*/


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageMain(MessageNew event) {
        mensaje = event.getMensaje();
        if (mensaje.equals("mapa")) {
            selectTab(llMapaOpcion);
            lineaFlechaVisible(ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
            llComida.setVisibility(GONE);
            llTienda.setVisibility(GONE);
            llParticipa.setVisibility(GONE);
            llDescuentos.setVisibility(GONE);
            rlBotonNaranjaTienda.setVisibility(GONE);
            rlEdittextTienda.setVisibility(GONE);
            rlBotonAzulTienda.setVisibility(View.VISIBLE);
            rlBotonNaranjaComida.setVisibility(GONE);
            rlEdittextComida.setVisibility(GONE);
            rlBotonAzulComida.setVisibility(View.VISIBLE);
            llMapa.setVisibility(View.VISIBLE);
            llServicios.setVisibility(View.VISIBLE);
            draweeView.setVisibility(GONE);
            dvCambiarPiso.setVisibility(GONE);
            hideSoftKeyboard();
            limpiarTodo();
            sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
            contadorPiso = sharedPreferences.getInt("piso", 0);
            cambiarFondoPiso();

        } else if (mensaje.equals("tiendas")) {
            selectTab(llTienda);

            initialDataTiendas();
        } else if (mensaje.equals("comida")) {
            initialDataComidas();
        } else if (mensaje.equals("descuentos")) {

            opcionPantalla = 4;
            limpiarTodo();
            selectTab(llDescuentosOpcion);
            initialDataDescuento();
            if (rltv.getTag().equals(0)) {
                rltv.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
            } else {
                rltv.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
            }
            lineaFlechaVisible(ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaParticipa, ivFlechaRojaParticipa);
            llComida.setVisibility(GONE);
            llTienda.setVisibility(GONE);
            llParticipa.setVisibility(GONE);
            llDescuentos.setVisibility(View.VISIBLE);
        } else if (mensaje.equals("promociones")) {
            initialDataParticipa();

            opcionPantalla = 5;
            limpiar();
            selectTab(llParticipaOpcion);
            lineaFlechaVisible(ivLineaRojaParticipa, ivFlechaRojaParticipa, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa);
            llComida.setVisibility(GONE);
            llTienda.setVisibility(GONE);
            llDescuentos.setVisibility(GONE);
            llParticipa.setVisibility(View.VISIBLE);
        }
        EventBus.getDefault().removeStickyEvent(event);

    }
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageRubros(RubroOpcion event) {
        opciones = event.getOpciones();
        tvTiendaOpciones.setText(R.string.tiendas);
        tvNumeroTiendas.setVisibility(View.VISIBLE);
        tvPisoPasilloTienda.setVisibility(View.VISIBLE);
        tvPisoPisoTienda.setVisibility(View.VISIBLE);
        recyclerViewTienda.setVisibility(View.VISIBLE);

        if (opciones > 0) {
            messages = new ArrayList<Stores>();
            for (Stores stores : RGlobal.tiendas) {
                if (event.getNombre().equalsIgnoreCase(stores.getCategory())) {
                    messages.add(stores);
                }
            }
        }
        adaptadorRubro = new TiendasViewAdapter(0, messages, this);
        recyclerViewTienda.setAdapter(adaptadorRubro);
        adaptadorRubro.notifyDataSetChanged();
        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onBusquedaMain(Busqueda event) {
        opcionBusqueda = event.getOpcion();
        busquedaTienda = event.getListTienda();

        if (opcionBusqueda == 1) {
            assert recyclerViewTienda != null;
            //listaTiendasAdapter = new TiendasViewAdapter(0, busquedaTienda, getApplicationContext());
            //recyclerViewTienda.setAdapter(listaTiendasAdapter);
        } else if (opcionBusqueda == 2) {
            assert recyclerViewComida != null;
            // listaComidaAdapter = new ComidaViewAdapter(busquedaTienda, getApplicationContext());
            //   recyclerViewComida.setAdapter(listaComidaAdapter);
        }
        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageMain(NodeNumber event) {
        xNodeInit = event.getX();
        yNodeInit = event.getY();
        numberFloorNodeInit = event.getFloor();
        startRoute = event.getNumeroNodo();
        drawViewInit.init(xNodeInit, yNodeInit, 0);
        drawViewInitFixed.init(xNodeInit, yNodeInit, 0);

        rlEstasAqui.setVisibility(View.VISIBLE);
        rlEstasAqui.setX(xNodeInit - 59);
        rlEstasAqui.setY(yNodeInit - 40);
        contadorPiso = numberFloorNodeInit;
        cambiarFondoPiso();
        //EventBus.getDefault().removeStickyEvent(event);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*EVENTO DE EVENTBUS PARA DETERMINAR SI HAY UN CAMBIO DE PISO Y PARA DETERMINAR QUE ESCALERA E IMAGEN HABILITAR
    * AL HACER CLICK SOBRE UNA DE ELLAS*/
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(Message event) {
        idCont = event.getCont();
        idStair = event.getStair();
        idContNode = event.getContNode();

        if (idCont > 0 && idStair.equals("uno") && idContNode == 0) {
            manoStair(600 * ancho, 280 * alto);
            btEscalera1.setEnabled(true);
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 1));
        } else if (idCont > 0 && idStair.equals("dos") && idContNode == 0) {
            manoStair(1300 * ancho, 280 * alto);
            btEscalera2.setEnabled(true);
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 1));
        } else if (idCont > 0 && idStair.equals("tres") && idContNode == 0) {
            manoStair(600 * ancho, 890 * alto);
            btEscalera3.setEnabled(true);
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 1));
        } else if (idCont > 0 && idStair.equals("cuatro") && idContNode == 0) {
            manoStair(1300 * ancho, 890 * alto);
            btEscalera4.setEnabled(true);
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 1));
        } else if (idContNode == 1) {
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 2));
            if (idStair.equals("uno")) {
                manoStair(600 * ancho, 280 * alto);
                btEscalera1.setEnabled(true);
            } else if (idStair.equals("dos")) {
                manoStair(1300 * ancho, 280 * alto);
                btEscalera2.setEnabled(true);
            } else if (idStair.equals("tres")) {
                manoStair(600 * ancho, 890 * alto);
                btEscalera3.setEnabled(true);
            } else if (idStair.equals("cuatro")) {
                manoStair(1300 * ancho, 890 * alto);
                btEscalera4.setEnabled(true);
            }
        } else if (idContNode > 1) {
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(idCont + 2));
            if (idStair.equals("uno")) {
                manoStair(600 * ancho, 280 * alto);
                btEscalera1.setEnabled(true);
            } else if (idStair.equals("dos")) {
                manoStair(1300 * ancho, 280 * alto);
                btEscalera2.setEnabled(true);
            } else if (idStair.equals("tres")) {
                manoStair(600 * ancho, 890 * alto);
                btEscalera3.setEnabled(true);
            } else if (idStair.equals("cuatro")) {
                manoStair(1300 * ancho, 890 * alto);
                btEscalera4.setEnabled(true);
            }
        }
        EventBus.getDefault().removeStickyEvent(event);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    *EVENTO DE EVENTBUS PARA DETERMINAR SI HAY UNA IMAGEN DE TIENDA A DIBUJAR Y EN QUE POSICION
    */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageRect(Message event) {
        rect = event.getRect();
        if (rect == 4) {
            dvImgStore.setX(rectDib[6]);
            dvImgStore.setY(rectDib[7] + 1);
            dvImgStore.getLayoutParams().width = (int) (rectDib[8] * ancho);
            dvImgStore.getLayoutParams().height = (int) (rectDib[9] * alto);

            if (img != null) {
                Uri uri = Uri.parse(img);
                dvImgStore.setImageURI(uri);
                dvImgStore.setVisibility(View.VISIBLE);
            }
            xPop = puntos.get(arregloRutaFinal.size() - 1).getLocationX();
            yPop = puntos.get(arregloRutaFinal.size() - 1).getLocationY();

            drawViewPointEnd.setVisibility(View.VISIBLE);
            drawViewRect.setVisibility(View.VISIBLE);
            drawViewPointEnd.init(rectDib);
            drawViewRect.init(contadorPiso, rectDib);
            popPupTienda(xPop, yPop);
        }
        EventBus.getDefault().removeStickyEvent(event);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNumberNodeFinal(StoreNumber event) {
        idCont = 0;
        dvImgStore.setVisibility(GONE);
        drawView.setVisibility(GONE);
        drawViewPoint.setVisibility(GONE);
        drawViewPointEnd.setVisibility(GONE);
        drawViewRect.setVisibility(GONE);

        endRoute = event.getLocal();

        calculateRoute(startRoute, endRoute);

        llTienda.setVisibility(GONE);
        llComida.setVisibility(GONE);
        llDescuentos.setVisibility(GONE);
        llParticipa.setVisibility(GONE);
        llMapa.setVisibility(View.VISIBLE);
        llServicios.setVisibility(View.VISIBLE);
        rlLineaGris.setVisibility(View.VISIBLE);
        selectTab(llMapaOpcion);
        lineaFlechaVisible(ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);

        seeStair();

        if (puntos.size() > 0) {
            contadorPiso = 0;
            contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(0));
            cambiarFondoPiso();
            drawView.setVisibility(View.VISIBLE);
            drawViewPoint.setVisibility(View.VISIBLE);
            drawViewPoint.startAnimation(animFadeIn);

            limpiar();
            drawView.init(puntos, arregloRutaFinal, arregloStair, idCont, rectDib, arregloFloorEnd, arregloStair2);
            drawViewPoint.init(puntos, idCont);
        }
        EventBus.getDefault().removeStickyEvent(event);
    }

    private String imagenTienda;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onPopUpMain(PopupMapa event) {

        if (!verTiendaActivate) {
            nombreTienda = event.getNombreTienda();
            numeroTienda = event.getNumeroTienda();
            numeroTelefono = event.getNumeroTelefono();
            numeroId = event.getNumeroId();
            email = event.getEmail();
            web = event.getWeb();
            imagenTienda = event.getStoreImage();
            List<String> tags = event.getTags();
            customDialogMapa(nombreTienda, numeroTienda, numeroTelefono, numeroId, tags, email, web, imagenTienda);
            EventBus.getDefault().removeStickyEvent(event);
            verTiendaActivate = true;
        } else {
            verTiendaActivate = false;
        }
    }

    //CONTEXTUAL
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onContextual(Contextual event) {
        finalContContextual = event.getPisoContextual();
        if (finalContContextual > 1 && contadorPiso == numeroPisoContextual) {
            tvDnombreTienda.setText(nombreTiendaContextual);
            tvporcentaje.setText(porcentajeContextual);
            rlPublicidadIcono.setX(xx1 + 35);
            rlPublicidadIcono.setY(yy1 + 73);
            //dibujarContextual(numeroTiendaContextualFin);
        }
        EventBus.getDefault().removeStickyEvent(event);
    }

    /*public void dibujarContextual(final String numeroTiendaContextualFin) {
        String uri;
        int imageResource;
        Drawable myDrawable;
        Log.e("TAG","QAZWSX");
        uri = "@drawable/img" + 10;
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        myDrawable = getResources().getDrawable(imageResource);

        imageOne = new ImageView(this);
        imageOne.setLayoutParams(new ViewGroup.LayoutParams(30, 30));
        imageOne.setImageDrawable(myDrawable);
        imageOne.setX(xx1);
        imageOne.setY(yy1);
        layout.addView(imageOne);

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < RGlobal.appF.arrDescuentos.size(); i++) {
                    for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                        if (numeroTiendaContextualFin.equals(RGlobal.appF.messages.get(j).getStoreNumber().toString())) {
                            EventBus.getDefault().postSticky(new MessageNew(RGlobal.listaImagenesDescuento.get(i).getImgURLWayfinder(), 8));
                        }
                    }
                }
            }
        });
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessagePopPupDescuento(MessageNew event) {
        customDialog(event.getOpcion(), event.getMensaje());
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        // temporizador.cancel();
    }


    public void selectTabTienda(View tab1, View tab2, View tab3) {
        tab1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab3.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
    }

    public void selectTabComida(View tab1, View tab2, View tab3, View tab4) {
        tab1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab4.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
        hideSoftKeyboard();
        rlBotonAzulComida.setVisibility(View.VISIBLE);
        rlBotonNaranjaComida.setVisibility(GONE);
        rlEdittextComida.setVisibility(GONE);
        etEdittextComida.setText("");
        etEdittextComida.setText("");

    }

    public void selectTabDescuento(View tab1, View tab2, View tab3, View tab4, View tab5) {
        tab1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_wl));
        tab5.setBackgroundColor(getResources().getColor(R.color.rojoOpciones));
    }

    //TODO Codigo cambiado
    private void initialDataTiendas() {
        recyclerViewTienda.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(this);
        recyclerViewTienda.setHasFixedSize(true);
        recyclerViewTienda.setLayoutManager(layoutManager);

        RGlobal.listaBuscarComida = RGlobal.tiendas;

        listaTiendasAdapter = new TiendasViewAdapter(0, RGlobal.tiendas, getApplicationContext());
        Log.d("Tiendas", RGlobal.tiendas.size() + "");
        adaptadorTienda = listaTiendasAdapter;
        adaptadorGeneral = adaptadorTienda;

        mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets2 = new ArrayList<>();

        List<String> sections = new ArrayList<>(26);
        ArrayList<Integer> mSectionPositions;

        mSectionPositions = new ArrayList<>(26);

        for (int i = 0, size = RGlobal.tiendas.size(); i < size; i++) {
            String section = StringUtils.stripAccents(String.valueOf(RGlobal.tiendas.get(i).getName().charAt(0)).toUpperCase());
            if (StringUtils.isNumeric(section)) {
                section = "#";
            }
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }

        int pos = 0;
        for (String word : sections) {
            mAlphabetItems.add(new AlphabetItem(pos, word, false));
            pos++;
        }

        fastScrollerTienda.setRecyclerView(recyclerViewTienda, mAlphabetItems);
        fastScrollerTienda.setUpAlphabet(mAlphabetItems);
        fastScrollerTienda.setVisibility(View.VISIBLE);

/*

        Rvl.setAdapter(adaptadorTienda);
        Rvl.setIndexTextSize(17);
        Rvl.setIndexBarCornerRadius(5);
        Rvl.setIndexbarWidth(30);
        Rvl.setIndexbarMargin(10);
*/

        recyclerViewTienda.setAdapter(listaTiendasAdapter);

        opcionPantalla = 2;
        limpiar();
        selectTab(llTiendasOpcion);
        selectTabTienda(llRubrosTienda, llPisoTienda, llAzTienda);
        lineaFlechaVisible(ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
        llComida.setVisibility(GONE);
        llParticipa.setVisibility(GONE);
        llDescuentos.setVisibility(GONE);
        llTienda.setVisibility(View.VISIBLE);
        llBotonesPisosTienda.setVisibility(GONE);
        llBotonesTienda.setVisibility(View.VISIBLE);
        rlBotonAzulTienda.setVisibility(View.VISIBLE);
        rlBotonNaranjaTienda.setVisibility(GONE);
        llBotonesTienda2.setVisibility(GONE);
        tvTiendaOpciones.setText(R.string.tiendas);
        tvNumeroTiendas.setVisibility(View.VISIBLE);
        tvPisoPasilloTienda.setVisibility(View.VISIBLE);
        tvPisoPisoTienda.setVisibility(View.VISIBLE);
        hideSoftKeyboard();
        limpiarBuscadorTienda();


    }

    private void initialDataComidas() {

        layoutManager = new LinearLayoutManager(this);
        recyclerViewComida.setHasFixedSize(true);
        recyclerViewComida.setLayoutManager(layoutManager);
        RGlobal.listaBuscarComida = RGlobal.listaRestauran;


        opcionPantalla = 3;
        tvTextoRestaurantes.setText(RGlobal.appF.arrAlimentacion.get(4).getNames().get(3).toString());
        tvTextoCafeterias.setText(RGlobal.appF.arrAlimentacion.get(4).getNames().get(0).toString());
        tvTextoConfiterias.setText(RGlobal.appF.arrAlimentacion.get(4).getNames().get(1).toString());
        tvTextoHeladerias.setText(RGlobal.appF.arrAlimentacion.get(4).getNames().get(2).toString());
        selectTab(llComidaOpcion);
        lineaFlechaVisible(ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);
        llTienda.setVisibility(GONE);
        llParticipa.setVisibility(GONE);
        llDescuentos.setVisibility(GONE);
        llComida.setVisibility(View.VISIBLE);
        selectTabComida(llCafeterias, llConfiterias, llHeladerias, llRestaurantes);
        tvComidaOpciones.setText(R.string.restorant);
        recyclerViewComida.setVisibility(View.VISIBLE);
        recyclerViewTienda.setVisibility(GONE);

        listaComidaAdapter = new ComidaViewAdapter(RGlobal.listaRestauran, getApplicationContext());
        recyclerViewComida.setAdapter(listaComidaAdapter);

        rlBotonNaranjaTienda.setVisibility(GONE);
        rlEdittextTienda.setVisibility(GONE);
        rlBotonAzulTienda.setVisibility(GONE);
        rlBotonNaranjaComida.setVisibility(GONE);

        //     rlEdittextComida.setVisibility(View.VISIBLE);
        rlBotonAzulComida.setVisibility(View.VISIBLE);

        draweeView.setVisibility(GONE);
        dvCambiarPiso.setVisibility(GONE);
        hideSoftKeyboard();
        limpiar();

    }

    private void initialDataDescuento() {
        lLayout = new GridLayoutManager(getApplicationContext(), 3);
        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view_descuentos);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        descuentosAdapter = new DescuentosAdapter(getApplicationContext(), getAllItemList());
        rView.setAdapter(descuentosAdapter);
    }

    private void initialDataParticipa() {
        Uri uri = Uri.parse(RGlobal.appF.imgParticipaWayfinder);
        ivParticipa.setImageURI(uri);
        llParticipa.setVisibility(View.VISIBLE);
    }

    private List<Descuentos> getAllItemList() {
        return RGlobal.listaDescuentos;
    }

    private void showSoftKeyboard() {
        InputMethodManager immq = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immq.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    /*
    *Se realiza el cambio de piso de piso dependiendo del valor de la variable contadorPiso.
    */
    public void cambiarFondoPiso() {
        rlPublicidadIcono.setVisibility(GONE);
        if (contadorPiso == 1) {
            if (numberFloorNodeInit == 1) {
                drawViewInit.setVisibility(View.VISIBLE);
                drawViewInitFixed.setVisibility(View.VISIBLE);
                rlEstasAqui.setVisibility(View.VISIBLE);
                rlPisoDosa.setVisibility(GONE);
                rlPisoDos.setVisibility(View.VISIBLE);
                rlPisoTresa.setVisibility(GONE);
                rlPisoTres.setVisibility(View.VISIBLE);
                rlPisoUno.setVisibility(GONE);
                rlPisoUnoa.setVisibility(View.VISIBLE);
                rlLineaGris.setVisibility(GONE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapauno));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso1pasillos));
                cambiarServiciosLayout(contadorPiso);
                drawViewInit.init(xNodeInit, yNodeInit, 0);
                drawViewInitFixed.init(xNodeInit, yNodeInit, 0);
                // lineaFlechaVisible(ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);

                hideSoftKeyboard();

            } else {
                drawViewInit.setVisibility(GONE);
                drawViewInitFixed.setVisibility(GONE);
                rlEstasAqui.setVisibility(GONE);
                rlPisoDosa.setVisibility(GONE);
                rlPisoDos.setVisibility(View.VISIBLE);
                rlPisoTresa.setVisibility(GONE);
                rlPisoTres.setVisibility(View.VISIBLE);
                rlPisoUno.setVisibility(GONE);
                rlPisoUnoa.setVisibility(View.VISIBLE);
                rlLineaGris.setVisibility(GONE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapauno));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso1pasillos));
                cambiarServiciosLayout(contadorPiso);

            }
        } else if (contadorPiso == 2) {
            if (numberFloorNodeInit == 2) {
                drawViewInit.setVisibility(View.VISIBLE);
                drawViewInitFixed.setVisibility(View.VISIBLE);
                rlEstasAqui.setVisibility(View.VISIBLE);
                rlPisoDosa.setVisibility(View.VISIBLE);
                rlPisoDos.setVisibility(GONE);
                rlPisoTresa.setVisibility(GONE);
                rlPisoTres.setVisibility(View.VISIBLE);
                rlPisoUno.setVisibility(View.VISIBLE);
                rlPisoUnoa.setVisibility(GONE);
                rlLineaGris.setVisibility(View.VISIBLE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapados));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso2pasillos));
                cambiarServiciosLayout(contadorPiso);
                drawViewInit.init(xNodeInit, yNodeInit, 0);
                drawViewInitFixed.init(xNodeInit, yNodeInit, 0);


            } else {
                drawViewInit.setVisibility(GONE);
                drawViewInitFixed.setVisibility(GONE);
                rlEstasAqui.setVisibility(GONE);
                rlPisoDosa.setVisibility(View.VISIBLE);
                rlPisoDos.setVisibility(GONE);
                rlPisoTresa.setVisibility(GONE);
                rlPisoTres.setVisibility(View.VISIBLE);
                rlPisoUno.setVisibility(View.VISIBLE);
                rlPisoUnoa.setVisibility(GONE);
                rlLineaGris.setVisibility(View.VISIBLE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapados));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso2pasillos));
                cambiarServiciosLayout(contadorPiso);
            }
        } else {
            if (numberFloorNodeInit == 3) {
                drawViewInit.setVisibility(View.VISIBLE);
                drawViewInitFixed.setVisibility(View.VISIBLE);
                rlEstasAqui.setVisibility(View.VISIBLE);
                rlPisoDosa.setVisibility(GONE);
                rlPisoDos.setVisibility(View.VISIBLE);
                rlPisoTresa.setVisibility(View.VISIBLE);
                rlPisoTres.setVisibility(GONE);
                rlPisoUno.setVisibility(View.VISIBLE);
                rlPisoUnoa.setVisibility(GONE);
                rlLineaGris.setVisibility(View.VISIBLE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapatres));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso3pasillos));
                cambiarServiciosLayout(contadorPiso);
                drawViewInit.init(xNodeInit, yNodeInit, 0);
                drawViewInitFixed.init(xNodeInit, yNodeInit, 0);


            } else {
                drawViewInit.setVisibility(GONE);
                drawViewInitFixed.setVisibility(GONE);
                rlEstasAqui.setVisibility(GONE);
                rlPisoDosa.setVisibility(GONE);
                rlPisoDos.setVisibility(View.VISIBLE);
                rlPisoTresa.setVisibility(View.VISIBLE);
                rlPisoTres.setVisibility(GONE);
                rlPisoUno.setVisibility(View.VISIBLE);
                rlPisoUnoa.setVisibility(GONE);
                rlLineaGris.setVisibility(View.VISIBLE);
                rlPisos.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapatres));
                rlPisosText.setBackgroundDrawable(getResources().getDrawable(R.drawable.piso3pasillos));
            }
        }
    }

    public void cambiarServiciosLayout(int contadorPiso) {
        temporizador.cancel();
        temporizador.start();
        layout.removeAllViews();
        rlPublicidadIcono.setVisibility(GONE);
        if (contadorPiso == 1) {
            ivS1.setBackgroundDrawable(getResources().getDrawable(R.drawable.informaciongris));
            ivS1a.setBackgroundDrawable(getResources().getDrawable(R.drawable.informacionblanco));
            ivS2.getLayoutParams().width = 20;
            ivS2.getLayoutParams().height = 35;
            ivS2a.getLayoutParams().width = 20;
            ivS2a.getLayoutParams().height = 35;
            ivS2.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosdamagris));
            ivS2a.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosdamablanco));
            ivS3.getLayoutParams().width = 18;
            ivS3.getLayoutParams().height = 35;
            ivS3a.getLayoutParams().width = 18;
            ivS3a.getLayoutParams().height = 35;
            ivS3.setBackgroundDrawable(getResources().getDrawable(R.drawable.banoshombresgris));
            ivS3a.setBackgroundDrawable(getResources().getDrawable(R.drawable.banoshombresblanco));
            ivS4.setBackgroundDrawable(getResources().getDrawable(R.drawable.cajerosgris));
            ivS4a.setBackgroundDrawable(getResources().getDrawable(R.drawable.cajerosblanco));
            tvInformacionAndBanos.setText(R.string.informacion);
            tvBanosDamaAndAscensor.setText(R.string.banos_damas);
            tvBanosCaballerosAndEstacionamiento.setText(R.string.banos_caballeros);
            tvBanosCaballerosAndEstacionamiento.setTextSize(17);
            tvCajerosAndDescuentos.setText(R.string.cajeros);
            llAscensor.setVisibility(View.VISIBLE);
            llCochesSillas.setVisibility(View.VISIBLE);
            llGuardaPeques.setVisibility(View.VISIBLE);
            llDescuentosPromociones.setVisibility(View.VISIBLE);
        } else if (contadorPiso == 2) {
            ivS1.setBackgroundDrawable(getResources().getDrawable(R.drawable.informaciongris));
            ivS1a.setBackgroundDrawable(getResources().getDrawable(R.drawable.informacionblanco));
            ivS2.getLayoutParams().width = 20;
            ivS2.getLayoutParams().height = 35;
            ivS2a.getLayoutParams().width = 20;
            ivS2a.getLayoutParams().height = 35;
            ivS2.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosdamagris));
            ivS2a.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosdamablanco));
            ivS3.getLayoutParams().width = 18;
            ivS3.getLayoutParams().height = 35;
            ivS3a.getLayoutParams().width = 18;
            ivS3a.getLayoutParams().height = 35;
            ivS3.setBackgroundDrawable(getResources().getDrawable(R.drawable.banoshombresgris));
            ivS3a.setBackgroundDrawable(getResources().getDrawable(R.drawable.banoshombresblanco));
            ivS4.setBackgroundDrawable(getResources().getDrawable(R.drawable.cajerosgris));
            ivS4a.setBackgroundDrawable(getResources().getDrawable(R.drawable.cajerosblanco));
            tvInformacionAndBanos.setText(R.string.informacion);
            tvBanosDamaAndAscensor.setText(R.string.banos_damas);
            tvBanosCaballerosAndEstacionamiento.setText(R.string.banos_caballeros);
            tvBanosCaballerosAndEstacionamiento.setTextSize(17);
            tvCajerosAndDescuentos.setText(R.string.cajeros);
            llAscensor.setVisibility(View.VISIBLE);
            //llCochesSillas.setVisibility(View.GONE);
            //llGuardaPeques.setVisibility(View.GONE);
            llDescuentosPromociones.setVisibility(View.VISIBLE);
        } else {
            ivS1.setBackgroundDrawable(getResources().getDrawable(R.drawable.informaciongris));
            ivS1a.setBackgroundDrawable(getResources().getDrawable(R.drawable.informacionblanco));
            ivS2.getLayoutParams().width = 30;
            ivS2.getLayoutParams().height = 30;
            ivS2a.getLayoutParams().width = 30;
            ivS2a.getLayoutParams().height = 30;
            ivS2.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosgris));
            ivS2a.setBackgroundDrawable(getResources().getDrawable(R.drawable.banosblanco));
            ivS3.getLayoutParams().width = 40;
            ivS3.getLayoutParams().height = 25;
            ivS3a.getLayoutParams().width = 40;
            ivS3a.getLayoutParams().height = 25;
            ivS3.setBackgroundDrawable(getResources().getDrawable(R.drawable.estacionamientosgris));
            ivS3a.setBackgroundDrawable(getResources().getDrawable(R.drawable.estacionamientosblanco));
            tvInformacionAndBanos.setText(R.string.informacion);
            tvBanosDamaAndAscensor.setText(R.string.banos);
            tvBanosCaballerosAndEstacionamiento.setText(R.string.estacionamiento);
            tvBanosCaballerosAndEstacionamiento.setTextSize(15);
            tvCajerosAndDescuentos.setText(R.string.cajeros);
        }
    }

    /*
   *FUNCION UTILIZADA SI EN LA RUTA HAY UN CAMBIO DE PISO Y SE LLEGA HASTA UNA ESCALERA
   */

    public void stairEvent() {
        temporizador.cancel();
        temporizador.start();
        draweeView.setVisibility(GONE);
        dvCambiarPiso.setVisibility(GONE);
        drawView.setVisibility(GONE);
        drawViewPoint.setVisibility(GONE);
        drawView.setVisibility(View.VISIBLE);
        drawViewPoint.setVisibility(View.VISIBLE);

        limpiar();

        if ((!RGlobal.clickeado) && (!RGlobal.finalizado)) {
            RGlobal.clickeado = true;
            RGlobal.inMapa = true;
            drawView.init(puntos, arregloRutaFinal, arregloStair, idCont, rectDib, arregloFloorEnd, arregloStair2);
            drawViewPoint.init(puntos, idCont);
            Log.d("Hola", "test");
        }
    }

    private void limpiar() {

        drawViewRect.clearAnimation();
        drawViewPoint.clearAnimation();
        drawViewPointEnd.clearAnimation();
        drawViewInit.clearAnimation();
        drawViewInitFixed.clearAnimation();
        drawView.clearAnimation();

        ocultarPopPupTienda(0, 0);
    }

    private void limpiarTodo() {

        drawViewRect.clearAnimation();
        drawViewPoint.clearAnimation();
        drawViewPointEnd.clearAnimation();
        drawViewInit.clearAnimation();
        drawViewInitFixed.clearAnimation();
        drawView.clearAnimation();
        dvImgStore.setVisibility(GONE);
        RGlobal.inMapa = false;
        draweeView.setVisibility(GONE);
        dvCambiarPiso.setVisibility(GONE);
        drawViewRect.setVisibility(GONE);
        drawViewPointEnd.setVisibility(GONE);
        //    drawViewInit.setVisibility(GONE);
        drawViewPoint.setVisibility(GONE);
        //   drawViewInitFixed.setVisibility(GONE);
        drawView.setVisibility(GONE);
        ocultarPopPupTienda(0, 0);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    *FUNCION DONDE SE CAMBIA LA POSICION DE LA IMAGEN CONTINUAR RUTA
    */
    public void manoStair(float x, float y) {
        temporizador.cancel();
        temporizador.start();
        draweeView.setVisibility(View.VISIBLE);
        dvCambiarPiso.setVisibility(View.VISIBLE);
        draweeView.startAnimation(animFadeIn);
        draweeView.setX(x);
        draweeView.setY(y + 45);
        dvCambiarPiso.setX(x + 40);
        dvCambiarPiso.setY(y + 45);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void fadeIn() {
        objectAnimator = ObjectAnimator.ofFloat(draweeView, "alpha", 0f, 1f);
        objectAnimator.setDuration(1000L);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fadeOut();
            }
        });
        objectAnimator.start();
    }

    private void fadeOut() {
        objectAnimator = ObjectAnimator.ofFloat(draweeView, "alpha", 1f, 0f);
        objectAnimator.setDuration(1000L);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fadeIn();
            }
        });
        objectAnimator.start();
    }

    private void cancelAnimator() {
        if (objectAnimator != null) {
            objectAnimator.end();
            objectAnimator = null;
        }
    }


    /*
   *FUNCION CALCULAR RUTA FINAL
   */
    public void calculateRoute(int a, int b) {
        draweeView.setVisibility(GONE);
        dvCambiarPiso.setVisibility(GONE);
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        edges.clear();

        /*
        AGREGA EDGES RESPECTO A CADA UNO DE LOS VERTICES
        */
        for (int i = 0; i < RGlobal.appF.arregloA.size(); i++) {
            Graph.Edge<String> ed = new Graph.Edge<String>((int) RGlobal.appF.arregloCosto.get(i), RGlobal.appF.vertices.get((int) RGlobal.appF.arregloA.get(i)), RGlobal.appF.vertices.get((int) RGlobal.appF.arregloB.get(i)));
            edges.add(ed);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        *PASAMOS VERTICES Y EDGES USANDO LA CLASE GRAPH PARA GENERAR GRAFO FINAL DE RUTA
        */
        Graph<String> graph = new Graph<String>(Graph.TYPE.UNDIRECTED, RGlobal.appF.vertices, edges);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        *LIMPIAMOS LAS VARIABLES Y ARREGLOS CADA VEZ QUE GENERAMOS UNA NUEVA RUTA
        */
        Astar astar = new Astar();
        arregloRutaFinal = new ArrayList();
        arregloRutaFinal.clear();
        arregloStair = new ArrayList();
        arregloStair.clear();
        arregloFloorEnd.clear();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        *TOMAMOS EL GRAFO FINAL Y CON LA CLASE ASTAR GENERAMOS SUS PESOS ENTRE LOS EDGES PARA DETERMINAR RUTA MAS CORTA ENTRE NODOS
        */
        stockList = astar.aStar(graph, RGlobal.appF.vertices.get(a), RGlobal.appF.vertices.get(b));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        *TOMAMOS EL ARREGLO STOCKLIST Y GENERAMOS UN NUEVO ARREGLO ELIMINANDO ESPACIOS Y SEPARANDO POR COMAS
        */
        String ss = "";

        for (int i = 0; i < stockList.size(); i++) {
            ss = String.valueOf(stockList.get(i));
            String[] sp = ss.split(",");
            arregloRutaFinal.add(sp[0]);
            arregloRutaFinal.add(sp[1]);
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        *UTILIZAMOS LINKEDHASHSET PARA ELIMINAR ELEMENTOS REPETIDOS Y GENERAR RUTA FINAL A DIBUJAR EN EL MAPA
        */
        linkedHashSet.addAll(arregloRutaFinal);
        arregloRutaFinal.clear();
        arregloRutaFinal.addAll(linkedHashSet);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Log.e("TAG","RUTA FINAL: "+arregloRutaFinal.toString());
        /*
        *RECORREMOS MAP PARA DETERMINAR COORD DE LA TIENDA A ILUMINAR Y PUNTO FINAL DE LLEGADA A DIBUJAR
        */
        for (Map.Entry<String, Nodes> cordRectTienda : RGlobal.appF.coordRect.entrySet()) {
            float xx = (float) RGlobal.arregloLocationX.get(Integer.parseInt(String.valueOf(arregloRutaFinal.get(arregloRutaFinal.size() - 1))) - 1);
            float yy = (float) RGlobal.arregloLocationY.get(Integer.parseInt(String.valueOf(arregloRutaFinal.get(arregloRutaFinal.size() - 1))) - 1);
            if (arregloRutaFinal.size() > 0) {
                if (cordRectTienda.getKey().equals(arregloRutaFinal.get(arregloRutaFinal.size() - 1))) {
                    img = cordRectTienda.getValue().getImg();
                    rectDib = new int[]{(int) cordRectTienda.getValue().getRectX(), (int) cordRectTienda.getValue().getRectY(), (int) cordRectTienda.getValue().getRectW(), (int) cordRectTienda.getValue().getRectH(), (int) xx, (int) yy,
                            (int) cordRectTienda.getValue().getImgX(), (int) cordRectTienda.getValue().getImgY(), (int) cordRectTienda.getValue().getImgW(), (int) cordRectTienda.getValue().getImgH()};
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        *DETERMINAR ESCALERAS ENCONTRADAS EN LA RUTA OPTIMA y AGREGA COORD X e Y DONDE SE DIBUJARA LA RUTA
        */
        puntos = new ArrayList<>();
        for (int i = 0; i < arregloRutaFinal.size(); i++) {
            for (int j = 0; j < RGlobal.listaNodesID.size(); j++) {
                if (arregloRutaFinal.get(i).equals(RGlobal.listaNodesID.get(j))) {
                    arregloFloorEnd.add(RGlobal.appF.arregloFloor.get(j));
                    if (RGlobal.appF.arregloType.get(j).equals("stair")) {
                        arregloStair.add(i);
                        arregloStair2.add(arregloRutaFinal.get(i));
                    }
                    puntos.add(new Nodes((float) RGlobal.arregloLocationX.get(j), (float) RGlobal.arregloLocationY.get(j)));
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    ///////////////////////////////////////////////FIN FUNCION CALCULAR RUTA FINAL///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    * Funcion que se ejecuta al inicio del recorrido de la ruta y muestra las escaleras.
    * */
    public void seeStair() {
        btEscalera1.setVisibility(View.VISIBLE);
        btEscalera2.setVisibility(View.VISIBLE);
        btEscalera3.setVisibility(View.VISIBLE);
        btEscalera4.setVisibility(View.VISIBLE);
        btEscalera1.setEnabled(false);
        btEscalera2.setEnabled(false);
        btEscalera3.setEnabled(false);
        btEscalera4.setEnabled(false);
        btEscalera1.setX((float) 285 * ancho);
        btEscalera1.setY((float) 183 * alto);
        btEscalera2.setX((float) 985 * ancho);
        btEscalera2.setY((float) 183 * alto);
        btEscalera3.setX((float) 285 * ancho);
        btEscalera3.setY((float) 795 * alto);
        btEscalera4.setX((float) 985 * ancho);
        btEscalera4.setY((float) 795 * alto);
    }

    public void quitarColorOpcionServicio(TextView op1, View op2, View op3, View op4) {
        op1.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op2.setVisibility(GONE);
        op3.setVisibility(GONE);
        op4.setVisibility(GONE);
    }


    public void cambiarColorServicios(TextView op1, View op2, View op3, View op4, TextView op5, View op6, View op7, View op8, TextView op9, View op10,
                                      View op11, View op12, TextView op13, View op14, View op15, View op16, TextView op17, View op18, View op19, View op20,
                                      TextView op21, View op22, View op23, View op24, TextView op25, View op26, View op27, View op28, TextView op29, View op30,
                                      View op31, View op32) {
        temporizador.cancel();
        temporizador.start();
        op1.setTextColor(ContextCompat.getColor(this, R.color.blanco));
        op2.setVisibility(View.VISIBLE);
        op3.setVisibility(View.VISIBLE);
        op4.setVisibility(View.VISIBLE);

        op5.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op6.setVisibility(GONE);
        op7.setVisibility(GONE);
        op8.setVisibility(GONE);

        op9.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op10.setVisibility(GONE);
        op11.setVisibility(GONE);
        op12.setVisibility(GONE);

        op13.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op14.setVisibility(GONE);
        op15.setVisibility(GONE);
        op16.setVisibility(GONE);

        op17.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op18.setVisibility(GONE);
        op19.setVisibility(GONE);
        op20.setVisibility(GONE);

        op21.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op22.setVisibility(GONE);
        op23.setVisibility(GONE);
        op24.setVisibility(GONE);

        op25.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op26.setVisibility(GONE);
        op27.setVisibility(GONE);
        op28.setVisibility(GONE);

        op29.setTextColor(ContextCompat.getColor(this, R.color.grisOpciones));
        op30.setVisibility(GONE);
        op31.setVisibility(GONE);
        op32.setVisibility(GONE);
    }

    /*
   * Funcion para cambiar imagen de los servicios segun sea el seleccionado en la pantalla del mapa
   * */
    public void cambiarServicios(String nameServices, int img) {

        layout.removeAllViews();

        if (nameServices.equals("descuentos")) {

            services.clear();

            for (int i = 0; i < RGlobal.appF.arrDescuentos.size(); i++) {
                for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                    if (RGlobal.appF.messages.get(j).getStoreNumber().equals(RGlobal.appF.arrDescuentos.get(i).getStoreNumber()) && RGlobal.appF.messages.get(j).getFloor() == contadorPiso) {
                        services.put(j, new Nodes((float) RGlobal.arregloLocationX.get(Integer.parseInt(RGlobal.appF.messages.get(j).getNodeID()) - 1), (float) RGlobal.arregloLocationY.get(Integer.parseInt(RGlobal.appF.messages.get(j).getNodeID()) - 1), Integer.parseInt(RGlobal.appF.messages.get(j).getNodeID().toString())));
                        Log.e("TAG", "STORE NUMBER: " + RGlobal.appF.messages.get(j).getStoreNumber().toString() + " XXXXX: " + (float) RGlobal.arregloLocationX.get(Integer.parseInt(RGlobal.appF.messages.get(j).getNodeID()) - 1) + " NODE ID: " + RGlobal.appF.messages.get(j).getNodeID().toString());
                    }
                }
            }

            String uri;
            int imageResource;
            Drawable myDrawable;
            for (Map.Entry<Integer, Nodes> servicesf : services.entrySet()) {
                uri = "@drawable/img" + img;
                imageResource = getResources().getIdentifier(uri, null, getPackageName());
                myDrawable = getResources().getDrawable(imageResource);

                final float xx2 = servicesf.getValue().getLocationX();
                final float yy2 = servicesf.getValue().getLocationY();

                imageOne = new ImageView(this);
                imageOne.setLayoutParams(new ViewGroup.LayoutParams(25, 25));
                imageOne.setImageDrawable(myDrawable);
                imageOne.setX(servicesf.getValue().getLocationX() - 10);
                imageOne.setY(servicesf.getValue().getLocationY() - 8);
                imageOne.setTag(servicesf.getValue().getNumDesc());
                layout.addView(imageOne);


                imageOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nombreDTienda = null;
                        int porcentajeDTienda = 0;
                        for (int i = 0; i < RGlobal.appF.arrDescuentos.size(); i++) {
                            for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                                if (RGlobal.appF.messages.get(j).getNodeID().equals(v.getTag().toString())) {
                                    nombreDTienda = RGlobal.appF.messages.get(j).getName().toString();

                                    if (RGlobal.appF.arrDescuentos.get(i).getStoreNumber().equals(RGlobal.appF.messages.get(j).getStoreNumber().toString())) {
                                        porcentajeDTienda = RGlobal.appF.arrDescuentos.get(i).getPercentaje();
                                        EventBus.getDefault().postSticky(new MessageNew(RGlobal.listaImagenesDescuento.get(i).getImgURLWayfinder(), 8));
                                    }
                                }
                            }

                        }


                        //    rlPublicidadIcono.setVisibility(View.VISIBLE);
                        tvDnombreTienda.setText(nombreDTienda);
                        tvporcentaje.setText(String.valueOf(porcentajeDTienda));
                        rlPublicidadIcono.setX(xx2 + 10);
                        rlPublicidadIcono.setY(yy2 + 68);
                    }
                });

                rlPublicidadIcono.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rlPublicidadIcono.setVisibility(GONE);
                    }
                });

            }
        } else {
            services.clear();
            for (int i = 0; i < RGlobal.appF.arregloType.size(); i++) {
                if (RGlobal.appF.arregloType.get(i).equals(nameServices) && RGlobal.appF.arregloFloor.get(i).equals(String.valueOf(contadorPiso))) {
                    services.put(i, new Nodes((float) RGlobal.arregloLocationX.get(i), (float) RGlobal.arregloLocationY.get(i)));
                }
            }
            String uri;
            int imageResource;
            Drawable myDrawable;
            for (Map.Entry<Integer, Nodes> servicesf : services.entrySet()) {
                uri = "@drawable/img" + img;
                imageResource = getResources().getIdentifier(uri, null, getPackageName());
                myDrawable = getResources().getDrawable(imageResource);

                imageOne = new ImageView(this);
                imageOne.setLayoutParams(new ViewGroup.LayoutParams(60, 60));
                imageOne.setImageDrawable(myDrawable);
                imageOne.setX(servicesf.getValue().getLocationX() - 30);
                imageOne.setY(servicesf.getValue().getLocationY() - 60);
                layout.addView(imageOne);
            }
        }
    }

    public void popPupTienda(int x, int y) {

        rlPopupTienda.setVisibility(View.VISIBLE);
        tvPopupTextoTienda.setText(nombreTienda);
        tvPopupTextoNumeroLocal.setText(numeroTienda);
        rlPopupTienda.setX(x - 10);
        rlPopupTienda.setY(y + 66);

    }

    public void ocultarPopPupTienda(int x, int y) {

        rlPopupTienda.setVisibility(GONE);
        tvPopupTextoTienda.setText(nombreTienda);
        tvPopupTextoNumeroLocal.setText(numeroId);
        rlPopupTienda.setX(x - 10);
        rlPopupTienda.setY(y + 66);

    }


    @Override
    public void onUserInteraction() {
        temporizador.cancel();
        temporizador.start();
    }

    @OnClick(R.id.rlPopupTienda)
    public void onViewClickedPopupTienda() {
        rlPopupTienda.setVisibility(GONE);
    }


    //TODO TEMPORIZADOR ELIMINADO MAL USO.
    public class Temporizador extends CountDownTimer {

        public Temporizador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //Log.e("QWERTY","ONTICK: "+millisUntilFinished);
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
            View view = MenuScreenActivity.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }

    }

    public void customDialog(final int opc, String urlImagen) {
        // custom dialog
        quitarColorOpcionServicio(textViewOpcion, viewOpcion1, viewOpcion2, viewOpcion3);
        final Dialog dialog = new Dialog(this, R.style.dialogTema);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_servicios);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView image = (ImageView) dialog.findViewById(R.id.ivServiciosBanos);
        SimpleDraweeView image2 = (SimpleDraweeView) dialog.findViewById(R.id.ivServiciosGuardaSillas);
        ImageView image3 = (ImageView) dialog.findViewById(R.id.ivCerrarServicios);
        SimpleDraweeView imageDescuento = (SimpleDraweeView) dialog.findViewById(R.id.imgDescuentooo);
        ImageView imagenCerrar = new ImageView(this);
        ImageView imagenPiso1 = new ImageView(this);
        ImageView imagenPiso2 = new ImageView(this);

        if (opc == 1) {
            dialog.setContentView(R.layout.popup_banos_dama);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosBanosDama);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
            imagenPiso2 = (ImageView) dialog.findViewById(R.id.ivIrPiso2);
        } else if (opc == 2) {
            dialog.setContentView(R.layout.popup_banos_caballero);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosBanosCaballero);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
            imagenPiso2 = (ImageView) dialog.findViewById(R.id.ivIrPiso2);
        } else if (opc == 3) {
            dialog.setContentView(R.layout.popup_guardapeques);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosGuardapeques);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
        } else if (opc == 4) {
            dialog.setContentView(R.layout.popup_coches_sillas);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosCochesSillas);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
        } else if (opc == 5) {
            dialog.setContentView(R.layout.popup_informacion);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosInformacion);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
            imagenPiso2 = (ImageView) dialog.findViewById(R.id.ivIrPiso2);
        } else if (opc == 6) {
            dialog.setContentView(R.layout.popup_cajeros);
            imagenCerrar = (ImageView) dialog.findViewById(R.id.ivCerrarServiciosCajeros);
            imagenPiso1 = (ImageView) dialog.findViewById(R.id.ivIrPiso1);
            imagenPiso2 = (ImageView) dialog.findViewById(R.id.ivIrPiso2);
        } else if (opc == 7) {
            image2.setVisibility(View.VISIBLE);
            image.setVisibility(GONE);
            image2.setBackgroundDrawable(getResources().getDrawable(R.drawable.descuentosweb));
            image3.setBackgroundDrawable(getResources().getDrawable(R.drawable.ivcerrarserviciosrojo));
        } else if (opc == 8) {
            imageDescuento.setVisibility(View.VISIBLE);
            imageDescuento.setImageURI(Uri.parse(urlImagen));
        }
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        imagenCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        imagenPiso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                limpiarTodo();
                if (opc == 1) {
                    contadorPiso = 1;
                    cambiarFondoPiso();
                    llBanosDamaAndAscensor.performClick();
                } else if (opc == 2) {
                    contadorPiso = 2;
                    cambiarFondoPiso();
                    llBanosCaballerosAndEstacionamiento.performClick();
                } else if (opc == 3) {
                    contadorPiso = 1;
                    cambiarFondoPiso();
                    llGuardaPeques.performClick();
                } else if (opc == 4) {
                    contadorPiso = 1;
                    cambiarFondoPiso();
                    llCochesSillas.performClick();
                } else if (opc == 5) {
                    contadorPiso = 1;
                    cambiarFondoPiso();
                    llInformacionAndBanos.performClick();
                } else if (opc == 6) {
                    contadorPiso = 1;
                    cambiarFondoPiso();
                    llCajerosAndDescuentos.performClick();
                }
            }
        });

        imagenPiso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                limpiarTodo();
                if (opc == 1) {
                    contadorPiso = 3;
                    cambiarFondoPiso();
                    cambiarServiciosLayout(3);
                    llBanosDamaAndAscensor.performClick();
                } else if (opc == 2) {
                    contadorPiso = 3;
                    cambiarFondoPiso();
                    cambiarServiciosLayout(3);
                    llBanosDamaAndAscensor.performClick();
                } else if (opc == 5) {
                    contadorPiso = 2;
                    cambiarFondoPiso();
                    llInformacionAndBanos.performClick();
                } else if (opc == 6) {
                    contadorPiso = 2;
                    cambiarFondoPiso();
                    llCajerosAndDescuentos.performClick();
                }
            }
        });

        dialog.show();
    }

    public void customDialogMapa(String text, String text2, String text3, final String node, List<String> tags, String email, String web, String imagenTienda) {
// custom dialog
        final Dialog dialog = new Dialog(this, R.style.dialogTema);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_tienda_mapa);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        SimpleDraweeView imgTienda = (SimpleDraweeView) dialog.findViewById(R.id.img_tienda);

        imgTienda.setImageURI(Uri.parse(imagenTienda));
        TextView tv_nombre_tienda = (TextView) dialog.findViewById(R.id.tv_nombre_tienda);
        tv_nombre_tienda.setText(text);
        for (Stores store : RGlobal.tiendas) {
            if (store.getStoreNumber().equals(text2)) {
                piso = store.getFloor();
                break;
            }
        }

        TextView tvLocalNumber = (TextView) dialog.findViewById(R.id.tvLocalNumber);
        tvLocalNumber.setText(text2 + " - Piso " + piso);
        TextView tvTelefonoNumber = (TextView) dialog.findViewById(R.id.tvTelefonoNumber);
        tvTelefonoNumber.setText(text3);

        TextView tvEmail = (TextView) dialog.findViewById(R.id.tvEmailDireccion);
        tvEmail.setText(email);

        TextView tvWeb = (TextView) dialog.findViewById(R.id.tvWebDireccion);
        tvWeb.setText(web);

        ImageView ivCerar = (ImageView) dialog.findViewById(R.id.ivCerrar);
        // ivCerar.setBackgroundResource(R.drawable.close_black);
        ivCerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        /* CODIGO NUEVO */
        TextView tv_tags = (TextView) dialog.findViewById(R.id.tvTags);
        String txTag = "";
        int c = 0;
        for (String tag : tags) {

            if (c == 0) {
                txTag = tag;
                c++;
            } else {
                txTag += ", " + tag;
                c++;
            }
        }
        tv_tags.setText(txTag);
        /* CODIGO NUEVO */

        LinearLayout btirTiendaMapaFinal = (LinearLayout) dialog.findViewById(R.id.btirTiendaMapaFinal);
        btirTiendaMapaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGlobal.finalizado = false;
                endRoute = Integer.parseInt(node) - 1;
                idCont = 0;
                dvImgStore.setVisibility(GONE);
                drawView.setVisibility(GONE);
                drawViewPoint.setVisibility(GONE);
                drawViewPointEnd.setVisibility(GONE);
                drawViewRect.setVisibility(GONE);

                nombreTiendaContextual = "dsx123";
                arrForContextual = new ArrayList<String>();
                if (arrForContextual.size() > 0) {
                    arrForContextual.clear();
                }

                arrStoresForContextual.clear();

                calculateRoute(startRoute, endRoute);

                llTienda.setVisibility(GONE);
                llComida.setVisibility(GONE);
                llDescuentos.setVisibility(GONE);
                llParticipa.setVisibility(GONE);
                llMapa.setVisibility(View.VISIBLE);
                llServicios.setVisibility(View.VISIBLE);
                rlLineaGris.setVisibility(View.VISIBLE);
                selectTab(llMapaOpcion);
                lineaFlechaVisible(ivLineaRojaMapa, ivFlechaRojaMapa, ivLineaRojaTiendas, ivFlechaRojaTiendas, ivLineaRojaComida, ivFlechaRojaComida, ivLineaRojaDescuentos, ivFlechaRojaDescuentos, ivLineaRojaParticipa, ivFlechaRojaParticipa);

                seeStair();

                if (puntos.size() > 0) {
                    contadorPiso = 0;
                    contadorPiso = Integer.parseInt((String) arregloFloorEnd.get(0));
                    cambiarFondoPiso();
                    drawView.setVisibility(View.VISIBLE);
                    drawViewPoint.setVisibility(View.VISIBLE);
                    drawViewPoint.startAnimation(animFadeIn);

                    for (int i = 0; i < RGlobal.appF.messages.size(); i++) {
                        if (RGlobal.appF.messages.get(i).getNodeID().equals(arregloRutaFinal.get(arregloRutaFinal.size() - 1).toString())) {
                            categoriaForContextual = RGlobal.appF.messages.get(i).getCategory().toString();
                            nombreTiendaContextualFin = RGlobal.appF.messages.get(i).getName().toString();
                        }
                    }

                    for (int i = 0; i < arregloRutaFinal.size(); i++) {
                        for (int j = 0; j < RGlobal.appF.messages.size(); j++) {
                            if (RGlobal.appF.messages.get(j).getNodeIDForContextual().equals(arregloRutaFinal.get(i).toString()) && RGlobal.appF.messages.get(j).getCategory().equals(categoriaForContextual)) {
                                arrForContextual.add(RGlobal.appF.messages.get(j).getStoreNumber());
                            }
                        }
                    }


                    for (int i = 0; i < arrForContextual.size(); i++) {
                        for (int j = 0; j < RGlobal.listaDescuentosCategoria.size(); j++) {
                            if (RGlobal.listaDescuentosCategoria.get(j).getStoreNumber().equals(arrForContextual.get(i).toString())) {
                                for (int k = 0; k < RGlobal.appF.messages.size(); k++) {
                                    if (RGlobal.appF.messages.get(k).getStoreNumber().equals(arrForContextual.get(i).toString())) {
                                        float afx = (float) RGlobal.arregloLocationX.get(Integer.parseInt(RGlobal.appF.messages.get(k).getNodeID())- 1);
                                        float afy = (float) RGlobal.arregloLocationY.get(Integer.parseInt(RGlobal.appF.messages.get(k).getNodeID())- 1);
                                        int bfx = (int) afx;
                                        int bfy = (int) afy;

                                        float ax = (float) RGlobal.arregloLocationX.get(Integer.parseInt(RGlobal.appF.messages.get(k).getNodeIDForContextual()) - 1);
                                        int bx = (int) ax;
                                        float ay = (float) RGlobal.arregloLocationY.get(Integer.parseInt(RGlobal.appF.messages.get(k).getNodeIDForContextual()) - 1);
                                        int by = (int) ay;

                                        arrStoresForContextual.put(String.valueOf(i), new Stores(RGlobal.appF.messages.get(k).getFloor(), RGlobal.appF.messages.get(k).getName(),
                                                RGlobal.appF.messages.get(k).getStoreNumber(), RGlobal.listaDescuentosCategoria.get(j).getPercentaje(), RGlobal.listaDescuentosCategoria.get(j).getTitle(),
                                                (float) bfx, (float) bfy, (float) bx, (float) by));

                                    }
                                }
                            }
                        }
                    }

                    /*for (Map.Entry<String, Stores> arrStForCont : arrStoresForContextual.entrySet()) {
                        //Log.e("TAG", "nombre contextual: " + nombreTiendaContextual);
                        if (nombreTiendaContextual.equals("dsx123") && !nombreTiendaContextualFin.equals(arrStForCont.getValue().getName())) {
                            numeroTiendaContextualFin = arrStForCont.getValue().getStoreNumber();
                            numeroPisoContextual = arrStForCont.getValue().getFloor();
                            nombreTiendaContextual = arrStForCont.getValue().getName();
                            Log.e("TAG", "nombre: " + nombreTiendaContextual);
                            Log.e("TAG", "porcentaje: " + nombreTiendaContextual);
                            Log.e("TAG", "nombre: " + nombreTiendaContextual);
                            porcentajeContextual = String.valueOf(arrStForCont.getValue().getPercentaje());
                            xx1 = arrStForCont.getValue().getCoordX();
                            yy1 = arrStForCont.getValue().getCoordY();
                       }
                    }*/
                    limpiar();
                    RGlobal.inMapa = true;
                    drawView.init(puntos, arregloRutaFinal, arregloStair, idCont, rectDib, arregloFloorEnd, arregloStair2);
                    drawViewPoint.init(puntos, idCont);

                }
                dialog.cancel();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                verTiendaActivate = false;
            }
        });
        dialog.show();
    }


    public void limpiarBuscadorTienda() {
        rlBotonNaranjaTienda.setVisibility(GONE);
        rlEdittextTienda.setVisibility(GONE);
        rlBotonAzulTienda.setVisibility(View.VISIBLE);
        llBotonesPisosTienda.setVisibility(GONE);
        llBotonesTienda2.setVisibility(GONE);
        llBotonesTienda.setVisibility(View.VISIBLE);
        etEdittextTienda.setText("");
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
