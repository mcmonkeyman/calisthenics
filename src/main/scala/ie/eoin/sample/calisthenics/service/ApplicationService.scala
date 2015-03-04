package ie.eoin.sample.calisthenics.service

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model._

class ApplicationService {

  private var applications = new SubmittedApplications()

  def submitApplication(job: Job, jobSeeker: JobSeeker, resume: Option[Resume]) = {
    val application = new JobApplication(job, jobSeeker, resume)
    applications = applications.submitApplication(application)
    applications
  }

  def filterByDay(monthDay: MonthDay) = {
    applications.filterByDay(monthDay)
  }

  def filterByJob(job: Job) = {
    applications.filterByJob(job)
  }

  def filterByJobSeeker(jobSeeker: JobSeeker) = {
    applications.filterByJobSeeker(jobSeeker)
  }

  def filterByEmployer(employer: Employer) = {
    applications.filterByEmployer(employer)
  }

  def filter(employer: Employer,
             monthDay: Option[MonthDay] = None,
             job: Option[Job] = None
              ) = {
    applications
      .filterByEmployer(employer)
      .filterByDay(monthDay)
      .filterByJob(job)
  }
}
