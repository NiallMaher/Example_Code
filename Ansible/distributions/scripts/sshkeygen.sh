#!/bin/bash

who_am_i=`whoami`
     if [ "$who_am_i" = 'root' ]; then
        echo "root user"
        echo -en "\n" | ssh-keygen -t rsa -P ''
     else
        echo "Non root user"
     fi
