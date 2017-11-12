package com.example.okabe.nexttry.main_views;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.DeliverArrayAdapter;
import com.example.okabe.nexttry.alert_dialog.DeliverDeleteDialog;
import com.example.okabe.nexttry.data_base.db_classes.Deliver;

import java.util.ArrayList;


public class DeliverPage extends Fragment {
    private ListView deliverListView;
    private ArrayList<Deliver> arrayDeliver;
    private int layout;
    private DeliverArrayAdapter adapter;
    private DeliverDeleteDialog deliverDeleteDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        arrayDeliver = MainView.baseHub.getDeliverArrayList();
        layout = getCorrectLayout();

        View view = inflater.inflate(layout, container, false);
        setCorrectView(view);

        return view;
    }

    private int getCorrectLayout() {
        if (arrayDeliver.isEmpty()) {
            return R.layout.empty_view;
        } else {
            return R.layout.deliver_fragment;
        }
    }

    private void setCorrectView(View view) {
        if (layout == R.layout.deliver_fragment) {
            //not empty
            deliverListView = view.findViewById(R.id.listView_Deliver);
            adapter = new DeliverArrayAdapter(getContext(), arrayDeliver);
            deliverListView.setAdapter(adapter);
            dialogOnClick();
            dialogOnLongClick();
        }
    }

    private void dialogOnClick() {
        //call the provider
        deliverListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.drawable.phone_call_image)
                        .setTitle("Llamar")
                        .setMessage("¿Desea llamar el proveedor?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Deliver toCall = arrayDeliver.get(pos);
                                String phone = "tel:" + toCall.getDeliverPhone();
                                Intent call = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
                                startActivity(call);
                            }
                        })
                        .setNegativeButton("Cancelar", null);
                dialog.create().show();
            }
        });
    }

    private void dialogOnLongClick() {
        deliverListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                deliverDeleteDialog = new DeliverDeleteDialog();
                deliverDeleteDialog.setDeleteSelected(position);
                deliverDeleteDialog.setArrayAdapter(adapter);
                deliverDeleteDialog.show(getFragmentManager(), "Eliminar");
                return true;
            }
        });
    }
}
