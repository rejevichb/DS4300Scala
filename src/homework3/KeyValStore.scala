package homework3

//PartB, Key,Value store

class KeyValStore {


  def get(key: String): String

  def set(key: String, value: String)

  def lpush(key: String, value: String)

  def rpush(key: String, value: String)

  def lpop(key: String): String

  def rpop(key: String): String

  def lrange(key: String): List[String]

  def llen(key: String)

  def flushall()

}




