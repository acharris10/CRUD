package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.VerActivity;
import com.cdp.agenda.entidades.Clientes;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>{

    ArrayList<Clientes> listaClientes;

    public  ListaClientesAdapter(ArrayList<Clientes> listaClientes){

        this.listaClientes = listaClientes;

    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false
        );
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {

        holder.viewIde.setText(String.valueOf(listaClientes.get(position).getId()));
        holder.viewNonbre.setText(listaClientes.get(position).getNombres());
        holder.viewApellido.setText(listaClientes.get(position).getApellidos());
        holder.viewCelular.setText(listaClientes.get(position).getCelular());
        holder.viewTelefono.setText(listaClientes.get(position).getTelefono());
        holder.viewCorreo.setText(listaClientes.get(position).getCorreo());
        holder.viewFecha_naci.setText(listaClientes.get(position).getFecha_naci());

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewIde, viewNonbre, viewApellido, viewCorreo, viewCelular, viewTelefono, viewFecha_naci;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIde = itemView.findViewById(R.id.viewIde);
            viewNonbre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewCelular = itemView.findViewById(R.id.viewCelular);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewFecha_naci = itemView.findViewById(R.id.viewFecha_naci);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }


}


