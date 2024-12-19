package Forest
import Utils.DoubleCompare


class Interval(val attributeIndex: Int) {
  private var subsets: Array[Array[Int]] = Array.empty
  private var lowerBound: Double = -1.0
  private var upperBound: Double = -1.0
  private var infoGain: Double = -1.0
  private val attributeIndex=attributeIndex

  def getInterval(value: Double): Int = {
    if (value >= lowerBound && value <= upperBound) 0 else 1
  }

  def getAttributeIndex: Int = attributeIndex

  def getSubsets: Array[Array[Int]] = subsets

  def getSubset(index: Int): Array[Int] = subsets(index)

  def getNumSubset: Int = subsets.length

  def setSubsets(subsets: Array[Array[Int]]): Unit = {
    this.subsets = subsets
  }

  def getLowerBound: Double = lowerBound

  def setLowerBound(lowerBound: Double): Unit = {
    this.lowerBound = lowerBound
  }

  def getUpperBound: Double = upperBound

  def setUpperBound(upperBound: Double): Unit = {
    this.upperBound = upperBound
  }

  def getInfoGain: Double = infoGain

  def setInfoGain(infoGain: Double): Unit = {
    this.infoGain = infoGain
  }
}
