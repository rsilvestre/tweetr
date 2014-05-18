package be.ephec.dao;

import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URISyntaxException;

public interface DAOIFile {

    void writeFile(Part part, String fileName, String path) throws IOException, URISyntaxException;
}
