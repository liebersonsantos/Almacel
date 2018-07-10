package com.liebersonsantos.almacel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.AttendantsDAO;
import com.liebersonsantos.almacel.dataBase.ClientDAO;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;
import com.liebersonsantos.almacel.model.Incident;
import com.liebersonsantos.almacel.view.IncidentDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.GRAY;

public class AdapterIncidents extends RecyclerView.Adapter<AdapterIncidents.IncidentsViewHolder> {

    private List<Incident> incidentList;
    private ClientDAO clientDAO;
    private AttendantsDAO attendantsDAO;

    public AdapterIncidents(Context context, List<Incident> incidentList) {
        this.incidentList = incidentList;
        this.clientDAO = new ClientDAO(context);
        this.attendantsDAO = new AttendantsDAO(context);
    }

    @NonNull
    @Override
    public IncidentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_description, parent, false);
        return new AdapterIncidents.IncidentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IncidentsViewHolder holder, final int position) {
        final Incident incident = incidentList.get(position);

        holder.bind(incident, attendantsDAO, clientDAO);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), IncidentDetailActivity.class);
                intent.putExtra("incident_id", incident.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return incidentList.size();
    }

    class IncidentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_content_id_incident)
        TextView txtIdIncident;

        @BindView(R.id.item_content_attendance)
        TextView txtAttendantName;

        @BindView(R.id.item_content_client_name)
        TextView txtClientName;

        @BindView(R.id.item_content_date)
        TextView txtDate;

        @BindView(R.id.imageView)
        ImageView imageView;

        IncidentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Incident incident, AttendantsDAO attendantsDAO, ClientDAO clientDAO) {

            if (incident != null) {
                txtIdIncident.setText(txtIdIncident.getContext().getString(R.string.text_fotmat, "Id: ", String.valueOf(incident.getId())));
                txtAttendantName.setText(txtIdIncident.getContext().getString(R.string.text_fotmat, "Atendente: ", attendantsDAO.getAttendantById(incident.getAttendant()).getAttendantName()));
                txtClientName.setText(txtIdIncident.getContext().getString(R.string.text_fotmat, "Cliente: ", clientDAO.getClientById(incident.getClient()).getName()));
                txtDate.setText(txtIdIncident.getContext().getString(R.string.text_fotmat, "Data de criação:\n", incident.getCreationTime()));

                if (incident.getStatus().equalsIgnoreCase("aberto")){
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            incident.setStatus("fechado");
                            new IncidentDAO(v.getContext()).updateIncident(incident);
                            imageView.setVisibility(View.GONE);
                            itemView.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.card_disabled));
                            Toast.makeText(v.getContext(), "Status alterado FECHADO", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.card_disabled));
                }
            }
        }
    }
}
