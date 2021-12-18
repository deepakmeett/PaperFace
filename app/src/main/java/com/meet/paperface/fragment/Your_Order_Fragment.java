package com.meet.paperface.fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.BlankFragment;
import com.meet.paperface.BlankFragment2;
import com.meet.paperface.adapter.Your_Order_Adapter;
import com.meet.paperface.model.Your_Order_Model;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * A simple {@link Fragment} subclass.
 */
public class Your_Order_Fragment extends Fragment {

//    private final List<Your_Order_Model> listdata = new ArrayList<>();
//    private RecyclerView rv;
//    private Your_Order_Adapter adaptor;
//    private FirebaseAuth firebaseAuth;
    ViewPager viewPager1;
    TabLayout tabLayout1;
    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;
    ViewPagerAdapter1 viewPagerAdapter1;
    Fragment fragment;
    String string;

    public Your_Order_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_your_order, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
//        DatabaseReference mUsersDatabase = FirebaseDatabase.getInstance().getReference().child( "YourOrder" );
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
        viewPager1 = view.findViewById( R.id.viewpagerFragment );
        tabLayout1 = view.findViewById( R.id.tabModeFragment );
        blankFragment = new BlankFragment();
        blankFragment2 = new BlankFragment2();
        tabLayout1.setupWithViewPager( viewPager1 );
        viewPagerAdapter1 = new ViewPagerAdapter1( getChildFragmentManager(), 0 );
        viewPagerAdapter1.addFragment( blankFragment, "First" );
        viewPagerAdapter1.addFragment( blankFragment2, "Second" );
        viewPager1.setAdapter( viewPagerAdapter1 );
//        rv = view.findViewById( R.id.recycle_view );
//        rv.setHasFixedSize( true );
//        rv.setLayoutManager( new LinearLayoutManager( getContext() ) );
//        mUsersDatabase.keepSynced( true );
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        final String myuid = Objects.requireNonNull( firebaseUser ).getUid();
//        Objects.requireNonNull( getActivity() ).setTitle( "Your Order" );
//        View view1 = getActivity().getCurrentFocus();
//        if (view1 != null) {
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService( Context.INPUT_METHOD_SERVICE );
//            Objects.requireNonNull( imm ).hideSoftInputFromWindow( view1.getWindowToken(), 0 );
//        }
//        mUsersDatabase.child( myuid ).addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ss : dataSnapshot.getChildren()) {
//                    Your_Order_Model user = ss.getValue( Your_Order_Model.class );
//                    listdata.add( user );
//
//                }
//                adaptor = new Your_Order_Adapter( getActivity(), listdata );
//                rv.setAdapter( adaptor );
//
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        } );
    }

    public class ViewPagerAdapter1 extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter1(@NonNull FragmentManager fm, int behavior) {
            super( fm, behavior );
        }

        public void addFragment(Fragment fragment, String string) {
            fragmentList.add( fragment );
            fragmentTitle.add( string );
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get( position );
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get( position );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
