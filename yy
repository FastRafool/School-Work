SELECT f."Meal Plan", f.Rating, f.Review_Text, f.Taste_Rating, f.Portion_Rating, m.ASIN, m.Quantity, m."Food Name"
FROM CXfeedback AS f
JOIN grade10asin AS m
ON f."Meal Plan" = m."Meal Plan";
