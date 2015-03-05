package ie.eoin.sample.calisthenics.model.collections

import com.github.nscala_time.time.Imports._
import ie.eoin.sample.calisthenics.model.{Employer, JobSeeker, Job, JobApplication}

class SubmittedApplications(val submittedApplications: List[(JobApplication, MonthDay)]) {

  def this() {this(List())}

  def submitApplication(application: JobApplication) = {
    new SubmittedApplications((application, MonthDay.now) :: submittedApplications)
  }

  def filter(filterObject: Any) = {
    val filterFunction = filterObject match {
      case day: MonthDay => filterByDayPartial(day)
      case Some(day:MonthDay) => filterByDayPartial(day)
      case job: Job => filterByJobPartial(job)
      case Some(job:Job) => filterByJobPartial(job)
      case jobSeeker: JobSeeker => filterByJobSeekerPartial(jobSeeker)
      case employer: Employer => filterByEmployerPartial(employer)
      case _ => { x:Any => true }
    }
    filterByFunction(filterFunction)
  }

  def filterByDayPartial(day: MonthDay): (Object) => Boolean = {
    case (_, filterDay: MonthDay) => day == filterDay
  }

  def filterByJobPartial(job:Job): (Object => Boolean) = {
    case (jobApplication: JobApplication, _) => jobApplication.isForJob(job)
  }

  def filterByJobSeekerPartial(jobSeeker:JobSeeker): (Object => Boolean) = {
    case (jobApplication: JobApplication, _) => jobApplication.isForJobSeeker(jobSeeker)
  }

  def filterByEmployerPartial(employer:Employer): (Object => Boolean) = {
    case (jobApplication: JobApplication, _) => jobApplication.isForEmployer(employer)
  }

  def filterByFunction(filterFunction: (Object) => Boolean) = {
    val result = submittedApplications filter filterFunction
    new SubmittedApplications(result)
  }

  def size = submittedApplications.size
}
