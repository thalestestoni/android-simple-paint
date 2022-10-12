package ifsc.testoni.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Stack;

public class SimplePaint extends View {
    Stack<Drawing> drawings;
    Drawing currentDrawing;
    ColorDrawable currentColor;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.drawings = new Stack<>();
        this.currentColor = new ColorDrawable();
        this.currentColor.setColor(Color.BLACK);
        this.currentDrawing = new Drawing(loadPaintDefaults(new Paint()), new Path(), new Scribble());
    }

    private void startNewDrawingLayer() {
        currentDrawing = new Drawing(loadPaintDefaults(new Paint()), new Path(), currentDrawing.shape.newInstance());
    }

    private Paint loadPaintDefaults(Paint paint) {
        paint.setColor(currentColor.getColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Drawing drawing : drawings) {
            drawing.toDraw(canvas);
        }

        currentDrawing.toDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentDrawing.path.moveTo(x, y);
                currentDrawing.path.lineTo(x, y);
                currentDrawing.shape.setFirstPoint(x, y);
                currentDrawing.shape.setSecondPoint(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                currentDrawing.path.lineTo(x, y);
                currentDrawing.shape.setSecondPoint(x, y);
                break;
            case MotionEvent.ACTION_UP:
                drawings.push(currentDrawing);
                startNewDrawingLayer();
                break;
            default:
                break;
        }

        invalidate();

        return true;
    }

    public void setColor(int color) {
        currentColor.setColor(color);
        currentDrawing.paint.setColor(color);
    }

    public void setDrawingShape(Shape shape) {
        currentDrawing.shape = shape;
    }

    public void undoLastDrawing() {
        drawings.pop();
        invalidate();
    }

    public void undoAllDrawings() {
        drawings.clear();
        invalidate();
    }
}
