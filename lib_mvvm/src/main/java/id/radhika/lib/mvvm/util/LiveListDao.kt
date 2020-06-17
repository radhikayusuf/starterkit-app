package id.radhika.lib.mvvm.util

import id.radhika.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Apr/2020
 **/
class LiveListDao<T>(private val defValue: ArrayList<T> = arrayListOf()) : ComponentLiveDao<ArrayList<T>>(defValue), List<T> {

    private val arrayList: ArrayList<T> = arrayListOf()

    fun call() {
        postValue(arrayList)
    }

    fun remove(element: T){
        arrayList.remove(element)
        postValue(arrayList)
    }

    fun removeAt(index: Int){
        arrayList.removeAt(index)
        postValue(arrayList)
    }

    fun add(element: T){
        arrayList.add(element)
        postValue(arrayList)
    }

    fun addAll(element: Array<T>){
        arrayList.addAll(element)
        postValue(arrayList)
    }

    fun addAll(element: List<T>){
        arrayList.addAll(element)
        postValue(arrayList)
    }

    fun addAll(element: ArrayList<T>){
        arrayList.addAll(element)
        postValue(arrayList)
    }

    fun addAll(element: Collection<T>){
        arrayList.addAll(element)
        postValue(arrayList)
    }

    fun addAll(element: Iterable<T>){
        arrayList.addAll(element)
        postValue(arrayList)
    }

    fun add(index: Int, element: T) {
        arrayList.add(index, element)
        postValue(arrayList)
    }

    fun clear() {
        arrayList.clear()
        postValue(arrayList)
    }

    fun toArrayList() = arrayList

    override val size: Int
        get() = arrayList.size

    override fun contains(element: T) = arrayList.contains(element)

    override fun containsAll(elements: Collection<T>) = arrayList.containsAll(elements)

    override fun get(index: Int) = arrayList.get(index)

    override fun indexOf(element: T) = arrayList.indexOf(element)

    override fun isEmpty() = arrayList.isEmpty()

    override fun iterator(): Iterator<T> = arrayList.iterator()

    override fun lastIndexOf(element: T) = arrayList.lastIndexOf(element)

    override fun listIterator() = arrayList.listIterator()

    override fun listIterator(index: Int) = arrayList.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = arrayList.subList(fromIndex, toIndex)
}