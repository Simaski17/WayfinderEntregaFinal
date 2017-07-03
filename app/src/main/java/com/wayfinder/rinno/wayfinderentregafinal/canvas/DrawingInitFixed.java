package com.wayfinder.rinno.wayfinderentregafinal.canvas;

import android.animation.AnimatorSet;
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

public class DrawingInitFixed extends View {
   Path path;
   Paint paint2;
    int contador;

    public float coordx;
    public float coordy;
    final AnimatorSet mAnimationSet = new AnimatorSet();

    public DrawingInitFixed(Context context) {
        super(context);
    }

    public DrawingInitFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingInitFixed(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(float x, float y, int cont) {
        coordx = x;
        coordy = y;

        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.rojopunto));

        path = new Path();
        if (cont == 0) {
            contador = cont;
            path.moveTo(coordx, coordy);
        }

    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawPath(path, paint2);
        c.drawCircle(coordx, coordy, 10, paint2);
        invalidate();
    }
}
