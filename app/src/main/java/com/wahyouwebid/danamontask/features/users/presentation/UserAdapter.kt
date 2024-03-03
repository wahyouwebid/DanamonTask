package com.wahyouwebid.danamontask.features.users.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.utils.UserRole
import com.wahyouwebid.danamontask.common.utils.loadImage
import com.wahyouwebid.danamontask.common.utils.splitString
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.databinding.AdapterAdminBinding
import com.wahyouwebid.danamontask.databinding.AdapterPhotosBinding

/**
 * Created by wahyouwebid
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class UserAdapter : PagingDataAdapter<PhotoEntity, UserAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: AdapterPhotosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            ivImage.loadImage(getItem(position)?.thumbnailUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPhotosBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    object DiffCallback : DiffUtil.ItemCallback<PhotoEntity>() {
        override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem == newItem
        }
    }
}