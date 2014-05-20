package be.ephec.dao;

import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DAOFile extends DAO implements DAOIFile {

    private static final int BUFFER_SIZE = 10240; // 10 ko

    public DAOFile(DAOFactory daoFactory) {
        super(daoFactory);
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    public void writeFile(Part part, String fileName, String path) throws IOException, URISyntaxException {
        /* Prépare les flux. */
        URI uri;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            /* Ouvre les flux. */
            uri = new URI(path);
            in = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE);

            out = new BufferedOutputStream(new FileOutputStream(new File(uri.getPath() + fileName)), BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];
            int size;
            while ((size = in.read(buffer)) > 0) {
                out.write(buffer, 0, size);
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
