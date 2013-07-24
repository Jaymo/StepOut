package com.stepout.android.models;

public class mainmenu {

	int mm_id;
	String mm_image_url;
	String mm_name;
	String mm_includes;
	String mm_rating;

	public mainmenu() {
     //Empty Constructor
	}

	public mainmenu(int mm_id, String mm_image_url, String mm_name,
			String mm_includes, String mm_rating) {
		this.mm_id = mm_id;
		this.mm_image_url = mm_image_url;
		this.mm_name = mm_name;
		this.mm_includes = mm_includes;
		this.mm_rating = mm_rating;
	}

	public mainmenu(String mm_image_url, String mm_name, String mm_includes,
			String mm_rating) {

		this.mm_image_url = mm_image_url;
		this.mm_name = mm_name;
		this.mm_includes = mm_includes;
		this.mm_rating = mm_rating;
	}

	public int getmm_id(int id) {
		return this.mm_id;
	} 

	public String getmm_image_url(String url) {
		return this.mm_image_url;
	}

	public String getmm_name(String name) {
		return this.mm_name;
	}

	public String mm_includes(String includes) {
		return this.mm_includes;
	}

	public String mm_rating(String rating ) {
		return this.mm_rating;
	}

}
