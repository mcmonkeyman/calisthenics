package ie.eoin.sample.calisthenics.service

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model.{Job, JobApplication}

class SubmittedApplications {

  var submittedAppications: List[(JobApplication, MonthDay)] = List()

  def submitApplication(application: JobApplication) = {
    submittedAppications = (application, MonthDay.now) :: submittedAppications
    submittedAppications
  }

  def filterByDay(day: MonthDay) = {
    submittedAppications.filter {
      case (_,filterDay) =>  day == filterDay
    }
  }

  def filterByJob(job:Job) = {
    submittedAppications.filter {
      case (jobApplication, _) => jobApplication.isForJob(job)
    }
  }
}
