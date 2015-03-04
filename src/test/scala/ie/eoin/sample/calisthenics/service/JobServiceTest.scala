package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model._
import org.scalatest.junit.AssertionsForJUnit
import org.junit.{Before, Assert, Test}

class JobServiceTest extends AssertionsForJUnit {

  var jobService: JobService = _
  var employer1: Employer = _
  var employer2: Employer = _
  var regularJob:Job = _

  @Before
  def setup = {
    employer1 = new Employer(new PersonName("employer1"))
    employer2 = new Employer(new PersonName("employer2"))
    jobService = new JobService()
    regularJob = new Job(employer1)
  }

  @Test
  def testPostJob() = {
    val results = jobService.postJob(regularJob)
    Assert.assertEquals(results.size, 1)
  }

  @Test
  def testFilteredByEmployerWhereJobExists() = {
    jobService.postJob(regularJob)
    val results = jobService.filterByEmployer(employer1)
    Assert.assertEquals(results.size, 1)
  }

  @Test
  def testFilteredByEmployerWhereJobDoesNotExist() = {
    jobService.postJob(regularJob)
    val results = jobService.filterByEmployer(employer2)
    Assert.assertEquals(results.size, 0)
  }

}
