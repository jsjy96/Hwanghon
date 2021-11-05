package com.example.mysololife.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysololife.R
import com.example.mysololife.utils.FBAuth
import com.example.mysololife.utils.FBRef

class ContentRVAdapter (val context : Context,
                        val items : ArrayList<ContentModel>,
                        val keyList : ArrayList<String>,
                        val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {

//    interface ItemClick {
//        fun onClick(viwe : View, position: Int)
//
//    }
//    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {

//        if(itemClick !=null) {
//            holder.itemView.setOnClickListener { v->
//                itemClick?.onClick(v, position)
//            }
//        }
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item : ContentModel, key : String) {

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contenttitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)

            }

            bookmarkArea.setOnClickListener {

                if(bookmarkIdList.contains(key)) {
                    //북마크가 있을 때
                        //bookmarkIdList.remove(key)
                        FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue()


                } else {
                    //북마크가 없을 때

                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true))
                }


            }

            contenttitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }

    }

}