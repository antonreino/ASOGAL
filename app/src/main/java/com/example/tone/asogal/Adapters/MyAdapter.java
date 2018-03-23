package com.example.tone.asogal.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tone.asogal.Model.Partido;
import com.example.tone.asogal.R;

import java.util.List;

/**
 * Created by Antonio Reino on 08/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Partido> listapartidos;

    public MyAdapter(List<Partido> listapartidos) {
        this.listapartidos = listapartidos;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.partidos,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        String EquipoLocal = listapartidos.get(position).getIdEquipoLocal();
        String EquipoVisitante = listapartidos.get(position).getIdEquipoVisitante();
        holder.IdEquipoLocal.setText(EquipoLocal);
        holder.IdEquipoVisitante.setText(EquipoVisitante);
    }

    @Override
    public int getItemCount() {
        return listapartidos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView IdEquipoLocal;
        private TextView IdEquipoVisitante;

        public ViewHolder(View v) {
            super(v);
            this.IdEquipoLocal = (TextView) v.findViewById(R.id.equipo1);
            this.IdEquipoVisitante=(TextView)v.findViewById(R.id.equipo2);
        }
    }
}

