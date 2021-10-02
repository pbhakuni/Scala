package com.example;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.api.gax.paging.Page;

public class QuickstartSample {
  public static void main(String... args) throws Exception {
    
    int copyCount = 2, counter = 0;
    String srcBucketName = "ikea-bucket-first";
    String targetBucketName = "ikea-bucket-second";
    String folderName = "folder-1/";
    Storage storage = StorageOptions.getDefaultInstance().getService();
    Page<Blob> b = storage.list(srcBucketName, BlobListOption.prefix(folderName));

    //Copy the data from one folder to another folder inside bucket
    /*
    for ( Blob blob : b.iterateAll()) {
      blob.copyTo(targetBucketName);
    }*/

    //Copy only 2 files
    /*
    for ( Blob blob : b.iterateAll()) {
      System.out.println("Copying the blob " + blob.getBlobId());
      blob.copyTo(targetBucketName);
      counter = counter + 1;

      if (counter == copyCount) break;
      
    } 
    */

    //Specify time break and lot size  
    for ( Blob blob : b.iterateAll()) {

      if(!blob.getName().equals(folderName)) {
        System.out.println("Copying the blob " + blob.getBlobId());
        blob.copyTo(targetBucketName);
        counter = counter + 1;
  
        if (counter == copyCount) 
        {
          System.out.println("Sleeping the process for 30 seconds");
          Thread.sleep(30000);
          counter = 0;
        }
      }      
    } 
  }
}

