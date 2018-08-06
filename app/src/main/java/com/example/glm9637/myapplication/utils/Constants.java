package com.example.glm9637.myapplication.utils;

import com.example.glm9637.myapplication.R;

public class Constants {

	public static final String NOTIFICATION_CHANNEL_ID = "timer";

	public static final String CUT_NAME_RUB = "Rubs";

	public static int getIconForCategory(long categoryId) {
		switch ((int) categoryId) {
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

	public static class Arguments {
		public static final String CUT_ID = "cut id";
		public static final String SAVE_INSTANCE_RECYCLERVIEW = "recyclerview instance";
		public static final String RECIPE_ID = "recipe id";
		public static final String NOTE_ID = "note id";
		public static final String STEP_ID = "step id";
		public static final String CATEGORY_ID = "category id";

		public static final String TIMER_DURATION = "timer duration";
		public static final String IS_RUB = "is rub";
		public static final String DESCRIPTION = "description";
		public static final String NAME = "name";
		public static final String COOKING_STYLE = "cooking style";
		public static final String DURATION = "duration";
		public static final String FIREBASE_REFERENCE = "firebase reference";
		public static final String SAVE_INSTANCE_ADAPTER = "save adapter state";
	}

	public static class Ids {
		public static final long CATEGORY_BEEF = 1;
		public static final long CATEGORY_PORK = 2;
		public static final long CATEGORY_POULTRY = 3;
		public static final long CATEGORY_FISH = 4;
		public static final long CATEGORY_VEGETABLE = 5;
		public static final long CATEGORY_OTHER = 6;

		public static final long CATEGORY_BEEF_RUB = 138;
		public static final long CATEGORY_PORK_RUB = 139;
		public static final long CATEGORY_POULTRY_RUB = 140;
		public static final long CATEGORY_FISH_RUB = 141;
		public static final long CATEGORY_VEGETABLE_RUB = 142;
		public static final long CATEGORY_OTHER_RUB = 143;

		public static final int TIMER_NOTIFICATION_ID = 100;

		public static long getRubCutId(long categoryId) {
			switch ((int) categoryId) {
				case (int) CATEGORY_BEEF:
					return CATEGORY_BEEF_RUB;
				case (int) CATEGORY_POULTRY:
					return CATEGORY_POULTRY_RUB;
				case (int) CATEGORY_FISH:
					return CATEGORY_FISH_RUB;
				case (int) CATEGORY_VEGETABLE:
					return CATEGORY_VEGETABLE_RUB;
				case (int) CATEGORY_OTHER:
					return CATEGORY_PORK_RUB;
				case (int) CATEGORY_OTHER_RUB:
					return CATEGORY_PORK_RUB;
			}
			return 0;
		}
	}

}
