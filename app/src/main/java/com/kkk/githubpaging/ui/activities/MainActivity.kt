package com.kkk.githubpaging.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.i
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kkk.githubpaging.R
import com.kkk.githubpaging.data.vo.GithubUIModel
import com.kkk.githubpaging.ui.adapters.RepoListAdapter
import com.kkk.githubpaging.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),KodeinAware{
    override val kodein: Kodein by kodein()

    private val mViewModel: MainViewModel by instance()

    private lateinit var mAdapter:RepoListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = RepoListAdapter()
        rvRepo.apply {
            layoutManager = GridLayoutManager(this@MainActivity,1)
            adapter = mAdapter
        }
        edtName.setText("Android MVVM Kotlin")
        mViewModel.loadGithubRepoList(edtName.text.toString(),1,10)
        edtName.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEARCH){
                mViewModel.loadGithubRepoList(edtName.text.toString(),1,10)
                mAdapter.submitList(null)
                 true
            }else{
                 false
            }
        }
        edtName.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.ACTION_DOWN || keyCode == KeyEvent.KEYCODE_ENTER){
                mViewModel.loadGithubRepoList(edtName.text.toString(),1,10)
                mAdapter.submitList(null)
                true
            }else{
                false
            }
        }

//        mViewModel.uiData.observe(this, Observer<GithubUIModel> { it ->
//            it.error.let{
//                Toast.makeText(this,"Loading Failed",Toast.LENGTH_SHORT).show()
//            }
//            it.list.let {repoList->
//                mAdapter.submitList(repoList)
//            }
//        })
//        mViewModel.pagedList.observe(this, Observer {
//            mAdapter.submitList(it)
//        })
//        mViewModel.errorMessage.observe(this, Observer {
//            Toast.makeText(this,"Loading Failed",Toast.LENGTH_SHORT).show()
//        })
        mViewModel.getGithubRepoList().observe(this, Observer { liveData ->
            liveData.observe(this, Observer {
                mAdapter.submitList(it)
            })
        })
        mViewModel.getErrorMessage().observe(this, Observer {
            Toast.makeText(this,"Loading Failed",Toast.LENGTH_SHORT).show()
        })
    }
}
