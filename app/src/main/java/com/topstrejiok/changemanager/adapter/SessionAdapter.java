package com.topstrejiok.changemanager.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.topstrejiok.changemanager.activity.MainActivity.KEY_SESSIONS;
import static com.topstrejiok.changemanager.activity.MainActivity.sessionController;


public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionVH> {

    private Context context;
    SharedPreferences mPrefs;

    public SessionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SessionVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_session_list, viewGroup, false);
        return new SessionVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionVH sessionVH, final int position) {
        /*if (position % 2 == 1) {
            sessionVH.root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorFirst));
        } else {
            sessionVH.root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorSecond));
        }*/
        sessionVH.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SessionActivity.class);
                intent.putExtra("kekshrek",sessionController.getSessionItem().get(position).getId().toString());
                context.startActivity(intent);
            }
        });
        sessionVH.header.setText(sessionController.getSessionItem().get(position).getName());
        sessionVH.dateTime.setText(sessionController.getSessionItem().get(position).getDateTime());
        sessionVH.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.text_are_you_sure);
                builder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton(R.string.text_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sessionController.getSessionItem().remove(position);
                        dialogInterface.dismiss();
                        notifyDataSetChanged();
                        saveData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        sessionVH.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.text_change_name);
                final View edt = LayoutInflater.from(context).inflate(R.layout.alert_item_session, null);
                ((TextView) edt.findViewById(R.id.AlertName)).setText(sessionController.getSessionItem().get(position).getName());

                builder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionController.getSessionItem().get(position).setName(((TextView) edt.findViewById(R.id.AlertName))
                                .getText().toString());
                        notifyDataSetChanged();
                        saveData();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setView(edt);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessionController.getSessionItem().size();
    }

    static class SessionVH extends RecyclerView.ViewHolder {

        TextView header;
        TextView dateTime;
        ConstraintLayout root;
        ImageView delete;
        ImageView edit;

        SessionVH(@NonNull View v) {
            super(v);
            root = v.findViewById(R.id.root);
            header = v.findViewById(R.id.header);
            dateTime = v.findViewById(R.id.dateTime);
            delete = v.findViewById(R.id.delete);
            edit = v.findViewById(R.id.edit);

        }
    }

    private void saveData() {
        mPrefs = context.getSharedPreferences("ASSA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(sessionController);
        mPrefs.edit().putString(KEY_SESSIONS, json).apply();
    }
}
