#!/usr/bin/perl
use diagnostics;
use strict;
use warnings;

use File::Copy;

my $poll_cycle = 10;
my $dest_dir = "<destination>";

while (1) {
    sleep $poll_cycle;

    my $dirname = '<source>';

    opendir my $dh, $dirname
        or die "Can't open directory '$dirname' for reading: $!";

    my @files = grep !/^[.][.]?$/, readdir $dh;
    closedir $dh;

    if (@files) {
        print "Dir is not empty\n";

        foreach my $target (@files) {
            # Move file
            move("$dirname/$target", "$dest_dir/$target");

		}
	}
}
