package id.radhika.feature.dummy.screen.home

import android.view.View
import id.radhika.feature.dummy.databinding.FragmentHomeBinding
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeScreen : BaseScreen<FragmentHomeBinding, HomeVM, HomeDao>(FragmentHomeBinding::inflate),
    View.OnClickListener {

    override fun getViewModel() = HomeVM::class.java

    override fun onViewReady() {
        binding.buttonDec.setOnClickListener(this)
        binding.buttonInc.setOnClickListener(this)
    }

    override fun render() = { data: HomeDao ->
        binding.progressIndicator.visibility = if (data.isLoading) View.VISIBLE else View.GONE
        binding.textContent.text = data.count.toString()
        binding.nameOfUser.text = data.name
    }

    override fun onClick(v: View?) {
        vm.calculate(v?.id == binding.buttonInc.id)
    }
}