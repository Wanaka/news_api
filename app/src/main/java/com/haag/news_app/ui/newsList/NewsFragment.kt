package com.haag.news_app.ui.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.haag.news_app.R
import com.haag.news_app.room.Read
import com.haag.news_app.ui.newsList.articlesRecyclerView.ArticlesAdapter
import com.haag.news_app.ui.newsList.articlesRecyclerView.ArticlesAdapter.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*


@AndroidEntryPoint
class NewsFragment : Fragment(), OnItemClickListener {

    private val vm: NewsViewModel by viewModels()

    private val titleList = mutableListOf<Read>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.app_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getTitleList().observe(viewLifecycleOwner, Observer { it ->
            it.forEach {
                titleList.add(it)
            }
        })

        vm.getArticlesList.observe(viewLifecycleOwner, Observer {
            newsRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ArticlesAdapter(it.articles, titleList, context, this@NewsFragment)
            }
            newsRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        })
    }

    override fun onItemChatClick(pos: Int, title: Read) {
        vm.insertTitleDao(title)
        val action =
            NewsFragmentDirections.actionNewsFragmentToArticleDetailsFragment(
                pos
            )
        vm.navigateTo(requireView(), action)
    }
}