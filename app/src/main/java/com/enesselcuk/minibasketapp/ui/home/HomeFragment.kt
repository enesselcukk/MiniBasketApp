package com.enesselcuk.minibasketapp.ui.home


import androidx.fragment.app.viewModels
import com.enesselcuk.minibasketapp.base.BaseFragment
import com.enesselcuk.minibasketapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getDataBinding() = FragmentHomeBinding.inflate(layoutInflater)
    private val viewModel: HomeViewModel by viewModels()

    override fun definition() {

    }

}