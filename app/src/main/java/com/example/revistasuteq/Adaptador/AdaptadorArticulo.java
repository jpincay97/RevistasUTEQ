package com.example.revistasuteq.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.revistasuteq.Modelo.Articulo;
import com.example.revistasuteq.R;

import java.util.ArrayList;

public class AdaptadorArticulo extends RecyclerView.Adapter<AdaptadorArticulo.ViewHolderArticulo>
        implements View.OnClickListener{

    ArrayList<Articulo> listaArticulo;
    private Context Ctx;
    private View.OnClickListener listener;

    public AdaptadorArticulo(ArrayList<Articulo> listaArticulo, Context mCtx) {
        this.listaArticulo = listaArticulo;
        Ctx=mCtx;
    }

    @Override
    public ViewHolderArticulo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_articulos,null,false);
        view.setOnClickListener(this);
        return new AdaptadorArticulo.ViewHolderArticulo(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticulo holder, final int position) {

        holder.txtArticulo.setText(listaArticulo.get(position).getTitulo());
        holder.txtAutores.setText(listaArticulo.get(position).getAutores());
        holder.txtSeccion.setText(listaArticulo.get(position).getSeccion());
        holder.txtDOI.setText("DOI: "+listaArticulo.get(position).getDoi());
    }

    @Override
    public int getItemCount() {
        return listaArticulo.size();
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

    public class ViewHolderArticulo extends RecyclerView.ViewHolder {

        TextView txtArticulo, txtAutores, txtDOI, txtSeccion;

        public ViewHolderArticulo(View itemView) {
            super(itemView);
            txtArticulo = (TextView) itemView.findViewById(R.id.txtArticulo);
            txtAutores = (TextView) itemView.findViewById(R.id.txtAutores);
            txtSeccion = (TextView) itemView.findViewById(R.id.txtSeccion);
            txtDOI = (TextView) itemView.findViewById(R.id.txtDOI);
        }
    }
}