package com.example.okabe.nexttry.settings_Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.StableArrayAdapter;

import java.util.ArrayList;


public class SettingsPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_activity, container, false);

        String[] data = {"Crear producto", "Añadir Proveedor", "Crear Cuenta de cobro"};
        ListView listViewSetting = view.findViewById(R.id.listView_addSettings);
        ArrayList<String> dataToListView = new ArrayList<>();
        addData(dataToListView, data);

        //Linking the adapter with the list
        final StableArrayAdapter dataAdapter = new StableArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dataToListView);
        listViewSetting.setAdapter(dataAdapter);

        listViewSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent openAddSpecific = null;
                switch (position) {
                    case 0:
                        openAddSpecific = new Intent(getContext(), AddStock.class);
                        break;
                    case 1:
                        openAddSpecific = new Intent(getContext(), AddDeliver.class);
                        break;
                    case 2:
                        openAddSpecific = new Intent(getContext(), AddBill.class);
                        break;
                }
                startActivity(openAddSpecific);
            }
        });
        return view;
    }

    public void addData(ArrayList arrayList, String[] data) {
        for (int i = 0; i < data.length; i++) {
            arrayList.add(data[i]);
        }
    }

}



