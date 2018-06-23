package com.example.a.alcoholapp.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.a.alcoholapp.Database.Entity.Drink;
import java.util.List;
import com.example.a.alcoholapp.R;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.DrinkViewHolder> {

    class DrinkViewHolder extends RecyclerView.ViewHolder {
        private final Button drinkItemView;

        private DrinkViewHolder(View itemView) {
            super(itemView);
            drinkItemView = itemView.findViewById(R.id.button);
        }
    }

    private final LayoutInflater mInflater;
    private List<Drink> mDrinks;

    DrinkListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new DrinkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink current = mDrinks.get(position);
        String text = "Drink: "+current.getName()+ "\n"+"Cl: "+current.getCl()+
                "\n"+"Calories: "+current.getCalories()+"\n"+"Alcohol%: "+current.getAlcoholPercentage();
        holder.drinkItemView.setText(text);
    }

    void setDrinks(List<Drink> drinks){
        mDrinks = drinks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDrinks != null)
            return mDrinks.size();
        else return 0;
    }

    @Override
    public long getItemId(int position) {
        return mDrinks.get(position).getId();
    }

    /**
     * Returns a drink from underlying adapter.
     * @param position the index of the Drink object
     * @return Drink object
     */
    public Drink getAtPosition(int position){
        return mDrinks.get(position);
    }
}


