package ifsc.testoni.mypaint;

import android.graphics.Paint;
import android.graphics.Path;

public class Drawing {
    private Paint paint;
    private Path path;

    public Drawing(Paint paint, Path path) {
        this.paint = paint;
        this.path = path;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public Path getPath() {
       return this.path;
    }
}
