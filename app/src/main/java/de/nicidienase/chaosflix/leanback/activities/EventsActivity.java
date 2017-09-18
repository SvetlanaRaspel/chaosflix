package de.nicidienase.chaosflix.leanback.activities;

import android.os.Bundle;

import de.nicidienase.chaosflix.R;
import de.nicidienase.chaosflix.shared.ChaosflixBaseActivity;

public class EventsActivity extends ChaosflixBaseActivity {

	public static final String CONFERENCE_ID = "conference_id";
	public static final String CONFERENCE = "conference";
	public static final String SHARED_ELEMENT_NAME = "shared_element";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO determin if we should use a Browse or a Grid layout
		setContentView(R.layout.activity_events_browse);
	}
}
