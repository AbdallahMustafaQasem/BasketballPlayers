package abdallah.qasem.basketballplayers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.models.Datum;


public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Object> itemList;
    Context context;

    private static final int TYPE_ListItem = 1;
    private final int TYPE_LOADING = 2;

    public PlayersAdapter(Context cont, ArrayList<Object> value) {



        this.itemList = value;
        this.context = cont;

    }

    @Override
    public int getItemViewType(int position) {

        if (itemList.get(position) instanceof Datum) {
            return TYPE_ListItem;
        }
        return TYPE_LOADING;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view;
        switch (viewType) {

            case TYPE_ListItem:

                RecyclerView.ViewHolder viewHolder = null;
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.row_players, parent, false);
                return new PlayerViewHolder(view, context);

            case TYPE_LOADING:
                view = LayoutInflater.from(context).inflate(R.layout.row_loading_item, parent, false);
                return new LoadingViewHolder(view);


        }

        return null;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof PlayerViewHolder) {

            PlayerViewHolder viewHolder = (PlayerViewHolder) holder;
            Datum datum = (Datum) itemList.get(position);
            viewHolder.tv_player_name.setText(datum.getFirstName() +""+ datum.getLastName());
            viewHolder.tv_team.setText(datum.getTeam().getName());


            // Player Image
            String ImageURL = "https://cdn2.thecatapi.com/images/" + datum.getId() + ".jpg";
            Picasso.get().load(ImageURL).into(viewHolder.iv_player_image);


            switch (datum.getPosition()) {
                case "G-F":
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#62FF00"));
                    break;
                case "C":
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#FF9800"));
                    break;
                case "F":
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
                    break;
                case "G":
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#E91E63"));
                    break;
                default:
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#2196F3"));
            }
        }

        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {


        TextView tv_player_name, tv_team;
        ImageView iv_player_image;

        public PlayerViewHolder(View itemView, Context context) {
            super(itemView);
            tv_player_name = itemView.findViewById(R.id.tv_player_name);
            tv_team = itemView.findViewById(R.id.tv_team);
            iv_player_image = itemView.findViewById(R.id.iv_player_image);

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
