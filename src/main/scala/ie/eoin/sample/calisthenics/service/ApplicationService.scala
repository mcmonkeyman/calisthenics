package ie.eoin.sample.calisthenics.service

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model._

class ApplicationService {
  private val applications = new SubmittedApplications()

  def submitApplication(job: Job, jobSeeker: JobSeeker, resume: Option[Resume]) = {
    val application = new JobApplication(job, jobSeeker, resume)
    applications.submitApplication(application)
  }

  def filterByDay(monthDay: MonthDay) = {
    applications.filterByDay(monthDay)
  }

  def filterByJob(job: Job) = {
    applications.filterByJob(job)
  }
}
