package br.com.weis.loginservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


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

        /**
         * TODO Trying to verify selected date
            Calendar currentDate = Calendar.getInstance();
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(day, month, year);
         */

        if (email.equalsIgnoreCase("ps@fiap.com.br") && password.equalsIgnoreCase("10")){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder.setContentTitle(getString(R.string.lblLoginVerification));
            notificationBuilder.setContentText(getString(R.string.lblWarning));
            notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(sound);

            notificationManager.notify(100, notificationBuilder.build());

            notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, LoginActivity.class),
                                                                                        PendingIntent.FLAG_UPDATE_CURRENT));
        }
        else {
            Toast.makeText(this, R.string.lblFailedLogin, Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }
}
