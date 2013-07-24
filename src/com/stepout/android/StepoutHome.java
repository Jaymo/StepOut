package com.stepout.android;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class StepoutHome extends SherlockFragmentActivity {
	public static int THEME = R.style.Theme_Sherlock_Light;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getResources().getBoolean(R.bool.has_two_panes)) {
            finish();
            return;
        }
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setIcon(R.drawable.ic_launcher);
        ActionBar.Tab tab1 = bar.newTab();
        ActionBar.Tab tab2 = bar.newTab();
        ActionBar.Tab tab3 = bar.newTab();
        ActionBar.Tab tab4 = bar.newTab();
        bar.setTitle("StepOut");
        tab1.setText("MAIN MENU");
        tab2.setText("AROUND ME");
        tab3.setText("EVENTS");
        tab4.setText("MORE");
        tab1.setTabListener(new StepoutTabListner());
        tab2.setTabListener(new StepoutTabListner());
        tab3.setTabListener(new StepoutTabListner());
        tab4.setTabListener(new StepoutTabListner());
        bar.addTab(tab1);
        bar.addTab(tab2);
        bar.addTab(tab3);
        bar.addTab(tab4);
		
		
	}
	
		private class StepoutTabListner implements ActionBar.TabListener
	    {

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if(tab.getPosition()==0)
			{
				FragmentA fragA = new FragmentA();
				ft.replace(android.R.id.content, fragA);
				invalidateOptionsMenu();
				
			}
			if(tab.getPosition()==1)
			{
				FragmentB fragB = new FragmentB();
				ft.replace(android.R.id.content, fragB);
				invalidateOptionsMenu();
			}
			if(tab.getPosition()==2)
			{
				FragmentC fragC = new FragmentC();
				ft.replace(android.R.id.content, fragC);
				invalidateOptionsMenu();
			}
			if(tab.getPosition()==3)
			{
				FragmentD fragD = new FragmentD();
				ft.replace(android.R.id.content, fragD);
				invalidateOptionsMenu();
			}
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		
	}

	
}
