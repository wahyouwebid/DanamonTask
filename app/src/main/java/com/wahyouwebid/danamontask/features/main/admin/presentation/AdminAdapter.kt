package com.wahyouwebid.danamontask.features.main.admin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahyouwebid.danamontask.common.utils.splitString
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.databinding.AdapterAdminBinding

/**
 * Created by wahyouwebid
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

class AdminAdapter(
    val showDetail: (UserEntity?) -> Unit,
    val showMore: (UserEntity?) -> Unit
) : PagingDataAdapter<UserEntity, AdminAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: AdapterAdminBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvName.text = getItem(position)?.username
            tvEmail.text = getItem(position)?.email
            tvThumbnail.text = getItem(position)?.username?.splitString()

            root.setOnClickListener {
                showDetail(getItem(position))
            }

            ivMore.setOnClickListener {
                showMore(getItem(position))
            }

            root.setOnClickListener {
                showDetail.invoke(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterAdminBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    object DiffCallback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}