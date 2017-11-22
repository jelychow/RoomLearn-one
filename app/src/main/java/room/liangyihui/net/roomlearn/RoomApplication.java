package room.liangyihui.net.roomlearn;

import android.app.Application;

/**
 * Created by zhouzheng on 2017/11/22.
 * Email jelychow@gmail.com
 */

public class RoomApplication extends Application {

   public static AppDatabase appDatabase;
    private AppExecutors mAppExecutors;
    public static  RoomApplication roomApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
        roomApplication = this;

        appDatabase= AppDatabase.getInstance(this,mAppExecutors);
    }
}
