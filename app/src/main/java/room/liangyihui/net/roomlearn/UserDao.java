package room.liangyihui.net.roomlearn;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by zhouzheng on 2017/11/22.
 * Email jelychow@gmail.com
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where userId in (:userIds)")
    List<User> getAll(int[]userIds);

    @Query("SELECT * FROM user where userId =(:userIds) LIMIT 1")
    User getId(int userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);
    @Insert
    void insertAll(List<User>list);

    @Delete
    void delete(User user);
}
