package io.trackflight_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.trackflight_app.R;
import io.trackflight_app.activities.DashboardActivity;

import io.trackflight_app.activities.MyFlightFragment;
import io.trackflight_app.models.Airports;
import io.trackflight_app.utils.RecyclerViewInterface;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MySearchClass> {
    private  final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<String> airportList;
    private ArrayList<String> filteredAirportList;
    private  Airports airport;
    Context context;
    ArrayList<String> name;
    ArrayList<String> code;
    ArrayList<String> city;

    public AirportAdapter(ArrayList<String> name, ArrayList<String> code, ArrayList<String> city, ArrayList<String> airportArrayList ,RecyclerViewInterface recyclerViewInterface) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.context=context;
        this.recyclerViewInterface=recyclerViewInterface;
        //ArrayList<Airports> airportArrayList =new ArrayList<Airports>();
        this.airportList = airportArrayList;
        this.filteredAirportList = name;


    }

    @NonNull
    @Override
    public MySearchClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_item,parent,false);
        MySearchClass mySearchClass =new  MySearchClass(view,recyclerViewInterface);
        return mySearchClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MySearchClass holder, int position) {
        holder.name.setText(name.get(position));
        holder.code.setText("("+code.get(position)+")");
        holder.city.setText(city.get(position));





    }

    @Override
    public int getItemCount() {
        return name.size();
    }





    public  class MySearchClass extends RecyclerView.ViewHolder {

        TextView name;
        TextView code;
        TextView city;
        public  MySearchClass(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            code=(TextView)itemView.findViewById(R.id.code);
            city=(TextView)itemView.findViewById(R.id.city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }

                }
            });
        }
    }
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();
                Log.i("--->", "performFiltering: "+charSequence);


                if (searchString.isEmpty()) {

                    filteredAirportList = city;

                } else {

                    ArrayList<String> tempFilteredList = new ArrayList<>();

                    for (String sting : city) {

                        // search for user title
                        if (sting.toLowerCase().contains(searchString)) {

                            tempFilteredList.add(sting);
                        }
                    }
                    System.out.println(city.size());

                    filteredAirportList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredAirportList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredAirportList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

