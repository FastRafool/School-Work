SELECT 
    mp."Food Name", 
    SUM(mp."Quantity") AS Total_Quantity, 
    SUM(mp."Quantity" * pd."Price ($)") AS Total_Price
FROM grade9asin AS mp
JOIN maindataset AS pd
ON mp.ASIN = pd.asin
GROUP BY mp."Food Name"
ORDER BY Total_Quantity DESC;



SELECT 
    mpd."Meal Plan",
    mpd."Food Name",
    SUM(mpd.Quantity) AS Total_Quantity,
    ROUND(SUM(mpd.Quantity * pd."Price ($)"), 2) AS Total_Price
FROM meal_plan_details AS mpd
JOIN product_details AS pd
ON mpd.ASIN = pd.asin
GROUP BY mpd."Meal Plan", mpd."Food Name"
ORDER BY Total_Price DESC;
