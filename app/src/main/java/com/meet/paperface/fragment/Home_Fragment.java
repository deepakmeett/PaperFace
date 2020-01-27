package com.meet.paperface.fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.activity.Payment_Activity;
import com.meet.paperface.adapter.View_Pager_Adapter;
import com.meet.paperface.model.View_Pager_Model;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    private Button ok;
    private Button done;
    private EditText edit_how;
    private EditText edit_extra;
    private TextView edit_page;
    private TextView edit_rs;
    private TextView textview1;
    private ViewPager viewPager;
    private int sec = 0;
    private DatabaseReference dr;


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_home, container, false );
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ok = view.findViewById( R.id.ok );
        done = view.findViewById( R.id.done );
        edit_how = view.findViewById( R.id.edit_How );
        edit_extra = view.findViewById( R.id.edit_Extra );
        edit_page = view.findViewById( R.id.edit_Pages );
        edit_rs = view.findViewById( R.id.edit_Rs );
        viewPager = view.findViewById( R.id.pager );
        textview1=view.findViewById(R.id.textview1);
        List<View_Pager_Model> view_pager_models = new ArrayList<>();
        View_Pager_Adapter view_pager_adapter = new View_Pager_Adapter( view_pager_models, getContext() );
        view_pager_models.add( new View_Pager_Model( R.drawable.sheet_third ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.sheet_first ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.sheet_sec ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.sheet_tlt ) );
        viewPager.setAdapter( view_pager_adapter );
        runnable.run();

        dr= FirebaseDatabase.getInstance().getReference().child("Price");



        Objects.requireNonNull(getActivity()).setTitle("Home");

       getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        View view1=getActivity().getCurrentFocus();

        if(view1 !=null){
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view1.getWindowToken(),0);
        }


        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                textview1.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString()+" Rs of 100 Pages");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View view1 = Objects.requireNonNull(getActivity()).getCurrentFocus();

                if (view1 != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(view1.getWindowToken(), 0);
                }

                String pages = edit_how.getText().toString();
                String extrapages = edit_extra.getText().toString();

                String pages1 = edit_how.getText().toString();

                if (pages1.isEmpty() || pages1.equals("0")) {
                    Toast.makeText( getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT ).show();

                    edit_how.setError("Please enter Bunch of pages");

                } else {


                    dr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String pages = edit_how.getText().toString();
                            String extrapages = edit_extra.getText().toString();
                            int total_pages;
                            float total_rs;
                            int i=Integer.parseInt(dataSnapshot.getValue().toString());

                            if (extrapages.isEmpty()) {
                                extrapages = "0";

                            }
                            int pages_int = Integer.parseInt( pages );
                            int extra_int = Integer.parseInt( extrapages );
                            total_pages = (pages_int * 100) + extra_int;
                            edit_page.setText( total_pages + "" );
                            total_rs = (float) ((total_pages * i) / 100.0);
                            String x = total_rs + "0";
                            edit_rs.setText( x );
                            Intent intent = new Intent( getContext(), Payment_Activity.class );
                            intent.putExtra( "total_page", total_pages + "" );
                            intent.putExtra( "total_rs", x );



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }

            }
        } );
        done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        float total_rs;
                        String pages = edit_how.getText().toString();
                        String extrapages = edit_extra.getText().toString();
                        if (extrapages.isEmpty()) {
                            extrapages = "0";

                        }
                        String pages1 = edit_how.getText().toString();
                        int total_pages;
                        if (pages1.isEmpty()) {
                            Toast.makeText( getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT ).show();

                        } else if (edit_page.getText().toString().isEmpty()) {
                            Toast.makeText( getActivity(), "Please Touch on ok", Toast.LENGTH_SHORT ).show();

                        } else {
                            int pages_int = Integer.parseInt( pages );
                            int extra_int = Integer.parseInt( extrapages );
                            total_pages = (pages_int * 100) + extra_int;

                            int i=Integer.parseInt(Objects.requireNonNull(dataSnapshot.getValue()).toString());


                            total_rs = (float) ((total_pages * i) / 100.0);
                            String x = total_rs + "0";
                            Intent intent = new Intent( getContext(), Payment_Activity.class );
                            intent.putExtra( "total_page", total_pages + "" );
                            intent.putExtra( "total_rs", x );
                            startActivity( intent );


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        } );
    }
    
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (sec <= 4) {
                if (sec == 4) {
                    sec = 0;
                }
                viewPager.setCurrentItem( sec );
                sec++;
                handler.postDelayed( this, 2000 );
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks( runnable );
    }
}