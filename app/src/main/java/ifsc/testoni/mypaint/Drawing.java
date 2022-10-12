package ifsc.testoni.mypaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Drawing {
    public Paint paint;
    public Path path;
    public Shape shape;

    public Drawing(Paint paint, Path path, Shape shape) {
        this.paint = paint;
        this.path = path;
        this.shape = shape;
    }

    public void toDraw(Canvas canvas) {
        shape.draw(canvas, path, paint);
    }
}
