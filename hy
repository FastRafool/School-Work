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



GENERIC_INTERNAL_ERROR: field ended by ';': expected ';' but got 'name' at line 1: optional binary food name. You may need to manually clean the data at location 's3://project4444/Unsaved/2023/08/03/tables/4811800b-e7d6-4cd2-85b3-d0257c277e96' before retrying. Athena will not delete data in your account.
This query ran against the "mealplan" database, unless qualified by the query. Please post the error message on our forum  or contact customer support  with Query Id: 4811800b-e7d6-4cd2-85b3-d0257c277e96
