package com.fatalzero.rsandroidfinal_task.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.HorizontalScrollView
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.databinding.ChipPanelBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

const val ANY=""

class ChipPanel(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : HorizontalScrollView(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private val binding: ChipPanelBinding
    private var count: Int = 0
    private var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.chip_panel, this, true)
        binding = ChipPanelBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ChipPanel, defStyleAttr, defStyleRes)
        with(binding) {
            count = typedArray.getInt(R.styleable.ChipPanel_chipCount, 0)
            buildChips(count)

        }
        typedArray.recycle()
    }

    private fun buildChips(count: Int) {

        var chipGroup = binding.chipGroup
        for (i in 0..count) {

            val chip = (
                    inflater
                        .inflate(R.layout.filter_chip_item, chipGroup, false)) as Chip
            chip.apply {
                text = "Item $i"
                id = i

                chipGroup.addView(this)
            }
        }
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
        var test: (CompoundButton, Boolean)->Unit

    }

    private fun setChipListener(chip: Chip, filter: String,checkedListener:(CompoundButton, Boolean)->Unit) =
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

}
