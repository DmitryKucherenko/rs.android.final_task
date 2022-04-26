package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeListFragmentBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter.FJokeAdapter
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter.FauvItemClickListener
import com.fatalzero.rsandroidfinal_task.utils.Constants.UNDEFINED_ID
import com.fatalzero.rsandroidfinal_task.utils.DebouncingTextWatcher
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class FavouriteListFragment : Fragment() {
    private var _binding: FauvoriteJokeListFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var favoriteItemClickListener: FauvItemClickListener? = null
    private var navController: NavController? = null
    private var favoriteRecyclerView: RecyclerView? = null
    private var searchTextView: EditText? = null
    private var addButton: FloatingActionButton? = null
    private val filtersArray by lazy { resources.getStringArray(R.array.filters_array) }
    private val component by lazy {
        (requireActivity().application as App).appComponent
    }

    private lateinit var chipGroupView: ChipGroup

    private var adapter: FJokeAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FauvoriteJokeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun search(query: String) {
        viewModel.searchJoke(query).observe(viewLifecycleOwner,
            { jokes ->
                jokes?.let {
                    adapter?.submitList(it)
                }
            })
    }


    private fun initView() {
        with(binding) {
            searchTextView = searchText
            favoriteRecyclerView = jokeRecyclerView
            addButton = floatingActionButton
            chipGroupView = binding.chipGroup
        }


        favoriteRecyclerView?.layoutManager = LinearLayoutManager(context)
        navController = findNavController()

    }


    private fun setChipGroup(chipGroup: ChipGroup?) {
        for (i in filtersArray.indices) {
            val filter = Filters.values()[i]
            val chip = (
                    layoutInflater
                        .inflate(R.layout.filter_chip_item, chipGroup, false)) as Chip
            chip.apply {
                text = filtersArray[i]
                id = filter.ordinal
                setChipListener(this, filter)
                chipGroup?.addView(this)
                //if (filter == Filters.Any) isChecked = true
            }
        }
    }

    private fun setChipListener(chip: Chip, filter: Filters) =
        chip.setOnCheckedChangeListener { _, isChecked ->

            val checkeFilters = requireNotNull(viewModel.checkedFilters.value)
//            if (isChecked) viewModel.addFilter(filter) else
//                viewModel.removeFilter(filter)
            when{
                filter == Filters.Any &&
                        filter !in checkeFilters && isChecked->viewModel.clearFilter()
                filter !in checkeFilters && isChecked -> viewModel.addFilter(filter)
                filter in checkeFilters && !isChecked -> viewModel.removeFilter(filter)
            }


            search(searchTextView?.text.toString())
        }

    private fun setupListener() {
        setChipGroup(chipGroupView)

        addButton?.setOnClickListener {
            navController?.navigate(
                FavouriteListFragmentDirections.actionBookMarksFragmentToAddFragment(
                    UNDEFINED_ID
                )
            )

        }

        searchTextView?.addTextChangedListener(
            DebouncingTextWatcher.getWatcher(lifecycleScope) { search(it.toString()) }
        )

        favoriteItemClickListener = object : FauvItemClickListener {
            override fun onItemClick(joke: Joke?) {
                viewModel.sendJoke(joke)
            }

            override fun onSaveItemClick(joke: Joke?) {
                throw Exception("NOT SUPPORTED!")
            }

            override fun onEditItemClick(id: String) {
                navController?.navigate(
                    FavouriteListFragmentDirections.actionBookMarksFragmentToAddFragment(
                        id
                    )
                )
            }

            override fun onDeleteItemClick(joke: Joke?) {
                viewModel.showDeleteDialog(joke)
            }
        }
        adapter = FJokeAdapter(favoriteItemClickListener)
        favoriteRecyclerView?.adapter = adapter

    }

    private fun udateChipGroup(filters: List<Filters>, chipGroupView: ChipGroup) {
          val anyChip = chipGroupView[Filters.Any.ordinal] as Chip
        val checkedFilters = requireNotNull(viewModel.checkedFilters.value)
        if (checkedFilters.contains(Filters.Any)) {
            chipGroupView.clearCheck()
            anyChip.isChecked = true
            anyChip.isEnabled = false
        } else {
            checkedFilters.forEach {
                chipGroupView.check(it.ordinal)
            }
            anyChip.isChecked = false
            anyChip.isEnabled = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        setupListener()




        viewModel.listDbLiveData.observe(
            viewLifecycleOwner
        ) { jokes ->
            jokes?.let {
                adapter?.submitList(it)
            }
        }

        viewModel.checkedFilters.observe(viewLifecycleOwner){
                filters -> udateChipGroup(filters,chipGroupView)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}