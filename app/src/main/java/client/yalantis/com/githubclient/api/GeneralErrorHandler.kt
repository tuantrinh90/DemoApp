package client.yalantis.com.githubclient.api

import android.support.annotation.StringRes
import android.text.TextUtils
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.mvp.BaseMvpView
import retrofit2.adapter.rxjava.HttpException
import rx.functions.Action1
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GeneralErrorHandler(view: BaseMvpView? = null,
                          private val mShowError: Boolean = false,
                          val onFailure: (Throwable, String?, Boolean) -> Unit)
    : Action1<Throwable> {

    private val mViewReference: WeakReference<BaseMvpView>

    init {
        mViewReference = WeakReference<BaseMvpView>(view)
    }

    override fun call(throwable: Throwable) {
        var isNetwork = false
        var errorBody: String? = null
        if (isNetworkError(throwable)) {
            isNetwork = true
            showMessage(R.string.internet_connection_unavailable)
        } else if (throwable is HttpException) {
            errorBody = throwable.message()
            if (errorBody != null) {
                handleError()
            }
        }

        onFailure(throwable, errorBody, isNetwork)
    }

    private fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }

    private fun handleError() {
        showErrorIfRequired(R.string.server_error)
    }

    private fun showErrorIfRequired(@StringRes strResId: Int) {
        if (mShowError) {
            mViewReference.get()?.showError(strResId)
        }
    }

    private fun showErrorIfRequired(error: String) {
        if (mShowError && !TextUtils.isEmpty(error)) {
            mViewReference.get()?.showError(error)
        }
    }

    private fun showMessage(@StringRes strResId: Int) {
        mViewReference.get()?.showMessage(strResId)
    }

    private fun showMessage(error: String) {
        if (error.isNotEmpty()) {
            mViewReference.get()?.showError(error)
        }
    }

}