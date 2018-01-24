package com.liuwei.knoweasy.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;

/**
 * Created by liuwei on 2017/9/13.
 */

public class LottieAnimActivity extends BaseActivity {

	private boolean hasInitAnim = false;
	//	private boolean hasStartAnim = false;
	private LottieAnimationView mLottieAnimationView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lottie_anim);
		setBackable();
		setTitle("Lottie Animation");

		mLottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view_code);
		findViewById(R.id.show_anim_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mLottieAnimationView.isAnimating()) {
					stopAnim();
					((TextView) v).setText("Play");
				} else {
					showAnim();
					((TextView) v).setText("Stop");
				}
//				hasStartAnim = !hasStartAnim;

			}
		});

		findViewById(R.id.testView).setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
	}

	private void showAnim() {
		if (!hasInitAnim) {
			hasInitAnim = true;
			mLottieAnimationView.setAnimation("done.json");
			mLottieAnimationView.loop(true);
			mLottieAnimationView.setScale(0.3f);
			mLottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animation) {
					Log.i("6v", "onAnimationStart: ");
				}

				@Override
				public void onAnimationEnd(Animator animation) {
					Log.i("6v", "onAnimationEnd: ");
				}

				@Override
				public void onAnimationCancel(Animator animation) {
					Log.i("6v", "onAnimationCancel: ");
				}

				@Override
				public void onAnimationRepeat(Animator animation) {
					Log.i("6v", "onAnimationRepeat: ");
				}
			});

		}
		mLottieAnimationView.playAnimation();
	}

	private void stopAnim() {
		if (hasInitAnim) {
			mLottieAnimationView.cancelAnimation();
		}
	}
}
