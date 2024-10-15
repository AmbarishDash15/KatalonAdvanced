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

public class LeaveTypeOnPayslip {
	public static void main(String[] args) {
		String date1 = "14 Nov 2024";
	}
	
	public static String getLeaveTypeonPayslip(String leaveType) {
		String leaveTypeOnPayslip = ''
		switch (leaveType) {
			case 'Annual Leave' :
			leaveTypeOnPayslip = 'Annual Leave'
			break;
		case 'Compassionate Leave' :
			leaveTypeOnPayslip = 'Compassionate Leave'
			break;
		case 'Carers Leave' :
			leaveTypeOnPayslip = 'Carers Leave'
			break;
		case 'Sick Leave Without Pay' :
			leaveTypeOnPayslip = 'Sick Leave Without Pay'
			break;
		case 'Jury Duty' :
			leaveTypeOnPayslip = 'Jury Duty'
			break;
		case 'Leave Without Pay' :
			leaveTypeOnPayslip = 'Leave Without Pay'
			break;
		case 'Parental Leave - Primary Carer' :
			leaveTypeOnPayslip = 'PL - Primary Carer'
			break;
		case 'Parental Leave - Primary Carer Without Pay' :
			leaveTypeOnPayslip = 'PL - Primary Carer LWOP'
			break;
		case 'Parental Leave - Primary Carer Half Pay' :
			leaveTypeOnPayslip = 'PL - Primary Carer HP'
			break;
		case 'Miscellaneous Leave' :
			leaveTypeOnPayslip = 'Miscellaneous Leave'
			break;
		case 'Miscellaneous Leave Without Pay' :
			leaveTypeOnPayslip = 'Miscellaneous LWOP'
			break;
		case 'Military Leave' :
			leaveTypeOnPayslip = 'Military Leave'
			break;
		case 'Purchased Additional Leave' :
			leaveTypeOnPayslip = 'Purchased Additional Lv'
			break;
		case 'Parental Leave - Non-Primary Carer' :
			leaveTypeOnPayslip = 'PL - Non Primary Carer'
			break;
		case 'Parental Leave - Non-Primary Carer Without Pay' :
			leaveTypeOnPayslip = 'PL - Non Primary LWOP'
			break;
		case 'Sick Leave Half Pay' :
			leaveTypeOnPayslip = 'Sick Leave Half Pay'
			break;
		case 'Special Leave With Pay' :
			leaveTypeOnPayslip = 'Special Leave With Pay'
			break;
		case 'Workers Compensation Leave' :
			leaveTypeOnPayslip = 'Workers Compensation Leave'
			break;
		case 'Workers Compensation Leave Other Employment' :
			leaveTypeOnPayslip = 'WC Lv Other Employment'
			break;
		case 'Family and Domestic Abuse Leave' :
			leaveTypeOnPayslip = 'Leave'
			break;
		case 'Long Term Leave Without Pay' :
			leaveTypeOnPayslip = 'Long Term LWOP'
			break;
		case 'Adoption Leave' :
			leaveTypeOnPayslip = 'Adoption Leave'
			break;
		}
	return leaveTypeOnPayslip
	}
}
