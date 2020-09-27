package com.haag.news_app.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.haag.news_app.R
import com.haag.news_app.model.Article
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article_details.*


@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private val vm: ArticleViewModel by viewModels()
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.app_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.articleArgs

        vm.getArticleDetails(article).observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })
    }

    private fun updateUi(article: Article) {
        articleDetailHeadline.text = article.title
        articleDetailcontent.text = article.description
        articleDetailUrl.text = getString(R.string.go_to_article_web)
        Picasso.get().load(article.urlToImage).placeholder(R.drawable.default_image)
            .into(articleDetailImage)

        articleDetailUrl.setOnClickListener {
            vm.openWebView(requireContext(), article.url)
        }
    }
}