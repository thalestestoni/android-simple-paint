package ifsc.testoni.mypaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Circle extends Shape {
    @Override
    public void draw(Canvas canvas, Path path, Paint paint) {
        canvas.drawCircle(xCoordinateFromTheCircleCenter(), yCoordinateFromTheCircleCenter(), radius(), paint);
    }

    @Override
    public Shape newInstance() {
        return new Circle();
    }

    private float diameter() {
        return (float) Math.sqrt(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y2)));
    }

    private float radius() {
        return diameter() / 2;
    }

    private float xCoordinateFromTheCircleCenter(){
        return (x1 + x2) / 2;
    }

    private float yCoordinateFromTheCircleCenter() {
        return (y1 + y2) / 2;
    }
}
