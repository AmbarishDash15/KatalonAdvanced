import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.LocalFileDetector as LocalFileDetector
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.selenium.driver.CRemoteWebDriver as CRemoteWebDriver
import com.kms.katalon.core.webui.driver.WebUIDriverType as WebUIDriverType
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.io.File as File
import java.io.RandomAccessFile as RandomAccessFile
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.keyword.pdf.PDF as PDF
import com.kms.katalon.keyword.pdf.PDFUtils as PDFUtils
import org.apache.pdfbox.pdmodel.PDDocument as PDDocument
import org.apache.pdfbox.text.PDFTextStripper as PDFTextStripper
import org.apache.pdfbox.text.PDFTextStripperByArea as PDFTextStripperByArea
import org.apache.pdfbox.Loader as Loader
import customUtilities.GetLatestFile as GetLatestFile
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import customUtilities.GetLatestFile as GetLatestFile

KeywordLogger logger = new KeywordLogger()
File pdfFile = new File(GlobalVariable.destinationFilePath)
PDDocument pdDoc = PDDocument.load(pdfFile)
//pdDoc = PDDocument.load(GlobalVariable.destinationFilePath);
pdDoc.getClass();
def pdfFileInText = ''

if (!pdDoc.isEncrypted()) {

	PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	stripper.setSortByPosition(true);

	PDFTextStripper tStripper = new PDFTextStripper();

	pdfFileInText = tStripper.getText(pdDoc);
}

// Create each line of text from the .PDF file
def lines = pdfFileInText.split('\\r?\\n')

// Parse & print each individual line, at this point you can modify the code
// within the loop to look for a specific piece of text or collect the data
for (String line : lines) {
    logger.logInfo(line)
}

