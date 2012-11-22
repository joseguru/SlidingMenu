package com.slidingmenu.example;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.slidingmenu.lib.SlidingMenu;

public class PropertiesActivity extends BaseActivity {

	public PropertiesActivity() {
		super(R.string.properties);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setSlidingActionBarEnabled(true);

		setContentView(R.layout.properties);
		
		// left and right modes
		Button left = (Button) findViewById(R.id.left);
		Button right = (Button) findViewById(R.id.right);
		left.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				getSlidingMenu().setMode(SlidingMenu.LEFT);
				getSlidingMenu().setShadowDrawable(R.drawable.defaultshadow);
			}
		});
		right.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				getSlidingMenu().setMode(SlidingMenu.RIGHT);
				getSlidingMenu().setShadowDrawable(R.drawable.defaultshadowright);
			}
		});

		// touch mode stuff
		RadioGroup touchAbove = (RadioGroup) findViewById(R.id.touch_above);
		touchAbove.check(R.id.touch_above_full);
		touchAbove.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.touch_above_full:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				case R.id.touch_above_margin:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					break;
				case R.id.touch_above_none:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}
		});

		// scroll scale stuff
		SeekBar scrollScale = (SeekBar) findViewById(R.id.scroll_scale);
		scrollScale.setMax(1000);
		scrollScale.setProgress(333);
		scrollScale.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) { }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getSlidingMenu().setBehindScrollScale((float) seekBar.getProgress()/seekBar.getMax());
			}
		});


		// behind width stuff
		SeekBar behindWidth = (SeekBar) findViewById(R.id.behind_width);
		behindWidth.setMax(1000);
		behindWidth.setProgress(750);
		behindWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) { }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress()/seekBar.getMax();
				getSlidingMenu().setBehindWidth((int) (percent * getSlidingMenu().getWidth()));
				getSlidingMenu().requestLayout();
			}
		});

		// shadow stuff
		CheckBox shadowEnabled = (CheckBox) findViewById(R.id.shadow_enabled);
		shadowEnabled.setChecked(true);
		shadowEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					getSlidingMenu().setShadowDrawable(R.drawable.defaultshadow);
				else
					getSlidingMenu().setShadowDrawable(null);
			}
		});
		SeekBar shadowWidth = (SeekBar) findViewById(R.id.shadow_width);
		shadowWidth.setMax(1000);
		shadowWidth.setProgress(75);
		shadowWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) { }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress()/ (float) seekBar.getMax();
				int width = (int) (percent * (float) getSlidingMenu().getWidth());
				getSlidingMenu().setShadowWidth(width);
				getSlidingMenu().invalidate();
			}
		});

		// fading stuff
		CheckBox fadeEnabled = (CheckBox) findViewById(R.id.fade_enabled);
		fadeEnabled.setChecked(true);
		fadeEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getSlidingMenu().setFadeEnabled(isChecked);
			}			
		});
		SeekBar fadeDeg = (SeekBar) findViewById(R.id.fade_degree);
		fadeDeg.setMax(1000);
		fadeDeg.setProgress(666);
		fadeDeg.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) { }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getSlidingMenu().setFadeDegree((float) seekBar.getProgress()/seekBar.getMax());
			}			
		});
	}

}
