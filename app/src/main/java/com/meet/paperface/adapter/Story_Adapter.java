package com.meet.paperface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.R;
import com.meet.paperface.model.Story_Model;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class Story_Adapter extends RecyclerView.Adapter<Story_Adapter.ViewHolder> {

    private final Context context;
    private final List<Story_Model> list;
    private DatabaseReference dr;
    private FirebaseAuth fa;

    public Story_Adapter(Context context, List<Story_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.story_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Story_Model storyModel = list.get(position);

        fa = FirebaseAuth.getInstance();
        FirebaseUser fu = fa.getCurrentUser();
        final String myuid = Objects.requireNonNull(fu).getUid();

        try {
            dr = FirebaseDatabase.getInstance().getReference().child("Reaction");


            holder.textView.setText(storyModel.getThesis());

            dr.child(storyModel.getName()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {
                        final String action= Objects.requireNonNull(dataSnapshot.child(myuid).getValue()).toString();
                        if(action.equals("like")){


                            holder.like.setBackground(ContextCompat.getDrawable(context,R.drawable.card_two_home));
                            holder.dislike.setBackground(ContextCompat.getDrawable(context,R.drawable.likebutton));

                        }
                        else {
                            holder.dislike.setBackground(ContextCompat.getDrawable(context,R.drawable.card_two_home));
                            holder.like.setBackground(ContextCompat.getDrawable(context,R.drawable.likebutton));


                        }
                    }catch (Exception e){


                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch (Exception e){

        }

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dr.child(storyModel.getName()).child(myuid).setValue("like");

            }
        });

        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dr.child(storyModel.getName()).child(myuid).setValue("dislike");


            }
        });
        Picasso.get().load(storyModel.getPictures()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(storyModel.getPictures()).fit().centerInside().into(holder.imageView);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textView;
        final Button like;
        final Button dislike;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.story);
            textView = itemView.findViewById(R.id.poem);
            like = itemView.findViewById(R.id.like);
            dislike = itemView.findViewById(R.id.dislike);


        }
    }
}
