package com.dolittle.bejeweled;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class GameView extends View {
	private Context mContext;

	BitmapDrawable bitD;
	Bitmap bitmap;

	public GameView(Context context) {
		super(context);
		mContext = context;
		bitD.createFromStream(mContext.getResources().openRawResource(R.drawable.icon), "icon");

	}

}
