package customUtilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.io.File;


public class ManageDownloads {
    public static void main(String[] args) {        
		String folderPath = GlobalVariable.DownloadPath
    }

   public static void manageFolder() {
	   String folderPath = GlobalVariable.DownloadPath
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            // Delete contents of the folder
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
                System.out.println("Deleted all contents of the folder: " + folderPath);
            } else {
                System.out.println("No contents to delete in the folder: " + folderPath);
            }
        } else {
            // Create the folder if it doesn't exist
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Folder created: " + folderPath);
            } else {
                System.out.println("Failed to create folder: " + folderPath);
            }
        }
    }
}
