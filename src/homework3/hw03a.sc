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

 */



// converts an unsigned integer to an n-bit binary string
def toBinary(x: Int, bits: Int): String = x.toBinaryString.reverse.padTo(bits, '0').reverse


val _8b1 = toBinary(1,8)
val _8b255 =toBinary(255, 8)
val _16b256 =toBinary(256, 16)
val _16b9999 = toBinary(9999, 16)
val _8b129 =toBinary(129, 8)
val _8b254 =toBinary(254, 8)

// weight = number of 1's in a binary number
// plot of the weight of binary numbers from 0 to 1024
def weight(b: String): Int = b.count(_ == '1')

weight(_8b1)
weight(_8b255)
weight(_16b256)
weight(_16b9999)
weight(_8b129)
weight(_8b254)

//TODO Jeff take a look at this
// returns the fraction of records that would have to be re-assigned to a new node
// if records were re-partitioned from startN to endN nodes using the mod function.
def moved(records: Int, startN: Int, endN: Int): Double = {
  0
}


moved(1000000, 100, 107)