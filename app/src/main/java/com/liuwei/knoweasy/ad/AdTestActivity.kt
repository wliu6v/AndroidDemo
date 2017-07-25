package com.liuwei.knoweasy.ad

import android.content.ComponentCallbacks
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.liuwei.knoweasy.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AdTestActivity : BaseActivity() {

    enum class AdSize(val size: String) {
        SIZE_320_50("320x50"),
        SIZE_300_250("300x250"),
        SIZE_320_100("320x100"),
        SIZE_LARGE("large"),
        SIZE_SMALL("small")
    }

    enum class AdType(val type: String) {
        DFP_BANNER("DFP Banner"),
        ADMOB_BANNER("Admob Banner"),
        FLURRY_NATIVE("Flurry Native"),
        DFP_NATIVE("DFP Native"),
        DFP_MRAID("DFP Mraid"),
        FB("Facebook Native")
    }

    var mAdBody: String = ""
    lateinit var mAdType: AdType
    lateinit var mAdSize: AdSize

    lateinit var view: UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = UI()
        view.setContentView(this@AdTestActivity)
        setBackable()
        initView()
    }

    fun initView() {

        // init ad size
        val sizeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, AdSize.values())
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.adSizeList.adapter = sizeAdapter
        view.adSizeList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // From https://stackoverflow.com/questions/36761478/cannot-set-onitemclicklistener-for-spinner-in-android
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mAdSize = AdSize.values()[position]
            }
        }

        // init ad type
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, AdType.values())
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.adTypeList.adapter = typeAdapter
        view.adTypeList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mAdType = AdType.values()[position]
            }
        }

        view.adLoadBtn.onClick {
            mAdBody = view.adInput.text.toString().trim()
            view.adLoadStatusTv.text = "Start Loading"
            view.adContainer.removeAllViews()
            loadAd()
        }
    }

    fun addMsg(msg: String) {
        view.adLoadStatusTv.text = "${view.adLoadStatusTv.text}\n$msg"
    }

    // region ---- load ad ----

    fun loadAd() {
        when (mAdType) {
            AdType.DFP_BANNER -> loadDfpBannerAd()
        }
    }

    fun loadDfpBannerAd() {
        val adView = PublisherAdView(this)
        adView.setAdSizes(newAdSize(mAdSize))
        adView.adUnitId = mAdBody

        var adBuilder = PublisherAdRequest.Builder()
        adView.adListener = object : AdListener() {
            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
                addMsg("onAdLeft")
            }

            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                addMsg("onAdFailed")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                addMsg("onAdClosed")
            }

            override fun onAdOpened() {
                super.onAdOpened()
                addMsg("onAdOpened")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                addMsg("onAdLoaded")
            }
        }
        adView.loadAd(adBuilder.build())
        view.adContainer.addView(adView)
    }

    fun newAdSize(size: AdSize): com.google.android.gms.ads.AdSize {
        when (size) {
            AdSize.SIZE_320_50 -> return com.google.android.gms.ads.AdSize(320, 50)
            AdSize.SIZE_320_100 -> return com.google.android.gms.ads.AdSize(320, 100)
            AdSize.SIZE_300_250 -> return com.google.android.gms.ads.AdSize(300, 250)
            else -> return com.google.android.gms.ads.AdSize(320, 50)
        }
    }

    // endregion

    inner class UI : AnkoComponent<AdTestActivity> {
        lateinit var adSelectList: Spinner
        lateinit var adInput: EditText
        lateinit var adTypeList: Spinner
        lateinit var adSizeList: Spinner
        lateinit var adLoadBtn: Button
        lateinit var adLoadStatusTv: TextView
        lateinit var adContainer: ViewGroup

        override fun createView(ui: AnkoContext<AdTestActivity>): View {
            return with(ui) {
                verticalLayout {
                    textView("Ad load history") {
                        textSize = dip(6).toFloat()
                    }.lparams {
                        margin = dip(10)
                    }

                    adSelectList = spinner().lparams {
                        width = matchParent
                        margin = dip(10)
                    }

                    adInput = editText {
                        hint = "Input your ad id"
                    }.lparams {
                        width = matchParent
                        margin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        textView("Ad type") {
                            textSize = dip(6).toFloat()
                        }.lparams {
                            margin = dip(10)
                        }

                        adTypeList = spinner().lparams {
                            width = matchParent
                            margin = dip(10)
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        textView("Ad size") {
                            textSize = dip(6).toFloat()
                        }.lparams {
                            margin = dip(10)
                        }

                        adSizeList = spinner().lparams {
                            width = matchParent
                            margin = dip(10)
                        }
                    }

                    adLoadBtn = button("Load") {
                        textSize = dip(6).toFloat()
                    }.lparams {
                        width = matchParent
                        verticalMargin = dip(12)
                        horizontalMargin = dip(36)
                    }

                    adLoadStatusTv = textView("Ad Load Status") {
                        textSize = dip(6).toFloat()
                    }.lparams {
                        margin = dip(10)
                    }

                    adContainer = frameLayout().lparams {
                        margin = dip(10)
                    }
                }
            }
        }
    }
}