package com.wahyouwebid.danamontask.features.users.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.base.BaseFragment
import com.wahyouwebid.danamontask.common.utils.FooterAdapter
import com.wahyouwebid.danamontask.common.utils.isVisibility
import com.wahyouwebid.danamontask.databinding.FragmentUsersBinding
import com.wahyouwebid.danamontask.navigation.NavigationAction
import com.wahyouwebid.danamontask.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class UsersFragment: BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val adapter: UserAdapter by lazy {
        UserAdapter()
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        rvData.adapter = adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        rvData.layoutManager = GridLayoutManager(context, 3)
        rvData.setHasFixedSize(true)
        adapter.addLoadStateListener { loadState(it) }
        ivLogout.setOnClickListener {
            viewModel.logout()
            navigation?.navigate(NavigationAction.UserToLogin)
        }
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
        viewModel.getPhotoList()
        viewModel.photoList.observe(viewLifecycleOwner) { result ->
            adapter.submitData(lifecycle, result)
        }
    }
}