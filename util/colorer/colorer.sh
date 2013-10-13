#!/bin/bash

function crash {
	echo $1
	exit 1
}

colors=( "rgb(30,27,27)" "rgb(179,49,44)" "rgb(59,81,26)" "rgb(81,48,26)" "rgb(37,49,146)" "rgb(123,47,190)" "rgb(40,118,151)" "rgb(171,171,171)" "rgb(67,67,67)" "rgb(216,129,152)" "rgb(65,205,52)" "rgb(222,207,42)" "rgb(102,137,211)" "rgb(195,84,205)" "rgb(235,136,68)" "rgb(240,240,240)" )

if [ $# -ne 2 ]; then
	crash "wrong count of parameters"
fi

input="$1"

# convert room.jpg -fuzz 15% -fill #84BE6A -opaque #d89060 room1.jpg

i=0
for color in "${colors[@]}"; do
	output="$2_$i.png"
	cmd="convert $input -fill $color -opaque rgb(255,0,255) $output"
	echo "Running: $cmd"
	$cmd
	i=$((i+1))
done