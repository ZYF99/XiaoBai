package com.example.myapplication.ui.addforum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemFilterGridImageBinding
import io.rong.imkit.picture.entity.LocalMedia


const val TYPE_CAMERA = 1
const val TYPE_PICTURE = 2

/**
 * 添加图片的RecyclerView适配器
 * */
class ReleaseDynamicGridImageAdapter(
    private val context: Context,
    private var imgList: List<LocalMedia>,
    private val onAddPicClick: () -> Unit,
    private val onGridItemClick: (Int, View) -> Unit,
    private val onGridItemDelClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val selectMax = 9

    override
    fun getItemCount(): Int {
        return if (imgList.size < selectMax) {
            imgList.size + 1
        } else {
            imgList.size
        }
    }

    override
    fun getItemViewType(position: Int): Int {
        return if (isShowAddItem(position)) {
            TYPE_CAMERA
        } else {
            TYPE_PICTURE
        }
    }

    private fun isShowAddItem(position: Int): Boolean {
        val size = imgList.size
        return position == size
    }


    class CameraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemFilterGridImageBinding? by lazy {
            DataBindingUtil.bind<ItemFilterGridImageBinding>(itemView)
        }
    }

    class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemFilterGridImageBinding? by lazy {
            DataBindingUtil.bind<ItemFilterGridImageBinding>(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CAMERA -> {
                CameraViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_filter_grid_image, parent, false)
                )
            }
            else -> {
                PictureViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_filter_grid_image, parent, false)
                )
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //少于8张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            val holder = (holder as CameraViewHolder)
            holder.binding?.ivContent?.setImageResource(R.drawable.icon_add_pic)
            holder.binding?.ivContent?.setOnClickListener { onAddPicClick() }
            holder.binding?.linDel?.visibility = View.INVISIBLE
        } else {
            val holder = (holder as PictureViewHolder)
            holder.binding?.linDel?.visibility = View.VISIBLE
            holder.binding?.linDel?.setOnClickListener {
                // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                if (position != RecyclerView.NO_POSITION) {
                    replaceData(imgList.apply { toMutableList().removeAt(position) })
                    onGridItemDelClick(position)
                }
            }
            val item = imgList[position]
            val mimeType = item.mimeType
            val path: String =
                when {
                    item.isCut && !item.isCompressed -> item.cutPath// 裁剪过
                    item.isCompressed || item.isCut && item.isCompressed -> item.compressPath// 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                    else -> item.path// 原图
                }
            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.color.colorPlaceHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(context)
                .load(path)
                .apply(options)
                .into(holder.binding?.ivContent!!)

            //itemView 的点击事件
            holder.binding?.root?.setOnClickListener { v ->
                onGridItemClick(position, v)
            }
        }

    }

    fun replaceData(newList: List<LocalMedia>) {
        imgList = newList
        notifyDataSetChanged()
    }

}




