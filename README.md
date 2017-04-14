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

After some time I felt that the a better algorithm could be written that would improve on 2n. So I wrote a couple of test programs to test my ideas. This repo is one of those ideas. It's still O(n), but closer to n moves than 2n moves, and that is important.

I'm not sure when I first wrote this. It has a time stamp back in Oct 2014, but it may have been start long before then. I think it's about time I cleaned it up a little/lot and finished it off - not to production quality, but good enough to demonstrate the idea. 















