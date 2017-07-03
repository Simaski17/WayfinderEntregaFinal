package com.wayfinder.rinno.wayfinderentregafinal.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wayfinder.rinno.wayfinderentregafinal.R;


/**
 * Created by tecnova on 09-04-2017.
 */

public class DrawingRectView extends View {//Clase canvas para dibujar rectangulo tienda.
    Path path;
    Paint paint2;
    float length;
    private int Xi = 0;
    private int Yi = 0;
    private int Xf = 0;
    private int Yf = 0;

    public DrawingRectView(Context context) {
        super(context);
    }

    public DrawingRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(int piso, int[] rectDib) {
        /*Se reciben el X inicio y X final mas el Y inicio y el Y final donde se dibujara el rectangulo de la tienda*/
        Xi = rectDib[0];
        Yi = rectDib[1];
        Xf = rectDib[2];
        Yf = rectDib[3];

        Log.e("TAG","RUTA FINAL DIBUJO: "+Xi +" Y: " +Yi + " Xf: "+Xf + " Yf: "+Yf);

        if (piso == 1) {
            paint2 = new Paint();
            paint2.setColor(getResources().getColor(R.color.tiendapisouno));
        } else if (piso == 2) {
            paint2 = new Paint();
            paint2.setColor(getResources().getColor(R.color.tiendapisodos));
        } else {
            paint2 = new Paint();
            paint2.setColor(getResources().getColor(R.color.tiendapisotres));
        }

        path = new Path();


        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        try {
            c.drawPath(path, paint2);
            c.drawRect(Xi, Yi, Xf, Yf, paint2);
            invalidate();
        } catch (Exception ignored) {

        }
    }
}