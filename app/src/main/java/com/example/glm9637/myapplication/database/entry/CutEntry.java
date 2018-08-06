package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.glm9637.myapplication.utils.Constants;

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
	private final long id;
	
	@ColumnInfo(name = "category_id")
	private final long categoryId;
	
	private final String name;
	private final String img;
	private final String description;
	private final String origin;
	
	public CutEntry(long id, long categoryId, String name, String img, String description, String origin) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.img = img;
		this.description = description;
		this.origin = origin;
	}
	
	public static CutEntry[] populateData() {
		return new CutEntry[]{
				//Beef Cuts
				new CutEntry(1, Constants.Ids.CATEGORY_BEEF, "Flat Iron", "", "Second in tenderness to the tenderloin steak, the flat iron is well-marbled, richly flavored and juicy. Best when cooked to no more than medium doneness", "chuck"),
				new CutEntry(2, Constants.Ids.CATEGORY_BEEF, "Top Blade Steak", "", "This well-marbled steak is juicy with excellent beef flavor. Similar to the flat iron, but the connective tissue has not been removed", "chuck"),
				new CutEntry(3, Constants.Ids.CATEGORY_BEEF, "Chuck Roast", "", "This is the classic pot roast, becoming moist and tender when braised and full of rich, beef flavor", "chuck"),
				new CutEntry(4, Constants.Ids.CATEGORY_BEEF, "Moast Tender Roast", "", "Lean, economical roast; best sliced thin", "chuck"),
				new CutEntry(5, Constants.Ids.CATEGORY_BEEF, "Shoulder Clod Roast", "", "Best when braised and boasts robust beef flavor. May also be roasted in oven and thinly sliced", "chuck"),
				new CutEntry(6, Constants.Ids.CATEGORY_BEEF, "Ranch Steak", "", "Economical and versatile, this lean cut is best when marinated before grilling", "chuck"),
				new CutEntry(7, Constants.Ids.CATEGORY_BEEF, "English Roast", "", "Best when braised and boasts robust beef flavor. May also be roasted in the oven and thinly sliced", "chuck"),
				new CutEntry(8, Constants.Ids.CATEGORY_BEEF, "Sierra Cut", "", "This lean, versatile steak is best when marinated before grilling and should be sliced thin against the grain", "chuck"),
				new CutEntry(9, Constants.Ids.CATEGORY_BEEF, "Underblade Steak", "", "Very juicy and well-marbled with a rich, beefy taste. May be marinated for additional flavor", "chuck"),
				new CutEntry(10, Constants.Ids.CATEGORY_BEEF, "Chuck Eye Steak", "", "Similar to a ribeye steak, but at a more economical price. Richly marbled and flavorful. Can be marinated before grilling", "chuck"),
				new CutEntry(11, Constants.Ids.CATEGORY_BEEF, "Chuck Eye Roast", "", "This is the classic pot roast, becoming moist and tender when braised and full of rich, beef flavor", "chuck"),
				new CutEntry(12, Constants.Ids.CATEGORY_BEEF, "Chuck Arm Roast", "", "Robust beef flavor, moist and extremely tender when braised for pot roast. May also be roasted in oven and sliced thin", "chuck"),
				new CutEntry(13, Constants.Ids.CATEGORY_BEEF, "Mock Tender Steak", "", "Lean and economical. Marinate overnight before grilling; best sliced thin", "chuck"),
				new CutEntry(14, Constants.Ids.CATEGORY_BEEF, "Tender Roast", "", "One of the most tender cuts, it's lean yet juicy and versatile. Economical and robust in flavor, it may be cut into medallions before or after cooking", "chuck"),
				new CutEntry(15, Constants.Ids.CATEGORY_BEEF, "Shoulder Tender Medallions", "", "One of the most tender beef cuts. Lean, juicy and versatile, with excellent flavor", "chuck"),
				new CutEntry(16, Constants.Ids.CATEGORY_BEEF, "Country-style Chuck Ribs", "", "Juicy and flavorful; becomes extremely tender when cooked slowly at a low temperature", "chuck"),
				new CutEntry(17, Constants.Ids.CATEGORY_BEEF, "Chuck Short Ribs", "", "Very flavorful, moist and tender when braised; available bone-in and boneless", "chuck"),
				
				new CutEntry(18, Constants.Ids.CATEGORY_BEEF, "Rump Roast", "", "Lean and economical, this cut is best enjoyed braised. When roasted in the oven, slice thin against the grain to maximize tenderness", "Round"),
				new CutEntry(19, Constants.Ids.CATEGORY_BEEF, "Top Round London Broil", "", "An economical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				new CutEntry(20, Constants.Ids.CATEGORY_BEEF, "Top Round Roast", "", "Economical, moderately tender and full-flavored. Slice thin against the grain", "Round"),
				new CutEntry(21, Constants.Ids.CATEGORY_BEEF, "Top Round Steak", "", "An economical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				new CutEntry(22, Constants.Ids.CATEGORY_BEEF, "Bottom Round Steak", "", "This lean steak is best in a tenderizing marinade and cooked to no more than medium rare doneness. Slice thin to maximize tenderness", "Round"),
				new CutEntry(23, Constants.Ids.CATEGORY_BEEF, "Bottom Round London Broil", "", "This lean steak is best in a tenderizing marinade and cooked to no more than medium rare doneness. Slice thin to maximize tenderness", "Round"),
				new CutEntry(24, Constants.Ids.CATEGORY_BEEF, "Bottom Round Roast", "", "Lean and economical, this cut is best enjoyed braised. When roasted in the oven, slice thin against the grain to maximize tenderness", "Round"),
				new CutEntry(25, Constants.Ids.CATEGORY_BEEF, "Eye Of Round Roast", "", "Very lean and economical. Best when cooked to medium rare and sliced thin against the grain", "Round"),
				new CutEntry(26, Constants.Ids.CATEGORY_BEEF, "Eye Of Round Steak", "", "A lean and economical cut. Best when marinated and cooked to medium rare", "Round"),
				new CutEntry(27, Constants.Ids.CATEGORY_BEEF, "Sirloin Tip Center Roast", "", "Lean, tender and economical, this roast's small size is perfect for an easy family dinner. Best when cooked to medium rare and sliced thin against the grain. May also be marinated", "Round"),
				new CutEntry(28, Constants.Ids.CATEGORY_BEEF, "Sirloin Tip Center Steak", "", "Sirloin Tip Center Steak", "Round"),
				new CutEntry(29, Constants.Ids.CATEGORY_BEEF, "Sirloin Tip Side Steak", "", "Sirloin Tip Side Steak", "Round"),
				new CutEntry(30, Constants.Ids.CATEGORY_BEEF, "Sirloin Tip Roast", "", "A lean cut from the bottom sirloin, this economical choice is best when carved into thin slices", "Round"),
				new CutEntry(31, Constants.Ids.CATEGORY_BEEF, "Butterfly Top Round Steak", "", "An economical and full-flavored cut. Best when marinated and sliced thinly against the grain", "Round"),
				
				new CutEntry(32, Constants.Ids.CATEGORY_BEEF, "Ribeye Steak", "", "This boneless steak is rich, tender, juicy and full-flavored, with generous marbling throughout", "Rib"),
				new CutEntry(33, Constants.Ids.CATEGORY_BEEF, "Prime Rib", "", "Rich flavor, juicy tenderness and majestic appearance. The grand champion of beef roasts. One of the most tender beef cuts. Fine-grained with generous marbling throughout", "Rib"),
				new CutEntry(34, Constants.Ids.CATEGORY_BEEF, "Ribeye Filet", "", "Boneless ribeye steak with the cap removed. Rich, beefy flavor and generous marbling", "Rib"),
				new CutEntry(35, Constants.Ids.CATEGORY_BEEF, "Boneless Ribeye Roast", "", "A rib roast without the bones. Rich, beefy flavor; juicy and tender with generous marbling throughout", "Rib"),
				new CutEntry(36, Constants.Ids.CATEGORY_BEEF, "Cowboy Steak", "", "Rich, juicy and very flavorful, with generous marbling throughout. A cowboy steak has a short frenched bone; the tomahawk, a long frenched bone", "Rib"),
				new CutEntry(37, Constants.Ids.CATEGORY_BEEF, "Rib Steak", "", "A rib steak that is fine-grained and juicy. Rich, beefy flavor and generous marbling throughout. One of the most tender beef cuts", "Rib"),
				new CutEntry(38, Constants.Ids.CATEGORY_BEEF, "Short Ribs", "", "May be bone-in or boneless. Very flavorful, moist and tender when braised", "Rib"),
				new CutEntry(39, Constants.Ids.CATEGORY_BEEF, "Back Ribs", "", "Back ribs are flavorful, and great when cooked on the grill", "Rib"),
				new CutEntry(40, Constants.Ids.CATEGORY_BEEF, "Chef-cut Ribeye", "", "Typically prepared with the bone in. Fat is removed and the cut is tied; also prepared boneless", "Rib"),
				
				new CutEntry(41, Constants.Ids.CATEGORY_BEEF, "Brisket", "", "A flavorful cut that becomes tender when cooked slowly at low temperatures. The traditional cut used for corned beef, and popular as smoked barbecue", "Brisket & Shank"),
				new CutEntry(42, Constants.Ids.CATEGORY_BEEF, "Brisket Flat", "", "The leaner portion from a whole brisket. Should be cooked slowly at low temperatures to maximize its tenderness. The traditional cut used for corned beef, and popular as smoked barbecue", "Brisket & Shank"),
				new CutEntry(43, Constants.Ids.CATEGORY_BEEF, "Brisket Point", "", "A rich, flavorful portion of the whole brisket. Best when cooked slowly at low temperatures - smoked or braised", "Brisket & Shank"),
				new CutEntry(44, Constants.Ids.CATEGORY_BEEF, "Shank Cross-cut", "", "Flavorful, lean and very tender when braised or cooked in liquid (stewed)", "Brisket & Shank"),
				
				new CutEntry(45, Constants.Ids.CATEGORY_BEEF, "Skirt Steak", "", "Boasts deep, rich, beefy flavor. Best when marinated before grilling; when slicing, cut against the grain", "Short Plate"),
				
				new CutEntry(46, Constants.Ids.CATEGORY_BEEF, "Flank Steak", "", "Lean and flavorful, and should be thinly sliced against the grain when carving. An ideal choice to marinate", "Flank"),
				new CutEntry(47, Constants.Ids.CATEGORY_BEEF, "Flap", "", "Similar to skirt steak, with robust flavor. May be marinated before cooking", "Flank"),
				
				new CutEntry(48, Constants.Ids.CATEGORY_BEEF, "Strip Filet", "", "This premium lean steak is well-marbled, tender and full of flavor", "Short Loin"),
				new CutEntry(49, Constants.Ids.CATEGORY_BEEF, "Strip Steak", "", "This premium lean steak is a steakhouse classic, known for its marbling, tenderness and flavor", "Short Loin"),
				new CutEntry(50, Constants.Ids.CATEGORY_BEEF, "Strip Filet", "", "This premium lean steak is well-marbled, tender and full of flavor", "Short Loin"),
				new CutEntry(51, Constants.Ids.CATEGORY_BEEF, "Strip Roast", "", "Lean, tender and full-flavored roast", "Short Loin"),
				new CutEntry(52, Constants.Ids.CATEGORY_BEEF, "Tenderloin Roast", "", "The most tender beef roast is lean, succulent and elegant, with mild flavor", "Short Loin"),
				new CutEntry(53, Constants.Ids.CATEGORY_BEEF, "T-bone Steak", "", "This well-marbled cut consists of two lean, tender steaks - the strip and tenderloin - connected by a telltale T-shaped bone. In a T-Bone, the tenderloin is between 1/2 and 1 1/4 inches in diameter", "Short Loin"),
				new CutEntry(54, Constants.Ids.CATEGORY_BEEF, "Porterhouse", "", "This well-marbled classic steakhouse cut consists of two tender steaks - the strip and tenderloin - connected by a telltale T-shaped bone. In a Porterhouse, the tenderloin is 1 1/4 inch or larger in diameter", "Short Loin"),
				new CutEntry(55, Constants.Ids.CATEGORY_BEEF, "Filet Mignon", "", "The most tender beef cut. Lean yet succulent and elegant. Melt-in-your-mouth texture, subtle flavor and compact shape", "Short Loin"),
				new CutEntry(56, Constants.Ids.CATEGORY_BEEF, "Hanger Steak", "", "Traditionally found in restaurants, this cut offers a very robust flavor. Best when cooked to medium rare or medium doneness", "Short Loin"),
				
				new CutEntry(57, Constants.Ids.CATEGORY_BEEF, "Center-cut Top Sirloin Steak", "", "This versatile steak is cut from the top sirloin. Lean, juicy and tender, it boasts good flavor", "Sirloin"),
				new CutEntry(58, Constants.Ids.CATEGORY_BEEF, "Sirloin Steak", "", "Family-sized steak that offers lean, well-flavored and moderately tender beef at an affordable every day price. Convenient and a great value with no bones and little fat. Versatile, juicy and delicious", "Sirloin"),
				new CutEntry(59, Constants.Ids.CATEGORY_BEEF, "Coulotte Steak", "", "One of the most tender cuts from the sirloin. Versatile and flavorful; may be marinated", "Sirloin"),
				new CutEntry(60, Constants.Ids.CATEGORY_BEEF, "Sirloin Filet", "", "This lean, versatile steak is juicy, tender and flavorful", "Sirloin"),
				new CutEntry(61, Constants.Ids.CATEGORY_BEEF, "Tri-tip Steak", "", "Steaks cut from a tri-tip roast; juicy and full of rich beef flavor", "Sirloin"),
				new CutEntry(62, Constants.Ids.CATEGORY_BEEF, "Tri-tip Roast", "", "Juicy, tender and versatile, this roast offers rich beef flavor. Easily recognized by its triangular shape, this West Coast favorite is gaining broader popularity", "Sirloin"),
				new CutEntry(63, Constants.Ids.CATEGORY_BEEF, "Ball Tip Steak", "", "Lean, economical steak that is best when marinated before grilling", "Sirloin"),
				new CutEntry(64, Constants.Ids.CATEGORY_BEEF, "Ball Tip Roast", "", "A lean cut from the bottom sirloin, this economical choice is best when carved into thin slices", "Sirloin"),
				new CutEntry(65, Constants.Ids.CATEGORY_BEEF, "Sirloin Flap", "", "Similar to skirt steak, with robust flavor. May be marinated before cooking", "Sirloin"),
				
				new CutEntry(66, Constants.Ids.CATEGORY_BEEF, "Cubed Steak", "", "Tenderized by a butcher; often breaded and pan-fried", "Other"),
				new CutEntry(67, Constants.Ids.CATEGORY_BEEF, "Beef for Kabobs", "", "Beef cut into 1 to 1 1/2-inch pieces and arranged on skewers before cooking. Commonly cut from the sirloin but can come from any tender cut", "Other"),
				new CutEntry(68, Constants.Ids.CATEGORY_BEEF, "Fajita Beef", "", "Almost any tender beef cut can be trimmed and cut into uniform strips for use in quickly cooked dishes like stir-fries or fajitas", "Other"),
				new CutEntry(69, Constants.Ids.CATEGORY_BEEF, "Beef Strips", "", "Almost any tender beef cut can be trimmed and cut into uniform strips for use in quickly cooked dishes like stir-fries or fajitas", "Other"),
				new CutEntry(70, Constants.Ids.CATEGORY_BEEF, "Beef for Stew", "", "Well-trimmed beef, cut into 3/4 to 1 1/2-inch pieces that is covered with liquid and simmered slowly in a covered pot. Commonly cut from the sirloin but can come from any tender cut", "Other"),
				new CutEntry(71, Constants.Ids.CATEGORY_BEEF, "Ground Beef", "", "Versatile, flavorful and economical. Shape into burger patties, meatballs or meatloaf; or brown and crumble for a variety of dishes", "Other"),
				
				new CutEntry(72, Constants.Ids.CATEGORY_BEEF, "Other", "", "Pieces that fit to no other Cut", "Other"),
				
				//Pork Entry
				new CutEntry(73, Constants.Ids.CATEGORY_PORK, "Boneless Loin Chops", "", "Pork chops are likely the least intimidating of all pork cuts because they are so easy to prepare", "Loin"),
				new CutEntry(74, Constants.Ids.CATEGORY_PORK, "Boneless Loin Roast", "", "Pork loin roasts should not be braised or stewed as they have a tendency to lose tenderness", "Loin"),
				new CutEntry(75, Constants.Ids.CATEGORY_PORK, "Boneless Sirloin Roast", "", "The Sirloin roast can be cut into cubes for stew or strips for stir-fry", "Loin"),
				new CutEntry(76, Constants.Ids.CATEGORY_PORK, "Tenderloin", "", "Pork tenderloin makes an elegant entree for a small dinner party but also can be roasted or grilled whole for a quick weeknight dinner", "Loin"),
				new CutEntry(77, Constants.Ids.CATEGORY_PORK, "Crown Roast", "", "These cuts make a showstopping centerpiece for an elegant dinner", "Loin"),
				new CutEntry(78, Constants.Ids.CATEGORY_PORK, "Baby Back Ribs", "", "Ribs are commonly prepared with either a “wet” or “dry” rub. Dry rubs consist of a mixture of herbs and spices, and can be applied just before barbecuing or grilling", "Loin"),
				new CutEntry(79, Constants.Ids.CATEGORY_PORK, "St. Louis Spare Ribs", "", "These often are the best type of ribs for recipes that require browning in a frying pan because the ribs are straight and flat", "Loin"),
				new CutEntry(80, Constants.Ids.CATEGORY_PORK, "Bone-in Boston Butt Roast ", "", "The blade roast is a well marbled cut. This versatile cut can be pot-roasted whole, cut up for stews or cooked over moist smoke in a smoker to transform it into classic pulled pork barbecue", "Shoulder"),
				new CutEntry(81, Constants.Ids.CATEGORY_PORK, "Boneless Boston Butt Roast", "", "The blade roast is a well marbled cut. This versatile cut can be pot-roasted whole, cut up for stews or cooked over moist smoke in a smoker to transform it into classic pulled pork barbecue", "Shoulder"),
				new CutEntry(82, Constants.Ids.CATEGORY_PORK, "Sausage", "", "Sausage has the capacity to blend well with a variety of herbs, spices, fruits and vegetables", "Shoulder"),
				new CutEntry(83, Constants.Ids.CATEGORY_PORK, "Grinds", "", "Fresh ground pork is unseasoned and makes a great substitute for other ground meats in your favorite recipes", "Shoulder"),
				new CutEntry(84, Constants.Ids.CATEGORY_PORK, "Skinless Pork Belly", "", "Fresh belly is succulent and richly flavorful and is often served in small portions. Pork belly is at its best and is most tender when prepared using a slow cooking method, such as braising", "Belly"),
				new CutEntry(85, Constants.Ids.CATEGORY_PORK, "Pork Belly", "", "Fresh belly is succulent and richly flavorful and is often served in small portions. Pork belly is at its best and is most tender when prepared using a slow cooking method, such as braising", "Belly"),
				new CutEntry(86, Constants.Ids.CATEGORY_PORK, "Bacon", "", "An abundance of fat gives bacon its sweet flavor and tender crispiness", "Belly"),
				new CutEntry(87, Constants.Ids.CATEGORY_PORK, "Ham", "", "Most hams are fully cooked, as noted on the label. Cooked hams can be served cold or after warming in the oven", "Leg"),
				new CutEntry(88, Constants.Ids.CATEGORY_PORK, "Shank", "", "Shanks can be fresh or cured and are normally braised, roasted or slow-cooked", "Leg"),
				new CutEntry(89, Constants.Ids.CATEGORY_PORK, "Other", "", "Pieces that fit to no other Cut", "Other"),
				
				new CutEntry(90, Constants.Ids.CATEGORY_POULTRY, "Chicken breast", "", "Chicken breasts, arguably one of the most difficult of the standard meats to grill. It's all too common of an occurrence to get dry, chewy breasts", "Chicken"),
				new CutEntry(91, Constants.Ids.CATEGORY_POULTRY, "Tenders", "", "Chicken tenders are awesome because they cook so quickly, especially on a grill.Serve Them up with some sort of veggies and call it good.", "Chicken"),
				new CutEntry(92, Constants.Ids.CATEGORY_POULTRY, "Chicken leg", "", "Chicken legs are some of the tastiest hunks of meat you can eat. They’re affordable and succulent if you treat them right", "Chicken"),
				new CutEntry(93, Constants.Ids.CATEGORY_POULTRY, "Chicken drumstick", "", "When it comes to affordability, chicken drumsticks tend to be the go-to cut of poultry for family dinners", "Chicken"),
				new CutEntry(94, Constants.Ids.CATEGORY_POULTRY, "Chicken thigh", "", "Chicken thighs are the juiciest, most-flavorful part of the chicken and when the bone is left in, well, they taste even better", "Chicken"),
				new CutEntry(95, Constants.Ids.CATEGORY_POULTRY, "Chicken wing", "", "Chicken Wings are great for any occasion. They are even better when grilled up with all that extra smoky flavor", "Chicken"),
				new CutEntry(96, Constants.Ids.CATEGORY_POULTRY, "Chicken wing tip", "", "The tip, the very end point of the wing, made up mostly of skin and cartilage, is a crunchy, chewy, tasty, underappreciated delight", "Chicken"),
				new CutEntry(97, Constants.Ids.CATEGORY_POULTRY, "Backs and Necks", "", "Chicken Backs are very popular in the American south: even though there is little meat on them, they offer lots of area to be coated with batter and deep-fried", "Chicken"),
				new CutEntry(98, Constants.Ids.CATEGORY_POULTRY, "Other Chicken Cuts", "", "Pieces that fit to no other Cut", "Chicken"),
				
				new CutEntry(99, Constants.Ids.CATEGORY_POULTRY, "Duck Roast", "", "Rich and full of flavour, duck meat is extremely nutritious, with high levels of protein, B vitamins and minerals such as zinc, potassium, magnesium and iron", "Duck"),
				new CutEntry(100, Constants.Ids.CATEGORY_POULTRY, "Duck Breast Filet", "", " If you're cooking duck breast its comparatively high fat content can be reduced by removing the skin, and the layer of fat that sits beneath it, before cooking", "Duck"),
				new CutEntry(101, Constants.Ids.CATEGORY_POULTRY, "Duck Wings", "", "Duck wings are pretty similar to chicken wings. Also sometimes they aren't as meaty, so the are often used for Stocks", "Duck"),
				new CutEntry(102, Constants.Ids.CATEGORY_POULTRY, "Other Duck Cuts", "", "Pieces that fit to no other Cut", "Duck"),
				
				new CutEntry(103, Constants.Ids.CATEGORY_POULTRY, "Turkey Breast Filet", "", "A tender cut, you can slice into any size you require for your recipe – diced, strips etc. ", "Turkey"),
				new CutEntry(104, Constants.Ids.CATEGORY_POULTRY, "Turkey Roast", "", "Roast for special occasions such as Christmas, Easter, Thanksgiving or for when you have a crowd to feed", "Turkey"),
				new CutEntry(105, Constants.Ids.CATEGORY_POULTRY, "Turkey Crown", "", " A great choice for smaller family roasts and for those who prefer the white breast meat", "Turkey"),
				new CutEntry(106, Constants.Ids.CATEGORY_POULTRY, "Thigh Joint", "", "Sometimes these are ready-stuffed or supplied in net with the skin attached. This keeps the meat juicy as it roasts", "Turkey"),
				new CutEntry(107, Constants.Ids.CATEGORY_POULTRY, "Drumstick", "", " Needs long, slow cooking - barbecuing, slow roasting or braising", "Turkey"),
				new CutEntry(108, Constants.Ids.CATEGORY_POULTRY, "Breast Joint", "", "Ideal for when you fancy cooking a roast dinner for fewer people", "Turkey"),
				new CutEntry(109, Constants.Ids.CATEGORY_POULTRY, "Minced Breast", "", "Highly versatile and can be used in any recipe in place of red meat mince", "Turkey"),
				new CutEntry(110, Constants.Ids.CATEGORY_POULTRY, "Other Turkey Cuts", "", "", "Turkey"),
				
				new CutEntry(111, Constants.Ids.CATEGORY_FISH, "Salmon", "", "Grilled salmon is a delightfully simple way to serve up a superfood dinner full of protein and healthy fats", "Fish"),
				new CutEntry(112, Constants.Ids.CATEGORY_FISH, "Tuna", "", "Tuna is a dense fish that is not too fatty and has a light flavor.   It tastes amazing with basic seasonings or works well with almost any seasoning", "Fish"),
				new CutEntry(113, Constants.Ids.CATEGORY_FISH, "Snapper", "", "The Red Snapper has firm meat with a very discreet taste and is therefore also suitable for people with fewer preferences for fish.", "Fish"),
				new CutEntry(114, Constants.Ids.CATEGORY_FISH, "Swordfish", "", "A firm, succulent and meaty fish whose texture can be compared to that of tuna. Swordfish is a bill fish with a deep iron-grey skin, slim body and long ‘sword’ or bill as the upper jaw", "Fish"),
				new CutEntry(115, Constants.Ids.CATEGORY_FISH, "Mahi-Mahi", "", "his fish is a Hearty fish with a firm yet flaky texture and rich flavor.  Excellent on the BBQ and great in the oven", "Fish"),
				new CutEntry(116, Constants.Ids.CATEGORY_FISH, "Halibut", "", "Halibut is by far the largest of all flat fish and is available mostly in steaks, fillets and cutlets. Its firm, meaty white flesh has a delicious flavour but it can dry out quite easily so needs careful cooking", "Fish"),
				new CutEntry(117, Constants.Ids.CATEGORY_FISH, "Sea Bass", "", "A superb sweet, white, textured fish, sea bass is a popular feature of menus. It is caught in the North Atlantic, from Norway to Senegal. It closely resembles sea bream in flavour", "Fish"),
				new CutEntry(118, Constants.Ids.CATEGORY_FISH, "Tilapia", "", "A Tilapia is a firm-textured, white fleshed fish. They are a resilient and adaptable as a species, and can be found in both fresh and saltwater", "Fish"),
				new CutEntry(119, Constants.Ids.CATEGORY_FISH, "Cod", "", "Cod is a key member of a whole family of fish including haddock, coley, pollack, whiting, ling and hake. All the related fish in this group are sea fish of varying sizes and share similar characteristics such as low-fat white flesh", "Fish"),
				new CutEntry(120, Constants.Ids.CATEGORY_FISH, "Trout", "", "Trout can have an earthy flavour, particularly if caught in the wild, and therefore benefits from citrus and acidic flavours including lemon and capers", "Fish"),
				new CutEntry(121, Constants.Ids.CATEGORY_FISH, "Walleye", "", " Walleye has a very light and non-fishy flavor. It is also known for having large fillets that will allow a single fish to fill up a large family-style portion that are popular in American cuisine", "Fish"),
				new CutEntry(122, Constants.Ids.CATEGORY_FISH, "Catfish", "", "Catfish is a Southern favorite that tastes delicious when seasoned and grilled", "Fish"),
				new CutEntry(123, Constants.Ids.CATEGORY_FISH, "Shrimp", "", "A staple at any party or function, you are guaranteed to be the hit of the party when you show up with these and some of our homemade cocktail sauce", "Fish"),
				new CutEntry(124, Constants.Ids.CATEGORY_FISH, "Shellfish", "", "Shellfish is a broad term for crustacean and mollusk seafood. Each group has its own varieties and its own peculiarities of preparation, but most shellfish is easy to cook, rich in minerals and bursting with flavour", "Fish"),
				new CutEntry(125, Constants.Ids.CATEGORY_FISH, "Other fish", "", "Pieces that fit to no other Cut", "Fish"),
				
				new CutEntry(126, Constants.Ids.CATEGORY_VEGETABLE, "Corn", "", "Nearly everyone ", "Veggies"),
				new CutEntry(127, Constants.Ids.CATEGORY_VEGETABLE, "Asparagus", "", "", "Veggies"),
				new CutEntry(128, Constants.Ids.CATEGORY_VEGETABLE, "Cauliflower", "", "", "Veggies"),
				new CutEntry(129, Constants.Ids.CATEGORY_VEGETABLE, "Brussels Sprouts", "", "", "Veggies"),
				new CutEntry(130, Constants.Ids.CATEGORY_VEGETABLE, "Zucchini", "", "", "Veggies"),
				new CutEntry(131, Constants.Ids.CATEGORY_VEGETABLE, "Potatoes", "", "", "Veggies"),
				new CutEntry(132, Constants.Ids.CATEGORY_VEGETABLE, "Pepper", "", "", "Veggies"),
				new CutEntry(133, Constants.Ids.CATEGORY_VEGETABLE, "Eggplant", "", "", "Veggies"),
				new CutEntry(134, Constants.Ids.CATEGORY_VEGETABLE, "Mushroom", "", "", "Veggies"),
				new CutEntry(135, Constants.Ids.CATEGORY_VEGETABLE, "Onions", "", "", "Veggies"),
				new CutEntry(136, Constants.Ids.CATEGORY_VEGETABLE, "Tomatoes", "", "", "Veggies"),
				new CutEntry(137, Constants.Ids.CATEGORY_VEGETABLE, "Other", "", "", "Veggies"),

				new CutEntry(138, Constants.Ids.CATEGORY_OTHER, "Other", "", "", "Other"),

				new CutEntry(Constants.Ids.CATEGORY_BEEF_RUB, Constants.Ids.CATEGORY_BEEF, Constants.CUT_NAME_RUB, "", "", ""),
				new CutEntry(Constants.Ids.CATEGORY_PORK_RUB, Constants.Ids.CATEGORY_PORK, Constants.CUT_NAME_RUB, "", "", ""),
				new CutEntry(Constants.Ids.CATEGORY_POULTRY_RUB, Constants.Ids.CATEGORY_POULTRY, Constants.CUT_NAME_RUB, "", "", ""),
				new CutEntry(Constants.Ids.CATEGORY_FISH_RUB, Constants.Ids.CATEGORY_FISH, Constants.CUT_NAME_RUB, "", "", ""),
				new CutEntry(Constants.Ids.CATEGORY_VEGETABLE_RUB, Constants.Ids.CATEGORY_VEGETABLE, Constants.CUT_NAME_RUB, "", "", ""),
				new CutEntry(Constants.Ids.CATEGORY_OTHER_RUB, Constants.Ids.CATEGORY_OTHER, Constants.CUT_NAME_RUB, "", "", ""),
			
			
		};
	}
	
	public long getId() {
		return id;
	}
	
	public long getCategoryId() {
		return categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImg() {
		return img;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getOrigin() {
		return origin;
	}
}
