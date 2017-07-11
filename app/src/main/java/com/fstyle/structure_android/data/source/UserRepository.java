package com.fstyle.structure_android.data.source;

import rx.Subscription;

/**
 * Created by le.quang.dao on 31/03/2017.
 */

public interface UserRepository extends BaseRepository {

    Subscription searchUsers(int limit, String keyWord);
}
