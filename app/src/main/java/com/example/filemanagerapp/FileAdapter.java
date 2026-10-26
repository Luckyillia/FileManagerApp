package com.example.filemanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<com.example.filemanagerapp.FileAdapter.FileViewHolder> {

    private final List<File>    FileList;

    public interface OnDeleteListener {
        void onDelete(long id);
    }

    public FileAdapter(List<File> FileList) {
        this.FileList    = FileList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File f = FileList.get(position);

        holder.btnName.setText(f.getName());
    }

    @Override
    public int getItemCount() {
        return FileList.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        public final Button   btnName;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            btnName = itemView.findViewById(R.id.btnName);
        }
    }
}
