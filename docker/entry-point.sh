#!/bin/sh

set -e

exec java "$@" -jar /mantis-sync-REST/mantisbt-sync-REST.jar
