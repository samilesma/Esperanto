package com.example.esperanto;

        import android.app.AlarmManager;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.app.TaskStackBuilder;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CompoundButton;
        import android.widget.Switch;

        import java.util.Calendar;


public class Settings_frag extends Fragment {
    private Switch sSound, sNotification;
    private Button b1;
    NotificationManager notificationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.settings_frag, container, false);

        sNotification = (Switch) view.findViewById(R.id.sNotification);
        sNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    setNotification(true);
                }
                else{
                    setNotification(false);
                }
            }

        });
        return view;
    }

    private void setNotification(Boolean notification){
        Controller.notification = notification;
    }
}