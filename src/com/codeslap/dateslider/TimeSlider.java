/*
 * Copyright (C) 2011 Daniel Berndt - Codeus Ltd  -  DateSlider
 * 
 * DateSlider which allows for an easy selection of a time if you only
 * want to offer certain minute intervals take a look at DateTimeSlider 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeslap.dateslider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout.LayoutParams;

import java.util.Calendar;

public class TimeSlider extends DateSlider {

    public TimeSlider(Context context, OnDateSetListener l, Calendar calendar) {
        super(context, null, l, calendar);
    }
    
    public TimeSlider(Context context, String title, OnDateSetListener l, Calendar calendar) {
        super(context, title, l, calendar);
    }

    /**
     * Create the hour and the minutescroller and feed them with their labelers
     * and place them on the layout.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // this needs to be called before everything else to set up the main layout of the DateSlider
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

        // create the hour scroller and assign its labeler and add it to the layout
        ScrollLayout mHourScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
        mHourScroller.setLabeler(new HourLabeler(this), mTime.getTimeInMillis(), 90, 60);
        addSlider(mHourScroller, 0, lp);
        mScrollerList.add(mHourScroller);

        // create the minute scroller and assign its labeler and add it to the layout
        ScrollLayout mMinuteScroller = (ScrollLayout) inflater.inflate(R.layout.scroller, null);
        mMinuteScroller.setLabeler(new MinuteLabeler(this), mTime.getTimeInMillis(), 45, 60);
        addSlider(mMinuteScroller, 1, lp);
        mScrollerList.add(mMinuteScroller);

        // this method _has_ to be called to set the onScrollListeners for all the Scrollers
        // in the mScrollerList.
        setListeners();
    }

    /**
     * define our own title of the dialog
     */
    @Override
    protected void setTitle() {
       setTitle(String.format("Selected Time: %tR", mTime));
    }

}
