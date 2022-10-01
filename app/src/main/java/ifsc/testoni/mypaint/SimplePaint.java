package ifsc.testoni.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Stack;

public class SimplePaint extends View {
    Stack<Drawing> drawings;
    Drawing drawing;
    ColorDrawable currentColor;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawings = new Stack<>();
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        startCurrentDrawingLayer();
    }

    public void startCurrentDrawingLayer() {
        drawing = new Drawing(new Paint(), new Path());
        drawing.getPaint().setColor(currentColor.getColor());
        drawing.getPaint().setStyle(Paint.Style.STROKE);
        drawing.getPaint().setStrokeWidth(20);
        drawing.getPaint().setColor(currentColor.getColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Drawing drawing : drawings) {
            canvas.drawPath(drawing.getPath(), drawing.getPaint());
        }

        canvas.drawPath(drawing.getPath(), drawing.getPaint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x, y;

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawing.getPath().moveTo(x, y);
                drawing.getPath().lineTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                drawing.getPath().lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                drawing.getPath().lineTo(x, y);
                drawings.push(new Drawing(drawing.getPaint(), drawing.getPath()));
                startCurrentDrawingLayer();
                break;
            default:
                break;
        }

        invalidate();

        return true;
    }

    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        drawing.getPaint().setColor(color.toArgb());
    }

    public void removeLastLayer() {
        drawings.pop();
        invalidate();
    }

    public void removeDrawings() {
        drawings.clear();
        invalidate();
    }
}
