package uk.co.applylogic.playground.mvvm.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uk.co.applylogic.playground.mvvm.R
import uk.co.applylogic.playground.mvvm.model.Blog

class BlogAdapter(private val blogList: List<Blog>?) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun getItemCount() = blogList!!.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.blog_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        blogList?.get(position)?.let { blog ->
            blog.thumbnail?.let {
                Glide.with(mContext!!)
                    .load(blog.thumbnail)
                    .into(holder.ivThumbnail)
            }

            blog.title?.let { holder.tvTitle.text = blog.title }
            blog.description?.let { holder.tvDescription.text = blog.description }
            blog.link?.let { holder.tvLink.text = blog.link }

            holder.tvLink.setOnClickListener {
                blog.link?.let {
                    try {
                        val intent = Intent()
                        intent.action = Intent.ACTION_VIEW
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(it)
                        mContext?.startActivity(intent)
                    } catch (e: Exception) {

                    }
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvLink: TextView = itemView.findViewById(R.id.tvLink)
    }
}