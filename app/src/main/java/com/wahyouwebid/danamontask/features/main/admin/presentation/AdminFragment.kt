package com.wahyouwebid.danamontask.features.main.admin.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyouwebid.danamontask.common.base.BaseFragment
import com.wahyouwebid.danamontask.common.utils.FooterAdapter
import com.wahyouwebid.danamontask.common.utils.isVisibility
import com.wahyouwebid.danamontask.databinding.FragmentAdminBinding
import dagger.hilt.android.AndroidEntryPoint

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class AdminFragment: BaseFragment<FragmentAdminBinding>(FragmentAdminBinding::inflate) {

    private val viewModel: AdminViewModel by viewModels()

    private val adapter: AdminAdapter by lazy {
        AdminAdapter (
            showDetail = {},
            showMore = {},
        )
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        rvData.adapter = adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        rvData.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvData.setHasFixedSize(true)
        adapter.addLoadStateListener { loadState(it) }
    }

    private fun loadState(loadState: CombinedLoadStates) {
        with(binding) {
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                adapter.itemCount < 1
            ) {
                tvEmptyData.isVisibility(true)
            } else {
                tvEmptyData.isVisibility(false)
            }
        }
    }

    override fun setupViewModel() {
        viewModel.getUserList()
        viewModel.userList.observe(viewLifecycleOwner) { result ->
            adapter.submitData(lifecycle, result)
        }
    }
}