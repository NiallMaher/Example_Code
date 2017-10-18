use strict;
use warnings;
use autodie;
use File::Copy;   #Gives you access to the "move" command

use constant {
    FROM_DIR => "<source>",
    TO_DIR   => "<destination>",
};

#Opens FROM_DIR, ao I can read from it
opendir my $dir, FROM_DIR;

# Loopa through the directory
while (my $file = readdir $dir) {
    next if ($file eq "." or $file eq "..");
    my $from = FROM_DIR . "/" . "$file";
    move $from, TO_DIR;
}