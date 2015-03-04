package ie.eoin.sample.calisthenics.model.collections

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model.{Employer, JobSeeker, Job, JobApplication}

class SubmittedApplications(val submittedApplications: List[(JobApplication, MonthDay)]) {

  def this() {
    this(List())
  }

  def submitApplication(application: JobApplication) = {
    new SubmittedApplications((application, MonthDay.now) :: submittedApplications)
  }

  def filterByDay(day: MonthDay) = {
    val result = submittedApplications.filter {
      case (_, filterDay) => day == filterDay
    }
    new SubmittedApplications(result)
  }

  def filterByDay(dayOption: Option[MonthDay]): SubmittedApplications = {
    dayOption match {
      case Some(day) => filterByDay(day)
      case _ => this
    }
  }

  def filterByJob(job: Job) = {
    val result = submittedApplications.filter {
      case (jobApplication, _) => jobApplication.isForJob(job)
    }
    new SubmittedApplications(result)
  }

  def filterByJob(jobOption: Option[Job]): SubmittedApplications = {
    jobOption match {
      case Some(job) => filterByJob(job)
      case _ => this
    }
  }

  def filterByJobSeeker(jobSeeker: JobSeeker) = {
    val result = submittedApplications.filter {
      case (jobApplication, _) => jobApplication.isForJobSeeker(jobSeeker)
    }
    new SubmittedApplications(result)
  }

  def filterByEmployer(employer: Employer) = {
    val result = submittedApplications.filter {
      case (jobApplication, _) => jobApplication.isForEmployer(employer)
    }
    new SubmittedApplications(result)
  }

  def size = submittedApplications.size
}
