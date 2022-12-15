package com.cyn.mt.nested

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyn.mt.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tabLayout
import kotlinx.android.synthetic.main.activity_main.titleBar
import kotlinx.android.synthetic.main.activity_main.viewPager
import kotlinx.android.synthetic.main.activity_nested.*
import kotlinx.android.synthetic.main.fragment_test.view.*

class NestedActivity : AppCompatActivity() {

    //屏幕宽
    var screenWidth = 0

    //屏幕高
    var screenHeight = 0

    //tabLayout的文本和图片
    private val tabTextData = arrayOf("常用药品", "夜间送药", "隐形眼镜", "成人用品", "医疗器械", "全部商家")
    private val tabIconData = arrayOf(
        R.mipmap.tab_icon,
        R.mipmap.tab_icon,
        R.mipmap.tab_icon,
        R.mipmap.tab_icon,
        R.mipmap.tab_icon,
        R.mipmap.tab_icon
    )
    private var fragmentData = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested)
        initView()
        initData()
    }


    private fun initView() {
        tv.showSnackBar("")
        //获取屏幕宽高
        val resources: Resources = this.resources
        val dm: DisplayMetrics = resources.displayMetrics
        screenWidth = dm.widthPixels
        screenHeight = dm.heightPixels

        //状态栏沉浸
        StatusBarUtil.immersive(this)

        //titleBar填充
        StatusBarUtil.setPaddingSmart(this, titleBar)

        //状态栏字体颜色设置为黑色
        StatusBarUtil.darkMode(this)

        //动态设置ViewPager高度
        rcData.post {
            val layoutParams = rcData.layoutParams
            layoutParams.width = screenWidth
            layoutParams.height = mNestedScrollView.height - tv.height
            rcData.layoutParams = layoutParams
        }
        mNestedScrollView.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                Log.i("TAG", "onScrollChange: $scrollX $scrollY $oldScrollX $oldScrollY")
                if (scrollY >= tv1.height && (!(tv1.isVisible))) {
                    tv1.visibility = View.VISIBLE
//                    mNestedScrollView.setMaxScrollY(rcTop.height + tv1.height)
                }

                if (scrollY < tv1.height && tv1.isVisible) {
                    tv1.visibility = View.GONE
//                    mNestedScrollView.setMaxScrollY(rcTop.height + tv1.height)
                }
            }

        })
    }


//    private inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
//        val intent = Intent(context, T::class.java)
//        intent.block()
//        context.startActivity(intent)
//    }
//
//    private inline fun <reified T> startActivity(context: Context) {
//        val intent = Intent(context, T::class.java)
//        context.startActivity(intent)
//    }

    private fun initData() {

        rcTop.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcTop.adapter = RcAdapter(this, 2)

        rcData.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcData.adapter = RcAdapter(this, 30)


//        //添加TabLayout
//        for (i in tabTextData.indices) {
//            tabLayout.addTab(tabLayout.newTab())
//            tabLayout.getTabAt(i)!!.setText(tabTextData[i]).setIcon(tabIconData[i])
//
//            //添加Fragment
//            fragmentData.add(TestFragment.newInstance(tabTextData[i]))
//        }
//
//        //Fragment ViewPager
//        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragmentData)
//
//        //TabLayout关联ViewPager
//        tabLayout.setupWithViewPager(viewPager)
//
//        //设置TabLayout数据
//        for (i in tabTextData.indices) {
//            tabLayout.getTabAt(i)!!.setText(tabTextData[i]).setIcon(tabIconData[i])
//        }

        //设置最大滑动距离
        rcTop.post {
            mNestedScrollView.setMaxScrollY(rcTop.height)
        }
    }
}