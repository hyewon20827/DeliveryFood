package com.example.deliveryfood.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentSecondBinding
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebviewResultFragment : BaseFragment<FragmentSecondBinding, DeliveryAddressViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_second
    override val viewmodel: DeliveryAddressViewModel by activityViewModels<DeliveryAddressViewModel>()

    private lateinit var resultHashMap : HashMap<String, String>

    private lateinit var listViewAdapter : ExpandableListViewAdapter
    private lateinit var chapterList : List<String>
    private lateinit var contentList : HashMap<String, List<String>>

    companion object{
        fun newInstance(title : String) = WebviewResultFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }

    override fun initStartView() {
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun FragmentSecondBinding.onCreateView() {
        resultHashMap = viewmodel.address_api_result_hashmap.value!!
        setViewState()
        setExpandableListViewAdapter()
    }

    override fun FragmentSecondBinding.onViewCreated() {

    }

    fun setViewState(){
        viewDataBinding.mainAddress.text = resultHashMap.get("address")
    }

    fun setExpandableListViewAdapter(){
        chapterList = listOf(R.string.warning_example.toString())
        contentList = HashMap()

        val content = resources.getStringArray(R.array.address_array).toList()

        contentList.put(chapterList[0], content)

        listViewAdapter = ExpandableListViewAdapter(requireContext(), chapterList, contentList)
        viewDataBinding.expandListView.setAdapter(listViewAdapter)
    }

    private inner class ExpandableListViewAdapter(private val context : Context, private val chapterList : List<String>, private val contentList : HashMap<String, List<String>>) : BaseExpandableListAdapter() {

        override fun getGroupCount()  = chapterList.size

        override fun getChildrenCount(p0: Int) = this.contentList[this.chapterList[p0]]!!.size

        override fun getGroup(p0: Int) = chapterList[p0]

        override fun getChild(p0: Int, p1: Int) = this.contentList[this.chapterList[p0]]!![p1]

        override fun getGroupId(p0: Int) = p0.toLong()

        override fun getChildId(p0: Int, p1: Int) = p1.toLong()

        override fun hasStableIds() = true

        override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
            var convertView = p2
            val chapterTitle = getGroup(p0) as String

            if(convertView == null){
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.chapterlist, null)
            }

            val chaperTv = convertView!!.findViewById<TextView>(R.id.chapterTv)
            chaperTv.text = chapterTitle

            return convertView
        }

        override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
            var convertView = p3
            val topicTitle = getChild(p0, p1)

            if(convertView == null){
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.contentlist, null)
            }

            val contentTv = convertView!!.findViewById<TextView>(R.id.contentTv)
            contentTv.text = topicTitle

            return convertView
        }

        override fun isChildSelectable(p0: Int, p1: Int) = true

    }
}