package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model._
import org.scalatest.junit.AssertionsForJUnit
import org.junit.{Before, Assert, Test}
import com.github.nscala_time.time.Imports._

class ApplicationServiceTest extends AssertionsForJUnit {

  var applicationService: ApplicationService = _
  var employer1: Employer = _
  var employer2: Employer = _
  var jReqJob: JReqJob = _
  var regularJob: Job = _
  var resume: Resume = _
  var jobSeekerWithResume: JobSeeker = _
  var jobSeekerWithoutResume: JobSeeker = _
  var today: MonthDay = _
  var yesterday: MonthDay = _

  @Before
  def setup = {
    applicationService = new ApplicationService()
    employer1 = new Employer(new PersonName("employer1"))
    employer1 = new Employer(new PersonName("employer2"))
    jReqJob = new JReqJob(employer1)
    regularJob = new Job(employer1)
    resume = new Resume(new URL("resume1"))
    jobSeekerWithResume = new JobSeeker(new PersonName("jobseeker1"), List(resume))
    jobSeekerWithoutResume = new JobSeeker(new PersonName("jobseeker2"), List())
    today = MonthDay.now
    yesterday = MonthDay.now.minusDays(1)
  }

  @Test
  def testValidJreqApplication() = {
    val result = applicationService.submitApplication(jReqJob, jobSeekerWithResume, Some(resume))
    Assert.assertEquals(result.size, 1)
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testJReqApplicationWithBadResume(): Unit = {
    applicationService.submitApplication(jReqJob, jobSeekerWithoutResume, Some(resume))
  }

  @Test
  def testValidSimpleApplication() = {
    val result = applicationService.submitApplication(regularJob, jobSeekerWithResume, None)
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByDayWhereDayExits() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByDay(today)
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByDayWhereDayDoesNotExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByDay(yesterday)
    Assert.assertEquals(result.size, 0)
  }

  @Test
  def testFilterByJobWhereJobExists() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByJob(regularJob)
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByJobWhereJobDoesNotExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByJob(jReqJob)
    Assert.assertEquals(result.size, 0)
  }

  @Test
  def testFilterByJobSeekerWhereJobSeekerExists() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByJobSeeker(jobSeekerWithoutResume)
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByJobSeekerWhereJobSeekerDoesNotExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByJobSeeker(jobSeekerWithResume)
    Assert.assertEquals(result.size, 0)
  }

  @Test
  def testFilterByEmployerWhereEmployerExists() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByEmployer(employer1)
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByEmployerWhereEmployerDoesNotExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filterByEmployer(employer2)
    Assert.assertEquals(result.size, 0)
  }

  @Test
  def testFilterWhereDayAndJobDoExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filter(employer1, Some(today), Some(regularJob))
    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterWhereDayAndJobDoNotExist() = {
    applicationService.submitApplication(regularJob, jobSeekerWithoutResume, None)
    val result = applicationService.filter(employer2, Some(yesterday))
    Assert.assertEquals(result.size, 0)
  }
}
