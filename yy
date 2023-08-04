SELECT f."Meal Plan", f.Rating, f.Review_Text, f.Taste_Rating, f.Portion_Rating, m.ASIN, m.Quantity, m."Food Name"
FROM feedback_table AS f
JOIN meal_plan_table AS m
ON f."Meal Plan" = m."Meal Plan";
