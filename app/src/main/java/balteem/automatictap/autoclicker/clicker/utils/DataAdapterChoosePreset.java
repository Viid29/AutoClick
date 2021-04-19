package balteem.automatictap.autoclicker.clicker.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import balteem.automatictap.autoclicker.clicker.AutoService;
import balteem.automatictap.autoclicker.clicker.R;
import balteem.automatictap.autoclicker.clicker.info.app_settings;
import balteem.automatictap.autoclicker.clicker.info.preset_info;

public class DataAdapterChoosePreset extends RecyclerView.Adapter<DataAdapterChoosePreset.ViewHolder> {
    private Context activity;
    private LayoutInflater inflater;
    private List<preset_info> adressLists;
    AlertDialog alertDialog;

    public DataAdapterChoosePreset(Context context, List<preset_info> adresssLists , AlertDialog myDialog) {
        activity=context;
        this.adressLists = adresssLists;
        alertDialog = myDialog;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chooser_preset_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final preset_info adressList = adressLists.get(position);

        holder.NameView.setText(adressList.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                app_settings.getInstance().setChoosePreset(adressList);
                app_settings.getInstance().setIndChoosenPreset(position);
                Intent intent = new Intent(activity, AutoService.class);
                intent.putExtra(AutoService.ACTION, AutoService.PRESET);
                intent.putExtra("interval",app_settings.getInstance().getInterval2());
                intent.putExtra(AutoService.MODE, AutoService.TAP);
                activity.startService(intent);


            }});
    }

    @Override
    public int getItemCount() {
        return adressLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView NameView  ;
        RelativeLayout Rel ;


        ViewHolder(View view){
            super(view);
            NameView = (TextView) view.findViewById(R.id.NameTextView);
        }
    }

}