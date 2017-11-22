package room.liangyihui.net.roomlearn;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME ="user-info" ;

    public abstract UserDao userDao();
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    Log.d("buildDatabase","getInstance");

                    sInstance = buildDatabase(context.getApplicationContext(), executors);
//
//                    executors.diskIO().execute(() -> {
//                        // Add a delay to simulate a long-running operation
//                        // Generate the data for pre-population
//                        AppDatabase database = AppDatabase.getInstance(RoomApplication.roomApplication, executors);
//                        List<User> users = new ArrayList<>();
//                        User user=new  User(1,"1","1");
//                        users.add(user);
//                        user.setUserId(2);
////                        users.add(user);
//
//                        database.runInTransaction(() -> {
//                            try {
//                                database.userDao().insertAll(user);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        });
//
//                        // notify that the database was created and it's ready to be used
////                            database.setDatabaseCreated();
//                        Log.d("buildDatabase","buildDatabase");
//                    });
                }
            }
        }
        return sInstance;
    }



    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
//                            List<User> users = new ArrayList<>();
                            User user=new  User(2,"1","1");
                            User user2=new  User(3,"1","1");

//                            users.add(user);
//                            user.setUserId(2);
//                            User user2 = user;
                           User [] users1= new User[] {
                                   user,  user2
                           } ;
                            database.runInTransaction(() -> {
                                try {
                                    database.userDao().insertAll(users1);

                                    Log.d("buildDatabase",""+  database.userDao().getAll().size());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            });

                            // notify that the database was created and it's ready to be used
//                            database.setDatabaseCreated();
                            Log.d("buildDatabase","buildDatabase");
                        });
                    }
                }).build();
    }


}