package com.ephec.utilities;

import javax.servlet.http.Part;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public class DirectoryTools {

    public static String getFilename(Part part, String field) {

        for (String cd : part.getHeader("content-disposition").split(";")) {

            if (cd.trim().startsWith(field)) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }

        return null;
    }
}
