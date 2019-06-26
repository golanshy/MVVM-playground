package uk.co.applylogic.playground.mvvm.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uk.co.applylogic.playground.mvvm.R
import uk.co.applylogic.playground.mvvm.viewmodel.MainViewModel
import uk.co.applylogic.playground.mvvm.model.Blog
import uk.co.applylogic.playground.mvvm.viewmodel.BlogRepository
import uk.co.applylogic.playground.mvvm.networking.APIInterface
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.applylogic.playground.mvvm.di.AppComponent
import uk.co.applylogic.playground.mvvm.di.AppModule
import uk.co.applylogic.playground.mvvm.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null

    private var blogAdapter: BlogAdapter? = null

    @Inject lateinit var apiInterface: APIInterface

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        mainViewModel = ViewModelProvider(this@MainActivity, SavedStateVMFactory(this)).get(MainViewModel::class.java)
        mainViewModel?.movieRepository = BlogRepository(apiInterface)

        getPopularBlog()
        swiperefresh.setOnRefreshListener { getPopularBlog() }
    }

    private fun getPopularBlog() {
        swiperefresh?.isRefreshing = false
        mainViewModel?.allBlog?.observe(this, Observer { blogList ->
            prepareRecyclerView(blogList)
        })
    }

    private fun prepareRecyclerView(blogList: List<Blog>?) {

        blogAdapter = BlogAdapter(blogList)
        blogRecyclerView?.layoutManager =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LinearLayoutManager(this)
            } else {
                GridLayoutManager(this, 4)
            }
        blogRecyclerView?.itemAnimator = DefaultItemAnimator()
        blogRecyclerView?.adapter = blogAdapter
    }
}
