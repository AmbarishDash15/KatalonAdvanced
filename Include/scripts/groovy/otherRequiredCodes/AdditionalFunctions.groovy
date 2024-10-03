package otherRequiredCodes;
import java.time.LocalTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.time.Duration


def calculateTimeDifference(String timeRange) {
    // Split the input string to extract start and end times
    def times = timeRange.split(" - ")
    LocalTime start = LocalTime.parse(times[0])
    LocalTime end = LocalTime.parse(times[1])

    // Calculate the duration between start and end time
    Duration duration = Duration.between(start, end)

    // Convert duration to minutes and deduct 30 minutes
    long totalMinutes = duration.toMinutes() - 30

    // Calculate hours and remaining minutes
    long hours = totalMinutes / 60
    long minutes = totalMinutes % 60

    return "${hours} hours ${minutes} minutes"
}

def calculateWorkingDays(String startDateStr, String endDateStr) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy")
	Date startDate = sdf.parse(startDateStr)
	Date endDate = sdf.parse(endDateStr)

	Calendar startCal = Calendar.getInstance()
	startCal.setTime(startDate)

	Calendar endCal = Calendar.getInstance()
	endCal.setTime(endDate)

	if (startCal.after(endCal)) {
		throw new IllegalArgumentException("Start date must be before end date")
	}

	int workingDays = 0

	while (!startCal.after(endCal)) {
		int dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK)
		if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
			workingDays++
		}
		startCal.add(Calendar.DAY_OF_MONTH, 1)
	}

	return workingDays
}


def multiplyTime(String timeStr, int multiplier) {
	// Split the input time string into hours and minutes
	def timeParts = timeStr.tokenize(" ")
	def hours = Integer.parseInt(timeParts[0])
	def minutes = Integer.parseInt(timeParts[2])
	
	// Convert total time to minutes
	int totalMinutes = (hours * 60) + minutes
	
	// Multiply by the given factor
	totalMinutes *= multiplier
	
	// Calculate the new hours and minutes
	int newHours = totalMinutes / 60
	int newMinutes = totalMinutes % 60
	
	return "${newHours} hours ${newMinutes} minutes"
}

// Example usage
def timeStr = "7 hours 54 minutes"
def multiplier = 2 // Change this multiplier as needed

def result = multiplyTime(timeStr, multiplier)
println("Total time: ${result}")



