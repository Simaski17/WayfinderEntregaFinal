package com.wayfinder.rinno.wayfinderentregafinal.canvas;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.wayfinder.rinno.wayfinderentregafinal.R;


/**
 * Created by tecnova on 10-04-2017.
 */

public class DrawingInit extends View {//Clase canvas para dibujar punto de inicio con animacion fade-in fade-out
    Path path;
    Paint paint2;
    int contador;

    public float coordx;
    public float coordy;
    final AnimatorSet mAnimationSet = new AnimatorSet();

    public DrawingInit(Context context) {
        super(context);
    }

    public DrawingInit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingInit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(float x, float y, int cont) {
        coordx = x;
        coordy = y;

        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.rojoefecto));

        path = new Path();
        if (cont == 0) {
            contador = cont;
            path.moveTo(coordx, coordy);
        }

        /*ObjectAnimator fade-in fade-out con tiempo de 0.6 segundos */
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1f, .3f);
        fadeOut.setDuration(500);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(this, "alpha", .3f, 1f);
        fadeIn.setDuration(500);


        mAnimationSet.end();
        mAnimationSet.play(fadeIn).after(fadeOut);



    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        if (c != null) {

            c.drawPath(path, paint2);
            c.drawCircle(coordx, coordy, 20, paint2);
            invalidate();
        }
    }
}

