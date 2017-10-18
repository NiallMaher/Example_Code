#!/bin/bash
# Run Ansible from source
# Setup the environment as required
# User specific environment and startup programs

#expected input
PLAYBOOK=$1
INVENTORY=$2
OPTION=$3


PRIVATEKEY=/opt/ansible/automation/common/sshkey/id_rsa_key

ANSIBLE_HOME=/opt/ansible/ansible-2.0.1.0

PATH=$PATH:${HOME}/bin:${ANSIBLE_HOME}/bin

#Set ANSIBLE Env to run from source
if [ -f ${ANSIBLE_HOME}/hacking/env-setup ]; then
       . ${ANSIBLE_HOME}/hacking/env-setup
fi

export ANSIBLE_HOST_KEY_CHECKING=False

