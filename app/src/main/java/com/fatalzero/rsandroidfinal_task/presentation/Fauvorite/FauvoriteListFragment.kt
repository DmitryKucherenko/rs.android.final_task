package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.R

import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeItemBinding
import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeListFragmentBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter.FJokeAdapter
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter.FauvItemClickListener
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.ItemClickListener
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import java.lang.Exception
import javax.inject.Inject

class FauvoriteListFragment : Fragment() {

    private var _binding: FauvoriteJokeListFragmentBinding? = null
    private val binding get() = _binding!!
    private var fauvoriteItemClickListener: FauvItemClickListener? = null
    private lateinit var navController: NavController
    private var favoriteRecyclerView: RecyclerView? = null

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }

    private var adapter: FJokeAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FauvorteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FauvoriteJokeListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteRecyclerView = binding.jokeRecyclerView
        favoriteRecyclerView?.layoutManager = LinearLayoutManager(context)
        navController =  findNavController()
        binding.floatingActionButton.setOnClickListener {
//            navController.navigate(R.id.action_bookMarksFragment_to_addFragment)
            navController.navigate(FauvoriteListFragmentDirections.actionBookMarksFragmentToAddFragment(-1))
        }

        fauvoriteItemClickListener = object : FauvItemClickListener {
            override fun onItemClick(joke: Joke?) {
                viewModel.sendJoke(joke)
            }

            override fun onSaveItemClick(joke: Joke?) {
                throw Exception("NOT SUPPORTED!")
            }

            override fun onEditItemClick(id: Int) {
                navController.navigate(FauvoriteListFragmentDirections.actionBookMarksFragmentToAddFragment(id))
            }

            override fun onDeleteItemClick(joke: Joke?) {
                viewModel.deleteJoke(joke)
            }
        }

        adapter = FJokeAdapter(fauvoriteItemClickListener)
        favoriteRecyclerView?.adapter = adapter
        viewModel.listDbLiveData.observe(viewLifecycleOwner,
            { jokes ->
                jokes?.let {
                    adapter?.submitList(it)
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}