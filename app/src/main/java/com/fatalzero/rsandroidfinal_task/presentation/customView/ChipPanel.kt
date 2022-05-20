package com.fatalzero.rsandroidfinal_task.presentation.customView

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.databinding.ChipPanelBinding
import com.fatalzero.rsandroidfinal_task.utils.Constants.ANY
import com.fatalzero.rsandroidfinal_task.utils.Constants.DEF_COLOR
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

typealias OnChipButtonsActionListener = (chip: Chip, filter: String) -> Unit

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
    private var layoutInflater: LayoutInflater
    private var listener: OnChipButtonsActionListener? = null
    private var chipGroupView: ChipGroup? = null
    private var backgroundItem: Int = 0


    init {
        layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.chip_panel, this, true)
        binding = ChipPanelBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ChipPanel, defStyleAttr, defStyleRes)
        chipGroupView = binding.chipGroup
        backgroundItem = typedArray.getInt(R.styleable.ChipPanel_chipItemBackground,  DEF_COLOR)
        typedArray.recycle()
    }

    fun setChipGroup(filters: List<String>) {
        chipGroupView?.removeAllViews()
        for (i in filters.indices) {
            val filter = filters[i]
            val chip = (
                    layoutInflater
                        .inflate(R.layout.filter_chip_item, chipGroupView, false)) as Chip
            chip.apply {
                text = filter
                id = i
                if (filter == ANY) isChecked = true
                setChipListener(this, filter)
                chipGroupView?.addView(this)
                chipBackgroundColor.apply { if(backgroundItem!=DEF_COLOR) ColorStateList.valueOf(backgroundItem) }

            }
        }
    }

    fun udateChipGroup(filters: List<String>) {
        if (chipGroupView?.size == 0) return
        val anyChip = chipGroupView?.get(0) as Chip
        if (filters.contains(ANY)) {
            chipGroupView?.clearCheck()
            anyChip.isChecked = true
            anyChip.isEnabled = false
        } else {
            chipGroupView?.forEach {
                val chip = it as Chip
                if (filters.contains(chip.text)) chip.isChecked = true
            }
        }
        anyChip.isChecked = false
        anyChip.isEnabled = true
    }

    fun setListener(listener: OnChipButtonsActionListener?) {
        this.listener = listener
    }

    private fun setChipListener(chip: Chip, filter: String) = listener?.invoke(chip, filter)

}
