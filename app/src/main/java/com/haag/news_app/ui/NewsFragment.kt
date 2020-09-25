package com.haag.news_app.ui

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.haag.news_app.R
import com.haag.news_app.model.Article
import com.haag.news_app.ui.articlesRecyclerView.ArticlesAdapter
import com.haag.news_app.ui.articlesRecyclerView.ArticlesAdapter.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment(), OnItemClickListener {

    private val vm: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        vm.userData.observe(viewLifecycleOwner, Observer {
            newsRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ArticlesAdapter(it.articles, context, this@NewsFragment)
            }
        })
    }

    override fun onItemChatClick(article: Article) {
        d(",,", "click on article: ${article.title}")
        vm.navigateTo(requireView(), R.id.action_newsFragment_to_articleDetailsFragment)
    }


}