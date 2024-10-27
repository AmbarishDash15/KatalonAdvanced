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
import java.text.SimpleDateFormat
import java.text.ParseException
import com.kms.katalon.core.util.KeywordUtil
import customUtilities.GetLatestFile as GetLatestFile
import customUtilities.LeaveTypeOnPayslip as LeaveTypeOnPayslip
import customUtilities.TimeConverter as TimeConverter
import customUtilities.PayPeriodDates as PayPeriodDates

KeywordLogger logger = new KeywordLogger()
filePath = GlobalVariable.destinationFilePath
PDDocument pdDoc = Loader.loadPDF(new File(filePath))
//pdDoc = PDDocument.load(GlobalVariable.destinationFilePath);
pdDoc.getClass();
def pdfFileInText = ''

if (!pdDoc.isEncrypted()) {

	PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	stripper.setSortByPosition(true);

	PDFTextStripper tStripper = new PDFTextStripper();

	pdfFileInText = tStripper.getText(pdDoc);
}
/*
// Create each line of text from the .PDF file
def lines = pdfFileInText.split('\\r?\\n')

// Parse & print each individual line, at this point you can modify the code
// within the loop to look for a specific piece of text or collect the data
for (String line : lines) {
    logger.logInfo(line)
}
*/
KeywordUtil.logInfo(pdfFileInText)

SimpleDateFormat formatdMMM = new SimpleDateFormat('d MMM yyyy')
SimpleDateFormat formatyyyymm = new SimpleDateFormat('yyyy-MM-dd')
SimpleDateFormat formatddMM = new SimpleDateFormat('dd.MM.yyyy')

def leavePeriodIterator = GlobalVariable.currentPayPeriod
def leaveArray = GlobalVariable.LeaveArray
def leaveEarningsString = ''
def leaveTypeString = ''
def leaveAbsenceDateNumberUnit = ''
def leaveAbsenceDateStart = ''
def leaveAbsenceDateEnd = ''
def leaveAbsenceNumber = '0'
def leaveNumberUnderAbsence = ''
def leaveAbsenceDateStartVerification = new Date()
def leaveAbsenceDateEndVerification = new Date()

def String[] payPeriodArray = (PayPeriodDates.getPayPeriodDates(GlobalVariable.payPeriod1StartDate, GlobalVariable.payPeriod1EndDate, leavePeriodIterator)).split(':')
leaveTypeString = LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType)
leaveTypeString = leaveTypeString.length() > 20 ? leaveTypeString.take(20) : leaveTypeString


if (GlobalVariable.leaveStartPayPeriod != GlobalVariable.leaveEndPayPeriod) {
	if(leavePeriodIterator == Integer.valueOf(GlobalVariable.leaveEndPayPeriod)) {
		leaveAbsenceDateStart = formatddMM.format(formatyyyymm.parse(payPeriodArray[0]))
		leaveAbsenceDateEnd = formatddMM.format(formatdMMM.parse(LeaveEndDate))
		leaveAbsenceDateStartVerification = formatddMM.parse(leaveAbsenceDateStart)
		leaveAbsenceDateEndVerification = formatddMM.parse(leaveAbsenceDateEnd)
	}
	else if(leavePeriodIterator == Integer.valueOf(GlobalVariable.leaveStartPayPeriod)){
		leaveAbsenceDateStart = formatddMM.format(formatdMMM.parse(LeaveStartDate))
		leaveAbsenceDateStartVerification = formatddMM.parse(leaveAbsenceDateStart)
		leaveAbsenceDateEnd = formatddMM.format(formatyyyymm.parse(payPeriodArray[1]))
		leaveAbsenceDateEndVerification = formatddMM.parse(leaveAbsenceDateEnd)
	}
	
	else {
		leaveAbsenceDateStart = formatddMM.format(formatyyyymm.parse(payPeriodArray[0]))
		leaveAbsenceDateEnd = formatddMM.format(formatyyyymm.parse(payPeriodArray[1]))
		leaveAbsenceDateStartVerification = formatddMM.parse(leaveAbsenceDateStart)
		leaveAbsenceDateEndVerification = formatddMM.parse(leaveAbsenceDateEnd)
	}
	for (int leaveArrayIterator = 0; leaveArrayIterator < leaveArray.size(); leaveArrayIterator++) {
		Date currentDate = formatyyyymm.parse(leaveArray[leaveArrayIterator][0])
		if ((currentDate >= leaveAbsenceDateStartVerification)&&(currentDate <= leaveAbsenceDateEndVerification)) {
			leaveAbsenceNumber = String.valueOf(Double.valueOf(leaveAbsenceNumber)+Double.valueOf(leaveArray[leaveArrayIterator][1]))
		}
	}
}
else {
	leaveAbsenceDateStart = formatddMM.format(formatdMMM.parse(LeaveStartDate))
	leaveAbsenceDateEnd = formatddMM.format(formatdMMM.parse(LeaveEndDate))
	leaveAbsenceDateStartVerification = formatddMM.parse(leaveAbsenceDateStart)
	leaveAbsenceDateEndVerification = formatddMM.parse(leaveAbsenceDateEnd)
	if (GlobalVariable.leaveUnit == 'Hours') {
		if (LeaveStartDate == LeaveEndDate) {
			leaveAbsenceNumber = TimeConverter.convertMinutesToDecimalHours(GlobalVariable.workMinutes)
		}
		else {
			for (def leaveArrayIterator = 0; leaveArrayIterator < leaveArray.size(); leaveArrayIterator++) {
				Date currentDate = formatyyyymm.parse(leaveArray[leaveArrayIterator][0])
	
				if ((currentDate >= leaveAbsenceDateStartVerification)&&(currentDate <= leaveAbsenceDateEndVerification)) {
					leaveAbsenceNumber = String.valueOf(Double.valueOf(leaveAbsenceNumber)+Double.valueOf(leaveArray[leaveArrayIterator][1]))
				}
			}
		}
	}
	else {
		if (LeaveStartDate == LeaveEndDate) {
			leaveAbsenceNumber = '1'
		}
		else {
			for (array in leaveArray) {
				leaveAbsenceNumber = String.valueOf(Integer.valueOf(leaveAbsenceNumber)+Integer.valueOf(array[1]))
			}
		}
	}
}

if (leaveAbsenceNumber.contains('.')) {
	def parts = leaveAbsenceNumber.split('\\.')
	if (parts.length == 2 && parts[1].length() == 1) {
		leaveNumberUnderAbsence = "${parts[0]}.${parts[1]}0"
	}
	else {
		leaveNumberUnderAbsence = leaveAbsenceNumber
	}
}
else {
	leaveNumberUnderAbsence = leaveAbsenceNumber
}

leaveEarningsString = leaveTypeString+' '+ leaveAbsenceNumber
leaveAbsenceDateNumberUnit = leaveAbsenceDateStart+' '+leaveAbsenceDateEnd+'           '+leaveNumberUnderAbsence+' '+ GlobalVariable.leaveUnit

if (pdfFileInText.contains(GlobalVariable.EmployeeFirstName+' '+GlobalVariable.EmployeeLastName)) {
	KeywordUtil.logInfo('Verified Employee name as : '+GlobalVariable.EmployeeFirstName+' '+GlobalVariable.EmployeeLastName)
}
else {
	KeywordUtil.logInfo('Tried to verify Employee name as : '+GlobalVariable.EmployeeFirstName+' '+GlobalVariable.EmployeeLastName)
}
if (pdfFileInText.contains(EmployeeID)) {
	KeywordUtil.logInfo('Verified Employee ID as : '+ EmployeeID)
}
else {
	KeywordUtil.logInfo('Tried to verify Employee ID as : '+ EmployeeID)
}
if (pdfFileInText.contains(leaveTypeString)) {
	KeywordUtil.logInfo('Verified Leave Type as : '+ leaveTypeString)
}
else {
	KeywordUtil.logInfo('Tried to verify Leave Type as : '+ leaveTypeString)
}
if (pdfFileInText.contains(leaveEarningsString)) {
	KeywordUtil.logInfo('Verified Under Earnings : '+ leaveEarningsString)
}
else {
	KeywordUtil.logInfo('Tried to verify Under Earnings : '+ leaveEarningsString)
}
if (pdfFileInText.contains(leaveAbsenceDateNumberUnit)) {
	KeywordUtil.logInfo('Verified Under Absences : '+ leaveAbsenceDateNumberUnit)
}
else {
	KeywordUtil.logInfo('Tried to verify Under Absences : '+ leaveAbsenceDateNumberUnit)
}



