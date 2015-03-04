package ie.eoin.sample.calisthenics.model

class JobApplication(val job: Job, val applicant: JobSeeker, val resume: Option[Resume]) {
  require(validResume(), "provided resume does not exits in jobSeekers list of resumes")

  def validResume() = {
    resumeNotRequired || applicant.containsResume(resume)
  }

  def resumeNotRequired = {
    !job.requiresResume
  }

  def isForJob(jobToTest: Job) = {
    jobToTest == job
  }

  def isForJobSeeker(jobSeekerToTest: JobSeeker) = {
    jobSeekerToTest == applicant
  }

  def isForEmployer(employerToTest: Employer) ={
    job.isOwner(employerToTest)
  }

 }
