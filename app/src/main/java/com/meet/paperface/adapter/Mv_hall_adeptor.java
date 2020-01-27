package com.meet.paperface.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.meet.paperface.hostelName.MV_hall;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;
import com.meet.paperface.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Mv_hall_adeptor extends RecyclerView.Adapter<Mv_hall_adeptor.ViewHolder>{

    private final Context fcontext;
    private List<Users> fupload;
    private List<Task_Class> fupload1;
    private DatabaseReference dr;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReferenceall;

    public void add(Users s) {
        fupload.add( s );
    }

    public Mv_hall_adeptor(Context context, List<Users> user, List<Task_Class> user1) {
        fcontext = context;
        fupload = user;
        fupload1 = user1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( fcontext ).inflate( R.layout.data_list, parent, false );
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        dr = FirebaseDatabase.getInstance().getReference().child( "Orders" );
        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Pastorder" );

        databaseReferenceall = FirebaseDatabase.getInstance().getReference().child( "Allorders" );
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child( "YourOrder" );
        final String ss = dr.getRef().getKey();
        holder.hostelname.setText( fupload.get( position ).getHostelname() );
        holder.mobileno.setText( fupload.get( position ).getMobileno() );
        holder.name.setText( fupload.get( position ).getName() );
        holder.other.setText( fupload.get( position ).getOther() );
        holder.payment.setText( fupload.get( position ).getPayment() );
        holder.roomno.setText( fupload.get( position ).getRoomno() );
        holder.totalpage.setText( fupload.get( position ).getTotalpage() );
        holder.totalrs.setText( fupload.get( position ).getTotalrs() );
        holder.date.setText( fupload.get( position ).getDate() );

        holder.x.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String currentDate = DateFormat.getDateTimeInstance().format( new Date() );
                HashMap<String, String> map = new HashMap<>();
                map.put( "date", currentDate );
                map.put( "pages", fupload.get( position ).getTotalpage() );
                map.put( "rs", fupload.get( position ).getTotalrs() );

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("hostelname", fupload.get( position ).getHostelname());
                hashMap.put("mobileno", fupload.get( position ).getMobileno());
                hashMap.put("name", fupload.get( position ).getName());
                hashMap.put("other", fupload.get( position ).getOther());
                hashMap.put("payment", fupload.get( position ).getPayment());
                hashMap.put("roomno",  fupload.get( position ).getRoomno());
                hashMap.put("totalpage", fupload.get( position ).getTotalpage());
                hashMap.put("totalrs", fupload.get( position ).getTotalrs());
                hashMap.put("uid", fupload.get( position ).getUid());
                hashMap.put("date", fupload.get( position ).getDate());

                databaseReferenceall.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {



                    }
                });
                databaseReference.child( fupload.get( position ).getUid() ).push().setValue( map ).addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( fcontext, "Done", Toast.LENGTH_SHORT ).show();

                        }

                    }
                } );
                databaseReference1.child( fupload.get( position ).getUid() ).removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText( fcontext, "Removed", Toast.LENGTH_SHORT ).show();

                    }
                } );
                dr.child( fupload1.get( position ).getKey() ).removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText( fcontext, "Removed", Toast.LENGTH_SHORT ).show();
                        dr.keepSynced( true );
                        fcontext.startActivity( new Intent( fcontext, MV_hall.class ) );
                        ((Activity)fcontext).finish();

                    }
                } );


            }
        } );

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView hostelname;
        final TextView mobileno;
        final TextView name;
        final TextView other;
        final TextView payment;
        final TextView roomno;
        final TextView totalpage;
        final TextView totalrs;
        TextView date;

        final ImageView x;

        ViewHolder(@NonNull View itemView) {
            super( itemView );
            hostelname = itemView.findViewById( R.id.card_hostelNum_blank );
            mobileno = itemView.findViewById( R.id.card_mobileno_blank );
            name = itemView.findViewById( R.id.card_name_blank );
            other = itemView.findViewById( R.id.card_other_blank );
            payment = itemView.findViewById( R.id.card_payment_blank );
            roomno = itemView.findViewById( R.id.card_room_blank );
            totalpage = itemView.findViewById( R.id.card_Pages_Blank );
            totalrs = itemView.findViewById( R.id.card_Rupees_Blank );
            date = itemView.findViewById( R.id.card_date_blank );

            x = itemView.findViewById( R.id.imageview );
        }
    }
}

