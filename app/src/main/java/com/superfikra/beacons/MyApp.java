package com.superfikra.beacons;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.mgmtsdk.feature.settings.api.Beacon;
import java.util.List;
import java.util.UUID;

public class MyApp extends Application {
    private BeaconManager beaconManager;
    boolean inClass;

    @Override
    public void onCreate() {
        super.onCreate();
        inClass = false;


        //identifying
        beaconManager = new BeaconManager(getApplicationContext());
        //making background monitoring faster
        beaconManager.setBackgroundScanPeriod(500,500);
        //listening for beacons
        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {



            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<com.estimote.coresdk.recognition.packets.Beacon> beacons) {

                Toast toast = Toast.makeText(getApplicationContext(), "onEnteredRegion()", Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();

                if (beaconRegion.getIdentifier().equals("inclassbeacon")) {

                    showNotificationAbsence(
                                "Check in confirmation",
                                "You have checked in from your class.",
                                2);

//                    if (inClass) {
//                        showNotificationAbsence(
//                                "Check out confirmation",
//                                "You have checked out from your class.",
//                                2);
//                    } else if (!inClass) {
//                        showNotificationAttendance(
//                                "Check in confirmation",
//                                "Dear Ali, We're glad to see you again.",
//                                1);
//                    }
//                } else if (beaconRegion.getIdentifier().equals("beacon in class")) {
//                    inClass = true;
//
//
                }
            }

            @Override
            public void onExitedRegion(BeaconRegion region) {

                Toast toast = Toast.makeText(getApplicationContext(), "onExitedRegion()", Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();

                showNotificationAbsence(
                                "Check out confirmation",
                                "You have checked out from your class.",
                                2);


            }
        });
        //connecting to beacons
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

//                Toast toast=Toast.makeText(getApplicationContext(),"onServiceReady()",Toast.LENGTH_SHORT);
//                toast.setMargin(50,50);
//                toast.show();

                beaconManager.startMonitoring(new BeaconRegion(
                        "inclassbeacon",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        10837, 38352));

            }
        });
    }



    //////// NOTIFICATIONS ////////
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        int icon = android.R.drawable.btn_star;
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
    public void showNotificationAbsence(String title, String message, int ic) {
        Intent notifyIntent = new Intent(this, AbsenceActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        int icon = R.drawable.ic_notification;
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
    public void showNotificationAttendance(String title, String message, int ic) {
        Intent notifyIntent = new Intent(this, AttendanceActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        int icon = R.drawable.ic_notification;
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
    public void showNotificationNo(String title, String message) {
        int icon = android.R.drawable.btn_star;
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }
}
