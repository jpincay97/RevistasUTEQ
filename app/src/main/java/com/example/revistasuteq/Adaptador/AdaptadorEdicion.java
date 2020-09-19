package com.example.revistasuteq.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.Modelo.Edicion;
import com.example.revistasuteq.R;

import java.util.ArrayList;

public class AdaptadorEdicion extends RecyclerView.Adapter<AdaptadorEdicion.ViewHolderEdicion>
        implements View.OnClickListener{

    ArrayList<Edicion> listaEdicion;
    private Context Ctx;
    private View.OnClickListener listener;

    public AdaptadorEdicion(ArrayList<Edicion> listaEdicion, Context mCtx) {
        this.listaEdicion = listaEdicion;
        Ctx=mCtx;
    }

    @Override
    public AdaptadorEdicion.ViewHolderEdicion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_ediciones,null,false);
        view.setOnClickListener(this);
        return new AdaptadorEdicion.ViewHolderEdicion(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorEdicion.ViewHolderEdicion holder, final int position) {
        Edicion Edicion = listaEdicion.get(position);
        holder.txtVolumen.setText("Volumen: "+listaEdicion.get(position).getVolumen());
        holder.txtNumero.setText("Número: "+listaEdicion.get(position).getNumero());
        holder.txtAnio.setText("Año: "+listaEdicion.get(position).getAnio());
        holder.txtFecha.setText("Fecha de publicación: "+listaEdicion.get(position).getFecha());
        Glide.with(Ctx)
                .load(Edicion.getImagen())
                .into(holder.imgImagen);
    }

    @Override
    public int getItemCount() {
        return listaEdicion.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolderEdicion extends RecyclerView.ViewHolder {

        TextView txtVolumen, txtNumero, txtAnio, txtFecha;
        ImageView imgImagen;

        public ViewHolderEdicion(View itemView) {
            super(itemView);
            txtVolumen = (TextView) itemView.findViewById(R.id.txtVolumen);
            txtNumero = (TextView) itemView.findViewById(R.id.txtNumero);
            txtAnio = (TextView) itemView.findViewById(R.id.txtAnio);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
            imgImagen = (ImageView) itemView.findViewById(R.id.imgArticulo);
        }
    }
}
