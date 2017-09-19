package de.nicidienase.chaosflix.touch.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.nicidienase.chaosflix.R;
import de.nicidienase.chaosflix.common.entities.recording.Conference;
import de.nicidienase.chaosflix.common.entities.recording.Event;
import de.nicidienase.chaosflix.touch.adapters.EventRecyclerViewAdapter;

public class EventsFragment extends Fragment {


	private static final String ARG_COLUMN_COUNT = "column-count";
	private static final String ARG_CONFERENCE = "conference";
	private int mColumnCount = 1;
	private OnEventsListFragmentInteractionListener mListener;
	private Conference mConference;
	private CharSequence mPreviousTitle;
	private ActionBar mActionBar;

	public EventsFragment() {
	}

	public static EventsFragment newInstance(Conference conference, int columnCount) {
		EventsFragment fragment = new EventsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		args.putParcelable(ARG_CONFERENCE, conference);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
			mConference = getArguments().getParcelable(ARG_CONFERENCE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
		mActionBar.setTitle(mConference.getTitle());
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}

			recyclerView.setAdapter(new EventRecyclerViewAdapter(mConference, mListener) {
			});
		}
		return view;
	}


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mActionBar = ((AppCompatActivity) context).getSupportActionBar();
		mPreviousTitle = mActionBar.getTitle();
		if (context instanceof OnEventsListFragmentInteractionListener) {
			mListener = (OnEventsListFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnListFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		mActionBar.setTitle(mPreviousTitle);
	}

	public interface OnEventsListFragmentInteractionListener{
		void onEventSelected(Event event, View v);
	}

}
