package com.example.cachua.note.ui.config;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;

public class Permission extends Fragment {
    public static void getPermissionCamera(Activity activity) {

        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CAMERA,

                }, 3);

    }
    public static void getPermissionRec(Activity activity) {

        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.RECORD_AUDIO,

                }, 4);

    }
    public static void getPermissionRead(Activity activity) {

        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,

                }, 5);

    }


}
