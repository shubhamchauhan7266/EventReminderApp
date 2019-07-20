package com.android.mvvmandroidlib.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object RequestNetworkManager {

    private var disposable: CompositeDisposable? = null

    private fun getDisposable(): CompositeDisposable? {
        if (disposable == null) {
            disposable = CompositeDisposable()
        }
        return disposable
    }

    private fun <T> getRequest(
        requestCode: Int,
        observable: Observable<T>,
        callback: SubscriptionCallback<T>
    ): Disposable {
        //create error if network is not available to propagate to observer
//        if (!DeviceManager.isNetworkAvailable()) {
//            observable =
//                Observable.error(NetworkException(SyncStateContract.Constants.ERROR_CODE_NO_NETWORK, SyncStateContract.Constants.ERROR_MSG_NO_NETWORK))
//        }
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Function {
                return@Function processNetworkError(it)
            })
            .subscribe({
                callback.onSuccess(requestCode, it)
            }, {
                if (it is NetworkException) {
                    callback.onError(requestCode, it.exceptionCode, it.exceptionMessage)
                } else {
                    callback.onError(requestCode, NetworkConstant.ERROR_CODE_EXCEPTION, it.localizedMessage)
                }
//                callback.onError(400, "Server Error")
            })
    }

    fun <T> addRequest(
        requestCode: Int,
        observable: Observable<T>,
        callback: SubscriptionCallback<T>
    ) {
        getDisposable()?.add(getRequest(requestCode, observable, callback))
    }

    /**
     * create observable if network error(socket timeout etc) or error code(other than 200) occur
     * @param e network error
     * @return Observable
     */
    private fun <T> processNetworkError(e: Throwable): Observable<T> {

        //TODO Need to be changed
        if (e is HttpException) {
            val response = e.response()
            var error: String? = null
            try {
                error = response.errorBody()!!.string()
                if (error!!.isNotEmpty()) {
                    return Observable.error(NetworkException(NetworkConstant.ERROR_CODE_SERVER_DOWN, error))
                }
            } catch (e1: IOException) {
                return Observable.error(NetworkException(NetworkConstant.ERROR_CODE_IO_EXCEPTION, ""))
            }

        } else if (e is SocketTimeoutException) {
            return Observable.error(NetworkException(NetworkConstant.ERROR_CODE_TIMEOUT, ""))
        }
        return Observable.error(e)
    }
}