package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Employer, Job, PersonName, JobSeeker}
import org.junit.{Before, Assert, Test}
import org.scalatest.junit.AssertionsForJUnit

class JobSeekerServiceTest extends AssertionsForJUnit {

  var jobSeekerService: JobSeekerService = _
  var jobSeeker1: JobSeeker = _
  var jobSeeker2: JobSeeker = _
  var employer1: Employer = _
  var regularJob: Job = _

  @Before
  def setup = {
    jobSeekerService = new JobSeekerService()
    jobSeeker1 = new JobSeeker(new PersonName("jobSeeker1"))
    jobSeeker2 = new JobSeeker(new PersonName("jobSeeker2"))
    employer1 = new Employer(new PersonName("employer1"))
    regularJob = new Job(employer1)

  }

  @Test
  def testSaveJobWithResult() = {
    jobSeekerService.saveJob(jobSeeker1, regularJob)
    val result = jobSeekerService.getSavedJobs(jobSeeker1)
    Assert.assertEquals(result.get.size, 1)
  }

  @Test
  def testSaveJobWithoutResult() = {
    jobSeekerService.saveJob(jobSeeker1, regularJob)
    val result = jobSeekerService.getSavedJobs(jobSeeker2)
    Assert.assertEquals(result, None)
  }
}
