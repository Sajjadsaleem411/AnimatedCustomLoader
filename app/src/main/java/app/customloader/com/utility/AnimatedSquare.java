package app.customloader.com.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import app.customloader.com.R;

public class AnimatedSquare extends View {
    Paint paint = new Paint();
    Context context;
    int lowPoint;
    int highPoint;
    int w, h;
    int gap;

    int f_line11_x1, f_line11_y1, f_line11_x2, f_line11_y2;
    int f_line12_x1, f_line12_y1, f_line12_x2, f_line12_y2;
    int f_line13_x1, f_line13_y1, f_line13_x2, f_line13_y2;
    int f_line14_x1, f_line14_y1, f_line14_x2, f_line14_y2;


    int s_line11_x1, s_line11_y1, s_line11_x2, s_line11_y2;
    int s_line12_x1, s_line12_y1, s_line12_x2, s_line12_y2;
    int s_line13_x1, s_line13_y1, s_line13_x2, s_line13_y2;
    int s_line14_x1, s_line14_y1, s_line14_x2, s_line14_y2;


    boolean f_line12, f_line13, f_line14;
    boolean s_line12, s_line13, s_line14, second = false;

    public AnimatedSquare(Context context) {
        super(context);
        this.context = context;
    }

    public AnimatedSquare(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public AnimatedSquare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void start() {
        initFirst(true);
    }

    public void stop() {
        initFirst(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        highPoint = (int) (w * 0.9);
        lowPoint = w - highPoint;
        gap = (int) (w * 0.032);
        initFirst(true);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        paint.setStrokeWidth((float) (w * 0.05));


        if (f_line11_y1 < highPoint) {
            f_line11_y1 += gap;
            //        canvas.drawf_line1(f_line11_x1, f_line11_y1, f_line11_x2, f_line11_y2, paint);

        } else {
            f_line12 = true;
            f_line12_y1 = f_line11_y1;
            f_line12_y2 = f_line11_y1;
            if (f_line12_x1 > lowPoint) {
                f_line12_x1 -= gap;
            } else {
                f_line13 = true;
                if (f_line13_y1 > lowPoint)
                    f_line13_y1 -= gap;
                else {
                    f_line14 = true;
                    if (f_line14_x1 < highPoint) {
                        f_line14_x1 += gap;
                    } else {
                        if (!second) {
                            second = true;
                            initSecond();
                        }
                    }
                }

            }
        }
        paint.setXfermode(null);
        paint.setColor(getResources().getColor(R.color.squareLoaderColor));
        canvas.drawLine(f_line11_x1, f_line11_y1, f_line11_x2, f_line11_y2, paint);
        if (f_line12)
            canvas.drawLine(f_line12_x1, f_line12_y1, f_line12_x2, f_line12_y2, paint);
        if (f_line13)
            canvas.drawLine(f_line13_x1, f_line13_y1, f_line13_x2, f_line13_y2, paint);
        if (f_line14)
            canvas.drawLine(f_line14_x1, f_line14_y1, f_line14_x2, f_line14_y2, paint);


        if (second) {
            //    paint.setColor(ContextCompat.getColor(context,s_color));
            if (s_line11_y1 < highPoint) {
                s_line11_y1 += gap;
                //        canvas.draws_line1(s_line11_x1, s_line11_y1, s_line11_x2, s_line11_y2, paint);

            } else {
                s_line12 = true;
                s_line12_y1 = s_line11_y1;
                s_line12_y2 = s_line11_y1;
                if (s_line12_x1 > lowPoint) {
                    s_line12_x1 -= gap;
                } else {
                    s_line13 = true;
                    if (s_line13_y1 > lowPoint)
                        s_line13_y1 -= gap;
                    else {
                        s_line14 = true;
                        if (s_line14_x1 < highPoint) {
                            s_line14_x1 += gap;
                        } else {
                            initFirst(true);
                            second = false;
                        }
                    }

                }
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

            canvas.drawLine(s_line11_x1, s_line11_y1, s_line11_x2, s_line11_y2, paint);

            if (s_line12)
                canvas.drawLine(s_line12_x1, s_line12_y1, s_line12_x2, s_line12_y2, paint);
            if (s_line13)
                canvas.drawLine(s_line13_x1, s_line13_y1, s_line13_x2, s_line13_y2, paint);
            if (s_line14)
                canvas.drawLine(s_line14_x1, s_line14_y1, s_line14_x2, f_line14_y2, paint);
        }

        invalidate();
    }


    public void initFirst(boolean flag) {
        if (!flag) {
            highPoint = 0;
            lowPoint = 0;
        }
        else {

            highPoint = (int) (w * 0.9);
            lowPoint = w - highPoint;
        }
        f_line11_x1 = highPoint;
        f_line11_y1 = lowPoint;
        f_line11_x2 = highPoint;
        f_line11_y2 = lowPoint;

        f_line12_x1 = highPoint;
        f_line12_y1 = lowPoint;
        f_line12_x2 = highPoint;
        f_line12_y2 = lowPoint;

        f_line13_x1 = lowPoint;
        f_line13_y1 = highPoint;
        f_line13_x2 = lowPoint;
        f_line13_y2 = highPoint;

        f_line14_x1 = lowPoint;
        f_line14_y1 = lowPoint;
        f_line14_x2 = lowPoint;
        f_line14_y2 = lowPoint;

        f_line12 = false;
        f_line13 = false;
        f_line14 = false;
        second = false;

    }

    public void initSecond() {
        s_line11_x1 = highPoint;
        s_line11_y1 = lowPoint;
        s_line11_x2 = highPoint;
        s_line11_y2 = lowPoint;

        s_line12_x1 = highPoint;
        s_line12_y1 = lowPoint;
        s_line12_x2 = highPoint;
        s_line12_y2 = lowPoint;

        s_line13_x1 = lowPoint;
        s_line13_y1 = highPoint;
        s_line13_x2 = lowPoint;
        s_line13_y2 = highPoint;

        s_line14_x1 = lowPoint;
        s_line14_y1 = lowPoint;
        s_line14_x2 = lowPoint;
        s_line14_y2 = lowPoint;
        s_line12 = false;
        s_line13 = false;
        s_line14 = false;
    }
}