
Class TestSuite


# Demonstrates a failover with multihoming (using a heartbeat timer per
# dest). Two endpoints with 2 interfaces with direct connections between
# each pair. Eventually in the primary becomes active again and all
# traffic moves back to the primary.
Class Test/sctp-multihome2-2Failover -superclass TestSuite


proc usage {} {
    global argv0
    puts stderr "usage: ns $argv0 <test> "
    exit 1
}

TestSuite instproc init {} {
    Trace set show_sctphdr_ 1
    $self instvar ns numnodes_
    set ns [new Simulator]
    set allchan [open temp.rands w]
    $ns trace-all $allchan
}

TestSuite instproc finish {} {
    $self instvar ns
    global quiet PERL
 
    $ns flush-trace
    if {$quiet == 0} {
        puts "Graphing..."
        set XGRAPH "xgraph"
        set RAW2XG_SCTP "../../bin/raw2xg-sctp"
        set WRAP 100
	exec $PERL $RAW2XG_SCTP -A -f -q -s .01 -m $WRAP -n 0 temp.rands \
		> temp.rands.points
        exec $XGRAPH -bb -tk -nl -m -x time -y packets temp.rands.points &
    }
    exit 0
}



Test/sctp-multihome2-2Failover instproc init {} {
    $self instvar ns sctp0 testName 0 host0_if0 host0_if1 host1_if0 host1_if1 err0 err1 err2 err3 err4 cbr0
    $self instvar host0_if2 host1_if2 host0_if3 host1_if3 host0_if4 host1_if4
    
    
    global quiet
    set testName multihome2-2Failover
    $self next
    
    Agent/SCTP set pathMaxRetrans_ 1000
    Agent/SCTP set changePrimaryThresh_ 1000
    Agent/SCTP set rtxToAlt_ 0  
    #0 is same 1 is alt default is alt
    
    Agent/SCTP set maxRto_ 10.0

    
    set sctp_numUnrelStreams 1
    set sctp_reliability 1

    set host0_core [$ns node]
    set host0_if0 [$ns node]
    set host0_if1 [$ns node]
    set host0_if2 [$ns node]
    set host0_if3 [$ns node]
    set host0_if4 [$ns node]


    $ns multihome-add-interface $host0_core $host0_if0
    $ns multihome-add-interface $host0_core $host0_if1
    $ns multihome-add-interface $host0_core $host0_if2
    $ns multihome-add-interface $host0_core $host0_if3
    $ns multihome-add-interface $host0_core $host0_if4


  
    
    set host1_core [$ns node]
    set host1_if0 [$ns node]
    set host1_if1 [$ns node]
    set host1_if2 [$ns node]
    set host1_if3 [$ns node]
    set host1_if4 [$ns node]

    
 
  
    $ns multihome-add-interface $host1_core $host1_if0
    $ns multihome-add-interface $host1_core $host1_if1
    $ns multihome-add-interface $host1_core $host1_if2
    $ns multihome-add-interface $host1_core $host1_if3
    $ns multihome-add-interface $host1_core $host1_if4

 
    
    $ns duplex-link $host0_if0 $host1_if0 54Mb 200ms DropTail
    $ns duplex-link $host0_if1 $host1_if1 54Mb 200ms DropTail
    $ns duplex-link $host0_if2 $host1_if2 54Mb 200ms DropTail
    $ns duplex-link $host0_if3 $host1_if3 54Mb 200ms DropTail
    $ns duplex-link $host0_if4 $host1_if4 54Mb 200ms DropTail

	
    set err0 [new ErrorModel]
    $ns lossmodel $err0 $host0_if0 $host1_if0

    set err1 [new ErrorModel]
    $ns lossmodel $err1 $host0_if1 $host1_if1

    set err2 [new ErrorModel]
    $ns lossmodel $err2 $host0_if2 $host1_if2

    set err3 [new ErrorModel]
    $ns lossmodel $err3 $host0_if3 $host1_if3

    set err4 [new ErrorModel]
    $ns lossmodel $err4 $host0_if4 $host1_if4

    
    set sctp0 [new Agent/SCTP]
    $ns multihome-attach-agent $host0_core $sctp0
    $sctp0 set mtu_ 1500
    $sctp0 set dataChunkSize_ 1468 
    $sctp0 set numOutStreams_ 1

	

    if {$quiet == 0} {
	$sctp0 set debugMask_ -1 
	$sctp0 set debugFileIndex_ 0

	set trace_ch [open trace.sctp w]
	$sctp0 set trace_all_ 1
	$sctp0 trace cwnd_
	$sctp0 trace rto_
	$sctp0 trace errorCount_
	$sctp0 attach $trace_ch
    }
    
    set sctp1 [new Agent/SCTP]
    $ns multihome-attach-agent $host1_core $sctp1
    $sctp1 set mtu_ 1500
    $sctp1 set initialRwnd_ 1000000000
    $sctp1 set useDelayedSacks_ 1

    
    if {$quiet == 0} {
	$sctp1 set debugMask_ -1
	$sctp1 set debugFileIndex_ 1
    }
    
    $ns connect $sctp0 $sctp1
    $sctp0 set-primary-destination $host1_if1
    
    
    set cbr0 [new Application/Traffic/CBR]
    $cbr0 attach-agent $sctp0

}


Test/sctp-multihome2-2Failover instproc run {} {

$self instvar ns sctp0 host1_if1 host0_if0 host0_if1 sctp0 cbr0 host0_if0 host1_if0 err0 err1 err2 err3 err4
$self instvar host0_if2 host1_if2 host0_if3 host1_if3 host0_if4 host1_if4 

$ns at 1.00 "$cbr0 start"

#UMTS Link
#$ns at 0 "$ns delay $host0_if0 $host1_if0 20ms duplex"
#$ns at 0 "$err0 set rate_ 0.03"

$ns at 1 "$sctp0 set-primary-destination $host1_if1"
$ns at 5 "$sctp0 set-primary-destination $host1_if2"
18 b 1
34 b 4 
$ns at 60 "$sctp0 set-primary-destination $host1_if4"
#$ns at 38 "$sctp0 set-primary-destination $host1_if1"




$ns at 0 "$err0 set rate_ 0"
#$ns at 19 "$err0 set rate_ 0.5"
#$ns at 30 "$err0 set rate_ 1.0"
#$ns at 90 "$err0 set rate_ 1.0"


$ns at 0 "$err1 set rate_ 0"
$ns at 1 "$err1 set rate_ 0"
$ns at 8 "$err1 set rate_ 0.0"
#$ns at 22 "$err1 set rate_ 0.5"
#$ns at 33 "$err1 set rate_ 0.75"
#$ns at 51 "$err1 set rate_ 1.0"
#$ns at 90 "$err1 set rate_ 1.0"


$ns at 0 "$err2 set rate_ 0"
$ns at 4 "$err2 set rate_ 0"
$ns at 10 "$err2 set rate_ 0.25"
$ns at 16 "$err2 set rate_ 0.0"
$ns at 19 "$err2 set rate_ 0.25"
$ns at 33 "$err2 set rate_ 0.0"
$ns at 51 "$err2 set rate_ 0.25"
#$ns at 65 "$err2 set rate_ 0.0"
#$ns at 68 "$err2 set rate_ 0.25"
#$ns at 89 "$err2 set rate_ 1.0"


$ns at 0 "$err3 set rate_ 0"
#$ns at 14 "$err3 set rate_ 0.0"
#$ns at 17 "$err3 set rate_ 0.5"
#$ns at 28 "$err3 set rate_ 1.0"
#$ns at 47 "$err3 set rate_ 0.75"
#$ns at 56 "$err3 set rate_ 0.0"
#$ns at 75 "$err3 set rate_ 0.75"
#$ns at 92 "$err3 set rate_ 0.0"
#$ns at 95 "$err3 set rate_ 0.5"

$ns at 0 "$err4 set rate_ 0.0"
$ns at 20 "$err4 set rate_ 1.0"
$ns at 59 "$err4 set rate_ 0.5"
$ns at 67 "$err4 set rate_ 0.0"





$ns at 1 "$ns delay $host0_if0 $host1_if0 1.0ms duplex"
$ns at 4 "$ns delay $host0_if0 $host1_if0 2.0ms duplex"
$ns at 6 "$ns delay $host0_if0 $host1_if0 1.0ms duplex"
$ns at 10 "$ns delay $host0_if0 $host1_if0 1.5ms duplex"
$ns at 16 "$ns delay $host0_if0 $host1_if0 4.0ms duplex"
$ns at 19 "$ns delay $host0_if0 $host1_if0 5.0ms duplex"
$ns at 30 "$ns delay $host0_if0 $host1_if0 2000.0ms duplex"
$ns at 101 "$ns delay $host0_if0 $host1_if0 2000.0ms duplex"



$ns at 1 "$ns delay $host0_if1 $host1_if1 5.0ms duplex"
$ns at 10 "$ns delay $host0_if1 $host1_if1 6.0ms duplex"
$ns at 13 "$ns delay $host0_if1 $host1_if1 3.0ms duplex"
$ns at 16 "$ns delay $host0_if1 $host1_if1 2.0ms duplex"
$ns at 18 "$ns delay $host0_if1 $host1_if1 28.0ms duplex"
$ns at 21 "$ns delay $host0_if1 $host1_if1 4.0ms duplex"
$ns at 33 "$ns delay $host0_if1 $host1_if1 2000.0ms duplex"
$ns at 102 "$ns delay $host0_if1 $host1_if1 2000.0ms duplex"



$ns at 1 "$ns delay $host0_if2 $host1_if2 2.0ms duplex"
$ns at 3 "$ns delay $host0_if2 $host1_if2 7.0ms duplex"
$ns at 6 "$ns delay $host0_if2 $host1_if2 1.0ms duplex"
$ns at 16 "$ns delay $host0_if2 $host1_if2 3.0ms duplex"
$ns at 19 "$ns delay $host0_if2 $host1_if2 60.0ms duplex"
$ns at 26 "$ns delay $host0_if2 $host1_if2 13.0ms duplex"
$ns at 33 "$ns delay $host0_if2 $host1_if2 2.0ms duplex"
$ns at 39 "$ns delay $host0_if2 $host1_if2 3.0ms duplex"
$ns at 41 "$ns delay $host0_if2 $host1_if2 2.0ms duplex"
$ns at 44 "$ns delay $host0_if2 $host1_if2 1.0ms duplex"
$ns at 49 "$ns delay $host0_if2 $host1_if2 2.0ms duplex"
$ns at 58 "$ns delay $host0_if2 $host1_if2 120.0ms duplex"
$ns at 65 "$ns delay $host0_if2 $host1_if2 2.0ms duplex"
$ns at 68 "$ns delay $host0_if2 $host1_if2 29.0ms duplex"
$ns at 75 "$ns delay $host0_if2 $host1_if2 1.ms duplex"
$ns at 85 "$ns delay $host0_if2 $host1_if2 2000.0ms duplex"
$ns at 97 "$ns delay $host0_if2 $host1_if2 2000.0ms duplex"


$ns at 1 "$ns delay $host0_if3 $host1_if3 1.0ms duplex"
$ns at 17 "$ns delay $host0_if3 $host1_if3 3.0ms duplex"
$ns at 27 "$ns delay $host0_if3 $host1_if3 2000.0ms duplex"
$ns at 47 "$ns delay $host0_if3 $host1_if3 80.0ms duplex"
$ns at 58 "$ns delay $host0_if3 $host1_if3 6.0ms duplex"
$ns at 61 "$ns delay $host0_if3 $host1_if3 20.0ms duplex"
$ns at 64 "$ns delay $host0_if3 $host1_if3 40.0ms duplex"
$ns at 67 "$ns delay $host0_if3 $host1_if3 115.0ms duplex"
$ns at 70 "$ns delay $host0_if3 $host1_if3 125.0ms duplex"
$ns at 74 "$ns delay $host0_if3 $host1_if3 105.0ms duplex"
$ns at 77 "$ns delay $host0_if3 $host1_if3 2.0ms duplex"
$ns at 92 "$ns delay $host0_if3 $host1_if3 3.0ms duplex"
$ns at 95 "$ns delay $host0_if3 $host1_if3 2.0ms duplex"
$ns at 98 "$ns delay $host0_if3 $host1_if3 3.0ms duplex"




$ns at 1 "$ns delay $host0_if4 $host1_if4 2000.0ms duplex"
$ns at 60 "$ns delay $host0_if4 $host1_if4 17.0ms duplex"
$ns at 67 "$ns delay $host0_if4 $host1_if4 5.0ms duplex"
$ns at 70 "$ns delay $host0_if4 $host1_if4 1.0ms duplex"
$ns at 73 "$ns delay $host0_if4 $host1_if4 25.0ms duplex"
$ns at 76 "$ns delay $host0_if4 $host1_if4 1.0ms duplex"
$ns at 89 "$ns delay $host0_if4 $host1_if4 3.0ms duplex"
$ns at 92 "$ns delay $host0_if4 $host1_if4 2.0ms duplex"
$ns at 98 "$ns delay $host0_if4 $host1_if4 6.0ms duplex"
$ns at 101 "$ns delay $host0_if4 $host1_if4 6.0ms duplex"




$ns at 130.0 "$self finish"
$ns run	
}	

proc runtest {arg} {
    global quiet
    set quiet 0
    
    set b [llength $arg]
    if {$b == 1} {
	set test $arg
    } elseif {$b == 2} {
	set test [lindex $arg 0]
	set q [lindex $arg 1]
	if { $q == "QUIET" } {
	    set quiet 1
	} else { usage }
    } else { usage }
    
    switch $test {
	sctp-multihome2-2Failover {
	    set t [new Test/$test]
	}
	default {
	    puts stderr "Unknown test $test"
	    exit 1
	}
    }
    $t run
}

global argv arg0
runtest $argv
