package ifsc.testoni.mypaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public abstract class Shape {
    protected float x1, y1, x2, y2;

    public Shape() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }

    protected void setFirstPoint(float x1, float y1) {
        this.x1 = x1;
        this.y1= y1;
    }

    protected void setSecondPoint(float x2, float y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public abstract void draw(Canvas canvas, Path path, Paint paint);
    public abstract Shape newInstance();
}
