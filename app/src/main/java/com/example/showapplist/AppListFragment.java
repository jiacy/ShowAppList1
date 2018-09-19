package com.example.showapplist;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

public class AppListFragment extends ListFragment {

	private ArrayList<AppInfo> appList = new ArrayList<AppInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getAppList();
		AppAdapter adapter = new AppAdapter(this.getActivity(), appList);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//
		Intent appIntent = appList.get(position).appIntent;
		if (appIntent != null) {
			startActivity(appIntent);
		} else {
			Toast toast = Toast.makeText(getActivity(),"Intent is null", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	/**
	 *
	 */
	private void getAppList() {
		PackageManager pm = this.getActivity().getPackageManager();
		// Return a List of all packages that are installed on the device.
		List<PackageInfo> packages = pm.getInstalledPackages(0);
		for (PackageInfo packageInfo : packages) {
			//
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)	// ��ϵͳӦ��
			{
				AppInfo info = new AppInfo();
				info.appName = packageInfo.applicationInfo.loadLabel(pm)
						.toString();
				info.pkgName = packageInfo.packageName;
				info.appIcon = packageInfo.applicationInfo.loadIcon(pm);
				//
				info.appIntent = pm.getLaunchIntentForPackage(packageInfo.packageName); 
				appList.add(info);
			} else {
				//
			}

		}
	}
}
