package Repository
import java.util.ArrayList


class MyAttribute {
  private val NOMINAL :int =0
  private val INTEGER :int =1
  private val REAL:int =2
  private var name :String
  private var tipo : int
  private var values: Array[String]
  private var lowerBound: Double
  private var upperBound: Double
  private var amplitude :Double
  private var index :int

  def MyAttribute(name : String, tipo: int , index : int)={
    
    this.name=name
    this.tipo=tipo
    this.setIndex(index)
  }

  def this(name: String , value: Array[String], index: int )=this{
    this(name,NOMINAL,index)
    this.values=value
  }

  def isNominal(tipo: int ):Boolean={
    tipo==NOMINAL
  }

  def isReal(tipo: int ):Boolean={
    tipo==REAL
  }

  def isInteger(tipo: int):Boolean={
    tipo==INTEGER
  }

  def indexOf(value:String ):int={
    values.indexOf(value)
  }

  def isNominal():Boolean={
    tipo==NOMINAL
  }
  
  def isReal():Boolean={
    tipo==REAL
  }
  def isInteger():Boolean={
    tipo==INTEGER
  }

  def name():String={name}
  def numValues():int={values.length}
  def value(index:int):String={
    values.get(index)
  }

  def setRange(lowerBound:Double,upperBound:Double):Unit={
    this.lowerBound=lowerBound
    this.upperBound=upperBound
    this.amplitude=upperBound-lowerBound
  }

  def getLowerBound():Double=lowerBound
  def getUpperBound():Double=upperBound
  def getAmplitude():Double=amplitude
  def getTipo():int=tipo
  def getIndex():int=index
  def setIndex(index:int):Unit=this.index=index


}
