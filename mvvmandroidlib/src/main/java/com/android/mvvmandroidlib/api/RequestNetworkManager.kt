package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


object RequestNetworkManager {

    private var disposable: CompositeDisposable? = null

    private fun getDisposable(): CompositeDisposable? {
        if (disposable == null) {
            disposable = CompositeDisposable()
        }
        return disposable
    }

    private fun <T : BaseResponseModel> getRequest(
        requestCode: Int,
        observable: Observable<T>,
        clazz: Class<BaseResponseModel>,
        callback: SubscriptionCallback<T>
    ): Disposable {

        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Function {
                return@Function processNetworkError(it)
            })
            .subscribe({
                callback.onSuccess(requestCode, it)
            }, {
                if (it is NetworkException) {

                    // TODO Handle expiry token or unauthorised case
                    if (it.exceptionCode == NetworkConstant.ERROR_CODE_SERVER) {
                        val x = Gson().fromJson(it.exceptionMessage, clazz)
                        callback.onSuccess(requestCode, x)
                    } else {
                        callback.onException(requestCode, it.exceptionCode, it.exceptionMessage)
                    }
                } else {
                    callback.onException(requestCode, NetworkConstant.ERROR_CODE_EXCEPTION, it.localizedMessage)
                }
            })
    }

    fun <T : BaseResponseModel> addRequest(
        requestCode: Int,
        observable: Observable<T>,
        callback: SubscriptionCallback<T>
    ) {
        getDisposable()?.add(getRequest(requestCode, observable, BaseResponseModel::class.java, callback))
    }

    fun <T : BaseResponseModel> addRequest(
        observable: Observable<T>,
        callback: SubscriptionCallback<T>
    ) {
        getDisposable()?.add(getRequest(-1, observable, BaseResponseModel::class.java, callback))
    }

    /**
     * create observable if network error(socket timeout etc) or error code(other than 200) occur
     * @param e network error
     * @return Observable
     */
    private fun <T> processNetworkError(e: Throwable): Observable<T> {

        //TODO Need to be changed
        when (e) {
            is HttpException -> {
                val response = e.response()
                var error: String? = null
                try {
                    response.headers()
                    error = response.errorBody()!!.string()
                    if (error!!.isNotEmpty()) {
                        return Observable.error(
                            NetworkException(
                                NetworkConstant.ERROR_CODE_SERVER,
                                error,
                                response.headers()
                            )
                        )
                    }
                } catch (e1: IOException) {
                    return Observable.error(
                        NetworkException(
                            NetworkConstant.ERROR_CODE_IO_EXCEPTION,
                            NetworkConstant.getErrorMessage(NetworkConstant.ERROR_CODE_IO_EXCEPTION)
                        )
                    )
                }

            }
            is SocketTimeoutException -> return Observable.error(
                NetworkException(
                    NetworkConstant.ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION,
                    NetworkConstant.getErrorMessage(NetworkConstant.ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION)
                )
            )
            is IOException -> return Observable.error(
                NetworkException(
                    NetworkConstant.ERROR_CODE_IO_EXCEPTION,
                    NetworkConstant.getErrorMessage(NetworkConstant.ERROR_CODE_IO_EXCEPTION)
                )
            )
            is UnknownHostException -> return Observable.error(
                NetworkException(
                    NetworkConstant.ERROR_CODE_UNKNOWN_HOST_EXCEPTION,
                    NetworkConstant.getErrorMessage(NetworkConstant.ERROR_CODE_UNKNOWN_HOST_EXCEPTION)
                )
            )
            is SocketException -> return Observable.error(
                NetworkException(
                    NetworkConstant.ERROR_CODE_SOCKET_EXCEPTION,
                    NetworkConstant.getErrorMessage(NetworkConstant.ERROR_CODE_SOCKET_EXCEPTION)
                )
            )
        }
        return Observable.error(e)
    }
}