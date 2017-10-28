package com.yugma.school;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;

import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class CloudStorageFileUpload {

	private static Storage storage = null;
	private static String bucketName = "cornerstone-version1";
	  
	  static {
		  try {
			storage =StorageOptions.newBuilder()
			  .setProjectId("cornerstone-school")
			  .setCredentials(ServiceAccountCredentials.fromStream(
					  new FileInputStream(
							  CloudStorageFileUpload.class.getClassLoader().getResource("cornerstone-ba509ef5e4e9.json").getPath())))
			  .build().getService();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //storage = StorageOptions.getDefaultInstance().getService();
	  }
	  
	  public Map<String,Object> uploadFile(MultipartFile filePart,String prefix) throws IOException {
		Long timestamp = UUID.randomUUID().getMostSignificantBits();
	    final String fileName = prefix+timestamp+filePart.getOriginalFilename();
	    
	    // the inputstream is closed by default, so we don't need to close it here
	    BlobInfo blobInfo =
        storage.create(
            BlobInfo
                .newBuilder(bucketName,fileName)
                // Modify access list to allow all users with link to read file
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                .setContentType(filePart.getContentType())
                .build(),
            filePart.getInputStream());
	    
	    Map<String,Object> map = new HashMap<>();
	    map.put("fileOriginalName", filePart.getOriginalFilename());
	    map.put("fileTimestamp",prefix.replaceAll("/", "%2f")+timestamp+
	    		filePart.getOriginalFilename()+"?alt=media");
	    map.put("blobInfo", blobInfo.getMediaLink());
	    // return the public download link
	    return map;
	  }
	  // [END uploadFile]

	 
	  public Map<String,Object> getImageDetail(MultipartFile filePart,
	                            String prefix) throws IOException, ServletException {
	   
	    final String fileName = filePart.getOriginalFilename().toLowerCase();
	    //String imageUrl = filePart.get;
	    // Check extension of file
	    if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
	      final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
	      String[] allowedExt = { "jpg", "jpeg", "png", "gif" };
	      for (String s : allowedExt) {
	        if (extension.equals(s)) {
	          return this.uploadFile(filePart,prefix);
	        }
	      }
	      throw new ServletException("file must be an image");
	    }
	    return null;
	  }
}
