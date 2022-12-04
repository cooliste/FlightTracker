package io.trackflight_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.trackflight_app.R;
import io.trackflight_app.activities.AirportsDetails;
import io.trackflight_app.activities.DashboardActivity;

import io.trackflight_app.models.Airports;
import io.trackflight_app.utils.RecyclerViewInterface;

public class SecondAirportAdapter extends RecyclerView.Adapter<SecondAirportAdapter.SearchClass> {

    private  final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<Airports> airportList;
    private ArrayList<Airports> filteredAirportList;
    Context context;
    ArrayList<String> name;
    ArrayList<String> country;
    ArrayList<String> city;
    ArrayList<String> latitude;
    ArrayList<String> longitude;
    ArrayList<String> icao;
    ArrayList<ImageView> flag_image;



    public SecondAirportAdapter(ArrayList<String> name, ArrayList<String> country, ArrayList<String> city,     ArrayList<String> latitude,    ArrayList<String> longitude,

            Context context,RecyclerViewInterface recyclerViewInterface, ArrayList<ImageView> flag_image, ArrayList<String> icao) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.context=context;
        this.longitude=longitude;
        this.latitude=latitude;
        this.recyclerViewInterface=recyclerViewInterface;
        this.flag_image=flag_image;
        this.icao=icao;

        //
        ArrayList<Airports> airportArrayList =new ArrayList<Airports>();
        this.airportList = airportArrayList;
        this.filteredAirportList = airportArrayList;


    }

    @NonNull
    @Override
    public SearchClass  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.secondairport_item,parent,false);
        SearchClass mySearchClass =new  SearchClass(view,recyclerViewInterface);
        return mySearchClass;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchClass holder, int position) {
        holder.name.setText(name.get(position));
        holder.country.setText(country.get(position));
        holder.city.setText(city.get(position));
       // holder.longitude.setText(longitude.get(position));
       // holder.latitude.setText(latitude.get(position));



    }
//    public SecondAirportAdapter hasFilter(Filter filter) {
//        isNotNull();
//        Filter actualFilter = actual.getFilter();
//        assertThat(actualFilter) //
//                .overridingErrorMessage("Expected filter <%s> but was <%s>.", filter, actualFilter) //
//                .isSameAs(filter);
//        return this;




////////////

    @Override
    public int getItemCount() {
        return name.size();
    }




    public  class SearchClass extends RecyclerView.ViewHolder {

        TextView name;
        TextView country;
        TextView city;
        TextView longitude;
        TextView latitude;
        TextView icao;
        ImageView flag_image;
        public  SearchClass(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name2);
            country=(TextView)itemView.findViewById(R.id.country);
            city=(TextView)itemView.findViewById(R.id.city2);
            latitude=(TextView)itemView.findViewById(R.id.latitude);
            longitude=(TextView)itemView.findViewById(R.id.longitude);
            //flag_image=(ImageView) itemView.findViewById(R.id.flagImageId);

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

}
