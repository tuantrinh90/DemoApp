package client.yalantis.com.githubclient.main.dashboard

import client.yalantis.com.githubclient.api.GeneralErrorHandler
import client.yalantis.com.githubclient.manager.ApiManager
import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

class DashboardPresenter : BaseMvpPresenterImpl<DashboardContract.View>(), DashboardContract.Presenter {
    override fun getHome() {
        ApiManager.getHomeList()
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showHomeList(it.homes) },
                        GeneralErrorHandler(mView, true)
                        { throwable, errorBody, isNetwork -> mView?.showError(throwable.message) })
    }
}