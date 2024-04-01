package com.example.myapp.ui.apidatalist.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.myapp.R
import com.example.myapp.clicklistener.RecyclerviewListener
import com.example.myapp.databinding.FragmentApiListBinding
import com.example.myapp.ui.apidatalist.adapter.ApiListAdapter
import com.example.myapp.ui.apidatalist.model.EntryModel
import com.example.myapp.ui.apidatalist.viewmodel.ApiListViewModel
import com.example.myapp.util.Resource
import com.example.myapp.utils.SEARCH_NEWS_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ApiListFragment : Fragment(R.layout.fragment_api_list),
    RecyclerviewListener {
    private val viewModel: ApiListViewModel by viewModels()
    var isLoading = false
    private var itemList = ArrayList<EntryModel>()
    var itemClickListener: RecyclerviewListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentApiListBinding.bind(view)

        itemClickListener=this@ApiListFragment
        val listAdapter = ApiListAdapter(itemList, itemClickListener!!)
        binding.apply {
            incHeader.tvTitle.text = "API List"
            rvList.apply {
                adapter = listAdapter
                setHasFixedSize(true)
            }
        }

        var job: Job? = null
        binding. etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    listAdapter.filter.filter(editable.toString())
                }
            }
        }

        viewModel.apiList.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                    isLoading = false
                    it.data?.let { apiResponse ->
                        listAdapter.submitList(apiResponse.entries)
                        listAdapter.notifyDataSetChanged()
                        Log.d("ListSize",apiResponse.count.toString())
                    }
                }
                is Resource.Error -> {
                    binding. paginationProgressBar.visibility = View.INVISIBLE
                    isLoading = true
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        Log.e(ContentValues.TAG, "Error: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.  paginationProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onItemClick(item: EntryModel) {
        if (item.link != null && item.link !=""){
            val bundle = Bundle()
            bundle.putString("ApiURL", item.link)
            NavHostFragment.findNavController(this@ApiListFragment)
                .navigate(R.id.action_apiListFragment_to_APIDetailFragment,bundle)
        }else{
            Toast.makeText(requireContext(), "Empty Url", Toast.LENGTH_SHORT).show()
        }

    }
}