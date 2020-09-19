package com.example.revistasuteq.Adaptador;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.Modelo.Revista;
import com.example.revistasuteq.R;

import java.util.ArrayList;

public class AdaptadorRevista extends RecyclerView.Adapter<AdaptadorRevista.ViewHolderRevista>
    implements View.OnClickListener{

    ArrayList<Revista> listaRevista;
    private Context Ctx;
    private View.OnClickListener listener;

    public AdaptadorRevista(ArrayList<Revista> listaRevista, Context mCtx) {
        this.listaRevista = listaRevista;
        Ctx=mCtx;
    }

    @Override
    public ViewHolderRevista onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_revistas,null,false);
        view.setOnClickListener(this);
        return new ViewHolderRevista(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorRevista.ViewHolderRevista holder, final int position) {
        Revista revista = listaRevista.get(position);
        holder.lblNombre.setText(listaRevista.get(position).getNombre());
        holder.lblDescripcion.setText(Html.fromHtml(listaRevista.get(position).getDescripcion()));
        Glide.with(Ctx)
                .load(revista.getImagen())
                .into(holder.imgImagen);
    }

    @Override
    public int getItemCount() {
        return listaRevista.size();
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

    public class ViewHolderRevista extends RecyclerView.ViewHolder {

        TextView lblNombre;
        TextView lblDescripcion;
        ImageView imgImagen;

        public ViewHolderRevista(View itemView) {
            super(itemView);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
            imgImagen = (ImageView) itemView.findViewById(R.id.imgImagen);
        }
    }
}