package dheeraj.sachan.advancedandroidshit;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(TAG);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            new Thread() {
                @Override
                public void run() {
                    Log.d(TAG, Thread.currentThread().getName());
                    String name = Thread.currentThread().getName();
                    for (int k =0;k< 5;k++) {
                        Log.d(TAG, "name = " + name);
                        Log.d(TAG, String.valueOf(k));
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }
                }
            }.start();
        }
    }

}
