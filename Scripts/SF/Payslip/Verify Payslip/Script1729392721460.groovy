import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import customUtilities.ManageDownloads as ManageDownloads
import org.openqa.selenium.Cookie as Cookie
import java.io.*
import java.net.HttpURLConnection as HttpURLConnection
import java.net.URL as URL
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

KeywordLogger logger = new KeywordLogger()

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/Print Preview title'), 0)

ManageDownloads.manageFolder()

WebUI.waitForElementPresent(findTestObject('Page_Print Preview/Last Document button'), 0)

WebUI.click(findTestObject('Page_Print Preview/Last Document button'))

renderedPaySlip = Integer.valueOf(GlobalVariable.leaveEndPayPeriod)

def previousURL = ''
def pdfUrl = ''

while (renderedPaySlip >= Integer.valueOf(GlobalVariable.leaveStartPayPeriod)) {
    WebUI.takeFullPageScreenshot()

    def windowIndex = WebUI.getWindowIndex()

    pdfUrl = WebUI.getAttribute(findTestObject('Page_Print Preview/iFrameContainer'), 'src')
	
	if (renderedPaySlip == Integer.valueOf(GlobalVariable.leaveEndPayPeriod)) {
		previousURL = pdfUrl
	}
	else {
		while(WebUI.getAttribute(findTestObject('Page_Print Preview/iFrameContainer'), 'src') == previousURL) {
			WebUI.delay(1)
		}
		pdfUrl = WebUI.getAttribute(findTestObject('Page_Print Preview/iFrameContainer'), 'src')
		previousURL = pdfUrl
	}

    destinationFile = (((((GlobalVariable.DownloadPath + '//') + EmployeeID) + '_') + renderedPaySlip) + 
    '.pdf')
/*
    WebUI.executeJavaScript(('window.open(\'' + pdfUrl) + '\', \'_blank\');', null)

    def tabIndex = DriverFactory.getCurrentWindowIndex()

    WebUI.switchToWindowIndex(windowIndex + 1)
*/
    Set<Cookie> cookies = DriverFactory.getWebDriver().manage().getCookies()

    URL url = new URL(pdfUrl)

    HttpURLConnection connection = ((url.openConnection()) as HttpURLConnection)

    connection.setRequestMethod('GET')

    StringBuilder cookieHeader = new StringBuilder()

    for (Cookie cookie : cookies) {
        cookieHeader.append(cookie.getName()).append('=').append(cookie.getValue()).append('; ')
    }
    
    connection.setRequestProperty('Cookie', cookieHeader.toString())

    connection.connect()

    if (connection.getResponseCode() == 200) {
        InputStream inputStream = connection.getInputStream()

        FileOutputStream outputStream = new FileOutputStream(new File(destinationFile))

        byte[] buffer = new byte[4096]

        int bytesRead

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
        
        outputStream.close()

        inputStream.close()
    } else {
        logger.logError('Unable to Download file')
    }
    
    connection.disconnect()
	logger.logInfo(destinationFile)
	GlobalVariable.destinationFilePath = destinationFile.replace('//','/')
	logger.logInfo(GlobalVariable.destinationFilePath)
    WebUI.callTestCase(findTestCase('SF/Payslip/Verify Payslip Data in Downloaded PDF'), [:], FailureHandling.STOP_ON_FAILURE)

    renderedPaySlip = (renderedPaySlip - 1)
	

    if (Integer.valueOf(GlobalVariable.leaveStartPayPeriod) <= renderedPaySlip) {
        WebUI.switchToWindowIndex(windowIndex)

        WebUI.waitForElementPresent(findTestObject('Page_Print Preview/Previous Document button'), 0)

        WebUI.click(findTestObject('Page_Print Preview/Previous Document button'))

        WebUI.delay(5)
		WebUI.waitForPageLoad(5)
    }
}

