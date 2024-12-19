file://<HOME>/Cesar/Proyectos/Intializing%20Algoritm/init/src/Repository/MyDataset.scala
### java.lang.IndexOutOfBoundsException: -1

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 364
uri: file://<HOME>/Cesar/Proyectos/Intializing%20Algoritm/init/src/Repository/MyDataset.scala
text:
```scala
package Repository
import Utils.* 


class MyDataset(
  protected val instanceSet: InstanceSet,
  protected val attributes: Array[MyAttribute],
  protected val instances: Array[MyInstance],
  protected val nClasses: Int,
  protected val nInputs: Int,
  protected val nAttr: Int,
  protected val classSupp: Array[Int]
) {

  def MyDataset(MyDataset dataset) = this(@@)
    {
        this(dataset,dataset.getNumInstances())
        dataset.copyInstance(this)
    }

def this(other: MyDataset, capacity: Int) = this(
    other.instanceSet,
    other.attributes,
    Vector.empty[MyInstance].padding(capacity),
    other.nClasses,
    other.nInputs,
    other.nAttr,
    other.classSupp
  ) 

  def this(datasetFile: String, isTrain: Boolean) = {
    instanceSet.readSet(datasetFile, isTrain)
    
    readAttributes()
    readInstances()
  }



  private def readAttributes(): Unit = {
    nInputs = Attributes.getInputNumAttributes()
    nAttr = nInputs + Attributes.getOutputNumAttributes()

    attributes = Vector.tabulate(nAttr)(i => 
      i < nInputs match {
        case true =>
          val attr = Attributes.getInputAttribute(i)
          MyAttribute(attr.getName(), 
            if (attr.getType() == Attribute.NOMINAL) 
              attr.getNominalValuesList().toList 
            else List.empty,
            i
          )
        case false =>
          val attr = Attributes.getOutputAttribute(0)
          MyAttribute(attr.getName(), 
            attr.getNominalValuesList().toList, 
            nInputs
          )
      }
    )

    nClasses = attributes(nInputs).numValues()
  }

  private def readInstances(): Unit = {
    instances = Vector.empty[MyInstance]
    classSupp = Array.fill(nClasses)(0)

    val numInstances = instanceSet.getNumInstances()

    for (j <- 0 until numInstances) {
      val values = Array.ofDim[Double](getNumAttributes())
      val classValue = (instanceSet.getOutputNumericValue(j, 0)).toInt
      
      instances :+= MyInstance(values, classValue)
      classSupp(classValue) += 1
    }
  }

  def addInstance(inst: MyInstance): Unit = {
    instances :+= inst
    classSupp(inst.classValue()) += 1
  }

  def getInstance(pos: Int): MyInstance = instances(pos)

  def getClassIndex(): Int = nInputs

  def getClassesSupp(pos: Int): Int = classSupp(pos)

  def getNumClasses(): Int = nClasses

  def getNumInstances(): Int = instances.length

  def getNumAttributes(): Int = nAttr

  def getNumInputs(): Int = nInputs

  def getClassAttribute(): MyAttribute = attributes(nInputs)

  def trimToSize(): Unit = instances.trimToSize()

  def sort(c: Comparator[MyInstance]): Unit = instances.sortBy(c)

  def quickSort(attr: Int, instanceIndexes: Array[Int], left: Int, right: Int): Unit = {
    val pivot = instances(instanceIndexes(left)).value(attr)
    var i = left
    var j = right

    while (i < j) {
      while (!DoubleCompare.greater(instances(instanceIndexes(i)).value(attr), pivot) && i < j) i += 1
      while (DoubleCompare.greater(instances(instanceIndexes(j)).value(attr), pivot)) j -= 1
      Utils.swap(instanceIndexes, i, j)
    }

    Utils.swap(instanceIndexes, left, j)

    if (left < j - 1) quickSort(attr, instanceIndexes, left, j - 1)
    if (j + 1 < right) quickSort(attr, instanceIndexes, j + 1, right)
  }

  def getClassSupp(instanceIndexes: Array[Int]): Array[Int] = {
    val result = Array.ofDim[Int](nClasses)
    for (i <- instanceIndexes) result(instances(i).classValue()) += 1
    result
  }
}
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:129)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:244)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:101)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:88)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:47)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:422)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: -1