package com.example.test.recyclerview

interface TypedRecyclerViewItem {
    fun getItemType(): Int
    fun isTheSameAs(other: TypedRecyclerViewItem): Boolean
    fun contentsAreTheSameAs(other: TypedRecyclerViewItem): Boolean
    var someBool: Boolean
    fun getCopy(someBool: Boolean): TypedRecyclerViewItem
}