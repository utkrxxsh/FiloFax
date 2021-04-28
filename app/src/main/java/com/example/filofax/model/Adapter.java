package com.example.filofax.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filofax.R;
import com.example.filofax.notes.NoteDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.filofax.R.layout.note_view_layout;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String>titles;
    List<String>content;

    public Adapter(List<String>titles,List<String>content){
        this.titles=titles;
        this.content=content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(note_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        final Integer code=getRandomColor();
        holder.mCardView.setCardBackgroundColor(holder.view.getResources().getColor(code,null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteDetails.class);
                intent.putExtra("title",titles.get(position));
                intent.putExtra("content",content.get(position));
                intent.putExtra("code",code);
                v.getContext().startActivity(intent);
            }
        });
    }

    private int getRandomColor() {
        List<Integer>colorCode = new ArrayList<>();
        //15 colors
        colorCode.add(R.color.gray);
        colorCode.add(R.color.fossil);
        colorCode.add(R.color.mink);
        colorCode.add(R.color.pearlDriver);
        colorCode.add(R.color.abalone);
        colorCode.add(R.color.harborGray);
        colorCode.add(R.color.smoke);
        colorCode.add(R.color.thunder);
        colorCode.add(R.color.pewter);
        colorCode.add(R.color.steel);
        colorCode.add(R.color.stone);
        colorCode.add(R.color.rhino);
        colorCode.add(R.color.trout);
        colorCode.add(R.color.seal);
        colorCode.add(R.color.lava);

        Random randomColor=new Random();
        int number=randomColor.nextInt(colorCode.size());
        return colorCode.get(number);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteTitle,noteContent;
        View view;
        CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle=itemView.findViewById(R.id.titles);
            noteContent=itemView.findViewById(R.id.content);
            mCardView=itemView.findViewById(R.id.noteCard);
            view=itemView;
        }
    }
}
