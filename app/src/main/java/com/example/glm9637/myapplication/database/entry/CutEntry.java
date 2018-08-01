package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Entity(tableName = "cut", foreignKeys = {
		@ForeignKey(entity = CategoryEntry.class, parentColumns = "id", childColumns = "category_id")
}, indices = {
		@Index(name = "IX_CUT_CATEGORY_ID", value = "category_id")
})
public class CutEntry {
	
	@PrimaryKey()
	private long id;
	
	@ColumnInfo(name = "category_id")
	private long categoryid;
	
	private String name;
	private int img;
	private String description;
	private String origin;
	
	public CutEntry(long id, long categoryid, String name, int img, String description, String origin) {
		this.id = id;
		this.categoryid = categoryid;
		this.name = name;
		this.img = img;
		this.description = description;
		this.origin = origin;
	}
	
	public static CutEntry[] populateData() {
		return new CutEntry[]{
				//Beef Cuts
				new CutEntry(1, 1, "Flat Iron", 0, "Second in tenderness to the tenderloin steak, the flat iron is well-marbled, richly flavored and juicy. Best when cooked to no more than medium doneness", "chuck"),
				new CutEntry(2, 1, "Top Blade Steak", 0, "This well-marbled steak is juicy with excellent beef flavor. Similar to the flat iron, but the connective tissue has not been removed", "chuck"),
				new CutEntry(3, 1, "Chuck Roast", 0, "This is the classic pot roast, becoming moist and tender when braised and full of rich, beef flavor", "chuck"),
				new CutEntry(4, 1, "Moast Tender Roast", 0, "Lean, economical roast; best sliced thin", "chuck"),
				new CutEntry(5, 1, "Shoulder Clod Roast", 0, "Best when braised and boasts robust beef flavor. May also be roasted in oven and thinly sliced", "chuck"),
				new CutEntry(6, 1, "Ranch Steak", 0, "Economical and versatile, this lean cut is best when marinated before grilling", "chuck"),
				new CutEntry(7, 1, "English Roast", 0, "Best when braised and boasts robust beef flavor. May also be roasted in the oven and thinly sliced", "chuck"),
				new CutEntry(8, 1, "Sierra Cut", 0, "This lean, versatile steak is best when marinated before grilling and should be sliced thin against the grain", "chuck"),
				new CutEntry(9, 1, "Underblade Steak", 0, "Very juicy and well-marbled with a rich, beefy taste. May be marinated for additional flavor", "chuck"),
				new CutEntry(10, 1, "Chuck Eye Steak", 0, "Similar to a ribeye steak, but at a more economical price. Richly marbled and flavorful. Can be marinated before grilling", "chuck"),
				new CutEntry(11, 1, "Chuck Eye Roast", 0, "This is the classic pot roast, becoming moist and tender when braised and full of rich, beef flavor", "chuck"),
				new CutEntry(12, 1, "Chuck Arm Roast", 0, "Robust beef flavor, moist and extremely tender when braised for pot roast. May also be roasted in oven and sliced thin", "chuck"),
				new CutEntry(13, 1, "Mock Tender Steak", 0, "Lean and economical. Marinate overnight before grilling; best sliced thin", "chuck"),
				new CutEntry(14, 1, "Tender Roast", 0, "One of the most tender cuts, it's lean yet juicy and versatile. Economical and robust in flavor, it may be cut into medallions before or after cooking", "chuck"),
				new CutEntry(15, 1, "Shoulder Tender Medallions", 0, "One of the most tender beef cuts. Lean, juicy and versatile, with excellent flavor", "chuck"),
				new CutEntry(16, 1, "Country-style Chuck Ribs", 0, "Juicy and flavorful; becomes extremely tender when cooked slowly at a low temperature", "chuck"),
				new CutEntry(17, 1, "Chuck Shourt Ribs", 0, "Very flavorful, moist and tender when braised; available bone-in and boneless", "chuck"),
				
				new CutEntry(18, 1, "Rump Roast", 0, "Lean and economical, this cut is best enjoyed braised. When roasted in the oven, slice thin against the grain to maximize tenderness", "Round"),
				new CutEntry(19, 1, "Top Round London Broil", 0, "An economical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				new CutEntry(20, 1, "Top Round Roast", 0, "Economical, moderately tender and full-flavored. Slice thin against the grain", "Round"),
				new CutEntry(21, 1, "Top Round Steak", 0, "An economical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				new CutEntry(22, 1, "Bottom Round Steak", 0, "This lean steak is best in a tenderizing marinade and cooked to no more than medium rare doneness. Slice thin to maximize tenderness", "Round"),
				new CutEntry(23, 1, "Bottom Round London Broil", 0, "This lean steak is best in a tenderizing marinade and cooked to no more than medium rare doneness. Slice thin to maximize tenderness", "Round"),
				new CutEntry(24, 1, "Bottom Round Roast", 0, "Lean and economical, this cut is best enjoyed braised. When roasted in the oven, slice thin against the grain to maximize tenderness", "Round"),
				new CutEntry(25, 1, "Eye Of Round Roast", 0, "Very lean and economical. Best when cooked to medium rare and sliced thin against the grain", "Round"),
				new CutEntry(26, 1, "Eye Of Round Steak", 0, "A lean and economical cut. Best when marinated and cooked to medium rare", "Round"),
				new CutEntry(27, 1, "Sirloin Tip Center Roast", 0, "Lean, tender and economical, this roast's small size is perfect for an easy family dinner. Best when cooked to medium rare and sliced thin against the grain. May also be marinated", "Round"),
				new CutEntry(28, 1, "Sirloin Tip Center Steak", 0, "Sirloin Tip Center Steak", "Round"),
				new CutEntry(29, 1, "Sirloin Tip Side Steak", 0, "Sirloin Tip Side Steak", "Round"),
				new CutEntry(30, 1, "Sirloind Tip Roast", 0, "A lean cut from the bottom sirloin, this economical choice is best when carved into thin slices", "Round"),
				new CutEntry(31, 1, "Butterfly Top Round Steak", 0, "An econmical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				
				new CutEntry(32, 1, "Ribeye Steak", 0, "This boneless steak is rich, tender, juicy and full-flavored, with generous marbling throughout", "Rib"),
				new CutEntry(33, 1, "Prime Rib", 0, "Rich flavor, juicy tenderness and majestic appearance. The grand champion of beef roasts. One of the most tender beef cuts. Fine-grained with generous marbling throughout", "Rib"),
				new CutEntry(34, 1, "Ribeye Filet", 0, "Boneless ribeye steak with the cap removed. Rich, beefy flavor and generous marbling", "Rib"),
				new CutEntry(35, 1, "Boneless Ribeye Roast", 0, "A rib roast without the bones. Rich, beefy flavor; juicy and tender with generous marbling throughout", "Rib"),
				new CutEntry(36, 1, "Cowboy Steak", 0, "Rich, juicy and very flavorful, with generous marbling throughout. A cowboy steak has a short frenched bone; the tomahawk, a long frenched bone", "Rib"),
				new CutEntry(37, 1, "Rib Steak", 0, "A rib steak that is fine-grained and juicy. Rich, beefy flavor and generous marbling throughout. One of the most tender beef cuts", "Rib"),
				new CutEntry(38, 1, "Short Ribs", 0, "May be bone-in or boneless. Very flavorful, moist and tender when braised", "Rib"),
				new CutEntry(39, 1, "Back Ribs", 0, "Back ribs are flavorful, and great when cooked on the grill", "Rib"),
				new CutEntry(40, 1, "Chef-cut Ribeye", 0, "Typically prepared with the bone in. Fat is removed and the cut is tied; also prepared boneless", "Rib"),
				
				new CutEntry(41, 1, "Brisket", 0, "A flavorful cut that becomes tender when cooked slowly at low temperatures. The traditional cut used for corned beef, and popular as smoked barbecue", "Brisket & Shank"),
				new CutEntry(42, 1, "Brisket Flat", 0, "The leaner portion from a whole brisket. Should be cooked slowly at low temperatures to maximize its tenderness. The traditional cut used for corned beef, and popular as smoked barbecue", "Brisket & Shank"),
				new CutEntry(43, 1, "Brisket Point", 0, "A rich, flavorful portion of the whole brisket. Best when cooked slowly at low temperatures - smoked or braised", "Brisket & Shank"),
				new CutEntry(44, 1, "Shank Cross-cut", 0, "Flavorful, lean and very tender when braised or cooked in liquid (stewed)", "Brisket & Shank"),
				
				new CutEntry(45, 1, "Skirt Steak", 0, "Boasts deep, rich, beefy flavor. Best when marinated before grilling; when slicing, cut against the grain", "Short Plate"),
				
				new CutEntry(46, 1, "Flank Steak", 0, "Lean and flavorful, and should be thinly sliced against the grain when carving. An ideal choice to marinate", "Flank"),
				new CutEntry(47, 1, "Flap", 0, "Similar to skirt steak, with robust flavor. May be marinated before cooking", "Flank"),
				
				new CutEntry(48, 1, "Strip Filet", 0, "This premium lean steak is well-marbled, tender and full of flavor", "Short Loin"),
				new CutEntry(49, 1, "Strip Steak", 0, "This premium lean steak is a steakhouse classic, known for its marbling, tenderness and flavor", "Short Loin"),
				new CutEntry(50, 1, "Strip Filet", 0, "This premium lean steak is well-marbled, tender and full of flavor", "Short Loin"),
				new CutEntry(51, 1, "Strip Roast", 0, "Lean, tender and full-flavored roast", "Short Loin"),
				new CutEntry(52, 1, "Tenderloin Roast", 0, "The most tender beef roast is lean, succulent and elegant, with mild flavor", "Short Loin"),
				new CutEntry(53, 1, "T-bone Steak", 0, "This well-marbled cut consists of two lean, tender steaks - the strip and tenderloin - connected by a telltale T-shaped bone. In a T-Bone, the tenderloin is between 1/2 and 1 1/4 inches in diameter", "Short Loin"),
				new CutEntry(54, 1, "Porterhouse", 0, "This well-marbled classic steakhouse cut consists of two tender steaks - the strip and tenderloin - connected by a telltale T-shaped bone. In a Porterhouse, the tenderloin is 1 1/4 inch or larger in diameter", "Short Loin"),
				new CutEntry(55, 1, "Filet Mignon", 0, "The most tender beef cut. Lean yet succulent and elegant. Melt-in-your-mouth texture, subtle flavor and compact shape", "Short Loin"),
				new CutEntry(56, 1, "Hanger Steak", 0, "Traditionally found in restaurants, this cut offers a very robust flavor. Best when cooked to medium rare or medium doneness", "Short Loin"),
				
				new CutEntry(57, 1, "Center-cut Top Sirloin Steak", 0, "This versatile steak is cut from the top sirloin. Lean, juicy and tender, it boasts good flavor", "Sirloin"),
				new CutEntry(58, 1, "Sirloin Steak", 0, "Family-sized steak that offers lean, well-flavored and moderately tender beef at an affordable every day price. Convenient and a great value with no bones and little fat. Versatile, juicy and delicious", "Sirloin"),
				new CutEntry(59, 1, "Coulotte Steak", 0, "One of the most tender cuts from the sirloin. Versatile and flavorful; may be marinated", "Sirloin"),
				new CutEntry(60, 1, "Sirloin Filet", 0, "This lean, versatile steak is juicy, tender and flavorful", "Sirloin"),
				new CutEntry(61, 1, "Tri-tip Steak", 0, "Steaks cut from a tri-tip roast; juicy and full of rich beef flavor", "Sirloin"),
				new CutEntry(62, 1, "Tri-tip Roast", 0, "Juicy, tender and versatile, this roast offers rich beef flavor. Easily recognized by its triangular shape, this West Coast favorite is gaining broader popularity", "Sirloin"),
				new CutEntry(63, 1, "Ball Tip Steak", 0, "Lean, economical steak that is best when marinated before grilling", "Sirloin"),
				new CutEntry(64, 1, "Ball Tip Roast", 0, "A lean cut from the bottom sirloin, this economical choice is best when carved into thin slices", "Sirloin"),
				new CutEntry(65, 1, "Sirloin Flap", 0, "Similar to skirt steak, with robust flavor. May be marinated before cooking", "Sirloin"),
				
				new CutEntry(66, 1, "Cubed Steak", 0, "Tenderized by a butcher; often breaded and pan-fried", "Other"),
				new CutEntry(67, 1, "Beef for Kabobs", 0, "Beef cut into 1 to 1 1/2-inch pieces and arranged on skewers before cooking. Commonly cut from the sirloin but can come from any tender cut", "Other"),
				new CutEntry(68, 1, "Fajita Beef", 0, "Almost any tender beef cut can be trimmed and cut into uniform strips for use in quickly cooked dishes like stir-fries or fajitas", "Other"),
				new CutEntry(69, 1, "Beef Strips", 0, "Almost any tender beef cut can be trimmed and cut into uniform strips for use in quickly cooked dishes like stir-fries or fajitas", "Other"),
				new CutEntry(70, 1, "Beef for Stew", 0, "Well-trimmed beef, cut into 3/4 to 1 1/2-inch pieces that is covered with liquid and simmered slowly in a covered pot. Commonly cut from the sirloin but can come from any tender cut", "Other"),
				new CutEntry(71, 1, "Ground Beef", 0, "Versatile, flavorful and economical. Shape into burger patties, meatballs or meatloaf; or brown and crumble for a variety of dishes", "Other"),
				
				new CutEntry(72, 1, "Other", 0, "Pieces that fit to no other Cut", "Other"),
				
				//Pork Entry
				new CutEntry(73, 2, "Boneless Loin Chops", 0, "Pork chops are likely the least intimidating of all pork cuts because they are so easy to prepare", "Loin"),
				new CutEntry(74, 2, "Boneless Loin Roast", 0, "Pork loin roasts should not be braised or stewed as they have a tendency to lose tenderness", "Loin"),
				new CutEntry(75, 2, "Boneless Sirloin Roast", 0, "The Sirloin roast can be cut into cubes for stew or strips for stir-fry", "Loin"),
				new CutEntry(76, 2, "Tenderloin", 0, "Pork tenderloin makes an elegant entree for a small dinner party but also can be roasted or grilled whole for a quick weeknight dinner", "Loin"),
				new CutEntry(77, 2, "Crown Roast", 0, "These cuts make a showstopping centerpiece for an elegant dinner", "Loin"),
				new CutEntry(78, 2, "Baby Back Ribs", 0, "Ribs are commonly prepared with either a “wet” or “dry” rub. Dry rubs consist of a mixture of herbs and spices, and can be applied just before barbecuing or grilling", "Loin"),
				new CutEntry(79, 2, "St. Louis Spare Ribs", 0, "These often are the best type of ribs for recipes that require browning in a frying pan because the ribs are straight and flat", "Loin"),
				new CutEntry(80, 2, "Bone-in Boston Butt Roast ", 0, "The blade roast is a wellmarbled cut. This versatile cut can be pot-roasted whole, cut up for stews or cooked over moist smoke in a smoker to transform it into classic pulled pork barbecue", "Shoulder"),
				new CutEntry(81, 2, "Boneless Boston Butt Roast", 0, "The blade roast is a wellmarbled cut. This versatile cut can be pot-roasted whole, cut up for stews or cooked over moist smoke in a smoker to transform it into classic pulled pork barbecue", "Shoulder"),
				new CutEntry(82, 2, "Sausage", 0, "Sausage has the capacity to blend well with a variety of herbs, spices, fruits and vegetables", "Shoulder"),
				new CutEntry(83, 2, "Grinds", 0, "Fresh ground pork is unseasoned and makes a great substitute for other ground meats in your favorite recipes", "Shoulder"),
				new CutEntry(84, 2, "Skinless Pork Belly", 0, "Fresh belly is succulent and richly flavorful and is often served in small portions. Pork belly is at its best and is most tender when prepared using a slow cooking method, such as braising", "Belly"),
				new CutEntry(85, 2, "Pork Belly", 0, "Fresh belly is succulent and richly flavorful and is often served in small portions. Pork belly is at its best and is most tender when prepared using a slow cooking method, such as braising", "Belly"),
				new CutEntry(86, 2, "Bacon", 0, "An abundance of fat gives bacon its sweet flavor and tender crispiness", "Belly"),
				new CutEntry(87, 2, "Ham", 0, "Most hams are fully cooked, as noted on the label. Cooked hams can be served cold or after warming in the oven", "Leg"),
				new CutEntry(88, 2, "Shank", 0, "Shanks can be fresh or cured and are normally braised, roasted or slow-cooked", "Leg"),
				new CutEntry(89, 2, "Other", 0, "Pieces that fit to no other Cut", "Other"),
				
				new CutEntry(90, 3, "Chicken breast", 0, "Chicken breasts, arguably one of the most difficult of the standard meats to grill. It's all too common of an occurrence to get dry, chewy breasts", "Chicken"),
				new CutEntry(91, 3, "Tenders", 0, "Chicken tenders are awesome because they cook so quickly, especially on a gril.Serve Them up with some sort of veggies and call it good.", "Chicken"),
				new CutEntry(92, 3, "Chicken leg", 0, "Chicken legs are some of the tastiest hunks of meat you can eat. They’re affordable and succulent if you treat them right", "Chicken"),
				new CutEntry(93, 3, "Chicken drumstick", 0, "When it comes to affordability, chicken drumsticks tend to be the go-to cut of poultry for family dinners", "Chicken"),
				new CutEntry(94, 3, "Chicken thigh", 0, "Chicken thighs are the juiciest, most-flavorful part of the chicken and when the bone is left in, well, they taste even better", "Chicken"),
				new CutEntry(95, 3, "Chicken wing", 0, "Chicken Wings are great for any occasion. They are even better when grilled up with all that extra smoky flavor", "Chicken"),
				new CutEntry(96, 3, "Chicken wing tip", 0, "The tip, the very end point of the wing, made up mostly of skin and cartilage, is a crunchy, chewy, tasty, underappreciated delight", "Chicken"),
				new CutEntry(97, 3, "Backs and Necks", 0, "Chicken Backs are very popular in the American south: even though there is little meat on them, they offer lots of area to be coated with batter and deep-fried", "Chicken"),
				new CutEntry(98, 3, "Other Chicken Cuts", 0, "Pieces that fit to no other Cut", "Chicken"),
				
				new CutEntry(99, 3, "Duck Roast", 0, "Rich and full of flavour, duck meat is extremely nutritious, with high levels of protein, B vitamins and minerals such as zinc, potassium, magnesium and iron", "Duck"),
				new CutEntry(100, 3, "Duck Breast Filet", 0, " If you're cooking duck breast its comparatively high fat content can be reduced by removing the skin, and the layer of fat that sits beneath it, before cooking", "Duck"),
				new CutEntry(101, 3, "Duck Wings", 0, "Duck wings are pretty similar to chicken wings. Also sometimes they aren't as meaty, so the are often used for Stocks", "Duck"),
				new CutEntry(102, 3, "Other Duck Cuts", 0, "Pieces that fit to no other Cut", "Duck"),
				
				new CutEntry(103, 3, "Turkey Breast Filet", 0, "A tender cut, you can slice into any size you require for your recipe – diced, strips etc. ", "Turkey"),
				new CutEntry(104, 3, "Turkey Roast", 0, "Roast for special occasions such as Christmas, Easter, Thanksgiving or for when you have a crowd to feed", "Turkey"),
				new CutEntry(105, 3, "Turkey Crown", 0, " A great choice for smaller family roasts and for those who prefer the white breast meat", "Turkey"),
				new CutEntry(106, 3, "Thigh Joint", 0, "Sometimes these are ready-stuffed or supplied in net with the skin attached. This keeps the meat juicy as it roasts", "Turkey"),
				new CutEntry(107, 3, "Drumstick", 0, " Needs long, slow cooking - barbecuing, slow roasting or braising", "Turkey"),
				new CutEntry(108, 3, "Breast Joint", 0, "Ideal for when you fancy cooking a roast dinner for fewer people", "Turkey"),
				new CutEntry(109, 3, "Minced Breast", 0, "Highly versatile and can be used in any recipe in place of red meat mince", "Turkey"),
				new CutEntry(110, 3, "Other Turkey Cuts", 0, "", "Turkey"),
				
				new CutEntry(111, 4, "Salmon", 0, "Grilled salmon is a delightfully simple way to serve up a superfood dinner full of protein and healthy fats", "Fish"),
				new CutEntry(112, 4, "Tuna", 0, "Tuna is a dense fish that is not too fatty and has a light flavor.   It tastes amazing with basic seasonings or works well with almost any seasoning", "Fish"),
				new CutEntry(113, 4, "Snapper", 0, "The Red Snapper has firm meat with a very discreet taste and is therefore also suitable for people with fewer preferences for fish.", "Fish"),
				new CutEntry(114, 4, "Swordfish", 0, "A firm, succulent and meaty fish whose texture can be compared to that of tuna. Swordfish is a bill fish with a deep iron-grey skin, slim body and long ‘sword’ or bill as the upper jaw", "Fish"),
				new CutEntry(115, 4, "Mahi-Mahi", 0, "his fish is a Hearty fish with a firm yet flaky texture and rich flavor.  Excellent on the BBQ and great in the oven", "Fish"),
				new CutEntry(116, 4, "Halibut", 0, "Halibut is by far the largest of all flat fish and is available mostly in steaks, fillets and cutlets. Its firm, meaty white flesh has a delicious flavour but it can dry out quite easily so needs careful cooking", "Fish"),
				new CutEntry(117, 4, "Sea Bass", 0, "A superb sweet, white, textured fish, sea bass is a popular feature of menus. It is caught in the North Atlantic, from Norway to Senegal. It closely resembles sea bream in flavour", "Fish"),
				new CutEntry(118, 4, "Tilapia", 0, "A Tilapia is a firm-textured, white fleshed fish. They are a resilient and adaptable as a species, and can be found in both fresh and saltwater", "Fish"),
				new CutEntry(119, 4, "Cod", 0, "Cod is a key member of a whole family of fish including haddock, coley, pollack, whiting, ling and hake. All the related fish in this group are sea fish of varying sizes and share similar characteristics such as low-fat white flesh", "Fish"),
				new CutEntry(120, 4, "Trout", 0, "Trout can have an earthy flavour, particularly if caught in the wild, and therefore benefits from citrus and acidic flavours including lemon and capers", "Fish"),
				new CutEntry(121, 4, "Walleye", 0, " Walleye has a very light and non-fishy flavor. It is also known for having large fillets that will allow a single fish to fill up a large family-style portion that are popular in American cuisine", "Fish"),
				new CutEntry(122, 4, "Catfish", 0, "Catfish is a Southern favorite that tastes delicious when seasoned and grilled", "Fish"),
				new CutEntry(123, 4, "Shrimp", 0, "A staple at any party or function, you are guaranteed to be the hit of the party when you show up with these and some of our homemade cocktail sauce", "Fish"),
				new CutEntry(124, 4, "Shellfish", 0, "Shellfish is a broad term for crustacean and mollusc seafood. Each group has its own varieties and its own peculiarities of preparation, but most shellfish is easy to cook, rich in minerals and bursting with flavour", "Fish"),
				new CutEntry(125, 4, "Other fish", 0, "Pieces that fit to no other Cut", "Fish"),
				
				new CutEntry(126, 5, "Corn", 0, "Nearly everyone ", "Veggies"),
				new CutEntry(127, 5, "Aspargus", 0, "", "Veggies"),
				new CutEntry(128, 5, "Cauliflower", 0, "", "Veggies"),
				new CutEntry(129, 5, "Brussels Sprouts", 0, "", "Veggies"),
				new CutEntry(130, 5, "Zucchini", 0, "", "Veggies"),
				new CutEntry(131, 5, "Potatos", 0, "", "Veggies"),
				new CutEntry(132, 5, "Pepper", 0, "", "Veggies"),
				new CutEntry(133, 5, "Eggplant", 0, "", "Veggies"),
				new CutEntry(134, 5, "Mushroom", 0, "", "Veggies"),
				new CutEntry(135, 5, "Onions", 0, "", "Veggies"),
				new CutEntry(136, 5, "Tomatoes", 0, "", "Veggies"),
				new CutEntry(137, 5, "Other", 0, "", "Veggies"),
			
		};
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getImg() {
		return img;
	}
	
	public void setImg(int img) {
		this.img = img;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
