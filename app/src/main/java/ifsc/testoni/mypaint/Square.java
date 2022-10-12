package ifsc.testoni.mypaint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Square extends Shape {
    @Override
    public void draw(Canvas canvas, Path path, Paint paint) {
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    @Override
    public Shape newInstance() {
        return new Square();
    }
}
