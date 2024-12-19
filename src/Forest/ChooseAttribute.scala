package Forest
import Utils.DoubleCompare
import Utils.Randomize
import Repository.MyDataset


class ChooseAttribute(private val dataset: MyDataset, 
                     instanceIndexes: Array[Int], 
                     attributesUsed: Array[Boolean]) {

  private var interval: Option[Interval] = None

  def run(): Unit = {
    var i = Randomize.randInt(0, dataset.getNumInputs())
    var j = i

    do {
      if (!attributesUsed(i)) {
        val chooseInterval = new ChooseInterval(dataset, instanceIndexes, i)
        chooseInterval.run()

        if (chooseInterval.getInterval().isDefined) {
          interval = Some(chooseInterval.getInterval())
        }
      }

      i = if (i == dataset.getNumInputs() - 1) 0 else i + 1
    } while (i != j && interval.isEmpty)
  }

  def getInterval(): Option[Interval] = interval
}