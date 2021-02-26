package com.example.test.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TypedRecyclerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(
    context,
    attributeSet,
    defStyleAttr
) {

    private val viewAdapter = com.example.test.recyclerview.Adapter()

    init {

        setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        registerTypes(viewAdapter)
        adapter = viewAdapter

        addItemDecoration(getDecoration())

        setPadding(0, 12, 0, 12)
        clipToPadding = false

    }

    fun setItems(items: List<TypedRecyclerViewItem>) {
        viewAdapter.dispatchItems(items)
    }

    private fun registerTypes(adapter: com.example.test.recyclerview.Adapter) {
        adapter.registerViewHolderType(
            1,
            Type1()
        )
        adapter.registerViewHolderType(
            2,
            Type2()
        )
    }

    private fun getDecoration(): ItemDecoration {
        return ItemSpacingDecoration()
    }

}