#!/bin/bash

# This script packages the BPSE website including preparation of binary downloads 

# variables

rootdir="."
src_doc="$rootdir/documentation/target/site"
src_basic="$rootdir/basic/target/site"
src_medium="$rootdir/medium/target/site"
src_core="$rootdir/core/target/site"

deploydir="$rootdir/bpse"
dest_doc=$deploydir
dest_basic="$deploydir/basic"
dest_medium="$deploydir/medium"
dest_core="$deploydir/core"
dest_packages="$deploydir/src-package"
packages="designpatterns pom.xml documentation"
basic_srcpackages="basic/pom.xml basic/src"

suffix=$(date +%k%M%j)

# run maven

cd $rootdir
echo "would you like to execute mvn clean & site? [y/N]"
read response
if [ "$response" = y ]
then
	echo "Executing 'mvn clean'"
	sleep 1
	mvn clean
	echo "Executing 'mvn site for core, basic and documentation'"
	sleep 2
	cd basic-archetype
	mvn clean
	cd ../designpatterns
	mvn clean
	cd ../basic
	mvn site
	cd ../documentation
	mvn clean
	mvn site
	cd ../core
	mvn site

	cd ..
fi

#checks

if [ ! -e $src_basic ] || [ ! -e $src_doc ] || [ ! -e $src_core ]
then
	echo "ERROR: one of these dirctories was not found in rootdir: $rootdir"
	echo $src_doc
	echo $src_basic
	echo $src_core
	echo "exiting..."
	exit 1
fi

#Create a new Deploy dir and move the old one out of the way

if [ -e $deploydir ] 
then
	echo "moving $deploydir to $deploydir.$suffix"
	echo "suffix format: Hour/Minute/Day of the Year"
	echo "creating new deploydir: $deploydir"
	mv $deploydir $deploydir.$suffix
	mkdir $deploydir
else
	echo "creating new deploydir: $deploydir"
	mkdir $deploydir
fi
echo "press any key to continue, ctrl+c to exit"
read

# Copy together the sources

cp -rv $src_doc/* $dest_doc/
mkdir $dest_core
cp -rv $src_core/apidocs $dest_core/
cp -rv $src_core/xref $dest_core/
mkdir $dest_basic
cp -rv $src_basic/apidocs $dest_basic/
cp -rv $src_basic/xref $dest_basic/
cp -rv $src_basic/xref-test $dest_basic/
#mkdir $dest_medium
#cp -rv $src_medium/apidocs $dest_medium/
#cp -rv $src_medium/xref $dest_medium/
#cp -rv $src_medium/xref-test $dest_medium/

# create zip, tar and rar packages of the java source code

echo "Done with documentation, now archiving the source packages into $dest_packages"
sleep 2
mkdir $dest_packages
mkdir $dest_packages/bpse
mkdir $dest_packages/basic-archetype

for p in $packages
do
 cp -rv $p $dest_packages/bpse/
done

mkdir $dest_packages/bpse/basic

for bp in $basic_srcpackages
do
 cp -rv $bp $dest_packages/bpse/basic/
done

cp -rv basic-archetype/* $dest_packages/basic-archetype/

sleep 2
cd $dest_packages
zip -r BPSE.zip bpse
rm -rf bpse
zip -r basic-archetype.zip basic-archetype
rm -rf basic-archetype
#tar cfvz $dest_packages/BPSE.tar.gz $packages
# rar a -r $dest_packages/BPSE.rar $packages
cd ..
cd ..
# wrap it all up

echo "Done with packaging, now archiving the build into $deploydir.zip"
if [ -e $deploydir.zip ]
then
	echo "moving $deploydir.zip to $deploydir.zip.$suffix"
	echo "suffix format: Hour/Minute/Day of the Year"
fi
sleep 5
zip -r9 $deploydir.zip $deploydir
