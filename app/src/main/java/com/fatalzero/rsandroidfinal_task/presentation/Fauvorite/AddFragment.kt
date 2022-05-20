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
import androidx.navigation.fragment.navArgs
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.databinding.AddFragmentBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.utils.Constants.UNDEFINED_ID
import com.fatalzero.rsandroidfinal_task.utils.GenID
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import javax.inject.Inject

class AddFragment : Fragment() {

    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val args by navArgs<AddFragmentArgs>()
    private val jokeId by lazy { args.jokeId }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AddViewModel::class.java]
    }
    private val component by lazy {
        (requireActivity().application as App).appComponent
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = AddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        viewModel.finish.observe(viewLifecycleOwner) {
            if (it) navController.popBackStack()
        }

        if (jokeId == UNDEFINED_ID) {
            launchAddMode()
        } else {
            launcEditeMode()
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.save(
                Joke(
                    id = GenID.generateId(),
                    category = binding.editCategory.text.toString(),
                    joke = binding.JokeTextMultiLine.text.toString()
                )
            )
        }
    }

    private fun launcEditeMode() {
        viewModel.get(jokeId)
        viewModel.joke.observe(viewLifecycleOwner, {
            binding.editCategory.setText(it.category)
            binding.JokeTextMultiLine.setText(it.joke)
        })
        binding.saveButton.setOnClickListener {
            val jokeEdit = viewModel.joke.value?.copy(
                category = binding.editCategory.text.toString(),
                joke = binding.JokeTextMultiLine.text.toString()
            )
            viewModel.save(jokeEdit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}