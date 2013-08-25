package phyllis.roading.slidermenudemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.sharesdk.framework.AbstractWeibo;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class FragmentChangeActivity extends BaseActivity {

	private Fragment mContent;
	private SlidingMenu menu;
	public static String TEST_IMAGE;

	public FragmentChangeActivity() {
		super(R.string.changing_fragments);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		menu = new SlidingMenu(this);

		// set the Above View
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new ColorFragment(R.color.yellow);

		// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new ColorMenuFragment(menu)).commit();

		// customize the SlidingMenu
		// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		AbstractWeibo.initSDK(this);
		// getActionBar().setDisplayShowTitleEnabled(false);
//		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
//				ActionBar.LayoutParams.MATCH_PARENT,
//				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
//		View viewTitleBar = getLayoutInflater().inflate(R.layout.activity_main,
//				null);
//		getActionBar().setCustomView(viewTitleBar, lp);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//		getActionBar().setDisplayShowCustomEnabled(true);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}
}
