//Brendan Rejevich
//DS4300 Large Scale Information Storage and Retrieval

/*
Part A - Warmup

## OO Style ##
      val nulls = Array[Int](0, 0, 0, 0)
      for (line <- Source.fromFile(filename).getLines) {
        val toks = line.split(",", -1)
        for (i <- 0 until toks.length)
          if (toks(i) == "") nulls(i) = nulls(i) + 1 }
      }
      println(nulls.mkString(","))

## Functional Style ##
      val nulls = Source.fromFile(filename).getLines
       .map(_.split(",", -1)).map(a => a.map(z => if (z == "") 1 else 0))
       .reduce((x, y) => (x zip y).map { case (u, v) => u + v })
      println(nulls.mkString(","))



 In the object oriented style of programming, we loop through every line
 in the file and then for each line in that collection, we loop through
 a collection of each element that was separated by a comma in that line,
 and then if we find an empty string as a value in that list (i.e. if there
 exists a substring ",," in the file, then we add one to the nulls list at
 the index of the null value in the line. The resulting comma separated
 string lists a count of null values per "column" of the data.

 The second part does the same thing, except it takes advantage of higher-level
 functional abstractions such as map and reduce. Map takes a function and some
 data, and applies that function to the data, returning the transformed data.
 The reduce() method applies a function against an accumulator and each element
 in the array (from left to right) to reduce it to a single value.  Essentially,
 we map the if statement to the data and then reduce it to this intermediary binary
 list, and then using zip we flatten



 */



// converts an unsigned integer to an n-bit binary string
def toBinary(x: Int, bits: Int): String =
  if (x.toBinaryString.length() > bits) throw new IllegalArgumentException()
  else x.toBinaryString.reverse.padTo(bits, '0').reverse


val _8b1 = toBinary(1,8)
val _8b255 =toBinary(255, 8)
val _16b256 =toBinary(256, 16)
val _16b9999 = toBinary(9999, 16)
val _8b129 =toBinary(129, 8)
val _8b254 =toBinary(254, 8)


// weight = number of 1's in a binary number
// plot of the weight of binary numbers from 0 to 1024
def weight(b: String): Int = b.count(_ == '1')

weight("0000000000000")
weight(_8b1)
weight(_8b255)
weight(_16b256)
weight(_16b9999)
weight(_8b129)
weight(_8b254)
weight("1")

// returns the fraction of records that would have to be re-assigned to a new node
// if records were re-partitioned from startN to endN nodes using the mod function.
def moved(records: Int, startN: Int, endN: Int): Double = {

  val minComm = minMutiHelper(startN,endN)
  val sets = records / minComm
  if (startN >= endN) 1 - sets * endN / records.toFloat
  else 1 - sets * startN / records.toFloat
}

def maxDivHelper(m: Int, n: Int): Int = {
  if(m < n)  maxDivHelper(n,m)
  else if (m % n == 0) n
  else maxDivHelper(n, m % n)
}

def minMutiHelper(m: Int, n: Int) : Int =  m * n / maxDivHelper(m, n)


moved(1000000, 100, 107)
