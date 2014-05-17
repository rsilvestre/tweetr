package com.ephec.dao;

import javax.servlet.http.Part;
import java.io.IOException;

public interface DAOIFile {

    void writeFile(Part part, String fileName, String path) throws IOException;
}
