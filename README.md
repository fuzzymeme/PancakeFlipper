# PancakeFlipper

Code related to a question I used to ask when giving interviews. 

In the past, as part of various roles I've interviewed candidate software engineers. At one company one of the preferred questions was the 'Pancake Flipper' question. 

Given a pile of (virtual) pancakes, each of different sizes, piled in a random order what algorithm would you use to order the pancakes from largest at the bottom to smallest at the top? The only move operation you have is to 'flip' reversing the order of all the pancakes above the flip point. 

If you replace the size of the pancake with a size ranking then the given pile is a list of integers that need to be ordered, so given...


4 3 1 5 2 0

you'd want to produce...

0 1 2 3 4 5

with the fewest number of moves. (You should like of the right as the base of the pile)

Now we weren't looking for a perfect solution, we just wanted listen to how the candidate approached the problem, and generally we'd look for a solution that involved around 2n moves. 

The 2n solution involved flipping the current highest out-of-position pancake to the top and then flipping from above the highest in-position pancake. In the case above we'd flip from below the five 

4 3 1 5 2 0

Flip below the five to give...

5 1 3 4 2 0

then flip below the zero to give...
0 2 4 3 1 5

Now the five is in the correct position and you can move on to correctly position the four. To do this you'd flip below the four to get it to the top then flip above the five to move the four down above the five. The four would then be in the correct position. You'd repeat this until the pile is in the correct order. Obviously with two moves per pancake the the number of moves required to order the pile 2 * the_number_of_pancakes, that is 2n. In big O this is O(n) aka linear with n. 

(BTW no code was expected of the candidate, we'd just look for the problem solving).

After some time I felt that the a better algorithm could be written that would improve on 2n. So I wrote a couple of test programs to test my ideas. This repo is one of those ideas. It's still O(n) flips, but closer to n moves than 2n moves, and that is important.

----------------------

I'm not sure when I first wrote this. It has a time stamp back in Oct 2014, but I may have been started long before then. I think it's about time I cleaned it up a little/lot and finished it off - not to production quality, but good enough to demonstrate the idea. 

----------------------

This version of the code is a sequence based solution. The problem is viewed as a list of numbers that need to be ordered but as a list of sequences that have to be joined. Each flip is made to reduce the number of sequences, by combining other sequences, until there remains only one sequence (which may have to be flipped so that the widest pancake is at the base).

Each flip (or set of flips) reduces the number of sequences. In a good case a single flip can join two shorter sequences, and the entire solution is made-up of this single flip actions. In such case there needs only be N flips to produce the desired result. The theory I wanted to test is whether this would work consistently and would, on average, produce something like N flips for an stack of size N. 

Early on I realised that there were some cases where there needs to be more than than one flip to reduce the number of sequences. This is where the sequences are pointing in different directions relative to one another. Indeed there are some configurations where there needs to be three flips to join two sequences. The interesting thing for me is to see how often those cases arise, if they push up the average significantly and if there can be avoided. 

----------------------

The sequence based solution works by reducing the number of sequences with each flip and the ensuring the final flip orientates the stack into the correct direction. For example when presented with the following stack...


3, 4, 0, 2, 1. The code will recognise the following sequences already in place. 

(3 4)(0)(2 1)

Here there is an obvious flip just above (to the left of) the 2 to join the first and second sequence to give....

0, 4, 3, 2, 1

Two sequences can now be merged to give (3, 4, 2, 1) with the remaining sequence (0) still present. Next with this example there are no simple flips next, so we have to flip at the base to give....

1, 2, 3, 4, 0 then above the 0 to give 4, 3, 2, 1, 0 then at the base to get the highest number to the base 0, 1, 2, 3, 4. Thus the solution is four flips long. 

Now to figure how the algorithm. (Side note here. There is a optimal solution but required N! memory. I wanted to be able to find a solution with around N flips for very long sequences - no N! memory of CPU solutions).

First consider the different type of sequences that could be merged. 

*some numbers* 0, 1 *some other numbers* 2, 3 *maybe more numbers*  

can be written as...

... 0, 1, ... 2, 3

This would be one configuration, below are all of them

1) ... 0, 1, ... 2, 3
2) ... 0, 1, ... 3, 2
3) ... 1, 0, ... 2, 3
4) ... 1, 0, ... 3, 2
5) ... 2, 3, ... 0, 1
6) ... 3, 2, ... 0, 1
7) ... 2, 3, ... 1, 0
8) ... 3, 2, ... 1, 0

To merge the sequences you would flip (at the points marked with |) to give the set of flips
```
1) ... 0, 1,| ... 2, 3   ->   ... 1, 0, ... | 2, 3   -> ... 0, 1, 2, 3                       = 2 flips
2) ... 0, 1, ... 3, 2 |  -> 2, 3 ... |1, 0.          -> ...3, 2, 1, 0                        = 2 flips 
3) ... 1, 0, ... |2, 3   -> ...0, 1, 2, 3                                                    = 1 flip 
4) ... 1, 0,| ... 3, 2   -> ... 0, 1, ... 3, 2 |     -> 2, 3 ... |1, 0    -> ...3, 2, 1, 0	 = 3 flips
5) ... 2, 3,| ... 0, 1   -> 3, 2, ...0, 1|           -> 1, 0, ... |2, 3   -> ...0, 1, 2, 3   = 3 flips
6) ... 3, 2, ... 0, 1|   -> 1, 0, ... |2, 3,         -> ...0, 1, 2, 3                        = 2 flips
7) ... 2, 3, ... |1, 0   -> ... 3, 2, 1, 0                                                   = 1 flip
8) ... 3, 2,| ... 1, 0   -> 2, 3, ... |1, 0          -> ... 3, 2, 1, 0                       = 2 flips
```

(I'm bound to have typoed in there somewhere)


Looking at these rules I saw that after the first step rule 4 became rule 2.
```
4) ... 1, 0,| ... 3, 2 -> ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0	
2)                        ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0 	
```

So the logically (and in the code) the algorithm would perform one step for rule 4 then use the functionality of rule 2. 
Looking at rule 2 I saw the after its first step it became rule 7. 

```
2) ... 0, 1, ... 3, 2 | -> 2, 3, ... |1, 0 -> ...3, 2, 1, 0 	
7)                         2, 3, ... |1, 0 -> ... 3, 2, 1, 0
```

Plotting out all for the relationship between the rules gave...
```
4 -> 2 -> 7 
          ^  
          |  
          8 
```

and 
```
5 -> 6 -> 3 
          ^  
          |  
          1 
```
So Rule 5 is one step followed by the steps of rule 6, which itself is one step followed by the steps of rule 3. 

Next I looked for some commonality - where I could generalise two for more rules. For example rules 3 and 7 are very similar. 
```
3) (1, 0) ... |(2, 3) -> ... (0, 1, 2, 3)	1 flip 
7) (2, 3) ... |(1, 0) -> ... (3, 2, 1, 0)	1 flip
```
It should also be noted that rules 3 and 7 only work if there is no sequence above (to the left of) the sequence on the left. 

The the matching of the sequences being the 1 to the 2 in rule three, and 2 to the 1 in rule 7. These can be generalised to 

(x, ..) ... |(x+-1, ...) -> *merged sequence*

Given that is this the combination of rules 3 and 7 I called it rule 37. 

Rules 1 and 8 can be combined to produce

(.., x)| ... (x+-1, ..) -> *then the steps of rule 37*


Rules 2 and 6 give...  
(.., x) ... (.., x+-1)| -> *then the steps of rule 37*


etc to give...  
```
45 -> 26 -> 37 
            ^  
            |  
            18 
```
So the bulk of the code would be these four rules. 

I also expected there to be others the might act as tweaks, but these should notionally be all that's required. For a long time I've considered the table on which the pancake stack sits to be of length one greater than the number of pancakes. This means that flipping the stack so that the widest pancake rests on the table is notionally merging that sequence with the sequence of the table. In practice I popped in a simple rule that that acted like rule 3 so the stack finishes the right way up. 

That's pretty much the solution. I think I'm going to stick with the rule names in the code as explaining each of the rules means as a function name is going to be a real pain, and given that test names are often much longer - well that's would be a mess. Hence the rule numbers are across the code. Hmm.. you may not understand the code without the readme, but having huge long function names would be just as bad.


-------

Does it solve the average N high stack in around N flips? Yes it does. 

#### Benchmarking
For benchmarking I wrote code to generate all permutations of pancakes (actually adapted some permutation code I found on-line) and ran against all permutations for given lengths. This produces the following results. 

```
Length        Permutations        Total flips       Av flips      Time (Clock)
2                  2                  1               0.50          0.013
3                  6                  6               1.50          0.013
4                  24                 61              2.52          0.018
5                  120                466             3.80          0.050
6                  720                3,629           5.04          0.172
7                  5040               31,678          6.29          0.605
8                  40320              304,169         7.54          5.434
9                  362,880            3,194,911       8.80          45.24

```
Although a quick look at that code would suggest it runs in O(n^3) that would be somewhat naive. As the length increases the number of permutations increases, but the number of stacks the code can process remains similar. With stacks of length 7, 8 and 9 being processed in at a rate of 8330, 7419 and 8021 permutations per second. Using only those data is very noisy, but that doesn't look O(n^3). Looking at the actual code it can be seen that outer most loop is indeed O(n). The next inner loop is usually terminated as soon as it finds a match, often within one or two checks. The next inner loop terminates as soon it finds a match (yes, strictly speaking still O(n)). The last loop could be converted to a constant time look up with a HashMap, but I'm not going to do that optimisation. So yes, given a difficult stack the it'll take time, but across all permutations it's actually pretty efficient. 

I've run it with stacks of great length, e.g. 2000, and 4000. That would be 2000! different permutations - way more than the number of atoms in the universe etc, etc. Indeed on-line calculators seem to drop out at 170! and going beyond is claimed to be infinity - even though 2000! is way, way more that 170!. Anyway the code takes about 5.5 seconds to process a stack of length 2000, taking longer to print the solution than to calculate the solution. So the goal of writing code that doesn't take n! CPU or memory to complete. 

It does seem that the average number of flips per permutation is moving towards N, and begins to exceed N for lengths greater than 15. Hmm, still in need of some optimisation. 

As part of the benchmarking the code stores the sequences that it take the most flips to resolve. Some of these so sub-optimal flips and it is clear more improvements can be made without, I believe, fundamentally altering the code. 

#### 24 April 2017
I'm going to leave the code here for a while as there's lot of other code I'd like to write. Some of that is related to this - most is not. Related to this is a alternative solution which will generate the optimum solutions but only of smaller stacks - it's an O(n!) memory algorithm. 

#### ToDos

- [] See that the flips to base solutions don't get undone by later flips
- [] Remove the MockSequencePancakeStack which adds nothing to the code
- [] Is Delta.INCREASING DECREASING still needed?
- [] createSeq a bit long
- [] ToDos
- [] Factorial Calculator based on BigInteger 























 
















