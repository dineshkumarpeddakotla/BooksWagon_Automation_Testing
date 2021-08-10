/*
 *Purpose : Class is implemented for read data from different files
 *                 @DataProvider annotation is used to identify the data provider
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon.utility;

import com.bookswagon.config.ReadProperties;

import java.lang.reflect.Method;

public class DataProvider {

    /**
     * getDataFromProvider method is used to read data from different providers
     * @param method test method name
     * @return data in double array
     */
    @org.testng.annotations.DataProvider(name = "BooksWagonData")
    public static Object[][] getDataFromProvider(Method method) {
        ExcelUtil excelUtil = new ExcelUtil(ReadProperties.init_properties().getProperty("excelFilePath"),
                ReadProperties.init_properties().getProperty("firstSheetName"));
        ExcelUtil excel = new ExcelUtil(ReadProperties.init_properties().getProperty("excelFilePath"),
                ReadProperties.init_properties().getProperty("secondSheetName"));
        switch (method.getName()) {
            case "loginTOApplication_With_ValidCredentials" :
            case "login":
                return excelUtil.readData(2, 2, 1, 2);

            case "searchText_Book":
            case "selectBook":
            case "addBook_To_Cart":
                return excelUtil.readData(2,2,3,3);

            case "changeProductQuantity":
                return excelUtil.readData(2, 2, 4, 4);

            case "enter_Shipping_Address":
                return excelUtil.readData(2, 2, 5, 12);

            case "review_Orders":
                return excelUtil.readData(2, 2, 3, 4);

            case "check_Email_Id":
                return excelUtil.readData(2, 2, 1, 1);

            case "loginToApplication_With_InvalidCredentials":
                return excel.readData(2, 2, 1, 2);
            case "check_Negative_QuantityAccepts_OrNot":
                return excel.readData(2, 2, 4, 4);
        }
        return null;
    }
}
