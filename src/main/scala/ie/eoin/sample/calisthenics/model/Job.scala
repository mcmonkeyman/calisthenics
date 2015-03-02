package ie.eoin.sample.calisthenics.model

class Job(val poster: Person) {

  def isOwner(person: Person) = {
    poster == person
  }

  def requiresResume = false
}
