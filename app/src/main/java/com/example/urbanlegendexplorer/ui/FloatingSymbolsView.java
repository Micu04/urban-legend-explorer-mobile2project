package com.example.urbanlegendexplorer.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.urbanlegendexplorer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloatingSymbolsView extends View {

    private final List<FloatingSymbol> symbols = new ArrayList<>();
    private final List<Bitmap> bitmaps = new ArrayList<>();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Random random = new Random();

    public FloatingSymbolsView(Context context) {
        super(context);
        init();
    }

    public FloatingSymbolsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.bloody));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.bloody_eye));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.grimoire));

        for (int i = 0; i < 25; i++) {
            symbols.add(createRandomSymbol());
        }
    }

    private FloatingSymbol createRandomSymbol() {
        return new FloatingSymbol(
                random.nextInt(1000),
                random.nextInt(2000),
                0.5f + random.nextFloat() * 1.5f,
                45 + random.nextInt(45),
                45 + random.nextInt(80),
                random.nextInt(bitmaps.size())
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (FloatingSymbol symbol : symbols) {
            symbol.update(getWidth(), getHeight(), random, bitmaps.size());

            paint.setAlpha(symbol.alpha);

            Bitmap originalBitmap = bitmaps.get(symbol.imageIndex);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                    originalBitmap,
                    symbol.size,
                    symbol.size,
                    false
            );

            canvas.drawBitmap(scaledBitmap, symbol.x, symbol.y, paint);
        }

        postInvalidateOnAnimation();
    }
}