package com.example.first2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class CardAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
            maximizar((EventAdapter.MyHolder) newHolder);
            return true;
        }

    private void maximizar(EventAdapter.MyHolder holder) {
        boolean seleccionada = holder.itemView.isSelected();

        float escala = seleccionada ? 1.15f : 1.0f;
        float z = seleccionada ? 30f: 0f;

        holder.card.animate()
                .scaleX(escala)
                .scaleY(escala)
                .translationX(z)
                .start();
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        item.itemView.animate().cancel();
        super.endAnimation(item);
    }

    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return true;
    }
}

