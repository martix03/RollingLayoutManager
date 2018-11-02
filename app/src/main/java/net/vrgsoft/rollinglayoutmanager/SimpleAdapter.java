package net.vrgsoft.rollinglayoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private List<Repo> repo;

    public SimpleAdapter() {
        this(new ArrayList<Repo>());
    }

    public SimpleAdapter(List<Repo> data) {
        repo = data;

    }

    public void setData(List<Repo> data) {
        repo = data;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout.item_simple, parent, false);
        return new SimpleViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bind(repo.get(position));
    }

    @Override
    public int getItemCount() {
        return repo.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView nome_textview;
        private TextView language_textview;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            nome_textview = itemView.findViewById(R.id.title);
            language_textview = itemView.findViewById(R.id.language);
        }

        public void bind(Repo data) {
            nome_textview.setText(data.getName());
            if (data.getLanguage().toString() != null)
                language_textview.setText(data.getLanguage().toString());
            else
                language_textview.setText("Undefined");
        }
    }
}
