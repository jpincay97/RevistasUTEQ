package com.example.revistasuteq.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.Modelo.Seccion;
import com.example.revistasuteq.R;

import java.util.ArrayList;

public class AdaptadorSeccion extends RecyclerView.Adapter<AdaptadorSeccion.ViewHolderSeccion>
        implements View.OnClickListener{

    ArrayList<Seccion> listaSeccion;
    private Context Ctx;
    private View.OnClickListener listener;

    public AdaptadorSeccion(ArrayList<Seccion> listaSeccion, Context mCtx) {
        this.listaSeccion = listaSeccion;
        Ctx=mCtx;
    }

    @Override
    public ViewHolderSeccion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_revistas,null,false);
        view.setOnClickListener(this);
        return new AdaptadorSeccion.ViewHolderSeccion(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSeccion holder, final int position) {

        holder.txtSeccion.setText(listaSeccion.get(position).getSeccion());
        //ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items);
        //holder.childRecyclerView.setAdapter(childRecyclerAdapter);
    }

    @Override
    public int getItemCount() {
        return listaSeccion.size();
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

    public class ViewHolderSeccion extends RecyclerView.ViewHolder {

        TextView txtSeccion, txtId;
        RecyclerView rvSecciones;

        public ViewHolderSeccion(View itemView) {
            super(itemView);
            txtSeccion = (TextView) itemView.findViewById(R.id.txtSeccion);
            rvSecciones = itemView.findViewById(R.id.rvRevistas);
        }
    }
}