package com.example.test.recyclerview

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class Type2 : TypedViewHolder {
    override fun onBindViewHolder(
        typedItem: TypedRecyclerViewItem?,
        holder: RecyclerView.ViewHolder
    ) {
        (holder as? ViewHolder)?.let { currentViewHolder ->
            (typedItem as? State)?.let { currentItem ->
                currentViewHolder.textView.text = currentItem.state
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val textMessage = TextView(parent.context)
        textMessage.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textMessage.setPadding(20, 10, 20, 10)
        textMessage.setBackgroundColor(Color.WHITE)
        textMessage.id = ViewCompat.generateViewId()
        return ViewHolder(textMessage)
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    data class State(
        val id: Long = 0L,
        val state: String = ""
    ) : TypedRecyclerViewItem {
        override fun contentsAreTheSameAs(other: TypedRecyclerViewItem): Boolean {
            return if (other is State) {
                this.state == other.state && this.someBool == other.someBool
            } else {
                false
            }
        }

        override var someBool: Boolean = false

        override fun getItemType(): Int = 2

        override fun isTheSameAs(other: TypedRecyclerViewItem): Boolean {
            return if (other is State) {
                this.id == other.id
            } else {
                false
            }
        }

        override fun toString(): String {
            return "State(id=$id, state='$state', someBool=$someBool)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as State

            if (id != other.id) return false
            if (state != other.state) return false
            if (someBool != other.someBool) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + state.hashCode()
            result = 31 * result + someBool.hashCode()
            return result
        }

        override fun getCopy(someBool: Boolean): TypedRecyclerViewItem {
            val newItem = copy()
            newItem.someBool = someBool
            return newItem
        }
    }
}