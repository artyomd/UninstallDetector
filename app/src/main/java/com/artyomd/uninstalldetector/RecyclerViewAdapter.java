package com.artyomd.uninstalldetector;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artyomd on 11/27/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

	private List<ApplicationInfo> dataSet = new ArrayList<>();
	private Context context;

	public void setDataSet(List<ApplicationInfo> dataSet) {
		this.dataSet = dataSet;
	}

	public RecyclerViewAdapter(Context context) {
		this.context = context.getApplicationContext();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
		return new ViewHolder(context, v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(dataSet.get(position));
	}

	@Override
	public int getItemCount() {
		if (dataSet == null) {
			return 0;
		}
		return dataSet.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView title;
		private TextView packageName;
		private ImageView imageView;
		private Context context;

		public ViewHolder(Context context, CardView view) {
			super(view);
			this.context = context;
			title = (TextView) view.findViewById(R.id.app_name);
			imageView = (ImageView) view.findViewById(R.id.imageView);
			packageName = (TextView) view.findViewById(R.id.package_name);
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = ViewHolder.this.context.getPackageManager().getLaunchIntentForPackage(packageName.getText().toString());
					if (intent != null) {
						ViewHolder.this.context.startActivity(intent);
					}
				}
			});
		}

		public void setData(ApplicationInfo applicationInfo) {
			PackageManager pm = context.getPackageManager();
			title.setText(applicationInfo.loadLabel(pm));
			packageName.setText(applicationInfo.packageName);
			imageView.setImageDrawable(applicationInfo.loadIcon(pm));
		}
	}

}
