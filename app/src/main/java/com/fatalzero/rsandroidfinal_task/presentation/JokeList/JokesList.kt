package com.fatalzero.rsandroidfinal_task.presentation.JokeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.databinding.JokesListFragmentBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.ItemClickListener
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokeAdapter
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.LoaderStateAdapter
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.collectLatest
import java.lang.Exception
import javax.inject.Inject

class JokesList : Fragment() {
    private var jokeRecyclerView: RecyclerView? = null
    private val filtersArray by lazy { resources.getStringArray(R.array.filters_array) }
    private val component by lazy {
        (requireActivity().application as App).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[JokesListViewModel::class.java]
    }

    private var _binding: JokesListFragmentBinding? = null
    private val binding get() = _binding!!
    private var sendItemClickListener: ItemClickListener? = null
    private var adapter: JokeAdapter? = null
    private var emptyView: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d("JokesListViewModel", "createListFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JokesListFragmentBinding.inflate(inflater, container, false)
        jokeRecyclerView = binding.jokeRecyclerView
        jokeRecyclerView?.layoutManager = LinearLayoutManager(context)
        sendItemClickListener = object : ItemClickListener {
            override fun onItemClick(joke: Joke?) {
                viewModel.sendJoke(joke)
            }

            override fun onSaveItemClick(joke: Joke?) {
                viewModel.saveJoke(joke)
            }

            override fun onDeleteItemClick(joke: Joke?) {
                throw Exception("NOT SUPPORTED!")
            }
        }

        adapter = JokeAdapter(sendItemClickListener)
        val loaderStateAdapter = LoaderStateAdapter {
            adapter?.retry()
        }
        jokeRecyclerView?.adapter = adapter?.withLoadStateFooter(loaderStateAdapter)
        emptyView = binding.emptyView
        binding.refreshButton.setOnClickListener { adapter?.retry() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chipGroup = binding.chipGroup
        setChipGroup(chipGroup)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            viewModel.jokeFlowData?.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }

        adapter?.addLoadStateListener { loadState ->
            when {
                loadState.refresh is LoadState.Error -> {
                    if (adapter?.itemCount ?: 0 < 1) {
                        jokeRecyclerView?.visibility = GONE
                        emptyView?.visibility = VISIBLE
                    }
                }
                loadState.refresh != LoadState.Loading -> {
                    jokeRecyclerView?.visibility = VISIBLE
                    emptyView?.visibility = GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setChipGroup(chipGroup: ChipGroup){
        for (i in filtersArray.indices){
            val filter = Filters.values()[i]
            val chip =
                (layoutInflater.inflate(R.layout.filter_chip_item, chipGroup, false)) as Chip
            chip.apply {
                text = filtersArray[i]
                id = filter.ordinal
                setChipListener(this, filter)
                chipGroup.addView(this)
            }

        }
    }

    private fun setChipListener(chip: Chip, filter: Filters) =
        chip.setOnCheckedChangeListener { _, isChecked ->
             viewModel.addFilter(filter)
        }


}
