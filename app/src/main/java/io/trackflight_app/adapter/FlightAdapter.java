package io.trackflight_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.trackflight_app.R;
import io.trackflight_app.models.Flights;
import io.trackflight_app.utils.RecyclerViewInterface;

public class FlightAdapter  extends RecyclerView.Adapter<FlightAdapter.FlightClass>{
    private  final RecyclerViewInterface recyclerViewInterface;

    ArrayList<String> departure_city;
    ArrayList<String> departure_date ;
    ArrayList<String> departure_time;
    ArrayList<String> departure_airports;
    ArrayList<String> arrival_city;
    ArrayList<String> arrival_date ;
    ArrayList<String> arrival_time;
    ArrayList<String> arrival_airports;
    ArrayList<String> flight;

    public FlightAdapter(RecyclerViewInterface recyclerViewInterface, Context context) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public FlightClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newflightitem,parent,false);
FlightClass myFlightClass =new FlightClass(view,recyclerViewInterface);
        return myFlightClass;
    }

    @Override
    public void onBindViewHolder(@NonNull FlightAdapter.FlightClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public  class   FlightClass extends RecyclerView.ViewHolder {

        TextView departure_date ;
        TextView departure_time ;
        TextView departure_city ;
        TextView departure_airport ;
        TextView arrival_date ;
        TextView arrival_time ;
        TextView arrival_city ;
        TextView arrival_airport ;
        TextView state ;
        TextView flight ;


        public  FlightClass(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);



            departure_airport=(TextView)itemView.findViewById(R.id.departure_airport);
            departure_city=(TextView)itemView.findViewById(R.id.details_city);
            departure_date=(TextView)itemView.findViewById(R.id.departure_date);
            departure_time=(TextView)itemView.findViewById(R.id.departure_time);
            arrival_airport=(TextView)itemView.findViewById(R.id.arrival_airport);
            arrival_city=(TextView)itemView.findViewById(R.id.arrival_city);
            arrival_date=(TextView)itemView.findViewById(R.id.arrival_date);
            arrival_time=(TextView)itemView.findViewById(R.id.arrival_time);
            state=(TextView)itemView.findViewById(R.id.state);
            flight=(TextView)itemView.findViewById(R.id.flight);

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
