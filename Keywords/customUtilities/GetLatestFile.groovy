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

public class GetLatestFile {
	public static void main(String[] args) {
		String folderPath = "C:\\Temp\\DownloadedPayslips";
		String latestFileName = getLatestFileName(folderPath);

		if (latestFileName != null) {
			System.out.println("Latest downloaded file: " + latestFileName);
		} else {
			System.out.println("No files found in the folder or the folder does not exist.");
		}
	}

	public static String getLatestFileName() {
		String folderPath = GlobalVariable.DownloadPath
		File folder = new File(folderPath);

		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0) {
				File latestFile = files[0];

				// Find the latest file
				for (File file : files) {
					if (file.isFile() && file.lastModified() > latestFile.lastModified()) {
						latestFile = file;
					}
				}

				// Return the name of the latest file with its extension
				return folderPath + "\\" + latestFile.getName();
			}
		}

		// Return null if no files found or the folder does not exist
		return null;
	}
}

