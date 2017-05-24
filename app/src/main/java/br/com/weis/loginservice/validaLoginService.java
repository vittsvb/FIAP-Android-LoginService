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
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String email = intent.getExtras().getString("email");
        String password = intent.getExtras().getString("password");
        int day = intent.getExtras().getInt("day");
        int month = intent.getExtras().getInt("month");
        int year = intent.getExtras().getInt("year");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        if (email.trim().equalsIgnoreCase("ps@fiap.com.br") && password.equalsIgnoreCase("10") && verifySelectedDate(year, month, day)){
            notificationBuilder.setContentTitle(getString(R.string.lblLoginVerification));
            notificationBuilder.setContentText(getString(R.string.lblValidLogin));
            notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_info);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(sound);
        }
        else {
            notificationBuilder.setContentTitle(getString(R.string.lblLoginVerification));
            notificationBuilder.setContentText(getString(R.string.lblFailedLogin));
            notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(sound);
        }

        notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, LoginActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(100, notificationBuilder.build());
        
        return START_STICKY;
    }

    private Boolean verifySelectedDate(int year, int month, int day){
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.add(Calendar.YEAR, -18);
        Date currentDate = currentCalendar.getTime();

        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, day);
        Date selectedDate = selectedCalendar.getTime();

        return selectedDate.before(currentDate);
    }
}
