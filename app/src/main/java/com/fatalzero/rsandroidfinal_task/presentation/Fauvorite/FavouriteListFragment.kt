package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeListFragmentBinding
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
        viewModel.searchJoke(query)
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


    private fun setChipGroup(chipGroup: ChipGroup?, filters: List<String>) {
        chipGroup?.removeAllViews()
        for (i in filters.indices) {
            val filter = filters[i]
            val chip = (
                    layoutInflater
                        .inflate(R.layout.filter_chip_item, chipGroup, false)) as Chip
            chip.apply {
                text = filter
                id = i
                if (filter == "Any") isChecked = true
                setChipListener(this, filter)
                chipGroup?.addView(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("REPO", "SHOW")
    }

    private fun setChipListener(chip: Chip, filter: String) =
        chip.setOnCheckedChangeListener { _, isChecked ->
            val checkedFilters = requireNotNull(viewModel.checkedFilters.value)
            when {
                filter == "Any" &&
                        filter !in checkedFilters && isChecked -> viewModel.clearFilter()
                filter !in checkedFilters && isChecked -> viewModel.addFilter(filter)
                filter in checkedFilters && !isChecked && filter != "Any" -> viewModel.removeFilter(
                    filter
                )
            }
        }

    private fun setupListener() {

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

    private fun udateChipGroup(filters: List<String>, chipGroupView: ChipGroup) {
        if (chipGroupView.size == 0) return
        val anyChip = chipGroupView[0] as Chip
        if (filters.contains("Any")) {
            chipGroupView.clearCheck()
            anyChip.isChecked = true
            anyChip.isEnabled = false
        } else {
            chipGroupView.forEach {

                val chip = it as Chip
                if (filters.contains(chip.text)) chip.isChecked = true
            }

        }
        anyChip.isChecked = false
        anyChip.isEnabled = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clearFilter()
        initView()
        setupListener()


        viewModel.getCategory().observe(
            viewLifecycleOwner
        ) { category ->
            val categoryWithAny = category.toMutableList()
            categoryWithAny.add(0, "Any")
            setChipGroup(chipGroupView, categoryWithAny)
            viewModel.checkedFilters.value?.let { udateChipGroup(it,chipGroupView) }
        }

        viewModel.listDbLiveData.observe(
            viewLifecycleOwner
        ) { jokes ->
            jokes?.let {
                adapter?.submitList(it)
            }
        }

        viewModel.checkedFilters.observe(viewLifecycleOwner) { filters ->
            udateChipGroup(filters, chipGroupView)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}