package com.fstyle.structure_android.data.source.local;

import io.realm.Realm;

/**
 * Created by le.quang.dao on 13/03/2017.
 */

public abstract class BaseLocalDataSource {

    protected Realm mRealm;

    public BaseLocalDataSource(Realm realm) {
        this.mRealm = realm;
    }

    public abstract void openTransaction();

    public abstract void closeTransaction();

}
