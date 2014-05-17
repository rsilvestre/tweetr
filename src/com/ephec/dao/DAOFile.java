package com.ephec.dao;

import javax.servlet.http.Part;
import java.io.*;

public class DAOFile implements DAOIFile {

    private static final int BUFFER_SIZE = 10240; // 10 ko
    private DAOFactory daoFactory;

    public DAOFile() {
    }

    public DAOFile(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    public void writeFile(Part part, String fileName, String path) throws IOException {
        /* Prépare les flux. */
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            /* Ouvre les flux. */
            in = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(new File(path
                    + fileName)), BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];
            int size;
            while ((size = in.read(buffer)) > 0) {
                out.write(buffer, 0, size);
            }
        } finally {
            try {
                out.close();
            } catch (IOException ignore) {
            }
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }
}
