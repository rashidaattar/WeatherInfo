package com.demo.weatherinfo.data.remote


/**
 * Created by Rashida on 4/1/20.
 *
 */

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<RequestType>
@MainThread internal constructor() {
    private val result = MediatorLiveData<Resource<RequestType>>()
    private var mDisposable: Disposable? = null

    internal val asLiveData: LiveData<Resource<RequestType>>
        get() = result

    private fun fetchFromNetwork() {
        createCall()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<RequestType> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        mDisposable = d
                    }
                }

                override fun onSuccess(requestType: RequestType) {
                    result.setValue(Resource.success(requestType))
                }

                override fun onError(e: Throwable) {
                    onFetchFailed()
                    result.setValue(Resource.error(e.message.toString(), null))
                    mDisposable!!.dispose()
                }
            })
    }


    @MainThread
    protected abstract fun createCall(): Single<RequestType>

    @MainThread
    protected abstract fun onFetchFailed()
}
