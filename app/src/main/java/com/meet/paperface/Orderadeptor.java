package com.meet.paperface;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meet.paperface.R;
import com.meet.paperface.model.Your_Order_Model;

import java.util.ArrayList;
import java.util.List;
public class Orderadeptor extends RecyclerView.Adapter<Orderadeptor.ViewHolder> {

    private Context fcontext;
    private List<Your_Order_Model> fupload = new ArrayList<>();

    public void add(Your_Order_Model s) {
        fupload.add( s );
    }

    public Orderadeptor(Context context, List<Your_Order_Model> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( fcontext ).inflate( R.layout.data_list_shop, parent, false );
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.page.setText( fupload.get( position ).getPage() );
        holder.rs.setText( fupload.get( position ).getRs() );
        holder.date.setText( fupload.get( position ).getDate() );

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView page, rs, date;

        ViewHolder(@NonNull View itemView) {
            super( itemView );
            page = itemView.findViewById( R.id.card_Pages_shop_blank );
            rs = itemView.findViewById( R.id.card_Rupees_shop_blank );
            date = itemView.findViewById( R.id.card_date_blank );

        }
    }
}

