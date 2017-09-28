package com.liuwei.knoweasy.tablayout

import android.animation.Animator
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.liuwei.knoweasy.R
import com.liuwei.knoweasy.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tablayout.*
import rx.Emitter
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by liuwei on 2017/9/13.
 */
class TabLayoutActivity : BaseActivity() {

    val tabBeanList = Arrays.asList(
            TabBean("Movie", R.drawable.header_pic_movie, Color.parseColor("#B41F37"), R.drawable.movie_avatar),
            TabBean("Life", R.drawable.header_pic_life, Color.parseColor("#8FADE1"), R.drawable.lifestyle_avatar),
            TabBean("Read", R.drawable.header_pic_read, Color.parseColor("#D7563A"), R.drawable.read_avatar),
            TabBean("Soccer", R.drawable.header_pic_soccer, Color.parseColor("#DCCCC0"), R.drawable.sport_avatar),
            TabBean("Tech", R.drawable.header_pic_tech, Color.parseColor("#88C5C4"), R.drawable.tech_avatar)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        setToolbar(toolbar)
        setBackable()
        title = ""

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT

        initViewPager()
        tabLayout.setupWithViewPager(viewPager)

        initPage(tabBeanList[0])
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        for ((title) in tabBeanList) {
            adapter.addFragment(TabLayoutFragment(), title)
        }

        viewPager.adapter = adapter

        Observable.create(Action1<Emitter<TabBean>> {
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                    //
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    //
                }

                override fun onPageSelected(position: Int) {
                    it.onNext(tabBeanList[position])
                }
            })
        }, Emitter.BackpressureMode.BUFFER)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    changePage(it)
                })
    }

    private fun initPage(tab: TabBean) {
        collapsingToolbarLayout.setStatusBarScrimColor(tab.color)
        collapsingToolbarLayout.setContentScrimColor(tab.color)
        val foreColor = Color.argb(180, Color.red(tab.color), Color.green(tab.color), Color.blue(tab.color))
        headerFore.setBackgroundColor(foreColor)
        headerScaleAnim.visibility = View.GONE
        headerBg.setImageResource(tab.bgRes)
        centerIcon.setImageResource(tab.iconRes)
    }

    private fun changePage(tab: TabBean) {
        // Change Page has 5 parts:
        // 1. Change Scrim Color
        // 2. Play a ripper animation for foreground translucence mask
        // 3. Replace foreground color with new one
        // 4. Replace background image with new one
        // 5. Replace center icon with three steps: shrink, replace img, expand.

        // 1. Scrim Color
        collapsingToolbarLayout.setStatusBarScrimColor(tab.color)
        collapsingToolbarLayout.setContentScrimColor(tab.color)

        // The foreground transparent
        val foreColor = Color.argb(180, Color.red(tab.color), Color.green(tab.color), Color.blue(tab.color))

        // All animation duration
        val ANIM_DUR = 300L

        // 2 & 3. Animation the foreground translucence mask
        val width = headerFore.width / 2
        val height = headerFore.height / 2
        val radius = Math.sqrt((width * width + height * height).toDouble()).toFloat()
        val foreGrow = ViewAnimationUtils.createCircularReveal(
                headerScaleAnim,
                width,
                height,
                0F,
                radius)
        foreGrow.duration = ANIM_DUR
        val foreTrans = AlphaAnimation(1f, 0f)
        foreTrans.duration = ANIM_DUR

        foreGrow.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                headerFore.setBackgroundColor(foreColor)
                headerScaleAnim.startAnimation(foreTrans)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                headerScaleAnim.visibility = View.VISIBLE
            }
        })

        foreTrans.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                headerScaleAnim.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        headerScaleAnim.setBackgroundColor(tab.color)
        foreGrow.start()

        // 4. background image animation
        Observable.timer(ANIM_DUR, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    headerBg.setImageResource(tab.bgRes)
                })

        // 5. Animation the centerIcon
        val shrink = ScaleAnimation(
                1f, 0f,
                1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        val grow = ScaleAnimation(
                0f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        shrink.duration = ANIM_DUR
        grow.duration = ANIM_DUR

        shrink.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //
            }

            override fun onAnimationEnd(animation: Animation?) {
                centerIcon.setImageResource(tab.iconRes)
                centerIcon.startAnimation(grow)
            }

            override fun onAnimationStart(animation: Animation?) {
                //
            }
        })
        centerIcon.startAnimation(shrink)
    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val mFragment = ArrayList<Fragment>()
        val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragment.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitles[position]
        }

        override fun getItem(position: Int): Fragment {
            return mFragment[position]
        }

        override fun getCount(): Int {
            return mFragment.size
        }

    }
}

data class TabBean(
        val title: String,
        @DrawableRes val bgRes: Int,
        @ColorInt val color: Int,
        @DrawableRes val iconRes: Int
)