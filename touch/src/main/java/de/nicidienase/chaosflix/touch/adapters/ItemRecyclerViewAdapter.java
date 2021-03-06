package de.nicidienase.chaosflix.touch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.nicidienase.chaosflix.R;

public abstract class ItemRecyclerViewAdapter<T> extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

	protected List<T> mItems;

	public ItemRecyclerViewAdapter(){
		mItems = new ArrayList<>();
	}

	public ItemRecyclerViewAdapter(List<T> items) {
		mItems = items;
	}

	public void setItems(List<T> items){
		mItems = items;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(getLayout(), parent, false);
		return new ViewHolder(view);
	}

	abstract int getLayout();


	@Override
	public int getItemCount() {
		return mItems.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public T mItem;
		public final View mView;
		public final ImageView mIcon;
		public final TextView mTitleText;
		public final TextView mSubtitle;
		public final TextView mTag;

		public ViewHolder(View view) {
			super(view);
			mView = view;
			mIcon = (ImageView) view.findViewById(R.id.imageView);
			mTitleText = (TextView) view.findViewById(R.id.title_text);
			mSubtitle = (TextView) view.findViewById(R.id.acronym_text);
			mTag = (TextView) view.findViewById(R.id.tag_text);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mTitleText.getText() + "'";
		}
	}
}
