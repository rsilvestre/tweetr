package com.ephec.dao;

import java.io.IOException;

import javax.servlet.http.Part;

public interface DAOIFile {

	void writeFile(Part part, String fileName, String path) throws IOException;
}
