import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.LocalFileDetector as LocalFileDetector
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.selenium.driver.CRemoteWebDriver as CRemoteWebDriver
import com.kms.katalon.core.webui.driver.WebUIDriverType as WebUIDriverType
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import java.io.File;
import java.io.RandomAccessFile;
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.keyword.pdf.PDF
import com.kms.katalon.keyword.pdf.PDFUtils

KeywordLogger logger = new KeywordLogger()

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.Loader

// Identify the driver

def path = RunConfiguration.getProjectDir() + '/DownloadedFiles/'
def fileName = 'download.pdf'
def fileToParse = (path + fileName).replace('/', '\\')
logger.logInfo(fileToParse)

// PDF Keyword call
File pdfFile = new File(fileToParse);
PDDocument pdDoc = Loader.loadPDF(pdfFile)
pdDoc.getClass();
String pdfFileInText = ""

if (!pdDoc.isEncrypted()) {

	PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	stripper.setSortByPosition(true);

	PDFTextStripper tStripper = new PDFTextStripper();

	pdfFileInText = tStripper.getText(pdDoc);

}
logger.logInfo(pdfFileInText)
// Create each line of text from the .PDF file
lines = pdfFileInText.split('\\r?\\n')

// Parse & print each individual line, at this point you can modify the code
// within the loop to look for a specific piece of text or collect the data

logger.logInfo(lines.length.toString())

//for (String line : lines) {
//	System.out.println(line)
//}