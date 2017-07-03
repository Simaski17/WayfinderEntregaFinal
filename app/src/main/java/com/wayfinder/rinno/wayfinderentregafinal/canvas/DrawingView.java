package com.wayfinder.rinno.wayfinderentregafinal.canvas;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.RGlobal;
import com.wayfinder.rinno.wayfinderentregafinal.model.Contextual;
import com.wayfinder.rinno.wayfinderentregafinal.model.Message;
import com.wayfinder.rinno.wayfinderentregafinal.model.Nodes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tecnova on 09-04-2017.
 */

public class DrawingView extends View { //Clase canvas para dibujar linea y punto de inicio. Se realiza animacion de linea.


    Path path;
    Paint paint;
    Paint paint2;
    float length;
    public String stair;
    public static ObjectAnimator animator;

    public ArrayList<Float> coordx = new ArrayList<Float>();
    public ArrayList<Float> coordy = new ArrayList<Float>();
    ArrayList arreglotemporal;
    ArrayList arreglosegmentado = new ArrayList();
    ArrayList arreglorecorrido = new ArrayList();
    ArrayList arregloUnidad = new ArrayList();

    ArrayList escalera = new ArrayList();


    int contador;
    int drawRect;
    private int sumaNode;
    private int sumaTemp;
    private int contNode;
    private int contSeg3;
    private int finalCont;


    public DrawingView(Context context) {
        super(context);

    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(List<Nodes> puntos, ArrayList arregloRuta, ArrayList arregloStair, int cont, final int[] rectDib, ArrayList arregloPiso, ArrayList arregloStair2) {


        /*inicializa las variables a utilizar cada vez que sedibuja la ruta*/
        drawRect = 0;
        contNode = 0;
        stair = "stair";
        arreglosegmentado.clear();
        coordx = new ArrayList<>();
        coordy = new ArrayList<>();



        /*Objeto animador. Encargado de hacer la animacion de la linea con un tiempo de duracion de 5 segundos*/
        animator = ObjectAnimator.ofFloat(this, "phase", 1.0f, 0.0f);
        if (arregloUnidad.size() == 1) {
            animator.setDuration(100);
        } else {
            animator.setDuration(5000);
        }


        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.rojopunto));
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.rojopunto));

        path = new Path();
        /*Agregamos los X e Y por donde va a ser dibujada la ruta*/
        for (int i = 0; i < puntos.size(); i++) {
            coordx.add((float) puntos.get(i).getLocationX());
            coordy.add((float) puntos.get(i).getLocationY());
        }

        arreglotemporal = new ArrayList();

        /*Si se detecta una escalera en la ruta optima se utiliza la funcion segmentar ruta donde se divide la ruta en dos.
        * Si no agrega la ruta al arreglo temporal*/
        if (arregloStair.size() > 0) {
            segmentarRuta(arregloRuta, arregloStair, arregloPiso, arregloStair2);
        } else {
            for (int i = 0; i < arregloRuta.size(); i++) {
                arreglotemporal.add(arregloRuta.get(i));
            }
            arreglosegmentado.add(arreglotemporal);
        }

        /*Primer if determina si la variable cont es igual a 0 y si el arreglo de la escalera es igual a 0. Si esto ocurre no hay cambio de piso
        * por lo tanto dibuja la ruta en el piso donde se haya seleccionado los puntos.
        *
        * Segundo if si la variable cont es igual a 0 pero el arreglo escalera es mayor a 0 ocurre que habra cambio de piso y la ruta se dibujara
        * solo hasta donde llegue la primera escalera y se quedara ahi esperando hasta que ocurra un click sobre la imagen de la escalera
        * o el icono de continuar ruta.
        *
        * Tercer if si la variable cont es diferente de 0 y el arreglo escalera es mayor que 0 se dibujara la ruta desde la segunda escalera hasta
        * el punto final de la ruta seleccionada.*/

        if (arreglosegmentado.size() > 2) {
            if (cont == 0 && arregloStair.size() == 0) {
                drawRect = 1;
                contador = cont;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(0);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(cont), coordy.get(cont));//Inicia el dibujado en el canvas desde este punto especifico
                    for (int k = cont + 1; k < arreglorecorrido.size(); k++) {
                        path.lineTo(coordx.get(k), coordy.get(k));//mueve la linea de el punto inicial hasta el puntio final que se le indique
                    }
                }
                Log.e("TAG","1");
            } else if (cont == 0) {
                contador = cont;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(0);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(cont), coordy.get(cont));
                    for (int k = cont + 1; k < arreglorecorrido.size(); k++) {
                        path.lineTo(coordx.get(k), coordy.get(k));
                        cont = k;
                        sumaNode = k;
                    }

                }
                arregloUnidad = (ArrayList) arreglosegmentado.get(1);
                contSeg3 = 1;
                Log.e("TAG","2");
            } else if (arregloUnidad.size() == 1) {
                contador = sumaNode + 1;
                sumaTemp = contador;
                contNode = 2;
                contSeg3 = 10;
                arregloUnidad.add(arreglosegmentado.get(2));
                Log.e("TAG","3");
            } else if (contSeg3 == 1 && cont == sumaNode) {
                contador = cont + 1;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(1);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(sumaNode + 1), coordy.get(sumaNode + 1));
                    for (int k = 1; k < arreglorecorrido.size(); k++) {
                        sumaTemp = sumaNode + k;
                        path.lineTo(coordx.get(sumaNode + k + 1), coordy.get(sumaNode + k + 1));
                    }
                }
                contNode = 1;
                cont = sumaTemp;
                Log.e("TAG","4");
            } else {
                drawRect = 1;
                contador = sumaTemp + 1;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(2);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(sumaTemp + 1), coordy.get(sumaTemp + 1));
                    for (int k = 1; k < arreglorecorrido.size(); k++) {
                        sumaNode = sumaTemp + k;
                        if (contSeg3 > 1) {
                            path.lineTo(coordx.get(sumaTemp + k + 1), coordy.get(sumaTemp + k + 1));
                        } else {
                            path.lineTo(coordx.get(sumaTemp + k + 2), coordy.get(sumaTemp + k + 2));
                        }
                    }
                }
                arregloUnidad.clear();
                cont = 10;
                Log.e("TAG","5");
            }
        } else {//DOS PISOS
            if (cont == 0 && arregloStair.size() == 0) {
                drawRect = 1;
                contador = cont;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(0);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(cont), coordy.get(cont));//Inicia el dibujado en el canvas desde este punto especifico
                    for (int k = cont + 1; k < arreglorecorrido.size(); k++) {
                        path.lineTo(coordx.get(k), coordy.get(k));//mueve la linea de el punto inicial hasta el puntio final que se le indique
                    }
                }
                //SETEPONTOCINCO
                Log.e("TAG","6");
            } else if (cont == 0) {
                /*Log.e("TAG","STAIR: "+arregloStair2.get(0));
                Log.e("TAG","RUTA TAMANIO: "+arreglosegmentado.size());
                ArrayList<String> prueba = (ArrayList<String>) arreglosegmentado.get(0);
                Log.e("TAG","RUTA PRUEBA: "+prueba.get(0));
                for(int i = 0; i < arregloStair.size(); i++){
                    for(int j = 0; j < arregloRuta.size(); j++){
                        if(arregloStair.get(i).equals(arregloRuta.get(0)) || arregloStair.get(i).equals(arregloRuta.get(arregloRuta.size() -1))){
                            Log.e("TAG","CASA IDEAS");
                        }
                    }
                }*/
                if(arreglosegmentado.size() == 1){
                    drawRect = 1;
                }
                contNode = 0;
                contador = cont;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(0);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(cont), coordy.get(cont));
                    for (int k = cont + 1; k < arreglorecorrido.size(); k++) {
                        path.lineTo(coordx.get(k), coordy.get(k));
                        cont = k;
                    }
                }
                Log.e("TAG","7");
            } else {
                contNode = 0;
                drawRect = 1;
                contador = cont + 1;
                for (int i = 0; i < arregloRuta.size(); i++) {
                    arreglorecorrido = (ArrayList) arreglosegmentado.get(1);
                    setDurationAnimation(arreglorecorrido.size());
                    path.moveTo(coordx.get(cont + 1), coordy.get(cont + 1));
                    for (int k = 1; k < arreglorecorrido.size(); k++) {
                        int suma = cont + k;
                        path.lineTo(coordx.get(cont + k + 1), coordy.get(cont + k + 1));
                    }
                }
                Log.e("TAG","8");
            }
        }
        /*Habilita la escalera y la imagen de continuar ruta dependiendo el numero de escalera donde se segmenta la ruta*/
        if (arreglorecorrido.get(arreglorecorrido.size() - 1).equals("8") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("55") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("393")) {
            stair = "uno";
        } else if (arreglorecorrido.get(arreglorecorrido.size() - 1).equals("5") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("56") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("395")) {
            stair = "dos";
        } else if (arreglorecorrido.get(arreglorecorrido.size() - 1).equals("7") || arreglorecorrido.get(arreglorecorrido.
                size() - 1).equals("57") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("394")) {
            stair = "tres";
        } else if (arreglorecorrido.get(arreglorecorrido.size() - 1).equals("6") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("58") || arreglorecorrido.get(arreglorecorrido.size() - 1).equals("396")) {
            stair = "cuatro";
        } else {
            stair = "stair";
        }
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

        float[] intervals = new float[]{length, length};

        /*Inicia la animacion*/


        animator.start();

        finalCont = cont; //Variable que es igual al cont y determina a que piso debe cambiar cuando se detecta que hay cambio de piso.
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * En los 3 if arriba se utiliza la variable drawrect si la variable es igual a 1 se envia un evento por eventbus
                 * donde se le informa a la actividad principal que debe iluminar la tienda bien sea por imagen o por codigo con canvas.
                 */

                if (RGlobal.inMapa) {
                    if ((drawRect == 1)) {
                        EventBus.getDefault().postSticky(new Message(4));
                        RGlobal.finalizado = true;
                        Log.e("TAG","LOCALIZADO");
                    }

                /*
                * Evento que se envia por eventbus donde se informa a la actividad principal a que piso debe cambiar.
                * La variable finalCont se encarga de llevar el menssaje del piso al cual debe cambiar.
                */
                    RGlobal.clickeado = false;
                    EventBus.getDefault().postSticky(new Message(finalCont, stair, contNode));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    /*LLama a la funcion que crea el efecto de dibujado de la linea*/
    public void setPhase(float phase) {
        //Log.d("pathview","setPhase called with:" + String.valueOf(phase));
        paint.setPathEffect(createPathEffect(length, phase, 0.0f));
        invalidate();//will calll onDraw

    }

    public void setDurationAnimation(int numNodes) {

        if (numNodes < 6) {
            animator.setDuration(2000);
        } else if (numNodes < 15) {
            animator.setDuration(4000);
        } else {
            animator.setDuration(5000);
        }
    }


    //TODO buscar el codigo donde se levanta el popup de publicidad contextual.
    //TODO Cambiar imagen  de loading en detalle de la tienda.


    /*Efecto creado para dar animacion a la linea. Si se elimina la linea aparece dibujada automaticamente sin hacer
    * el efecto que se va dibujando punto a punto en un intervalo de tiempo*/
    private PathEffect createPathEffect(float pathLength, float phase, float offset) {
        if (phase == 0.5) {
            //Toast.makeText(getContext(), "ASDFGHJKL: "+finalCont, Toast.LENGTH_SHORT).show();
            EventBus.getDefault().postSticky(new Contextual(finalCont));
        }
        return new DashPathEffect(new float[]{
                pathLength, pathLength
        },
                Math.max(phase * pathLength, offset));
    }

    /*Funcion que se ejecuta si se encuentra una escalera en la ruta a dibujar
    * divide la ruta en dos segmentos. Cada uno dibujados por separado.*/
    public void segmentarRuta(ArrayList arregloRuta, ArrayList arregloStair, ArrayList arregloPiso, ArrayList arregloStair2) {


        if (arregloPiso.get(0).equals("3") && arregloPiso.get(arregloPiso.size() - 1).equals("2") || arregloPiso.get(0).equals("2") && arregloPiso.get(arregloPiso.size() - 1).equals("3")) {

            for (int i = 0; i < arregloRuta.size(); i++) {
                int j = 0;
                arreglotemporal.add(arregloRuta.get(i));
                if (j <= arregloStair.size()) {
                    if (i == (int) arregloStair.get(j) || i == arregloRuta.size() - 1) {
                        arreglosegmentado.add(arreglotemporal);
                        arreglotemporal = new ArrayList();
                    }
                }
                j++;
            }

        } else if (arregloPiso.get(0).equals("1") && arregloPiso.get(arregloPiso.size() - 1).equals("2") || arregloPiso.get(0).equals("2") && arregloPiso.get(arregloPiso.size() - 1).equals("1")) {
            for (int i = 0; i < arregloRuta.size(); i++) {
                int j = 0;
                arreglotemporal.add(arregloRuta.get(i));
                if (j <= arregloStair.size()) {
                    if (i == (int) arregloStair.get(j) || i == arregloRuta.size() - 1) {
                        arreglosegmentado.add(arreglotemporal);
                        arreglotemporal = new ArrayList();
                    }
                }
                j++;
            }
        } else {
            for (int i = 0; i < arregloStair2.size(); i++) {
                escalera.add(Integer.parseInt((String) arregloStair2.get(i)));
            }
            for (int i = 0; i < arregloRuta.size(); i++) {
                int k = Integer.parseInt((String) arregloRuta.get(i));
                if (escalera.contains(k)) {
                    if ((arregloRuta.size() - 1) != i) {
                        if (escalera.contains(Integer.parseInt((String) arregloRuta.get(i + 1)))) {
                            arreglotemporal.add(String.valueOf(k));
                            arreglosegmentado.add(arreglotemporal);
                            arreglotemporal = new ArrayList();
                        } else {
                            arreglotemporal.add(String.valueOf(k));
                        }
                    } else {
                        arreglotemporal.add(String.valueOf(k));
                        arreglosegmentado.add(arreglotemporal);
                        arreglotemporal = new ArrayList();
                    }
                } else {
                    arreglotemporal.add(String.valueOf(k));
                }
                if ((arregloRuta.size() - 1) == i) {
                    arreglosegmentado.add(arreglotemporal);
                    arreglotemporal = new ArrayList();
                }
            }
        }
    }

    @Override
    public void onDraw(final Canvas c) {
        super.onDraw(c);

        try {
            c.drawPath(path, paint);
            c.drawCircle(coordx.get(contador), coordy.get(contador), 10, paint2);
            invalidate();
        } catch (Exception ignored) {

        }
    }


}