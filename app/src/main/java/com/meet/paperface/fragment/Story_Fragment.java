package com.meet.paperface.fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.adapter.Story_Adapter;
import com.meet.paperface.model.Story_Model;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Story_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private Story_Adapter story_adapter;
    private List<Story_Model> models;

    public Story_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_story, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        recyclerView = view.findViewById( R.id.recycler_frag );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        models = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Story" );
        databaseReference.keepSynced( true );

        Objects.requireNonNull(getActivity()).setTitle("Story");
        View view1=getActivity().getCurrentFocus();

        if(view1 !=null){
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view1.getWindowToken(),0);
        }

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Story_Model story_model = dataSnapshot1.getValue( Story_Model.class );
                    models.add( story_model );
                }
                story_adapter = new Story_Adapter( getContext(), models );
                recyclerView.setAdapter( story_adapter );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}
