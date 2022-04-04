package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fatalzero.rsandroidfinal_task.databinding.AddFragmentBinding
import com.fatalzero.rsandroidfinal_task.utils.Constants.UNDEFINED_ID
import com.fatalzero.rsandroidfinal_task.utils.GenID
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : Fragment() {

    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val args by navArgs<AddFragmentArgs>()
    private val jokeId by lazy { args.jokeId }


    val addViewModel: AddViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        addViewModel.finish.observe(viewLifecycleOwner, {
            if (it) navController.popBackStack()
        })

        if (jokeId == UNDEFINED_ID) {
            launchAddMode()
        } else {
            launcEditeMode()
        }
    }

    private fun launchAddMode() {
        binding.imageButton.setOnClickListener {
            addViewModel.save(
                com.fatalzero.rsandroidfinal_task.domain.model.Joke(
                    id = GenID.generateId(),
                    category = binding.categoryEdit.text.toString(),
                    joke = binding.JokeTextMultiLine.text.toString()
                )
            )
        }
    }

    private fun launcEditeMode() {
        addViewModel.get(jokeId)
        addViewModel.joke.observe(viewLifecycleOwner, {
            binding.categoryEdit.setText(it.category)
            binding.JokeTextMultiLine.setText(it.joke)
        })
        binding.imageButton.setOnClickListener {
            val jokeEdit = addViewModel.joke.value?.copy(
                category = binding.categoryEdit.text.toString(),
                joke = binding.JokeTextMultiLine.text.toString()
            )
            addViewModel.save(jokeEdit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}