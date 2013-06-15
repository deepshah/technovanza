package mumbai.VJTI.Technovanza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class PhotoGallery extends Activity implements OnItemSelectedListener,
		ViewFactory {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.gallery);

		mSwitcher = (ImageSwitcher) findViewById(R.id.imgswitcher);
		mSwitcher.setFactory(this);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		mSwitcher.setImageResource(mImageIds[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
	}

	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}

	private ImageSwitcher mSwitcher;

	public class ImageAdapter extends BaseAdapter {
		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);

			i.setImageResource(mThumbIds[position]);
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			i.setBackgroundResource(R.drawable.picture_frame);
			return i;
		}

		private Context mContext;
	}

	private Integer[] mThumbIds = { R.drawable.thumb1, R.drawable.thumb2,
			R.drawable.thumb3, R.drawable.thumb4, R.drawable.thumb5,R.drawable.taj,R.drawable.vintage,R.drawable.monster,R.drawable.thanecreek,R.drawable.icengine2 };

	private Integer[] mImageIds = { R.drawable.sample1, R.drawable.sample2,
			R.drawable.sample3, R.drawable.sample4, R.drawable.sample1 ,R.drawable.taj,R.drawable.vintage,R.drawable.monster,R.drawable.thanecreek,R.drawable.icengine2};
	
	public void mainpage(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

}
