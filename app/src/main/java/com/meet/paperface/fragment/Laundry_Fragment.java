package com.meet.paperface.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.meet.paperface.R;
/**
 * A simple {@link Laundry_Fragment} subclass.
 */
public class Laundry_Fragment extends Fragment {
    LinearLayout linearLayout;
    TextView textView, textView1, textView2;

    public Laundry_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_laundry, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
//        linearLayout = view.findViewById( R.id.linear );
//        textView = view.findViewById( R.id.deliveryTime );
//        textView1 = view.findViewById( R.id.tfno );
//        textView2 = view.findViewById( R.id.doubl );
        
        linearLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setBackgroundResource( R.drawable.tff );
            }
        } );
    }
}
