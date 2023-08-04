SELECT 
    mp."Food Name", 
    SUM(mp."Quantity") AS Total_Quantity, 
    SUM(mp."Quantity" * pd."Price ($)") AS Total_Price
FROM meal_plan_details AS mp
JOIN product_details AS pd
ON mp.ASIN = pd.asin
GROUP BY mp."Food Name"
ORDER BY Total_Quantity DESC;
