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
def toBinary(x: Int, bits: Int): String = {
  "fix"
}


// weight = number of 1's in a binary number
// plot of the weight of binary numbers from 0 to 1024
def weight(b: String): Int = {
  0
}


// returns the fraction of records that would have to be re-assigned to a new node
// if records were re-partitioned from startN to endN nodes using the mod function.
def moved(records: Int, startN: Int, endN: Int): Double = {
  0
}


moved(1000000, 100, 107)