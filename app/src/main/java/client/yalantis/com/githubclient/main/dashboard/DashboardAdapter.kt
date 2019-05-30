package client.yalantis.com.githubclient.main.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.api.ApiSettings
import client.yalantis.com.githubclient.model.Home
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class DashboardAdapter(val context: Context, val homesList: MutableList<Home>,
                       val onItemClick: (Home) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = homesList[position].createdAt
        holder.itemView.setOnClickListener { onItemClick(homesList[position]) }
        val ro = RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(client.yalantis.com.githubclient.R.mipmap.ic_launcher)
                .override(150, 150)
                .fitCenter()
        Glide.with(context).load(String.format("%s%s", ApiSettings.URL, homesList[position].media.url))
                .apply(ro)
                .into(holder.ivPhoto1)
        Glide.with(context).load(String.format("%s%s", ApiSettings.URL, homesList[position].media.url))
                .into(holder.ivPhoto2)

        var pos = position + 1
        if (pos % 2 == 0) {
            holder.ivPhoto2.visibility = View.VISIBLE
            holder.ivPhoto1.visibility = View.GONE
        } else {
            holder.ivPhoto2.visibility = View.GONE
            holder.ivPhoto1.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = homesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,
                false).let { ViewHolder(it) }
    }

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        var ivPhoto1: CircleImageView = containerView.findViewById(R.id.ivPhoto1)
        var tvTitle: TextView = containerView.findViewById(R.id.tvTitle)
        var ivPhoto2: ImageView = containerView.findViewById(R.id.ivPhoto2)
    }


    fun addListHome(homes: MutableList<Home>) {
        homesList.addAll(homes)
        notifyDataSetChanged()
    }
}