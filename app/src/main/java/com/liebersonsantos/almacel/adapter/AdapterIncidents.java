package com.liebersonsantos.almacel.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.model.Incident;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIncidents extends RecyclerView.Adapter<AdapterIncidents.IncidentsViewHolder>{

    private List<Incident> incidentList;

    public AdapterIncidents(List<Incident> incidentList) {
        this.incidentList = incidentList;
    }

    @NonNull
    @Override
    public IncidentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_description, parent, false);

        return new AdapterIncidents.IncidentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncidentsViewHolder holder, int position) {

        Incident incident = incidentList.get(position);

        if(incident != null){
            holder.txtIdIncident.setText(String.valueOf(incident.getId()));
            holder.txtAttendantName.setText(incident.getAttendantName());
            holder.txtClientName.setText(incident.getClientName());
            holder.txtDescricao.setText(incident.getDescription());
            holder.txtStatus.setText(incident.getStatus());
            holder.txtCreationTime.setText(incident.getCreationTime());
        }
    }

    @Override
    public int getItemCount() {
        return incidentList.size();
    }

    public static class IncidentsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_content_id_incident)
        TextView txtIdIncident;
        @BindView(R.id.item_content_attendant_name)
        TextView txtAttendantName;
        @BindView(R.id.item_content_client_name)
        TextView txtClientName;
        @BindView(R.id.item_content_description)
        TextView txtDescricao;
        @BindView(R.id.item_content_status)
        TextView txtStatus;
        @BindView(R.id.item_content_creation_time)
        TextView txtCreationTime;
        @BindView(R.id.item_content_btn_conclude_incident)
        Button btnConcludeIncident;

        public IncidentsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

//        public void bind(Incident incident){
//
//            txtIdIncident.setText(incident.toString());
//            ImageUtil.loadImage(Constantes.URL_BASE_CATEGORY_COVERS + categories.getMagazineCatCover(), imgItemCategory, progressBarCategory, R.drawable.logo);
//
//
//        }

    }
}
