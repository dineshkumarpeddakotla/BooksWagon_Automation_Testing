/*
 *Purpose : Class is implemented for reading properties file
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 30-07-2021
 */

package com.bookswagon.config;

import com.bookswagon.constants.IConstants;
import com.bookswagon.utility.FileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static Properties init_properties() {

        Properties prop = new Properties();
        try {
            File file = new File(IConstants.CONFIG_FILE);
            if (!file.exists()) {
                throw new FileNotFoundException("File doesn't exits");
            }
            FileInputStream fis = new FileInputStream(file);

            prop.load(fis);
        } catch (IOException  | FileNotFoundException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
