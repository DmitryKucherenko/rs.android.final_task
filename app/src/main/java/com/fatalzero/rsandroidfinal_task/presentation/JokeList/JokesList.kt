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
import com.fatalzero.rsandroidfinal_task.databinding.JokesListFragmentBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.ItemClickListener
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokeAdapter
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.LoaderStateAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class JokesList : Fragment() {
    private var jokeRecyclerView: RecyclerView? = null


    private val jokesListViewModel: JokesListViewModel by viewModel()
    private var _binding: JokesListFragmentBinding? = null
    private val binding get() = _binding!!
    private var sendItemClickListener: ItemClickListener? = null
    private var adapter: JokeAdapter? = null
    private var emptyView: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {

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
                jokesListViewModel.sendJoke(joke)
            }

            override fun onSaveItemClick(joke: Joke?) {
                jokesListViewModel.saveJoke(joke)
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

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            jokesListViewModel.jokeFlowData.collectLatest { pagingData ->
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


}
