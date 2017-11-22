package room.liangyihui.net.roomlearn

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var tvTest: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTest = findViewById(R.id.tv_test);


        var mAppExecutors = AppExecutors()
        Handler().postDelayed({
            mAppExecutors.diskIO().execute {

                var list = RoomApplication.appDatabase.userDao().all;

                var user: User? = RoomApplication.appDatabase.userDao().getId(2) ?: return@execute

                var name = user?.firstName
                Log.d("buildDatabase", "diskIO")
                Log.d("buildDatabase", ""+name)

                mAppExecutors.mainThread().execute {
                    tvTest?.text = "name"+name
                    Log.d("buildDatabase", "mainThread")


                }
            }

        },0)



    }
}
