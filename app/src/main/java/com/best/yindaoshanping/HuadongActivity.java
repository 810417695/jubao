package com.best.yindaoshanping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.best.activity.R;
import com.best.util.BaseActivity;
import com.best.denglu.DengluActivity;

import java.util.ArrayList;
import java.util.List;


public class HuadongActivity extends BaseActivity {

	private JazzyViewPager mJazzy;
	ImageView re;
	ViewPager vp = null;
	List<View> views = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_main);
		vp = (ViewPager) findViewById(R.id.jazzy_pager);


		setupJazziness(JazzyViewPager.TransitionEffect.FlipVertical);

		View v1 = LayoutInflater.from(this).inflate(R.layout.viewpager_1, null);
		View v2 = LayoutInflater.from(this).inflate(R.layout.viewpager_2, null);
		views.add(v1);
		views.add(v2);
		vp.setAdapter(new MainAdapter());
		v2.findViewById(R.id.lijitiyan).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HuadongActivity.this,DengluActivity.class));
				finish();
			}
		});
	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Toggle Fade");
		String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
		for (String effect : effects)
			menu.add(effect);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().toString().equals("Toggle Fade")) {
			mJazzy.setFadeEnabled(!mJazzy.getFadeEnabled());
		} else {
			JazzyViewPager.TransitionEffect effect = JazzyViewPager.TransitionEffect.valueOf(item.getTitle().toString());
			setupJazziness(effect);
		}
		return true;
	}

	private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		mJazzy.setTransitionEffect(effect);
		mJazzy.setAdapter(new MainAdapter());
		mJazzy.setPageMargin(30);
	}

	private class MainAdapter extends PagerAdapter {
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
//			TextView text = new TextView(HuadongActivity.this);
//			text.setGravity(Gravity.CENTER);
//			text.setTextSize(30);
//			text.setTextColor(Color.WHITE);
//			text.setText("Page " + position);
//			text.setPadding(30, 30, 30, 30);
//			int bg = Color.rgb((int) Math.floor(Math.random()*128)+64,
//					(int) Math.floor(Math.random()*128)+64,
//					(int) Math.floor(Math.random()*128)+64);
//			text.setBackgroundColor(bg);
//			container.addView(text, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//			mJazzy.setObjectForPosition(text, position);

//			return text;

//			ImageView imageView = new ImageView(HuadongActivity.this);
//			int[] img = {R.drawable.splash,R.drawable.splash_guide_01,R.drawable.splash_guide_02};
//			imageView.setImageResource(img[position]);
//			container.addView(imageView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//			mJazzy.setObjectForPosition(imageView,position);
//
//			return imageView;
			View v = views.get(position);
			container.addView(v, 0);
			mJazzy.setObjectForPosition(v,position);
			return v;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(mJazzy.findViewFromObject(position));
		}
		@Override
		public int getCount() {
			return views.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}
	}
}
