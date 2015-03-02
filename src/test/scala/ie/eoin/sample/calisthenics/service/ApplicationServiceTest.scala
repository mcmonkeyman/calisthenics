package ie.eoin.sample.calisthenics.service

import org.scalatest.junit.AssertionsForJUnit
import ie.eoin.sample.calisthenics.model._
import org.junit.{Assert, Test}
import com.github.nscala_time.time.Imports._

class ApplicationServiceTest extends AssertionsForJUnit {

  @Test
  def testValidJreqApplication() = {
    val applicationService = new ApplicationService()

    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new JReqJob(employer1)

    val resume1 = new Resume(new URL("resume1"))
    val jobSeeker1 = new JobSeeker(new PersonName("jobseeker1"), List(resume1))

    val result = applicationService.submitApplication(testJob1, jobSeeker1, Some(resume1))

    Assert.assertEquals(result.size, 1)
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testJReqApplicationWithBadResume(): Unit = {
    val applicationService = new ApplicationService()


    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new JReqJob(employer1)

    val resume1 = new Resume(new URL("resume1"))
    val jobSeeker1 = new JobSeeker(new PersonName("jobseeker1"), List())

    applicationService.submitApplication(testJob1, jobSeeker1, Some(resume1))
  }

  @Test
  def testValidSimpleApplication() = {
    val applicationService = new ApplicationService()

    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new Job(employer1)

    val jobSeeker1 = new JobSeeker(new PersonName("jobseeker1"), List())

    val result = applicationService.submitApplication(testJob1, jobSeeker1, None)

    Assert.assertEquals(result.size, 1)
  }

  @Test
  def testFilterByDay() = {
    val applicationService = new ApplicationService()

    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new Job(employer1)

    val jobSeeker1 = new JobSeeker(new PersonName("jobseeker1"), List())

    applicationService.submitApplication(testJob1, jobSeeker1, None)
    val result1 = applicationService.filterByDay(MonthDay.now)
    val result2 = applicationService.filterByDay(MonthDay.now.minusDays(1))
    Assert.assertEquals(result1.size, 1)
    Assert.assertEquals(result2.size, 0)
  }

  @Test
  def testFilterByJob() = {
    val applicationService = new ApplicationService()

    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new Job(employer1)
    val testJob2 = new Job(employer1)

    val jobSeeker1 = new JobSeeker(new PersonName("jobseeker1"), List())

    applicationService.submitApplication(testJob1, jobSeeker1, None)
    val result1 = applicationService.filterByJob(testJob1)
    val result2 = applicationService.filterByJob(testJob2)
    Assert.assertEquals(result1.size, 1)
    Assert.assertEquals(result2.size, 0)
  }

}
