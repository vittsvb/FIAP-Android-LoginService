package br.com.weis.loginservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class validaLoginService extends Service {
    public validaLoginService() {    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String email = intent.getExtras().getString("email");
        String password = intent.getExtras().getString("password");
        int day = intent.getExtras().getInt("day");
        int month = intent.getExtras().getInt("month");
        int year = intent.getExtras().getInt("year");

        if (email.equalsIgnoreCase("ps@fiap.com.br") && password.equalsIgnoreCase("10")){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder.setContentTitle("Validaçao Login");
            notificationBuilder.setContentText("Login OK");

            notificationManager.notify(100, notificationBuilder.build());

            notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, LoginActivity.class),
                                                                                        PendingIntent.FLAG_UPDATE_CURRENT));
        }
        else {
            Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }
}
