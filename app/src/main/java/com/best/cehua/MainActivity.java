package com.best.cehua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;


import com.best.activity.BianjidpActvity;
import com.best.activity.MydizhiActivity;
import com.best.activity.MyqianbaoActivity;
import com.best.activity.R;
import com.best.activity.XiaoxiActivity;
import com.best.activity.YouhuijuanActivity;
import com.best.util.BaseActivity;
import com.best.fram.Dianpu;
import com.best.fram.DingDanFragment;
import com.best.fram.Dongtai;
import com.best.fram.ShangPinFragment;
import com.best.yanzheng.RenzhengActvity;

import java.util.Random;

public class MainActivity extends BaseActivity implements View.OnClickListener {

	private DragLayout dl;
	private ListView lv;
	FragmentManager fm;
	RadioButton dianpu,dongtai,dingdan,shangpin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dianpu = (RadioButton) findViewById(R.id.dianpu);
		dongtai = (RadioButton) findViewById(R.id.dongtai);
		dingdan = (RadioButton) findViewById(R.id.dingdan);
		shangpin = (RadioButton) findViewById(R.id.shangpin);

		dianpu.setOnClickListener(this);
		dongtai.setOnClickListener(this);
		shangpin.setOnClickListener(this);
		dingdan.setOnClickListener(this);
		dianpu.setChecked(true);

		fm = getSupportFragmentManager();
		if(savedInstanceState==null){
			FragmentTransaction ftt=fm.beginTransaction();
			Dianpu dp=new Dianpu();
			ftt.add(R.id.jiemian,dp,"dianpu");
			ftt.commit();
		}
//		Util.initImageLoader(this); 注释之后没有变化
		//滑动时activity变小
		initDragLayout();
		//注释之后运行出错
		initView();
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.item_text, new String[] {  "我的钱包",
				"消息中心", "我的地址",
				"意见反馈",
				"关于我们", "检查更新"}));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
									int position, long arg3) {
				switch (position){
					case 0:
						Intent intent = new Intent(MainActivity.this, MyqianbaoActivity.class);
						startActivity(intent);
						break;
					case 1:
						Intent intent1 = new Intent(MainActivity.this, XiaoxiActivity.class);
						startActivity(intent1);
						break;
					case 2:
						Intent intent2 = new Intent(MainActivity.this, MydizhiActivity.class);
						startActivity(intent2);
						break;
				}
			}
		});
	}

	public void xiaoxi(View view){
		startActivity(new Intent(MainActivity.this,XiaoxiActivity.class));
	}

	private void initDragLayout() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setDragListener(new DragLayout.DragListener() {
			@Override
			public void onOpen() {
				lv.smoothScrollToPosition(new Random().nextInt(30));
			}

			@Override
			public void onClose() {

			}

			@Override
			public void onDrag(float percent) {

			}
		});
	}
	public void bianjidianpu(View view){
		startActivity(new Intent(MainActivity.this, BianjidpActvity.class));
	}
	public void youhuijuan(View view){
		startActivity(new Intent(MainActivity.this, YouhuijuanActivity.class));
	}
	public void dianzhangriji(View view){
//        startActivity(new Intent(getActivity(),));
	}
	public void renzhengziliao(View view){
		startActivity(new Intent(MainActivity.this, RenzhengActvity.class));
	}
	public void yunfeishezhi(View view){

	}

	@Override
	public void initView() {


	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		//开启一个事务
		FragmentTransaction ftt = fm.beginTransaction();
		if(fm.findFragmentByTag("dianpu")!=null){
			ftt.hide(fm.findFragmentByTag("dianpu"));
		}
		if(fm.findFragmentByTag("shangpin")!=null){
			ftt.hide(fm.findFragmentByTag("shangpin"));
		}
		if(fm.findFragmentByTag("dingdan")!=null){
			ftt.hide(fm.findFragmentByTag("dingdan"));
		}
		if(fm.findFragmentByTag("dongtai")!=null){
			ftt.hide(fm.findFragmentByTag("dongtai"));
		}

		int id = v.getId();
		if(id== R.id.dianpu) {
			if(fm.findFragmentByTag("dianpu")!=null){
				ftt.show(fm.findFragmentByTag("dianpu"));
			}else{
				Dianpu bf = new Dianpu();
				//add(父布局ID，Fragment,Tag);
				ftt.add(R.id.jiemian,bf,"dianpu");

			}

		}else if(id == R.id.dongtai){
			if(fm.findFragmentByTag("dongtai")!=null){
				ftt.show(fm.findFragmentByTag("dongtai"));
			}else{
				Dongtai pf = new Dongtai();
				ftt.add(R.id.jiemian, pf, "dongtai");

			}

		}else if(id==R.id.dingdan){
			if(fm.findFragmentByTag("dingdan")!=null){
				ftt.show(fm.findFragmentByTag("dingdan"));
			}else {
				DingDanFragment af = new DingDanFragment();
				ftt.add(R.id.jiemian, af, "dingdan");

			}
		}else if(id==R.id.shangpin){
			if(fm.findFragmentByTag("shangpin")!=null){
				ftt.show(fm.findFragmentByTag("shangpin"));
			}
			else {
				ShangPinFragment ff = new ShangPinFragment();
				ftt.add(R.id.jiemian, ff, "shangpin");
			}
		}
		ftt.commit();
	}
}
