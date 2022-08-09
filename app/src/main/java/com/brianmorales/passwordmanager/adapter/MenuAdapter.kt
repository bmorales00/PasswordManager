package com.brianmorales.passwordmanager.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brianmorales.passwordmanager.R
import com.brianmorales.passwordmanager.model.Passcard

class MenuAdapter(
    private var context: Context,
    private var passCardList: ArrayList<Passcard>,
    )
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private lateinit var onCardClickListener:OnCardClickListener

    interface OnCardClickListener{
        fun onCardClicked(position: Int)
    }

    fun setOnCardClickListener(listener: OnCardClickListener){
        onCardClickListener = listener
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_passcard,parent,false)
        return ViewHolder(view, onCardClickListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position)

    }

    override fun getItemCount() = passCardList.size

    inner class ViewHolder(itemView: View, listener: OnCardClickListener) : RecyclerView.ViewHolder(itemView) {
        private var tvLink = itemView.findViewById<TextView>(R.id.tvLink)
        private var tvUser = itemView.findViewById<TextView>(R.id.tvUser)
        private var wvIcon = itemView.findViewById<WebView>(R.id.wvIcon)

        init{
            itemView.setOnClickListener{
                listener.onCardClicked(adapterPosition)
            }
        }

//        @SuppressLint("SetJavaScriptEnabled")
        fun bind(position: Int) {
            tvLink.text = passCardList[position].link
            tvUser.text = passCardList[position].userName
            val url = "http://" + passCardList[position].link + "/favicon.ico"
            wvIcon.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(url)
                    Log.i(TAG, "Loading")
                    return true
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                }
            }

        }

    }

}

//wvIcon.webViewClient = object: WebViewClient(){
//                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                    super.onPageStarted(view, url, favicon)
//
//                }
//
////                override fun onProgressChanged(view: WebView?, newProgress: Int) {
////                    super.onProgressChanged(view, newProgress)
////                }
////
////                override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
////                    super.onReceivedIcon(view, icon)
////                }
//
//
//            }
//
//            wvIcon.settings.javaScriptEnabled = true
//            wvIcon.settings.loadsImagesAutomatically = true
//
//
//
//            wvIcon.loadUrl("http://" + passCardList[position].link + "/favicon.ico")
//            wvIcon.favicon