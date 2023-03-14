#!/bin/bash

# Find all implemented Ants, Weapons, Armor, DecisionBlocks
# Optional printing commented out
echo "Detecting implemented Armors, Weapons, Ants, DecisionBlocks, and Levels..."
for i in Ant Weapon Armor AI
	do 
		#echo "Finding ${i}s..."
		find antscuttle/core/src/com/antscuttle/game/${i}/implementations "*.java" 2>/dev/null | grep -Eo "\w*.java" > antscuttle/assets/detector/$i.txt
		#num=$(wc -l antscuttle/assets/detector/${i}.txt | grep -Eo "[0-9]*")
		#echo $num ${i}s loaded 
		
	done
	
#echo "Finding Levels..."

find 'antscuttle/core/src/com/antscuttle/game/Level/levels' "*.java" 2>/dev/null | grep -Eo "\w*.java" > antscuttle/assets/detector/Level.txt

#num=$(wc -l antscuttle/assets/detector/Level.txt | grep -Eo "[0-9]*")
#echo $num Levels loaded 

