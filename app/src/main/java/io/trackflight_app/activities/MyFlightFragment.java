package io.trackflight_app.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.trackflight_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFlightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFlightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public static final String AIRPORT_ID = "airport";
    public static final String CITY = "city";

    private String mParam1;
    private String mParam2;
    EditText dateInput;
    EditText arrival_field;
    EditText departure_field;
    SearchView searchView;
    Calendar myCalendar;

    public MyFlightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFlightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFlightFragment newInstance(String param1, String param2) {
        MyFlightFragment fragment = new MyFlightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inf=late the layout for this fragment
        return  inflater.inflate(R.layout.fragment_my_flight, container, false);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        departure_field = view.findViewById(R.id.arrival);
        arrival_field = view.findViewById(R.id.departure);
        dateInput = view.findViewById(R.id.date);
        TextView clear_text = view.findViewById(R.id.clear);
        Button search = view.findViewById(R.id.search_flights);
        searchView = view.findViewById(R.id.searchView);

        ImageButton exchange = view.findViewById(R.id.exchange);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(),FlightsActivity.class);
                startActivity(intent);


            }
        });
        Bundle results = new Bundle();
        String value1 = results.getString("edttext");
        System.out.println(value1);

        arrival_field.setText(value1);
        // Toolbar toolbar=view.findViewById(R.id.toolbar);

        //ActionBar

        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();

        //Select city

        arrival_field.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
//                SearchFragment searchFragment=new SearchFragment();
//                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.content,searchFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();


            }
        });
        departure_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);


            }
        });


        //Exchange two input field
        exchange.setOnClickListener(v -> {
            String first_field = arrival_field.getText().toString();
            String second_field = departure_field.getText().toString();
            arrival_field.setText(second_field);
            departure_field.setText(first_field);
        });

        //Suppression de tous les champs
        clear_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrival_field.setText("");
                departure_field.setText("");
                dateInput.setText("");
            }
        });

        //Date selection
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        String my_format = "EE, dd LLLL yyyy ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(my_format, Locale.US);
        dateInput.setText("Departing "+simpleDateFormat.format(myCalendar.getTime()));
        dateInput.setOnClickListener(view1 -> {
            new DatePickerDialog(getContext(),
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
                    .show();
        });

    }

    private void updateLabel() {
        String my_format = "EE, dd LLLL yyyy ";
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(my_format, Locale.US);
        dateInput.setText("Departing "+simpleDateFormat.format(myCalendar.getTime()));
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case SearchActivity.REQUEST_CODE:
//
//
//                if (resultCode == Activity.RESULT_OK && data != null) {
//                    String name = (String) data.getSerializableExtra(CITY);
//                    System.out.println(name);
//                    arrival_field.setText(name);
//                    departure_field.setText(name);
//
//
//                    System.out.println(name);
//                }
//                break;
//        }
//    }
}