#!/bin/bash

output=jar_output
outtmp=$output/tmp
binPath=bin_data
dist=$output/dist
compl=readyToCopy

echo -n Preparing...

od=`pwd`
cd "$od"

if [ ${#output} -lt 5 ]; then
	echo "weird string in output directory variable. halting for safety reasons."
	exit 1
fi

rm -fr ./$outtmp/*
rm -fr ./$output/*
rm -fr ./$dist/*

mkdir $output &>/dev/null
mkdir $outtmp &>/dev/null
mkdir $dist &>/dev/null

touch "$outtmp/.placeholder"

echo Done
echo -n Parsing version...

ver_line=`grep -Ei 'version +=' src/minecraft/monnef/crafting/common/Reference.scala`
test $? -ne 0 && { echo "Cannot determine version" ; exit 2 ; }

version=`sed -r 's/^.*"(.*)".*$/\1/' <<< "$ver_line"`
test $? -ne 0 && { echo "Cannot parse version" ; exit 2 ; }

test ${#version} -gt 8 && { echo "Parsed version is suspiciously long, stopping" ; exit 2 ; }

echo Done
echo "Version detected: [$version]"
#sleep 0.3

echo -n Copying mod files...
cp -r $binPath/* "$outtmp"
cp -r reobf/minecraft/monnef "$outtmp"
rm -fr "$outtmp/monnef/core"

outName="auto_assembly_table_$version"

cd "$outtmp"
zip -q -9r "../$outName.jar" ./*

cd "$od"

echo Done
#jar packing done

echo -n Creating final zips...
cd $dist
mkdir mods
cd "$od"
cp "$output/$outName.jar" "$dist/mods"
cd "$dist"
outNameZ="mod_aat_${version}_packed"
zip -q -9r "../$outNameZ.zip" ./*
cd "$od"

echo Done

cd $output
echo -n Creating and populating ready-to-copy directory...
if [ "${#compl}" -lt 5 ]; then
	echo "weird string in compl directory variable. halting for safety reasons."
	exit 1
fi
rm -fr "$compl"
mkdir "$compl"
mkdir "$compl/mods"
cp "${outName}.jar" "$compl/mods/"

cd "$od"
echo Done


