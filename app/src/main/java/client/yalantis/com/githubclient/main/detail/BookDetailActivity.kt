package client.yalantis.com.githubclient.main.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.api.ApiSettings.PHOTO
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class BookDetailActivity : BaseMvpActivity<BookDetailsContract.View,
        BookDetailsContract.Presenter>(),
        BookDetailsContract.View {


    override var mPresenter: BookDetailsContract.Presenter = BookDetailsPresenter()

    companion object {
        fun newInstance(context: Context, photo: String): Intent =
                Intent(context, BookDetailActivity::class.java).apply {
                    putExtra(PHOTO, photo)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var ivPhoto = findViewById<ImageView>(R.id.ivPhoto)
        Glide.with(getContext()).load(intent.getStringExtra(PHOTO)).into(ivPhoto)
        toolbar.title = getString(R.string.title_detail)
    }

}