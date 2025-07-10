package com.example.tarealistviewprendas;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPrendas extends ArrayAdapter<Prendas> {
    public AdaptadorPrendas(Context context, ArrayList<Prendas> datos) {
        super(context, R.layout.lyprendas, datos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.lyprendas, parent, false);
        }
        Prendas p = getItem(position);

        TextView lblCategoria = convertView.findViewById(R.id.lblCategoria);
        TextView lblTitulo    = convertView.findViewById(R.id.lblTitulo);
        TextView lblPrecio    = convertView.findViewById(R.id.lblPrecio);
        TextView lblDescripcion = convertView.findViewById(R.id.lblDescripcion);


        lblTitulo    .setText(               p.getTitulo());
        lblDescripcion.setText(               p.getDescripcion());
        String catPrefix = "Category: ";
        String category = p.getCategoria();
        SpannableStringBuilder catSp = new SpannableStringBuilder(catPrefix + category);
        catSp.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.prefixColor)),
                0,
                catPrefix.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        lblCategoria.setText(catSp);

        String prPrefix = "Price: ";
        String price    = p.getPrecio();
        SpannableStringBuilder prSp = new SpannableStringBuilder(prPrefix + price);
        prSp.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.prefixColor)),
                0,
                prPrefix.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        lblPrecio.setText(prSp);

        ImageView img = convertView.findViewById(R.id.imgUsr);
        Glide.with(getContext())
                .load(p.getUrlimagen())
                .into(img);

        return convertView;
    }

}
