package ie.eoin.sample.calisthenics.model

class Job(poster: Person) {

  def isOwner(person: Person) = {
    poster == person
  }
}
