file://<HOME>/Cesar/Proyectos/Intializing%20Algoritm/init/src/Repository/MyDataset.scala
### java.lang.IndexOutOfBoundsException: -1

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 388
uri: file://<HOME>/Cesar/Proyectos/Intializing%20Algoritm/init/src/Repository/MyDataset.scala
text:
```scala
package Repository
import Utils.* 


class MyDataset {
  protected var instanceSet: InstanceSet = _
  protected var attributes: List[MyAttribute] = _
  protected var instances: List[MyInstance] = _
  protected var nClasses: Int = _
  protected var nInputs: Int = _
  protected var nAttr: Int = _
  protected var classSupp: Array[Int] = _

  def MyDataset(dataset: MyDataset) = {
    this(@@)
  }

  

  

  private def readSet(file: String, isTrain: Boolean): Unit = {
    if (isTrain) Attributes.clearAll()

    try {
      instanceSet = new InstanceSet()
      instanceSet.readSet(file, isTrain)
    } catch {
      case e: Exception => System.exit(e.hashCode())
    }
  }

  private def readAttributes(): Unit = {
    nInputs = Attributes.getInputNumAttributes()
    nAttr = nInputs + Attributes.getOutputNumAttributes()

    attributes = (0 until nAttr).map(i => {
      val attr = Attributes.getInputAttribute(i)
      val newAttr = if (attr.getType() == Attribute.NOMINAL) {
        new MyAttribute(attr.getName(), new ArrayList(attr.getNominalValuesList()), i)
          .setRange(0, newAttr.numValues() - 1.0f)
      } else {
        new MyAttribute(attr.getName(), attr.getType(), i)
          .setRange(attr.getMinAttribute(), attr.getMaxAttribute())
      }
      newAttr
    })

    attributes(nInputs) = new MyAttribute(
      Attributes.getOutputAttribute(0).getName(),
      new ArrayList(Attributes.getOutputAttribute(0).getNominalValuesList()),
      nInputs
    ).setRange(0, newAttr.numValues() - 1.0f)

    nClasses = attributes(nInputs).numValues()
  }

  private def readInstances(): Unit = {
    val numInstances = instanceSet.getNumInstances()

    instances = (0 until numInstances).map(j => {
      val values = Array.fill[Double](getNumAttributes())(0.0)
      var classValue = 0
      
      (0 until nInputs).foreach(i => 
        values(i) = instanceSet.getInputNumericValue(j, i)
      )

      classValue = (instanceSet.getOutputNumericValue(j, 0)).toInt

      new MyInstance(values, classValue)
    })

    classSupp = Array.fill(nClasses)(0)
    instances.foreach(inst => 
      classSupp(inst.classValue()) += 1
    )
  }

  def copyInstance(dest: MyDataset): Unit = {
    dest.instances = dest.instances.map(new MyInstance(_))
  }

  def addInstance(inst: MyInstance): Unit = {
    instances = inst :: instances
    classSupp(inst.classValue()) += 1
  }

  def getAttribute(index: Int): MyAttribute = attributes(index)
  
  def getAttributes(): List[MyAttribute] = attributes
  
  def getClassIndex(): Int = nInputs
  
  def getClassesSupp(pos: Int): Int = classSupp(pos)
  
  def getClassesSupp(): Array[Int] = classSupp
  
  def getInstanceSet(): InstanceSet = instanceSet
  
  def getInstance(pos: Int): MyInstance = instances(pos)
  
  def getInstances(): List[MyInstance] = instances
  
  def isMissing(i: Int, v: Int): Boolean = getInstance(i).isMissing(v)
  
  def getNumClasses(): Int = nClasses
  
  def getNumInstances(): Int = instances.length
  
  def getNumAttributes(): Int = nAttr
  
  def getNumInputs(): Int = nInputs
  
  def getClassAttribute(): MyAttribute = attributes(nInputs)
  
  def trimToSize(): Unit = {
    instances = instances.take(instances.length) // Scala lists are immutable, so we create a new list
  }
  
  def sort(c: Comparator[MyInstance]): Unit = {
    instances.sortBy(inst => inst)
  }
  
  def sort(attr: Int, instanceIndexes: Array[Int]): Unit = {
    quickSort(attr, instanceIndexes, 0, instanceIndexes.length - 1)
  }

  private def quickSort(attr: Int, indexes: Array[Int], left: Int, right: Int): Unit = {
    val pivot = instances(indexes(left)).value(attr)
    var i = left
    var j = right

    while (i < j) {
      while (!DoubleCompare.greater(instances(indexes(i)).value(attr), pivot) && i < j)
        i += 1
      
      while (DoubleCompare.greater(instances(indexes(j)).value(attr), pivot))
        j -= 1

      if (i < j)
        Utils.swap(instanceIndexes, i, j)

      if (i == j) {
        i += 1
        j -= 1
      }
    }

    Utils.swap(instanceIndexes, left, j)

    if (left < j - 1)
      quickSort(attr, instanceIndexes, left, j - 1)

    if (j + 1 < right)
      quickSort(attr, instanceIndexes, j + 1, right)
  }

  def getClassSupp(instanceIndexes: Array[Int]): Array[Int] = {
    val classSupport = new Array(nClasses)(0)
    
    instanceIndexes.foreach(i => 
      classSupport(instances(i).classValue()) += 1
    )
    
    classSupport
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