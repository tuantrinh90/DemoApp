package client.yalantis.com.githubclient.main.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.api.ApiSettings.URL
import client.yalantis.com.githubclient.main.detail.BookDetailActivity
import client.yalantis.com.githubclient.model.Home
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class DashboardActivity : BaseMvpActivity<DashboardContract.View,
        DashboardPresenter>(),
        DashboardContract.View {
    var recyclerHome: RecyclerView? = null
    override fun showHomeList(homes: MutableList<Home>) {
        mAdapter?.addListHome(homes)
        hideProgress()
    }

    private var mAdapter: DashboardAdapter? = null
    override var mPresenter: DashboardPresenter = DashboardPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerHome = findViewById(R.id.recyclerHome)
        setUpRecyclerView()
        mPresenter.getHome()
        toolbar.title = getString(R.string.title_dashboard)
        showProgress()
    }

    private fun setUpRecyclerView() {
        mAdapter = DashboardAdapter(getContext(), ArrayList()) {
            startActivity(BookDetailActivity.newInstance(this, String.format("%s%s", URL, it.media.url)))
        }
        recyclerHome?.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        recyclerHome?.adapter = mAdapter
    }

    private fun showProgress() {
        recyclerHome?.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        recyclerHome?.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
    }
}

