package com.example.sachinsharma.cricfun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class BowlingFragment extends Fragment {
    String selectedCountry;
    Spinner spinner;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    TextView title;
    public String team1;
    public String team2;


    public BowlingFragment newInstance(String team1,String team2){
        Bundle args=new Bundle();
        args.putString("team1",team1);
        args.putString("team2",team2);
        BowlingFragment fragment=new BowlingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        team1 = getArguments().getString("team1","ind");
        team2=getArguments().getString("team2","sa");
        selectedCountry = team1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_bowling, container, false);
        setSpinnerContent(view);
        title = view.findViewById(R.id.bwTitle);
        title.setText("Bowling Card of " + selectedCountry.toUpperCase());
        rv = view.findViewById(R.id.bowlingRv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        showSelected();
        return view;
    }

    private void showSelected() {
        adapter = new BowlingRvAdapter(getContext(), selectedCountry);
        rv.setAdapter(adapter);
    }

    private void setSpinnerContent(View view) {
        spinner = view.findViewById(R.id.spinner);
        ArrayList<String> c = new ArrayList<>();
        c.add(team1);
        c.add(team2);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, c);

        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCountry = adapterView.getSelectedItem().toString();
                // Toast.makeText(adapterView.getContext(),selectedCountry,Toast.LENGTH_LONG).show();

                if(selectedCountry==team1) {
                    selectedCountry=selectedCountry.split(" ")[0];
                }
                else if(selectedCountry==team2){
                    selectedCountry=selectedCountry.split(" ")[1];
                }

                title.setText("Bowling Card of " + selectedCountry);
                adapter = new BowlingRvAdapter(getContext(), selectedCountry);
                showSelected();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
