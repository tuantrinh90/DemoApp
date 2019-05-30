package client.yalantis.com.githubclient.main.dashboard

import client.yalantis.com.githubclient.model.Home
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView

object DashboardContract {

    interface View : BaseMvpView {
        fun showHomeList(repositories: MutableList<Home>)
    }
    interface Presenter : BaseMvpPresenter<View> {
        fun getHome()
    }
}
