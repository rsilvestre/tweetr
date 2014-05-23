package be.ephec.utilities;

import javax.servlet.http.Part;

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

    public static String getFilename(Part part) {
        String filename = part.getHeader("content-disposition");
        return filename.substring(filename.lastIndexOf("=") + 1).trim().replace("\"", "").replace("_", "");
    }
}
