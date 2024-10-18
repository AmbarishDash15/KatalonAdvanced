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

public class GetNameFromDesignation {
	public static void main(String[] args) {
		// Example usage
		String designation = "HRBP"; // Adjust this to test different dates
	}

	public static String getHRBPName(String groupName) {
		String nameHRBP = ''
		switch (groupName){
			case 'Gener. Support Services' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'People & Culture' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Safety & Capability' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Community' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Corporate Affairs' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Operations' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Production' :
				nameHRBP = 'Andrew Klaips'
				break;
			case 'Asset Mgt & Technology' :
				nameHRBP = 'Leanne Le Coic'
				break;
			case 'Environment' :
				nameHRBP = 'Leanne Le Coic'
				break;
			case 'Weather & Water' :
				nameHRBP = 'Leanne Le Coic'
				break;
			case 'Snowy 2.0' :
				nameHRBP = 'Rachel Mulholland'
				break;
			case 'HPP' :
				nameHRBP = 'Rachel Mulholland'
				break;
			case 'Finance Group' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Legal' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Procurement' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Trading' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Gov Risk & Compliance' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Portfolio Management' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Strategy' :
				nameHRBP = 'Vitali Loukachonok'
				break;
			case 'Regulatory' :
				nameHRBP = 'Vitali Loukachonok'
				break;
		}
		return nameHRBP
	}
	public static String getApproverName(String designation) {
		String employeeName = ''
		switch (designation) {
			case 'HR Admin' :
				employeeName = 'Bronwyn Burbury'
				break;
			case 'Manager People & Culture Operations' :
				employeeName = 'Nick Oakman'
				break;
			case 'Payroll Admin' :
				employeeName = 'Tracey Mears'
		}
		return employeeName
	}
}
