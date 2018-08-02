package com.example.glm9637.myapplication.utils;

import com.example.glm9637.myapplication.R;

public class Constants {

    public static class Arguments{
        public static final String CUT_ID = "cut id";
	    public static final String SAVE_INSTANCE_RECYCLERVIEW = "recyclerview instance";
        public static final String RECIPE_ID = "recipe id";
	    public static final String NOTE_ID = "note id";
	    public static final String STEP_ID = "step id";
	    public static String CATEGORY_ID = "category id";
    }

    public static class Ids{
        public static final long CATEGORY_BEEF = 1;
        public static final long CATEGORY_PORK = 2;
        public static final long CATEGORY_POULTY = 3;
        public static final long CATEGORY_FISH = 4;
        public static final long CATEGORY_VEGETABLE = 5;
        public static final long CATEGORY_OTHER = 6;
    }
    
    public static int getIconForCategory(long categoryId){
    	switch ((int) categoryId){
		    case 1:
		    	return R.drawable.ic_beef;
		    case 2:
		    	return R.drawable.ic_pork;
		    case 3:
		    	return R.drawable.ic_poultry;
		    case 4:
		    	return R.drawable.ic_fish;
		    case 5:
		    	return R.drawable.ic_beef;
		    default:
		    	return R.drawable.ic_beef;
	    }
	    
    }

}
