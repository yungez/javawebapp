package com.yungez.testweb2;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.GenericEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

import org.glassfish.jersey.media.multipart.*;
import org.glassfish.jersey.media.multipart.file.*;

import com.sun.research.ws.wadl.Application;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/file")
public class fileOperation {
	
	private static final String UPLOAD_FOLDER = new File(".").getAbsolutePath();
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		if (uploadedInputStream == null || fileDetail == null) {
			return Response.status(400).entity("Invalid form data").build();
		}
		
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500).entity(se.getMessage()).build();
		}
		
		String destFileName = UPLOAD_FOLDER + File.separator + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, destFileName);
		} catch (IOException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(202).entity("Successfully saved file " + destFileName).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<TestFile> GetFiles() throws Exception {
		File folder = new File(UPLOAD_FOLDER);
		
		if (!folder.exists()) {
			return null;			
		}
				
		File[] files = folder.listFiles();
		
		List<TestFile> list = new ArrayList<TestFile>();
		
		for(File file: files) {
			TestFile testfile = new TestFile();
			testfile.setFilePath(file.getAbsolutePath());
			
			list.add(testfile);
		}
		
		return list;
	}
	
	private void createFolderIfNotExists(String dirName) throws SecurityException {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}
	
	private void saveToFile(InputStream stream, String targetFileName) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		
		out = new FileOutputStream(new File(targetFileName));
		while ((read = stream.read(bytes)) != -1) {
			out.write(bytes,0, read);
		}
		out.flush();
		out.close();
	}

}
