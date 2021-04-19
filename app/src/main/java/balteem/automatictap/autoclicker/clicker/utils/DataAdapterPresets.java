package balteem.automatictap.autoclicker.clicker.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.List;

import balteem.automatictap.autoclicker.clicker.R;
import balteem.automatictap.autoclicker.clicker.info.preset_info;

public class DataAdapterPresets extends RecyclerView.Adapter<DataAdapterPresets.ViewHolder> {
    private Context activity;
    private LayoutInflater inflater;
    private List<preset_info> adressLists;

    public DataAdapterPresets(Context context, List<preset_info> adresssLists) {
        activity=context;
        this.adressLists = adresssLists;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.presets_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final preset_info adressList = adressLists.get(position);

        holder.NameView.setText(adressList.getName());

        holder.ChangeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(activity);
                View promptsView = li.inflate(R.layout.change_name_layout, null);
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(activity);
                final EditText NameEditText = promptsView.findViewById(R.id.NameEditText);
                TextView SaveTextView = promptsView.findViewById(R.id.TextViewSave);
                mDialogBuilder.setView(promptsView);
                NameEditText.setText(adressList.getName());

                final AlertDialog alertDialog = mDialogBuilder.create();
                SaveTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!NameEditText.getText().toString().equals("")) {
                            alertDialog.cancel();
                            holder.NameView.setText(NameEditText.getText().toString());
                            converterClass.changeName(position,NameEditText.getText().toString() );
                            try {
                                converterClass.unconvert();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Save.savePresets(activity);
                            adressLists.get(position).setName(NameEditText.getText().toString());
                        }

                    }});

                alertDialog.show();
            }});
        holder.StartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }});
        holder.DeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adressLists.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
    //            converterClass.deletePreset(position);
                try {
                    converterClass.unconvert();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Save.savePresets(activity);
            }});
    }

    @Override
    public int getItemCount() {
        return adressLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView NameView  ;
        RelativeLayout Rel ;
        ImageView StartImageView , ChangeImageView , DeleteImageView ;

        ViewHolder(View view){
            super(view);
            NameView = (TextView) view.findViewById(R.id.NameTextView);

            StartImageView =  view.findViewById(R.id.StartImageView);
            ChangeImageView =  view.findViewById(R.id.ChangeImageView);
            DeleteImageView =  view.findViewById(R.id.DeleteImageView);



        }
    }

}