package com.connectapp.user.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.connectapp.user.R;
import com.connectapp.user.data.Member;
import com.connectapp.user.util.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

@SuppressLint("InflateParams")
@SuppressWarnings("unused")
public class MemberListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<Member> members = new ArrayList<Member>();


	public MemberListAdapter(Context mContext, ArrayList<Member> members) {
		this.mContext = mContext;
		this.members = members;
		mInflater = LayoutInflater.from(mContext);
		Log.d("Member", "member array size: " + members.size());
	}

	@Override
	public int getCount() {
		return members.size();
	}

	@Override
	public Object getItem(int position) {
		return members.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View hView = convertView;
		if (convertView == null) {
			hView = mInflater.inflate(R.layout.list_item_mem_dir_member, null);
			ViewHolder holder = new ViewHolder();
			holder.memberName = (TextView) hView.findViewById(R.id.tv_item);
			holder.memberIcon = (ImageView) hView.findViewById(R.id.iv_profile_thumb);

			hView.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) hView.getTag();
		holder.memberName.setText(members.get(position).name);
		ImageUtil.displayRoundImage(holder.memberIcon, members.get(position).picUrl, null);

		return hView;
	}


	class ViewHolder {
		TextView memberName;
		ImageView memberIcon;

	}
}
