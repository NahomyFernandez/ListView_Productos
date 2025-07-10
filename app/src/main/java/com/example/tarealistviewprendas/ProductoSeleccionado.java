package com.example.tarealistviewprendas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ProductoSeleccionado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto_seleccionado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView img     = findViewById(R.id.imgDetail);
        TextView tvTitle  = findViewById(R.id.tvTitle);
        TextView tvPrice  = findViewById(R.id.tvPrice);
        TextView tvCat    = findViewById(R.id.tvCategory);
        Button btnPay   = findViewById(R.id.btnPay);


        Intent intent = getIntent();
        String title       = intent.getStringExtra("title");
        String price       = intent.getStringExtra("price");
        String category    = intent.getStringExtra("category");
        String imageUrl    = intent.getStringExtra("imageUrl");

        tvTitle.setText(title);
        String label   = "Total a pagar: ";
        String monto   = "$" + price;
        String text    = label + monto;

        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(
                new ForegroundColorSpan(Color.parseColor("#FF5722")),
                0,
                label.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        tvPrice.setText(spannable);

        String label2   = "Categoría: " ;
        String text2 = label2 + category ;


        SpannableString spannable2 = new SpannableString(text2);
        spannable2.setSpan(
                new ForegroundColorSpan(Color.parseColor("#FF5722")),
                0,
                label2.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        tvCat.setText(spannable2);

              Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.cargando)
                .into(img);

        btnPay.setOnClickListener(v -> {
            Intent i = new Intent(ProductoSeleccionado.this, Pago.class);
            startActivity(i);

        });

        Button btnNewProduct = findViewById(R.id.btnNewProduct);
        btnNewProduct.setOnClickListener(v -> {
            finish();
        });

    }
}

