package phyllis.roading.slidermenudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.sharesdk.onekeyshare.ShareAllGird;
import cn.sharesdk.onekeyshare.SharePage;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class ColorMenuFragment extends ListFragment {
	
	private SlidingMenu menu;

	public ColorMenuFragment(SlidingMenu menu){
		this.menu=menu;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.color_names);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, colors);
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new ColorFragment(R.color.red);
			break;
		case 1:
			newContent = new ColorFragment(R.color.green);
			break;
		case 2:
			newContent = new ColorFragment(R.color.blue);
			break;
		case 3:
			newContent = new ColorFragment(android.R.color.white);
			break;
		case 4:
			newContent = new ColorFragment(android.R.color.black);
			break;
		case 5:
			showGrid(false);
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	private void showGrid(boolean silent) {
		Intent i = new Intent(menu.getContext(), ShareAllGird.class);
		// 分享时Notification的图标
		i.putExtra("notif_icon", R.drawable.ic_launcher);
		// 分享时Notification的标题
		i.putExtra("notif_title", menu.getContext().getString(R.string.app_name));

		// address是接收人地址，仅在信息和邮件使用，否则可以不提供
		i.putExtra("address", "12345678901");
		// title标题，在印象笔记、邮箱、信息、微信（包括好友和朋友圈）、人人网和QQ空间使用，否则可以不提供
		i.putExtra("title", menu.getContext().getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
		i.putExtra("titleUrl", "http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		i.putExtra("text", menu.getContext().getString(R.string.share_content));
		// imagePath是本地的图片路径，除Linked-In外的所有平台都支持这个字段
		//i.putExtra("imagePath", MainActivity.TEST_IMAGE);
		// imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段
		//i.putExtra("imageUrl", "http://img.appgo.cn/imgs/sharesdk/content/2013/06/13/1371120300254.jpg");
		// url仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		i.putExtra("url", "http://sharesdk.cn");
		// thumbPath是缩略图的本地路径，仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		//i.putExtra("thumbPath", MainActivity.TEST_IMAGE);
		// appPath是待分享应用程序的本地路劲，仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		//i.putExtra("appPath", MainActivity.TEST_IMAGE);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
		i.putExtra("comment", menu.getContext().getString(R.string.share));
		// site是分享此内容的网站名称，仅在QQ空间使用，否则可以不提供
		i.putExtra("site", menu.getContext().getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用，否则可以不提供
		i.putExtra("siteUrl", "http://sharesdk.cn");

		// foursquare分享时的地方名
		i.putExtra("venueName", "Southeast in China");
		// foursquare分享时的地方描述
		i.putExtra("venueDescription", "This is a beautiful place!");
		// foursquare分享时的地方纬度
		i.putExtra("latitude", 23.122619f);
		// foursquare分享时的地方经度
		i.putExtra("longitude", 113.372338f);

		// 是否直接分享
		i.putExtra("silent", silent);
		// 设置自定义的外部回调
		i.putExtra("callback", OneKeyShareCallback.class.getName());
		menu.getContext().startActivity(i);
	}


	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof FragmentChangeActivity) {
			FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
			fca.switchContent(fragment);
		} 
	}
}
