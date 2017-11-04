package com.fstyle.structure_android.data.source.remote.api.error;

import io.reactivex.functions.Consumer;

/**
 * Created by Sun on 4/16/2017.
 */

public abstract class RequestError implements Consumer<Throwable> {

    /**
     * Don't override this method.
     * Override {@link RequestError#onRequestError(BaseException)} instead
     */
    @Override
    public void accept(Throwable throwable) {
        if (throwable == null) {
            onRequestError(BaseException.toUnexpectedError(new Throwable("Unknown exception")));
            return;
        }
        if (throwable instanceof BaseException) {
            onRequestError((BaseException) throwable);
        } else {
            onRequestError(BaseException.toUnexpectedError(throwable));
        }
    }

    abstract void onRequestError(BaseException error);
}
