package ifsc.testoni.mypaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;
    ImageView colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simplePaint = findViewById(R.id.simplePaint);
        colorPicker = findViewById(R.id.colorPicker);
    }

    public void colorPickerSelectColor(View view) {
        new ColorPickerDialog.Builder(this)
            .setTitle("Seletor de cores")
            .setPreferenceName("MyColorPicker")
            .setPositiveButton(getString(R.string.confirm),
                    (ColorEnvelopeListener) (envelope, fromUser) -> handleColorPickerConfirm(envelope))
            .setNegativeButton(getString(R.string.cancel),
                    (dialogInterface, i) -> dialogInterface.dismiss())
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(12)
            .show();
    }

    public void handleSelectLine(View view) {
        simplePaint.setDrawingShape(new Line());
    }

    public void handleSelectScribble(View view) {
        simplePaint.setDrawingShape(new Scribble());
    }

    public void handleSelectCircle(View view) {
        simplePaint.setDrawingShape(new Circle());
    }

    public void handleSelectSquare(View view) {
        simplePaint.setDrawingShape(new Square());
    }

    public void handleUndoDrawing(View view) {
        simplePaint.undoLastDrawing();
    }

    public void handleDeleteAllDrawings(View view) {
        simplePaint.undoAllDrawings();
    }

    private void handleColorPickerConfirm(ColorEnvelope envelope) {
        simplePaint.setColor(envelope.getColor());
        colorPicker.setColorFilter(envelope.getColor());
    }
}