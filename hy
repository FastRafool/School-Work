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
    mp."Food Name", 
    SUM(mp."Quantity") AS Total_Quantity, 
    ROUND(SUM(mp."Quantity" * pd."Price ($)"), 2) AS Total_Price
FROM grade9asin AS mp
JOIN maindataset AS pd
ON mp.ASIN = pd.asin
GROUP BY mp."Food Name"
ORDER BY Total_Quantity DESC;

