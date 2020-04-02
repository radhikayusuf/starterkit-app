package id.radhika.app.home

import android.view.View
import id.radhika.app.databinding.FragmentHomeBinding
import id.radhika.lib.mvvm.BaseFragment

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeScreen : BaseFragment<FragmentHomeBinding, HomeVM, HomeDao>(FragmentHomeBinding::inflate),
    View.OnClickListener {

    override fun getViewModel() = HomeVM::class.java

    override fun onViewReady() {
        binding.buttonDec.setOnClickListener(this)
        binding.buttonInc.setOnClickListener(this)
    }

    override fun render() = { data: HomeDao ->
        binding.textContent.text = data.count.toString()
    }

    override fun onClick(v: View?) {
        vm.calculate(v?.id == binding.buttonInc.id)
    }
}