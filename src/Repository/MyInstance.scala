package Repository

case class MyInstance(values: Array[Double], classValue: Int) {
  def classValue(): Int = classValue
  
  def values(): Array[Double] = values
  
  def value(index: Int): Double = values(index)
  
  def isMissing(index: Int): Boolean = Double.isNaN(values(index))
  
  def numValues(): Int = values.length
  
  def setMissing(index: Int): Unit = setValue(index, Double.NaN)
  
  def setValue(index: Int, value: Double): Unit = values(index) = value
  
  override def toString(): String = {
    val builder = new StringBuilder("[")

    values.zipWithIndex.foreach { case (value, i) =>
      if (i > 0) builder.append(", ")
      builder.append(value)
    }

    builder.append("]")
    builder.toString()
  }
}
