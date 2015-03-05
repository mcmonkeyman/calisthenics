package ie.eoin.sample.calisthenics.service

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model._
import ie.eoin.sample.calisthenics.model.collections.SubmittedApplications

class ApplicationService {

  private var applications = new SubmittedApplications()

  def submitApplication(job: Job, jobSeeker: JobSeeker, resume: Option[Resume]) = {
    val application = new JobApplication(job, jobSeeker, resume)
    applications = applications.submitApplication(application)
    applications
  }

  def filterByDay(monthDay: MonthDay) = {
    applications.filter(monthDay)
  }

  def filterByJob(job: Job) = {
    applications.filter(job)
  }

  def filterByJobSeeker(jobSeeker: JobSeeker) = {
    applications.filter(jobSeeker)
  }

  def filterByEmployer(employer: Employer) = {
    applications.filter(employer)
  }

  def filter(employer: Employer,
             monthDay: Option[MonthDay] = None,
             job: Option[Job] = None
              ) = {
    applications
      .filter(employer)
      .filter(monthDay)
      .filter(job)
  }
}
