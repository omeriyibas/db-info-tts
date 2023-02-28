package com.example.project;

import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<String> btn_titles;
    List<Integer> colors;
    LayoutInflater inflater;
    MainActivity mainActivity = new MainActivity();


    public Adapter(Context ctx, List<String> btn_titles, List<String> titles,List<Integer> colors){
        this.titles = titles;
        this.btn_titles = btn_titles;
        this.colors=colors;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btn.setText(btn_titles.get(position));
        holder.btn.setBackgroundColor(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),titles.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
                    mainActivity.textToSpeech.speak(titles.get(getAdapterPosition()), TextToSpeech.QUEUE_FLUSH,null,null);
                }
            });
        }
    }
}