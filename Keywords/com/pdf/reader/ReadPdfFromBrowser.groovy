package com.pdf.reader

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
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

import internal.GlobalVariable

import java.io.BufferedInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadPdfFromBrowser {

	PDDocument pdDoc;

	@Keyword
	public String PdfReaderUtil(String html, WebDriver driver){

		String pdfFileInText = "";
		KeywordLogger logger = new KeywordLogger()
		Thread.sleep(5000);
		URL url = new URL(html);
		//BufferedInputStream fileToParse = new BufferedInputStream(url.openStream());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // 200
			def inputStream = connection.getInputStream()
			def bufferedInputStream = new BufferedInputStream(inputStream)
			// Process the input stream as needed
			System.out.println("Successfully opened URL.");
			pdDoc = PDDocument.load(bufferedInputStream);
			pdDoc.getClass();
	
			if (!pdDoc.isEncrypted()) {
	
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
	
				PDFTextStripper tStripper = new PDFTextStripper();
	
				pdfFileInText = tStripper.getText(pdDoc);
			}
			driver.close();
			return pdfFileInText;
		} else {
			System.out.println("Error: " + responseCode);
		}

		
	}
}