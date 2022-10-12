package ifsc.testoni.mypaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Scribble extends Shape {
    public void draw(Canvas canvas, Path path, Paint paint) {
        canvas.drawPath(path, paint);
    }

    @Override
    public Shape newInstance() {
        return new Scribble();
    }
}
