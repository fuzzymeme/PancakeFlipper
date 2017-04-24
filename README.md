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


3, 4, 0, 2, 1. the code will recognise the following sequences already in place. 

(3 4)(0)(2 1)

Next there is an obvious flip just above (to the left of) the 2 to join the first and second sequence to give....

0, 4, 3, 2, 1

The two sequences can now be merged to give (3, 4, 2, 1) with the remaining sequence (0) still present. Next with this example there are no simple flips, so we have to flip at the base to give....

1, 2, 3, 4, 0 then above the 0 to give 4, 3, 2, 1, 0 then at the base to get the highest number to the base 0, 1, 2, 3, 4. Thus the solution is four flips long. Now to figure how the algorithm. (Side note here. There is a optimal solution but required N! memory. I wanted to be able to find a solution with around N flips for very long sequences - no N! memory of CPU solutions).

First consider the different type of sequences that could be merged. 

<some numbers> 0, 1 <some other numbers> 2, 3 <maybe more numbers> 

can be written as...

... 0, 1, ... 2, 3

This would be one case below are all the configurations. 

1) ... 0, 1, ... 2, 3
2) ... 0, 1, ... 3, 2
3) ... 1, 0, ... 2, 3
4) ... 1, 0, ... 3, 2
5) ... 2, 3, ... 0, 1
6) ... 3, 2, ... 0, 1
7) ... 2, 3, ... 1, 0
8) ... 3, 2, ... 1, 0

To merge the sequences you would flip (at the points marked with |) to give the set of flips

1) ... 0, 1,| ... 2, 3 -> ... 1, 0, ... | 2, 3 -> ... 0, 1, 2, 3   					2 flips
2) ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0 							2 flips 
3) ... 1, 0, ... |2, 3 -> ...0, 1, 2, 3												1 flip 
4) ... 1, 0,| ... 3, 2 -> ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0	3 flips
5) ... 2, 3,| ... 0, 1 -> 3, 2, ...0, 1| -> 1, 0, ... |2, 3, -> ...0, 1, 2, 3, 		3 flips
6) ... 3, 2, ... 0, 1| -> 1, 0, ... |2, 3, -> ...0, 1, 2, 3, 						2 flips
7) 2, 3, ... |1, 0 -> ... 3, 2, 1, 0												1 flip
8) ... 3, 2,| ... 1, 0 -> 2, 3, ... |1, 0 -> ... 3, 2, 1, 0							2 flips


(I'm bound to have typoed in there somewhere)


Looking at these rules I saw that after the first step rule 4 became rule 2.

4) ... 1, 0,| ... 3, 2 -> ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0	
2) 						  ... 0, 1, ... 3, 2 | -> 2, 3 ... |1, 0 -> ...3, 2, 1, 0 	

So the logically (and in the code) the algorithm would perform one step for rule 4 then use the functionality of rule 2. 
Looking at rule 2 I saw the after its first step it became rule 7. 

2) ... 0, 1, ... 3, 2 | -> 2, 3, ... |1, 0 -> ...3, 2, 1, 0 	
7) 						   2, 3, ... |1, 0 -> ... 3, 2, 1, 0

Plotting out all for the relationship between the rules gave...

4 -> 2 -> 7 
		  ^  
		  |  
		  8 


and 

5 -> 6 -> 3 
		  ^  
		  |  
		  1 


Here I looked for some commonality - where I could generalise two for more rules. For example rules 3 and 7 are very similar. 

3) (1, 0) ... |(2, 3) -> ... (0, 1, 2, 3)	1 flip 
7) (2, 3) ... |(1, 0) -> ... (3, 2, 1, 0)	1 flip

The the matching of the sequences being the 1 to the 2 in rule three, and 2 to the 1 in rule 7. These can be generalised to 

(x, ..) ... |(x+-1, ...) -> <megered sequence> 

Given that is this the combination of rules 3 and 7 I called it rule 37. 

Rules 1 and 8 can be combined to produce

(.., x)| ... (x+-1, ..) -> rule 37


Rules 2 and 6 give...
(.., x) ... (.., x+-1)| -> rule 37


etc to give...  

45 -> 26 -> 37 
		  	^  
		  	|  
		  	18 

So the bulk of the code would be these four rules. 

I also expected there to be others the might act as tweaks, but these should notionally be all that's required. For a long time I've considered the table on which the pancake stack sits to be of length one greater than the number of pancakes. This means that flipping the stack so that the widest pancake rests on the table is notionally merging that sequence with the sequence of the table. In practice I popped in a simple rule that that acted like rule 3 so the stack finishes the right way up. 

That's pretty much the solution. I think I'm going to stick with the rule names in the code as explaining each of the rules means as a function name is going to be a real pain, and given that test names are often much longer - well that's would be a mess. Hence the rule numbers are across the code. Hmm.. you may not understand the code without the readme, but having huge long function names would be just as bad.


-------

Does it solve the average N high stack in around N flips? Yes it does. 

For benchmarking I wrote code to generate all permutations of pancakes (actually adapted some permutation code I found on-line) and ran against all permutations for given lengths. This produces the following results. 


Length 			Permutations 		Total flips 			Av flips 		Time (Clock)
2					2					1					0.5					0.013
3					6					9					1.5					0.13
	




















 
















