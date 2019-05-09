package mvvm.com.retrofitjava.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import mvvm.com.retrofitjava.R;
import mvvm.com.retrofitjava.model.Characters;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private final List<Characters.DataBean.ResultsBean> mResponse;

    public CharacterAdapter(List<Characters.DataBean.ResultsBean> response) {
        mResponse = response;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        holder.txt_character_name.setText(mResponse.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_character_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_character_name=itemView.findViewById(R.id.txt_character_name);
        }
    }
}
